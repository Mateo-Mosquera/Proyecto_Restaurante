-- database: ../DataBase/Restaurante.sqlite

/*
CopyRight
autor: Mateo_Mosquera
description: CRUD De datos para restaurante
*/

-- Limpiar las tablas antes de insertar datos
DELETE FROM Reservas_Mesas;
DELETE FROM Reservas;
DELETE FROM Mesas;
DELETE FROM Gerente;
DELETE FROM Restaurante;
DELETE FROM Cliente;

-- Insertar datos en la tabla Cliente
INSERT INTO Cliente (Nombre, Apellido, Telefono, Correo)
VALUES
('Mateo', 'Mosquera', '0979391707', 'mateomosquera13@gmail.com'),
('Luis', 'Fernandez', '0923451707', 'luisfernandez@gmail.com'),
('Sandra', 'Martinez', '0912345678', 'sandramartinez@gmail.com');

-- Actualizar nombres de clientes a mayúsculas
UPDATE Cliente
SET Nombre = UPPER(Nombre);

-- Consultar datos de la tabla Cliente
SELECT * FROM Cliente;

-- Insertar datos en la tabla Restaurante
INSERT INTO Restaurante (Nombre, Direccion)
VALUES
('Nombre del restaurante 1', 'Av. 12 de octubre'),
('Nombre del restaurante 2', 'Av. Naciones Unidas');

-- Consultar datos de la tabla Restaurante
SELECT * FROM Restaurante;

-- Insertar datos en la tabla Gerente
INSERT INTO Gerente (ID_Restaurante, Nombre, Correo)
VALUES
(1, 'Carlos Ramirez', 'carlosramirez@gmail.com');

-- Consultar datos de la tabla Gerente
SELECT * FROM Gerente;

-- Insertar datos en la tabla Mesas
INSERT INTO Mesas (ID_Restaurante, NumeroMesa, Capacidad)
VALUES
(1, 1, 4),
(1, 2, 4),
(1, 3, 2),
(2, 1, 6),
(2, 2, 4);

-- Consultar datos de la tabla Mesas
SELECT * FROM Mesas;

-- Insertar datos en la tabla Reservas
INSERT INTO Reservas (ID_Gerente, ID_Cliente, Fecha_reserva, Estado)
VALUES
(1, 1, '2024-07-23', 'pendiente'),
(1, 2, '2024-07-26', 'confirmada');

-- Consultar datos de la tabla Reservas
SELECT * FROM Reservas;

-- Insertar datos en la tabla Reservas_Mesas
INSERT INTO Reservas_Mesas (ID_Reservas, ID_Mesas)
VALUES
(1, 1),
(2, 4),
(2, 5);

-- Consultar datos de la tabla Reservas_Mesas
SELECT * FROM Reservas_Mesas;
