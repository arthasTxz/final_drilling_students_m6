

---

# Proyecto Spring Boot (Frontend y Backend) con Docker

Este proyecto consiste en dos aplicaciones Spring Boot (frontend y backend) y una base de datos PostgreSQL, todas contenidas en contenedores Docker mediante Docker Compose.

## Prerrequisitos

Asegúrate de tener instalados los siguientes programas:

- [Docker](https://www.docker.com/get-started)
- [Docker Compose](https://docs.docker.com/compose/install/)
- [Java 17+](https://adoptopenjdk.net/) (o la versión requerida por el proyecto)
- [Maven](https://maven.apache.org/)

## Configuración del Proyecto

1. **Clonar el repositorio:**
   ```bash
   git clone https://github.com/arthasTxz/final_drilling_students_m6.git
   cd proyecto
   ```

2. **Generar los archivos `.jar`** para frontend y backend:
   ```bash
   # Para el backend
   cd backend
   mvn clean package
   cd ..

   # Para el frontend
   cd frontend
   mvn clean package
   cd ..
   ```

   Los archivos `.jar` se generarán en las carpetas `target`:
    - **Backend**: `backend/target/backend-0.0.1-SNAPSHOT.jar`
    - **Frontend**: `frontend/target/frontend-0.0.1-SNAPSHOT.jar`

3. **Verificar los `Dockerfile`:**
   Asegúrate de que los `Dockerfile` de backend y frontend estén configurados correctamente para copiar los `.jar` generados.

   Ejemplo para el backend:
   ```dockerfile
   COPY target/backend-0.0.1-SNAPSHOT.jar /app/backend.jar
   ```

## Inicializar el Proyecto

Para construir y ejecutar los contenedores:

```bash
docker-compose up --build
```

Esto hará lo siguiente:
- Creará y ejecutará contenedores para `db`, `backend` y `frontend`.
- Establecerá conexiones entre los servicios a través de la red `app-network`.

### Acceso a las Aplicaciones

- **Frontend**: [http://localhost:8081](http://localhost:8081)
- **Backend**: [http://localhost:8080](http://localhost:8080)

### Base de Datos PostgreSQL

La base de datos PostgreSQL se ejecutará en el contenedor `db` con los siguientes parámetros:
- **Base de datos**: `mydatabase`
- **Usuario**: `myuser`
- **Contraseña**: `secret`
- **Puerto en host**: `5433` (mapeado a `5432` en el contenedor)

Para conectarte a la base de datos desde tu máquina local:
```bash
psql -h localhost -p 5433 -U myuser -d mydatabase
```

## Variables de Entorno

Las aplicaciones están configuradas para usar un perfil `prod`. Puedes personalizar variables de entorno en `docker-compose.yml`.

Ejemplo:
```yaml
environment:
  - SPRING_PROFILES_ACTIVE=prod
```

## Comandos Útiles

- **Detener los contenedores:**
  ```bash
  docker-compose down
  ```

- **Ver logs en tiempo real:**
  ```bash
  docker-compose logs -f
  ```

- **Reconstruir las imágenes:**
  ```bash
  docker-compose up --build
  ```

## Posibles Problemas y Soluciones

1. **Error de conexión: `Connection Refused`**
    - Asegúrate de que los servicios `backend` y `frontend` estén en la misma red `app-network`.
    - Verifica que los puertos no estén ocupados.

2. **Puertos en uso**
    - Si los puertos `8080`, `8081` o `5433` están ocupados, cambia los mapeos en `docker-compose.yml`:
      ```yaml
      ports:
        - "8082:8080"  # Cambiar el puerto del host
      ```

---

¿Necesitas algún ajuste adicional o más detalles en este `README.md`?