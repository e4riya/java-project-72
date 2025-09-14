@import hexlet.code.dto.BasePage
@param BasePage page

@template.main(
    page = page,
    content = @`
        <h2>Анализатор страниц</h2>
        <p>Бесплатно проверяйте сайты на SEO пригодность</p>
        <form action="/urls" method="post">
            <div>
                <label>
                    <input type="text" required name="url" />
                </label>
            </div>
            <input type="submit" value="Проверить" />
        </form>
    `
)
