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

