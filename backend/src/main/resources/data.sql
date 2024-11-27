-- Script SQL para inicializar datos en la tabla alumnos
INSERT INTO alumnos ( rut, nombre, direccion)
VALUES
    ( '12345678-9', 'Juan Pérez', 'Calle Falsa 123, Santiago'),
    ( '23456789-0', 'Ana Gómez', 'Av. Siempre Viva 456, Viña del Mar'),
    ( '34567890-1', 'Carlos Fuentes', 'Pje. Los Cedros 789, Concepción'),
    ( '45678901-2', 'María López', 'Camino Real 101, La Serena'),
    ( '56789012-3', 'Pedro Martínez', 'Calle Principal 111, Valparaíso');

-- Inserta datos en la tabla materias (suponiendo que Materia tiene campos id y nombre)
INSERT INTO materias ( nombre)
VALUES
    ('Matemáticas'),
    ( 'Historia'),
    ( 'Ciencias'),
    ( 'Física'),
    ( 'Química'),
    ( 'Inglés');

INSERT INTO materia_alumno (alumnos_id, materias_id)
VALUES
    (1, 1),
    (1, 2),
    (2, 3),
    (2, 5),
    (3, 1),
    (4, 1),
    (5, 6);
