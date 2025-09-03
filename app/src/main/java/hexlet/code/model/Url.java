package hexlet.code.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@RequiredArgsConstructor
public class Url {
    private final String name;
    private final Timestamp createdAt;

    @Setter
    private int id;
}
