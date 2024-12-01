# Imagen base con JDK 17
FROM openjdk:17-jdk-slim

# Crear directorio de trabajo
WORKDIR /app

# Copiar el JAR generado al contenedor
COPY target/backend-0.0.1-SNAPSHOT.jar /app/backend.jar

# Exponer el puerto donde corre el backend
EXPOSE 8080

# Ejecutar el JAR
ENTRYPOINT ["java", "-jar", "/app/backend.jar"]