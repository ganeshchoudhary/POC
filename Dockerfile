FROM maven:3.5.2-jdk-8-alpine AS BUILD
#COPY checkstyle.xml /tmp/
COPY pom.xml /tmp/
COPY src /tmp/src/
WORKDIR /tmp/
RUN mvn install -DskipTests
#apt install
#apt-get install nano
#apk add nano
#apk
FROM openjdk:8-jdk-alpine
WORKDIR /app
COPY --from=BUILD /tmp/target/app.jar app.jar
#ARG JAR_FILE=target/app.jar
#COPY ${JAR_FILE} app.jar
#COPY src/main/resources/config/sentry.properties config/sentry.properties
#COPY configs configs

EXPOSE 8000
#ENV credential_file=file:configs/credential.properties
#ENV configuration_file=file:configs/configuration.properties
ENV spring_profiles_active=beta

ENTRYPOINT [ "java","-jar","app.jar"]


