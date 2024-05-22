# Usa una imagen base con Java
# Usa una imagen base con Maven y Java
FROM maven:3.8.3-openjdk-8 AS build

# Establece el directorio de trabajo
WORKDIR /app

# Copia el archivo pom.xml primero para descargar las dependencias
COPY pom.xml .

# Descarga las dependencias
RUN mvn dependency:go-offline -B

# Copia el código fuente del proyecto
COPY src src

# Compila la aplicación
RUN mvn package -DskipTests

# Usa una imagen base con Java
FROM openjdk:8-jdk-alpine

# Establece el directorio de trabajo
WORKDIR /app

# Copia el archivo JAR compilado de la etapa anterior
COPY --from=build /app/target/users-api-0.0.1-SNAPSHOT.jar .

# Define las variables de entorno
ENV JWT_SECRET=PRFT
ENV SERVER_PORT=8083

# Expone el puerto en el que se ejecutará la aplicación
EXPOSE $SERVER_PORT

# Comando para ejecutar la aplicación cuando se inicie el contenedor
CMD ["java", "-jar", "users-api-0.0.1-SNAPSHOT.jar"]