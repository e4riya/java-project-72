@import hexlet.code.dto.UrlPage
@import hexlet.code.utils.NamedRoutes
@param UrlPage page

@template.main(
    page = page,
    content = @`
        <h1>Сайт: ${page.getUrl().getName()}</h1>
        <table class="table table-striped table-bordered">
            <tr>
                <td>ID</td>
                <td>${page.getUrl().getId()}</td>
            </tr>
            <tr>
                <td>Имя</td>
                <td>${page.getUrl().getName()}</td>
            </tr>
            <tr>
                <td>Дата создания</td>
                <td>${page.getUrl().getCreatedAt().toString()}</td>
            </tr>
        </table>
        <h2>Проверки</h2>
        <form action="${NamedRoutes.getUrlChecks(page.getUrl().getId())}" method="POST">
            <button type="submit">Запустить проверку</button>
        </form>
        <table class="table table-striped table-bordered">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Код ответа</th>
                    <th>title</th>
                    <th>h1</th>
                    <th>description</th>
                    <th>Дата проверки</th>
                </tr>
            </thead>
            <tbody>
                @for(var check : page.getChecks())
                    <tr>
                        <td>${check.getId()}</td>
                        <td>${check.getStatusCode()}</td>
                        <td>${check.getTitle()}</td>
                        <td>${check.getH1()}</td>
                        <td>${check.getDescription()}</td>
                        <td>${check.getCreatedAt().toString()}</td>
                    </tr>
                @endfor
            </tbody>
        </table>
    `
)
