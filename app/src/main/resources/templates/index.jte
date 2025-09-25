@import hexlet.code.dto.UrlsPage
@import hexlet.code.utils.NamedRoutes
@param UrlsPage page

@template.main(
    page = page,
    content = @`
        <table class="table table-striped table-bordered table-hover">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Имя</th>
                    <th>Последняя проверка</th>
                    <th>Код ответа</th>
                </tr>
            </thead>
            <tbody>
                @for(var url : page.getUrls())
                <tr>
                    <td>${url.getId()}</td>
                    <td><a href="${NamedRoutes.getUrl(url.getId())}">${url.getName()}</a></td>
                    <td>${page.getUrlIdToCheck().get(url.getId()) != null ? page.getUrlIdToCheck().get(url.getId()).getCreatedAt().toString() : ""}</td>
                    <td>${page.getUrlIdToCheck().get(url.getId()) != null ? page.getUrlIdToCheck().get(url.getId()).getStatusCode().toString() : ""}</td>
                </tr>
                @endfor
            </tbody>
        </table>
    `
)
