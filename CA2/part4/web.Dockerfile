
FROM tomcat:10-jdk17

RUN apt-get update \
    && apt-get install -y git \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /usr/src/app

COPY CA1/part3 .

WORKDIR /usr/src/app/react-and-spring-data-rest-basic

RUN chmod +x gradlew \
    && ./gradlew clean build \
    && cp ./build/libs/react-and-spring-data-rest-basic-0.0.1-SNAPSHOT-plain.war /usr/local/tomcat/webapps/basic-0.0.1-SNAPSHOT.war


EXPOSE 8080
