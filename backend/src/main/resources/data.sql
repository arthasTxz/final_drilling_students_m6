-- Script SQL para inicializar datos en la tabla alumnos
INSERT INTO alumnos ( rut, nombre, direccion)
VALUES
    ( '12345678-9', 'Juan Pérez', 'Calle Falsa 123, Santiago'),
    ( '23456789-0', 'Ana Gómez', 'Av. Siempre Viva 456, Viña del Mar'),
    ( '34567890-1', 'Carlos Fuentes', 'Pje. Los Cedros 789, Concepción'),
    ( '45678901-2', 'María López', 'Camino Real 101, La Serena'),
    ( '56789012-3', 'Pedro Martínez', 'Calle Principal 111, Valparaíso');

-- Inserta datos en la tabla materias (suponiendo que Materia tiene campos id y nombre)
INSERT INTO materias ( nombre, alumno_id)
VALUES
    ('Matemáticas', 1),
    ( 'Historia', 1),
    ( 'Ciencias', 2),
    ( 'Física', 3),
    ( 'Química', 4),
    ( 'Inglés', 5)
