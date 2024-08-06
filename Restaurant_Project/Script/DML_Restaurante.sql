--database: ../database/Restaurante.sqlite
/*
CopyRight
@author: Nic1205
@version: 1.0
@date: 01/08/2024
@description: Script para modificar datos
*/
DELETE FROM Clientes;
DELETE FROM Mesas;
DELETE FROM Reservas;

INSERT INTO Clientes
 (Cedula        , Nombre        , Apellido  , Telefono      , Correo) VALUES
 ('0650090160'  , 'Alejandro'   , 'Novillo' , '0992660401'  , 'alejandro.novillo.u@gmail.com');

INSERT INTO Mesas
 (Capacidad) VALUES
 (1)
,(1)
,(2)
,(2)
,(2)
,(2)
,(4)
,(4)
,(4)
,(4);

INSERT INTO Reservas
(Cedula         , Id_Mesa   , Fecha         , Hora) VALUES
('0650090160'   , 3         , '2024-08-10'  , '10:00');

INSERT INTO Personal (Cedula, Nombre, Apellido, Telefono, Correo)
VALUES 
('1234567890', 'Juan', 'Pérez', '555-1234', 'juan.perez@restaurante.com'),
('0987654321', 'María', 'González', '555-5678', 'maria.gonzalez@restaurante.com');


UPDATE Reservas
SET Estado = 'C'
WHERE Cedula = '0650090160';

UPDATE Mesas
SET Estado = 'X'
WHERE Id_Mesa = 3;

SELECT
    count(Estado) 'Nro de Mesas Disponibles'
    FROM Mesas
    WHERE Estado = 'A';

SELECT
    count(Estado) ' Nro de Reservas'
    FROM Reservas
    WHERE Estado = 'R';

SELECT
    count(Estado) 'Nro de Reservas Finalizadas'
    FROM Reservas
    WHERE Estado = 'F';

SELECT
     (Reservas.Id_Mesa) 'Mesa reservada'
    ,(Reservas.Cedula) 'Cedula Cliente'
    ,(Clientes.Nombre || ' ' || Clientes.Apellido) 'Cliente'
    ,(Reservas.Fecha) 'Fecha de Reserva'
    ,(Reservas.Hora) 'Hora de Reserva'
    ,CASE Reservas.Estado
    WHEN 'R' THEN 'Realizada'
    WHEN 'P' THEN 'Pendiente'
    WHEN 'C' THEN 'Cancelada'
    WHEN 'F' THEN 'Finalizada'
    END AS 'Estado de la Reserva'
    FROM Reservas
    JOIN Clientes ON Reservas.Cedula = Clientes.Cedula;