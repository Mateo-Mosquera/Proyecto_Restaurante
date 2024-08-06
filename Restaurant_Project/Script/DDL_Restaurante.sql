--database: ../database/Restaurante.sqlite
/*
CopyRight
@author: Nic1205
@version: 1.0
@date: 01/08/2024
@description: Script para crear las estructuras de las entidades del sistema de reservas
*/

DROP TABLE IF EXISTS Clientes;
DROP TABLE IF EXISTS Mesas;
DROP TABLE IF EXISTS Reservas;
DROP TABLE IF EXISTS Personal;

CREATE TABLE Clientes (
   IDCliente        INTEGER PRIMARY KEY AUTOINCREMENT
  ,Cedula           VARCHAR(10) NOT NULL UNIQUE
  ,Nombre           VARCHAR(50) NOT NULL
  ,Apellido         VARCHAR(50) NOT NULL
  ,Telefono         VARCHAR(20) NOT NULL
  ,Correo           VARCHAR(50) NOT NULL
  ,FechaCreacion    DATE DEFAULT CURRENT_DATE
);

CREATE TABLE Mesas (
   IDMesa           INTEGER PRIMARY KEY AUTOINCREMENT
  ,Capacidad        INTEGER NOT NULL
  ,FechaCreacion    DATE DEFAULT CURRENT_DATE
  ,Estado           VARCHAR(1) NOT NULL DEFAULT 'A' CHECK (Estado IN ('A', 'X'))
);

CREATE TABLE Reservas (
   IDReserva        INTEGER PRIMARY KEY AUTOINCREMENT
  ,Cedula           VARCHAR(10) NOT NULL
  ,IDMesa           INTEGER NOT NULL
  ,Fecha            DATE NOT NULL
  ,Hora             TIME NOT NULL
  ,FechaCreacion    DATE DEFAULT CURRENT_DATE
  ,Estado           VARCHAR(1) NOT NULL DEFAULT 'R' CHECK (Estado IN ('R', 'P', 'C', 'F'))
  ,CONSTRAINT fk_Clientes FOREIGN KEY  (Cedula) REFERENCES Clientes (Cedula)
  ,CONSTRAINT fk_Mesas    FOREIGN KEY  (IDMesa) REFERENCES Mesas    (IDMesa)
);
CREATE TABLE Personal (
   IDPersonal       INTEGER PRIMARY KEY AUTOINCREMENT
  ,Cedula           VARCHAR(10) NOT NULL
  ,Nombre           VARCHAR(50) NOT NULL
  ,Apellido         VARCHAR(50) NOT NULL
  ,Telefono         VARCHAR(20) NOT NULL
  ,Correo           VARCHAR(50) NOT NULL
  ,FechaCreacion    DATE DEFAULT CURRENT_DATE
  );