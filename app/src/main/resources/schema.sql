DROP TABLE IF EXISTS url_checks;
DROP TABLE IF EXISTS urls;

CREATE TABLE urls (
                      id SERIAL PRIMARY KEY,
                      name VARCHAR(255) UNIQUE,
                      created_at TIMESTAMP
);

CREATE TABLE url_checks (
                            id SERIAL PRIMARY KEY,
                            url_id INT,
                            status_code INT,
                            h1 VARCHAR(100),
                            title VARCHAR(100),
                            description VARCHAR(255),
                            created_at TIMESTAMP,
                            FOREIGN KEY (url_id) REFERENCES urls(id)
);
