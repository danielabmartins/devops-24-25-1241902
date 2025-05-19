FROM gradle:7.0-jdk11 AS build

WORKDIR /app

COPY gradle_basic_demo ./

RUN gradle wrapper

RUN chmod +x gradlew

RUN ./gradlew build


FROM eclipse-temurin:11-jre

WORKDIR /app

COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 12345

ENTRYPOINT ["java", "-cp", "app.jar", "basic_demo.ChatServerApp", "12345"]
