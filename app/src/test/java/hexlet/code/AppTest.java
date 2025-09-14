package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;

import hexlet.code.model.Url;
import hexlet.code.utils.NamedRoutes;
import hexlet.code.utils.UrlCheckRepository;
import hexlet.code.utils.UrlRepository;
import io.javalin.Javalin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.stream.Stream;

import io.javalin.testtools.JavalinTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class AppTest {
    private Javalin app;

    @BeforeEach
    public void setup() throws IOException, SQLException {
        app = App.getApp();
        UrlRepository.removeAll();
    }

    private static Stream<String> routes() {
        return Stream.of(
            NamedRoutes.getRoot(),
            NamedRoutes.getUrls()
        );
    }

    @ParameterizedTest
    @MethodSource("routes")
    public void testGetStaticRoutes(String route) {
        JavalinTest.test(
            app, (server, client) -> {
                var response = client.get(route);
                assertEquals(200, response.code());
            }
        );
    }

    @Test
    public void testGetDynamicAndPostRoutes() {
        JavalinTest.test(
            app, (server, client) -> {
                var url =
                    new Url("https://www.youtube.com/?app=desktop&hl=ru", new Timestamp(System.currentTimeMillis()));
                UrlRepository.save(url);
                var response = client.get(NamedRoutes.getUrl(url.getId()));
                assertEquals(200, response.code());
                response = client.post(NamedRoutes.getUrlChecks(url.getId()));
                assertEquals(200, response.code());
                UrlCheckRepository.removeAll();
                UrlRepository.removeAll();
                response = client.get(NamedRoutes.getUrl(url.getId()));
                assertEquals(404, response.code());

                response = client.post(NamedRoutes.getUrlChecks(url.getId()));
                assertEquals(500, response.code());

                response = client.post(NamedRoutes.getUrls());
                assertEquals(200, response.code());
            }
        );
    }
}
