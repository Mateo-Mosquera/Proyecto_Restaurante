-- database: ../DataBase/Restaurante.sqlite

/*
CopyRight
autor: Mateo_Mosquera
description: CRUD De datos para restaurante
*/

--DELETE FROM Cliente;
--DELETE FROM Profesor;
--DELETE FROM Curso;
--DELETE FROM Leccion;
--DELETE FROM Tarea;
--DELETE FROM Nota;
--DELETE FROM Inscripcion;


SELECT * FROM Estudiante;

INSERT INTO Estudiante (Nombre, Apellido, Telefono, Email)
VALUES
('Mateo','Mosquera','0979391707','mateomosquera13@gmail.com'),
('Luis','Fernandez','0923451707','luisfernandez@gmail.com'),
('Sandra','Martinez','0912345678','sandramartinez@gmail.com');

UPDATE  Estudiante
SET     Nombre = upper(Nombre);
-- WHERE   ID_Estudiante   =   3;

SELECT * FROM Profesor;
INSERT INTO Profesor (Nombre, Apellido, Telefono, Email, Especialidad)
VALUES
('Cris','Montiel','0999991909','crismontiel@gmail.com', 'Programacion I'),
('Jose','Velazques','0923991907','josevelazques@gmail.com', 'Calculo en una variable'),
('Stevan','Garcia','0991791901','stevangarcia@gmail.com', 'Algebra Lineal');

SELECT * FROM Curso;
INSERT INTO Curso (ID_Profesor, Nombre, Descripcion)
VALUES
(1, 'Programacion I', 'En este curso aprenderemos los fundamentos de la programacion en Java'),
(2, 'Calculo en una variable', 'En este curso aprenderemos los fundamentos del calculo en una variable'),
(3, 'Algebra Lineal', 'En este curso aprenderemos los fundamentos de la algebra lineal');

SELECT * FROM Leccion;
INSERT INTO Leccion (ID_Curso, ID_Profesor, Titulo, Contenido)
VALUES
(7, 1, 'Introduccion a la programacion', 'En esta leccion aprenderemos sobre variables'),
(8, 2, 'Variables', 'En esta leccion aprenderemos sobre limites'),
(9, 3, 'Condicionales', 'En esta leccion aprenderemos matrices');

SELECT * FROM Tarea;
INSERT INTO Tarea (ID_Curso, ID_Estudiante, ID_Leccion, Descripcion, Fecha_Entrega)
VALUES
(7, 2, 1, 'Realizar un programa que calcule el area de un triangulo', '23-07-2024'),
(8, 3, 2, 'Realizar el siguiente limite: lim tiende a 2 de x-5x+6', '26-07-2024'),
(9, 4, 3, 'Realizar la siguiente matriz a una matriz inversa', '30-07-2024');

SELECT * FROM Nota;
INSERT INTO Nota (ID_Tarea, ID_Estudiante, Calificacion, Comentario)
VALUES
(1, 2, 10, 'Excelente trabajo'),
(2, 3, 8, 'Regular trabajo'),
(3, 4, 9, 'Buena trabajo');

SELECT * FROM Inscripcion;
INSERT INTO Inscripcion (ID_Estudiante, ID_Curso, Fecha_Inscripcion)
VALUES
(2, 7, '01-07-2024'),
(3, 8, '01-07-2024'),
(4, 9, '01-07-2024');
