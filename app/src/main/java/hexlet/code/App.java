package hexlet.code;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import hexlet.code.utils.BaseRepository;
import io.javalin.Javalin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) throws SQLException {
        var app = getApp();
        app.start(getPort());

        app.get("/", ctx -> ctx.result("Hello World"));
    }
    public static int getPort() {
        var port = System.getenv().getOrDefault("PORT", "8080");
        return Integer.parseInt(port);
    }
    public static String getDB() {
        var db = System.getenv().getOrDefault("JDBC_DATABASE_URL", "jdbc:h2:mem:project;DB_CLOSE_DELAY=-1");
        return db;
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

        var app = Javalin.create(config -> config.bundledPlugins.enableDevLogging());
        return app;
    }
}
