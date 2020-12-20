FROM maven:3.5.2-jdk-8-alpine AS BUILD
COPY pom.xml /tmp/
COPY src /tmp/src/
WORKDIR /tmp/
RUN mvn install -DskipTests

FROM openjdk:8-jdk-alpine
WORKDIR /app
COPY --from=BUILD /tmp/target/app.jar app.jar


EXPOSE 8000
ENV mongo_db_url=file:configs/credential.properties
ENV mongo_db_username=file:configs/configuration.properties
ENV mongo_db_password = "test"
ENV spring_profiles_active=beta

ENTRYPOINT [ "java","-jar","app.jar"]


