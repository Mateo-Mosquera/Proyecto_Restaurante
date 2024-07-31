-- database: ../DataBase/Restaurante.sqlite

/*
CopyRight
autor: Mateo_Mosquera
description: CRUD De datos para restaurante
*/

--DELETE FROM Cliente;
--DELETE FROM Restaurante;
--DELETE FROM Gerente;
--DELETE FROM Mesas;
--DELETE FROM Reservas;
--DELETE FROM Reservas_Mesas;


SELECT * FROM Cliente;

INSERT INTO Cliente (Nombre, Apellido, Telefono, Email)
VALUES
('Mateo','Mosquera','0979391707','mateomosquera13@gmail.com'),
('Luis','Fernandez','0923451707','luisfernandez@gmail.com'),
('Sandra','Martinez','0912345678','sandramartinez@gmail.com');

UPDATE  Cliente
SET     Nombre = upper(Nombre);
-- WHERE   ID_Cliente   =   3;

SELECT * FROM Restaurante;
INSERT INTO 
Restaurante 
(Nombre,                   Direccion)VALUES
('Nombre del restaurante', 'Av. 12 de octubre'),
('Nombre del restaurante', 'Av. Naciones Unidas'),

SELECT * FROM Gerente;
INSERT INTO Gerente 
(ID_Restaurante, Nombre, correo)VALUES
(1, 'Carlos Ramirez', 'carlosramirez@gmail.com');

SELECT * FROM Mesas;
INSERT INTO Mesas 
(ID_Restaurante,NumeroMesa, Capacidad)VALUES
(1,                 1,          4       ),
(1,                 2,          4       ),
(1,                 3,          2       ),
(2,                 1,          6       ),
(2,                 2,          4       );

SELECT * FROM Reservas;
INSERT INTO Reservas 
(ID_Gerente, ID_Cliente, Fecha_reserva,  Estado)VALUES
(1,             1,       '23-07-2024',   "pendiente"),
(2,             2,       '26-07-2024',   "confirmada");

INSERT INTO Reservas_Mesas 
(ID_Reservas, ID_Mesas) VALUES
(1,              1),
(2,              4),
(2,              5);


