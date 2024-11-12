# HITO 1 (BACKEND)

## Generación del Proyecto
- Usar **Spring Initializr** para generar el proyecto con las dependencias necesarias.
- Crear modelos en la carpeta `models` para que persistan en la base de datos.

## Modelos

1. **Clase Alumno** con los siguientes atributos:
   - `Id` de tipo `Long`
   - `Rut` de tipo `String`
   - `Nombre` de tipo `String`
   - `Dirección` de tipo `String`
   - `materiaList` de tipo `Set<Materia>`

2. **Clase Materia** con los siguientes atributos:
   - `Id` de tipo `Long`
   - `Nombre` de tipo `String`
   - `Alumno` de tipo `Alumno`

> **Nota:** Implementar un **Logger** dentro de la creación del proyecto. La ubicación y forma del Logger es de libre elección.

## Repositorios

- Crear interfaces que implementen `JpaRepository` en la carpeta `repository`.
  1. Interfaz `AlumnoRepository`.
  2. Interfaz `MateriaRepository`.

## Servicios

1. **Clase AlumnoService**
   - Método `save` para guardar un alumno.
   - Método `findAll` para obtener todos los registros de alumnos.

2. **Clase MateriaService**
   - Método `save` para guardar una materia.

## Controladores

1. **Clase AlumnoController**
   - Método `findAll` para obtener todos los alumnos.
   - Método `save` para guardar un alumno.

2. **Clase MateriaController**
   - Método `save` para crear una materia.


   
# JWT

## Configuración Inicial
- Agregar las dependencias de **Spring Security** y **jsonwebtoken.io** a Maven.
- Definir la **clave** y el **tiempo de expiración del token** en el archivo `application.yml` dentro de la carpeta `resources`.

## Modelos

1. **Clase User** con los siguientes atributos:
   - `Id` de tipo `Long`
   - `Name` de tipo `String`
   - `Username` de tipo `String`
   - `Email` de tipo `String`
   - `Roles` de tipo `List<Role>`

2. **Enumeración Role**
   ```java
   public enum Role implements GrantedAuthority {
       ROLE_ADMIN, ROLE_CLIENT;
       public String getAuthority() {
           return name();
       }
   }
   ```

## Repositorio y Excepciones

- Crear la interfaz `UserRepository` que implemente `JpaRepository`.
- Crear una excepción que extienda `RuntimeException` para manejar excepciones personalizadas.

## Clases de Seguridad

- Crear las clases necesarias para la verificación y generación de tokens en la carpeta `security`:
   1. **Clase JwtTokenProvider**.
   2. **Clase JwtTokenFilter**.
   3. **Clase JwtTokenFilterConfigurer**.

## Configuración de Seguridad

- Crear la clase `WebSecurityConfig`, que extiende de `WebSecurityConfigurerAdapter` para asegurar la aplicación.

## Servicio de Usuario

- Crear la clase `UserService` con los siguientes métodos:
   1. **signin**: Autenticar usuario.
   2. **signup**: Registrar usuario.
   3. **loadUserByUsername**: Verificar los atributos del usuario.

## Controlador de Usuario

- Crear la clase `UserController` para exponer los endpoints:
   1. Método **signup** para registro de usuarios.
   2. Método **signin** para login de usuarios, retornando un token.

---

# HITO 2 (FRONTEND)

## Generación del Proyecto

- Usar **Spring Initializr** para generar el proyecto con las dependencias necesarias para la aplicación web.

## Data Transfer Objects (DTOs)

1. **Clase AlumnoDTO** con los siguientes atributos:
   - `Id` de tipo `Long`
   - `Rut` de tipo `String`
   - `Nombre` de tipo `String`
   - `Dirección` de tipo `String`
   - `materiaList` de tipo `Set<Materia>`

2. **Clase MateriaDTO** con los siguientes atributos:
   - `Id` de tipo `Long`
   - `Nombre` de tipo `String`
   - `Alumno` de tipo `Alumno`

3. **Clase UserDTO** con los siguientes atributos:
   - `Username` de tipo `String`
   - `Password` de tipo `String`
   - `Roles` de tipo `List<Role>`

## Servicios

1. **Clase AlumnoService**
   - Método `findAll`, que consume el servicio REST del Backend.

2. **Clase UserService**
   - Método `signin`, que envía los datos del usuario para iniciar sesión en el frontend.

## Controlador de Login

- Crear la clase `LoginController` con los siguientes métodos:
   1. **login**: Muestra la vista de login.
   2. **home**: Muestra los datos obtenidos desde el servicio de Alumnos.
```

