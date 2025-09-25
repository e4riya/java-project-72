package hexlet.code.controllers;

import hexlet.code.dto.BasePage;
import hexlet.code.dto.UrlPage;
import hexlet.code.dto.UrlsPage;
import hexlet.code.model.Url;
import hexlet.code.model.UrlCheck;
import hexlet.code.utils.NamedRoutes;
import hexlet.code.repositories.UrlCheckRepository;
import hexlet.code.utils.UrlChecker;
import hexlet.code.repositories.UrlRepository;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

import java.net.URI;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.javalin.rendering.template.TemplateUtil.model;

public class UrlController {

    public static void build(Context ctx) {
        String flash = ctx.consumeSessionAttribute("flash");
        BasePage page = new BasePage();
        page.setFlash(flash);
        ctx.render("build.jte", model("page", page));
    }

    public static void create(Context ctx) {
        try {
            var result = new URI(ctx.formParam("url")).toURL();
            var entity = new Url(result.toString());
            try {
                UrlRepository.save(entity);
                ctx.sessionAttribute("flash", "Страница успешно добавлена");
            } catch (SQLException e) {
                ctx.sessionAttribute("flash", "Страница уже существует");
            } finally {
                ctx.redirect("/urls");
            }
        } catch (Exception e) {
            ctx.sessionAttribute("flash", "Некорректный URL");
            ctx.redirect("/");
        }
    }

    public static void index(Context ctx) throws SQLException {
        List<Url> entities = UrlRepository.getEntites();
        Map<Long, UrlCheck> urlIdToCheck = UrlCheckRepository.findAll();
        Map<Long, UrlCheck> lastChecks = new HashMap<>();
        for (var entity : entities) {
            var lastCheck = urlIdToCheck.get(entity.getId());
            if (lastCheck != null) {
                lastChecks.put(entity.getId(), lastCheck);
            }
        }
        var urlsPage = new UrlsPage(entities, lastChecks);
        String flash = ctx.consumeSessionAttribute("flash");
        urlsPage.setFlash(flash);
        ctx.render("index.jte", model("page", urlsPage));
    }

    public static void show(Context ctx) throws SQLException {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var result = UrlRepository.find(id).orElseThrow(
            () -> new NotFoundResponse("Url with id = " + id + " not found"));
        var checks = UrlCheckRepository.findByUrlId(id);
        var urlPage = new UrlPage(result, checks);
        String flash = ctx.consumeSessionAttribute("flash");
        urlPage.setFlash(flash);
        ctx.render("show.jte", model("page", urlPage));
    }

    public static void createCheck(Context ctx) throws SQLException {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var urlCheck = UrlChecker.check(id);
        if (urlCheck.getStatusCode() == 200) {
            UrlCheckRepository.save(urlCheck);
            ctx.sessionAttribute("flash", "Страница успешно проверена");
        } else {
            ctx.sessionAttribute("flash", "Некорректный адрес");
        }
        ctx.redirect(NamedRoutes.getUrl(id));
    }
}
