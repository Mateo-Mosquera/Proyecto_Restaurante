-- database: ../DataBase/Restaurante.sqlite

/*
CopyRight
autor: Mateo_Mosquera
description: Definición de la estructura de las tablas para el sistema de reservas del restaurante
*/

-- Crear la tabla Cliente
CREATE TABLE Cliente (
    IDCliente INTEGER PRIMARY KEY AUTOINCREMENT,
    Nombre TEXT NOT NULL,
    Apellido TEXT NOT NULL,
    Telefono TEXT NOT NULL,
    Correo TEXT NOT NULL,
    Estado CHAR(1) DEFAULT 'A' -- Estado 'A' significa activo, 'X' significa eliminado
);

-- Crear la tabla Restaurante
CREATE TABLE Restaurante (
    IDRestaurante INTEGER PRIMARY KEY AUTOINCREMENT,
    Nombre TEXT NOT NULL,
    Direccion TEXT NOT NULL
);

-- Crear la tabla Gerente
CREATE TABLE Gerente (
    IDGerente INTEGER PRIMARY KEY AUTOINCREMENT,
    IDRestaurante INTEGER NOT NULL,
    Nombre TEXT NOT NULL,
    Correo TEXT NOT NULL,
    FOREIGN KEY (IDRestaurante) REFERENCES Restaurante(IDRestaurante)
);

-- Crear la tabla Mesas
CREATE TABLE Mesas (
    IDMesa INTEGER PRIMARY KEY AUTOINCREMENT,
    IDRestaurante INTEGER NOT NULL,
    NumeroMesa INTEGER NOT NULL,
    Capacidad INTEGER NOT NULL,
    FOREIGN KEY (IDRestaurante) REFERENCES Restaurante(IDRestaurante)
);

-- Crear la tabla Reservas
CREATE TABLE Reservas (
    IDReserva INTEGER PRIMARY KEY AUTOINCREMENT,
    IDGerente INTEGER NOT NULL,
    IDCliente INTEGER NOT NULL,
    Fecha_reserva DATE NOT NULL,
    Estado TEXT NOT NULL,
    FOREIGN KEY (IDGerente) REFERENCES Gerente(IDGerente),
    FOREIGN KEY (IDCliente) REFERENCES Cliente(IDCliente)
);

-- Crear la tabla Reservas_Mesas
CREATE TABLE Reservas_Mesas (
    IDReservaMesa INTEGER PRIMARY KEY AUTOINCREMENT,
    IDReserva INTEGER NOT NULL,
    IDMesa INTEGER NOT NULL,
    FOREIGN KEY (IDReserva) REFERENCES Reservas(IDReserva),
    FOREIGN KEY (IDMesa) REFERENCES Mesas(IDMesa)
);
