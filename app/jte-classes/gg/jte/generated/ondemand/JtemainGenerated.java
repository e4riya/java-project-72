package gg.jte.generated.ondemand;
import gg.jte.Content;
import hexlet.code.dto.BasePage;
public final class JtemainGenerated {
	public static final String JTE_NAME = "main.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,2,2,2,12,12,30,30,32,32,32,34,34,36,36,36,43,49,49,49,2,3,3,3,3};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, Content content, BasePage page) {
		jteOutput.writeContent("\n<!doctype html>\n<html lang=\"en\">\n<head>\n    <meta charset=\"UTF-8\">\n    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n    <title>Welcome</title>\n\n    ");
		jteOutput.writeContent("\n    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css\"\n          rel=\"stylesheet\"\n          integrity=\"sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN\"\n          crossorigin=\"anonymous\">\n</head>\n<body>\n<main class=\"container my-4\">\n    <nav class=\"navbar navbar-expand-lg navbar-light bg-light mb-4\">\n        <div class=\"container-fluid\">\n            <a class=\"navbar-brand\" href=\"/\">Анализатор страниц</a>\n            <ul class=\"navbar-nav\">\n                <li class=\"nav-item\"><a class=\"nav-link\" href=\"/\">Главная</a></li>\n                <li class=\"nav-item\"><a class=\"nav-link\" href=\"/urls\">Сайты</a></li>\n            </ul>\n        </div>\n    </nav>\n\n    ");
		if (page.getFlash() != null) {
			jteOutput.writeContent("\n        <div class=\"alert alert-info\" role=\"alert\">\n            ");
			jteOutput.setContext("div", null);
			jteOutput.writeUserContent(page.getFlash());
			jteOutput.writeContent("\n        </div>\n    ");
		}
		jteOutput.writeContent("\n\n    ");
		jteOutput.setContext("main", null);
		jteOutput.writeUserContent(content);
		jteOutput.writeContent("\n\n    <footer class=\"mt-5 border-top pt-3\">\n        <p class=\"text-muted\">Created by <a href=\"https://github.com/e4riya\">e4riya</a></p>\n    </footer>\n</main>\n\n");
		jteOutput.writeContent("\n<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js\"\n        integrity=\"sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL\"\n        crossorigin=\"anonymous\"></script>\n</body>\n</html>\n");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		Content content = (Content)params.get("content");
		BasePage page = (BasePage)params.getOrDefault("page", null);
		render(jteOutput, jteHtmlInterceptor, content, page);
	}
}
