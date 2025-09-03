FROM gradle:8.14-jdk23

WORKDIR /app

COPY /app .

RUN ["./gradlew", "clean", "build"]

CMD ["./gradlew", "run"]
