package hexlet.code;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import gg.jte.ContentType;
import gg.jte.TemplateEngine;
import gg.jte.resolve.ResourceCodeResolver;
import hexlet.code.controllers.UrlController;
import hexlet.code.utils.BaseRepository;
import hexlet.code.utils.NamedRoutes;
import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinJte;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) throws SQLException {
        var app = getApp();
        app.start(getPort());
    }

    private static int getPort() {
        var port = System.getenv().getOrDefault("PORT", "8080");
        return Integer.parseInt(port);
    }

    private static String getDB() {
        var db = System.getenv().getOrDefault("JDBC_DATABASE_URL", "jdbc:h2:mem:project;DB_CLOSE_DELAY=-1");
        return db;
    }

    private static TemplateEngine createTemplateEngine() {
        ClassLoader classLoader = App.class.getClassLoader();
        ResourceCodeResolver codeResolver = new ResourceCodeResolver("templates", classLoader);
        TemplateEngine templateEngine = TemplateEngine.create(codeResolver, ContentType.Html);
        return templateEngine;
    }

    public static Javalin getApp() throws SQLException {
        var hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(getDB());

        var dataSource = new HikariDataSource(hikariConfig);
        var url = App.class.getClassLoader().getResourceAsStream("schema.sql");
        var sql = new BufferedReader(new InputStreamReader(url)).lines().collect(Collectors.joining("\n"));
        try (var connection = dataSource.getConnection(); var statement = connection.createStatement()) {
            statement.execute(sql);

        }
        BaseRepository.dataSource = dataSource;

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte(createTemplateEngine()));
        });

        app.get(NamedRoutes.getRoot(), UrlController::build);
        app.post(NamedRoutes.getUrls(), UrlController::create);
        app.get(NamedRoutes.getUrls(), UrlController::index);
        app.get(NamedRoutes.getUrl("{id}"), UrlController::show);
        app.post(NamedRoutes.getUrlChecks("{id}"), UrlController::createCheck);
        return app;
    }
}
