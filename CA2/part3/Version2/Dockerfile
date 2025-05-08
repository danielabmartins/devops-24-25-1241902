FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY gradle_basic_demo/build/libs/basic_demo-0.1.0.jar chat-server.jar
EXPOSE 12345
ENTRYPOINT ["java", "-cp", "chat-server.jar", "basic_demo.ChatServerApp", "12345"]
