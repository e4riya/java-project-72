package gg.jte.generated.ondemand;
import hexlet.code.dto.UrlsPage;
import hexlet.code.utils.NamedRoutes;
public final class JteindexGenerated {
	public static final String JTE_NAME = "index.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,2,2,2,4,4,7,7,18,18,20,20,20,21,21,21,21,21,21,21,21,21,21,21,21,22,22,22,23,23,23,25,25,28,28,28,29,29,29,2,2,2,2};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, UrlsPage page) {
		jteOutput.writeContent("\n");
		gg.jte.generated.ondemand.JtemainGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\n        <table class=\"table table-striped table-bordered table-hover\">\n            <thead>\n                <tr>\n                    <th>ID</th>\n                    <th>Имя</th>\n                    <th>Последняя проверка</th>\n                    <th>Код ответа</th>\n                </tr>\n            </thead>\n            <tbody>\n                ");
				for (var url : page.getUrls()) {
					jteOutput.writeContent("\n                <tr>\n                    <td>");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(url.getId());
					jteOutput.writeContent("</td>\n                    <td><a");
					var __jte_html_attribute_0 = NamedRoutes.getUrl(url.getId());
					if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_0)) {
						jteOutput.writeContent(" href=\"");
						jteOutput.setContext("a", "href");
						jteOutput.writeUserContent(__jte_html_attribute_0);
						jteOutput.setContext("a", null);
						jteOutput.writeContent("\"");
					}
					jteOutput.writeContent(">");
					jteOutput.setContext("a", null);
					jteOutput.writeUserContent(url.getName());
					jteOutput.writeContent("</a></td>\n                    <td>");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(page.getChecks().get(url.getId()) != null ? page.getChecks().get(url.getId()).getCreatedAt().toString() : "");
					jteOutput.writeContent("</td>\n                    <td>");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(page.getChecks().get(url.getId()) != null ? page.getChecks().get(url.getId()).getStatusCode().toString() : "");
					jteOutput.writeContent("</td>\n                </tr>\n                ");
				}
				jteOutput.writeContent("\n            </tbody>\n        </table>\n    ");
			}
		}, page);
		jteOutput.writeContent("\n");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		UrlsPage page = (UrlsPage)params.get("page");
		render(jteOutput, jteHtmlInterceptor, page);
	}
}
