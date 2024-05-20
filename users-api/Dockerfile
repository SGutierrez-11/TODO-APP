FROM openjdk:8-jdk-alpine AS build
COPY . /app
WORKDIR /app
RUN ./mvnw clean package -DskipTests

FROM openjdk:8-jre-alpine
WORKDIR /app

ENV JWT_SECRET=PRFT
ENV SERVER_PORT=8083

EXPOSE 8083
COPY --from=build /app/target/users-api-0.0.1-SNAPSHOT.jar ./users-api.jar
CMD ["java", "-jar", "users-api.jar"]
