package hexlet.code;

import hexlet.code.model.Url;
import hexlet.code.utils.UrlChecker;
import hexlet.code.repositories.UrlRepository;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class UrlCheckerTest {
    private static MockWebServer mockWebServer;

    @BeforeEach
    void setUp() throws SQLException {
        App.getApp();
        UrlRepository.removeAll();
    }

    @BeforeAll
    static void setupServer() throws Exception {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
    }

    @AfterAll
    static void shutdownServer() throws Exception {
        mockWebServer.shutdown();
        UrlRepository.removeAll();
    }

    @Test
    void checkShouldParseHtmlCorrectly() throws Exception {
        // Подготавливаем мок-ответ
        String html = """
            <html>
                <head>
                    <title>Test Page</title>
                    <meta name="description" content="Test description">
                </head>
                <body>
                    <h1>Test H1</h1>
                </body>
            </html>
            """;

        mockWebServer.enqueue(new MockResponse()
                                  .setResponseCode(200)
                                  .setBody(html)
        );

        // Создаём тестовый URL в репозитории
        var testUrl = new Url(mockWebServer.url("/").toString());
        testUrl.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        UrlRepository.save(testUrl);

        // Выполняем проверку
        var urlCheck = UrlChecker.check(testUrl.getId());

        assertEquals(200, urlCheck.getStatusCode());
        assertEquals("Test Page", urlCheck.getTitle());
        assertEquals("Test H1", urlCheck.getH1());
        assertEquals("Test description", urlCheck.getDescription());
    }

    @Test
    void checkShouldHandle404() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                                  .setResponseCode(404)
                                  .setBody("<html><body>Not Found</body></html>")
        );

        var testUrl = new Url(mockWebServer.url("/404").toString());
        testUrl.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        UrlRepository.save(testUrl);

        var urlCheck = UrlChecker.check(testUrl.getId());

        assertEquals(404, urlCheck.getStatusCode());
        assertNull(urlCheck.getH1());
        assertEquals("", urlCheck.getTitle());
        assertNull(urlCheck.getDescription());
    }
}
