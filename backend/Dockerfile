FROM maven:3.8.3-openjdk-17 AS build

COPY pom.xml mvnw ./
COPY .mvn .mvn

COPY src src

RUN chmod +x ./mvnw && ./mvnw clean package -DskipTests

FROM openjdk:17-jdk

WORKDIR /app

COPY --from=build target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
