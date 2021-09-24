USE zapatillas;

DROP TABLE IF EXISTS Proveedores;
CREATE TABLE Proveedores (
    telefono INT NOT NULL,
    direccion VARCHAR(30) NOT NULL,
    codigoProveedor INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(30) NOT NULL
);

DROP TABLE IF EXISTS Facturas;
CREATE TABLE Facturas(
	idFactura INT AUTO_INCREMENT PRIMARY KEY,
	fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	precioTotal DECIMAL(8, 2),
    idTrabajador INT,
	idCliente INT
);

DROP TABLE IF EXISTS Trabajadores;
create table Trabajadores(
  DNI VARCHAR(9) NOT NULL,
  idTrabajador INT Auto_Increment PRIMARY Key,
  telefono INT NOT NULL,
  nombre VARCHAR(35) NOT NULL
);

DROP TABLE IF EXISTS Calcetines;
CREATE TABLE Calcetines(
	idCalcetines INT AUTO_INCREMENT PRIMARY KEY,
	color VARCHAR(9),
	talla INT,
	stock BOOLEAN,
	nombre VARCHAR(35),
	precio DECIMAL(8, 2),
	tejido VARCHAR(20),
    idAlmacen int,
    codigoMarca int
);

DROP TABLE IF EXISTS Zapatillas;
CREATE TABLE Zapatillas(
	idZapatillas INT AUTO_INCREMENT PRIMARY KEY,
	color VARCHAR(9),
	talla INT,
	stock BOOLEAN,
	nombre VARCHAR(35),
	precio DECIMAL(8, 2),
	tipo VARCHAR(20),
    idAlmacen int,
    codigoMarca int
);


DROP TABLE IF EXISTS Clientes;
CREATE TABLE Clientes(
  idCliente INT Auto_Increment PRIMARY KEY,
  nombre VARCHAR(35) NOT NULL,
  socio BOOLEAN NOT NULL,
  DNI VARCHAR(9)
);

DROP TABLE IF EXISTS Almacen;
create Table Almacen(
  idAlmacen INT Auto_Increment PRIMARY Key,
  telefono INT NOT NULL,
  capacidad INT NOT NULL,
  direccion VARCHAR(30) NOT NULL
);

DROP TABLE IF EXISTS Marca;
create table Marca(
  codigoMarca INT Auto_Increment PRIMARY Key,
  nombre VARCHAR(35) NOT NULL
);

DROP TABLE IF EXISTS Suministra;
CREATE TABLE Suministra(
	codigoProveedor INT, 
	idZapatillas INT,
	idCalcetines INT,
	precioSuministro DECIMAL(8, 2)
);

DROP TABLE IF EXISTS Contiene;
CREATE TABLE Contiene(
	unidades INT,
	precio DECIMAL(8, 2),
	idZapatillas INT,
	idCalcetines INT
);

ALTER TABLE Facturas
	ADD CONSTRAINT FK_Facturas FOREIGN KEY(idTrabajador) REFERENCES Trabajadores(idTrabajador);

ALTER TABLE Facturas 
  ADD CONSTRAINT FK1_Facturas FOREIGN KEY(idCliente) REFERENCES Clientes(idCliente);


ALTER TABLE Calcetines
  ADD CONSTRAINT FK_Calcetines FOREIGN KEY(idAlmacen) REFERENCES Almacen(IDAlmacen) ON DELETE CASCADE;

ALTER TABLE Zapatillas
  ADD CONSTRAINT FK_Zapatillas FOREIGN KEY(idAlmacen) REFERENCES Almacen(IDAlmacen) ON DELETE CASCADE;


ALTER TABLE Calcetines
ADD CONSTRAINT FK1_Calcetines FOREIGN KEY(codigoMarca) REFERENCES Marca(CodigoMarca) ON DELETE CASCADE;

ALTER TABLE Zapatillas
ADD CONSTRAINT FK2_Zapatillas FOREIGN KEY(codigoMarca) REFERENCES Marca(CodigoMarca) ON DELETE CASCADE;


ALTER TABLE Suministra
	ADD CONSTRAINT FK1_Suministra FOREIGN KEY(codigoProveedor) REFERENCES Proveedores(CodigoProveedor) ON DELETE CASCADE;

ALTER TABLE Suministra
	ADD CONSTRAINT FK2_Suministra FOREIGN KEY(idZapatillas) REFERENCES Zapatillas(idZapatillas) ON DELETE CASCADE;

ALTER TABLE Suministra
	ADD CONSTRAINT FK3_Suministra FOREIGN KEY(idCalcetines) REFERENCES Calcetines(idCalcetines) ON DELETE CASCADE;


ALTER TABLE Contiene
	ADD CONSTRAINT FK1_Contiene FOREIGN KEY(idZapatillas) REFERENCES Zapatillas(idZapatillas) ON DELETE CASCADE;

ALTER TABLE Contiene
	ADD CONSTRAINT FK2_Contiene FOREIGN KEY(idCalcetines) REFERENCES Calcetines(idCalcetines) ON DELETE CASCADE;