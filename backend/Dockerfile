# Etapa de build
FROM maven:3-openjdk-17 AS build

WORKDIR /app

COPY pom.xml ./
COPY src ./src

RUN mvn dependency:go-offline
RUN mvn clean package

# Etapa de execução
FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY --from=build /app/target/backend-0.0.1-SNAPSHOT.jar /app/target/backend-0.0.1-SNAPSHOT.jar

CMD ["java", "-jar", "target/backend-0.0.1-SNAPSHOT.jar"]