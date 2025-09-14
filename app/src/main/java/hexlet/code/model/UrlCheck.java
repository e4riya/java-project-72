package hexlet.code.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@RequiredArgsConstructor
public class UrlCheck {
    private final Integer statusCode;
    private final String title;
    private final String h1;
    private final String description;
    private final Long urlId;
    private final Timestamp createdAt;

    @Setter
    private Long id;
}
