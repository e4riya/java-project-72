package hexlet.code.utils;

import hexlet.code.model.UrlCheck;
import hexlet.code.repositories.UrlRepository;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.sql.SQLException;

public class UrlChecker {

    public static UrlCheck check(Long id) throws SQLException {
        var url = UrlRepository.find(id).get();
        HttpResponse<String> response = Unirest.get(url.getName()).asString();
        int statusCode = response.getStatus();
        String html = response.getBody();
        Document doc = Jsoup.parse(html);
        String title = doc.title();
        Element h1El = doc.selectFirst("h1");
        String h1 = h1El != null ? h1El.text() : null;
        Element descEl = doc.selectFirst("meta[name=description]");
        String description = descEl != null ? descEl.attr("content") : null;

        return new UrlCheck(statusCode, title, h1, description, id);
    }
}
