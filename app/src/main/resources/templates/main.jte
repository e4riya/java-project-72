@import gg.jte.Content
@import hexlet.code.dto.BasePage
@param Content content
@param BasePage page = null

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Welcome</title>

    <!-- Bootstrap 5 CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
          crossorigin="anonymous">
</head>
<body>
<main class="container my-4">
    <nav class="navbar navbar-expand-lg navbar-light bg-light mb-4">
        <div class="container-fluid">
            <a class="navbar-brand" href="/">Анализатор страниц</a>
            <ul class="navbar-nav">
                <li class="nav-item"><a class="nav-link" href="/">Главная</a></li>
                <li class="nav-item"><a class="nav-link" href="/urls">Сайты</a></li>
            </ul>
        </div>
    </nav>

    @if(page.getFlash() != null)
        <div class="alert alert-info" role="alert">
            ${page.getFlash()}
        </div>
    @endif

    ${content}

    <footer class="mt-5 border-top pt-3">
        <p class="text-muted">Created by <a href="https://github.com/e4riya">e4riya</a></p>
    </footer>
</main>

<!-- Bootstrap JS Bundle (с Popper) -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
</body>
</html>
