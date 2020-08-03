USE Proyecto

CREATE TABLE Tipo_Persona(
	Codigo_Tipo varchar(4) PRIMARY KEY,
	Descripcion varchar(20) NOT NULL
);
INSERT INTO Tipo_Persona
VALUES('CLI','Cliente'),
('ADM','Administrador'),
('VEN','Vendedor'),
('PROV','Proveedor')


CREATE TABLE Persona(
	Codigo varchar(15) PRIMARY KEY,
	Nombre varchar(25) NOT NULL,
	Telefono varchar(14) NOT NULL,
	Direccion varchar(35) NOT NULL,
	Tipo varchar(4) NOT NULL
	CONSTRAINT FK_Tipo_Persona FOREIGN KEY (Tipo) REFERENCES Tipo_Persona(Codigo_Tipo)
);

CREATE TABLE Cliente (
	Codigo varchar(15) PRIMARY KEY,
	Credito float NOT NULL,
	LimCredito float NOT NULL
	CONSTRAINT FK_Cliente_Persona FOREIGN KEY (Codigo) REFERENCES Persona(Codigo)
);

CREATE TABLE Administrador (
	Codigo varchar(15) PRIMARY KEY,
	Contraseña varchar(20) NOT NULL,
	CONSTRAINT FK_Administrador_Persona FOREIGN KEY (Codigo) REFERENCES Persona(Codigo)
);

CREATE TABLE Vendedor (
	Codigo varchar(15) PRIMARY KEY,
	Contraseña varchar(20) NOT NULL,
	Ventas float NOT NULL
	CONSTRAINT FK_Vendedor_Persona FOREIGN KEY (Codigo) REFERENCES Persona(Codigo)
);

CREATE TABLE Tipo_Componente(
	Codigo_Tipo varchar(5) PRIMARY KEY,
	Descripcion varchar(20) NOT NULL
);
INSERT INTO Tipo_Componente
VALUES('DD','Disco Duro'),
('MB','Motherboard'),
('Micro','Microprocesador'),
('RAM','RAM')

CREATE TABLE Componente(
	Modelo varchar(25) NOT NULL,
	NumeroSerie varchar(10) PRIMARY KEY,
	Marca varchar(25) NOT NULL,
	CantDisponible int NOT NULL,
	CantMin int NOT NULL,
	CantMax int NOT NULL,
	CantidadVentas int NOT NULL,
	Tipo varchar(5) NOT NULL,
	PVActual float NOT NULL, 
	PCActual float NOT NULL
	CONSTRAINT FK_Tipo_Componente FOREIGN KEY (Tipo)
    REFERENCES Tipo_Componente(Codigo_Tipo)
);

CREATE TABLE DiscoDuro(
	NumeroSerie varchar(10) PRIMARY KEY,
	CapAlmacenamiento varchar(15) NOT NULL,
	TipoConexion varchar(15) NOT NULL,
	CONSTRAINT FK_DiscoDuro_Componente FOREIGN KEY (NumeroSerie)
    REFERENCES Componente(NumeroSerie)
);



CREATE TABLE Micro(
	NumeroSerie varchar(10) PRIMARY KEY,
	TipoConexion varchar(15) NOT NULL,
	Velocidad varchar(15) NOT NULL,
	CONSTRAINT FK_Micro_Componente FOREIGN KEY (NumeroSerie)
    REFERENCES Componente(NumeroSerie)
);

CREATE TABLE Motheboard(
	NumeroSerie varchar(10) PRIMARY KEY,
	TipoConector varchar(15) NOT NULL,
	TipoRAM varchar(15) NOT NULL,
	CONSTRAINT FK_Motherboard_Componente FOREIGN KEY (NumeroSerie)
    REFERENCES Componente(NumeroSerie)
);

CREATE TABLE RAM(
	NumeroSerie varchar(10) PRIMARY KEY,
	CantMemoria varchar(15) NOT NULL,
	TipoMemoria varchar(15) NOT NULL,
	CONSTRAINT FK_RAM_Componente FOREIGN KEY (NumeroSerie)
    REFERENCES Componente(NumeroSerie)
);

CREATE TABLE OrdenCompra(
	Codigo varchar(10) PRIMARY KEY,
	NumeroSerieComponente varchar(10),
	CantiCompos int, 
	CostoTotal float,
	Realizada bit,--If true, it has been asigned to provider, else it hasn't
	CONSTRAINT FK_OrdenCompra_Componente FOREIGN KEY (NumeroSerieComponente)
    REFERENCES Componente(NumeroSerie)
); 

CREATE TABLE Proveedor(
	Codigo varchar(15) PRIMARY KEY,
	Debito float NOT NULL,
	CONSTRAINT FK_Proveedor_Persona FOREIGN KEY (Codigo)
    REFERENCES Persona(Codigo)
);

CREATE TABLE OrdenCompra_Proveedor(
	CodigoOrden varchar(10),
	CodigoProveedor varchar(15),
	CONSTRAINT PK_OrdenCompra_Proveedor PRIMARY KEY (CodigoOrden,CodigoProveedor),
	CONSTRAINT FK_OrdenCompraPR1 FOREIGN KEY (CodigoOrden) REFERENCES OrdenCompra(Codigo),
    CONSTRAINT FK_OrdenCompraPR2 FOREIGN KEY (CodigoProveedor) REFERENCES Proveedor(Codigo)
);

CREATE TABLE Componente_Proveedor(
	NumeroSerie varchar(10),
	CodigoProveedor varchar(15),
	PrecioCompra float,
	PrecioVenta float 
	CONSTRAINT PK_NumeroSerie_CodigoProveedor PRIMARY KEY (NumeroSerie,CodigoProveedor,PrecioCompra,PrecioVenta),
	CONSTRAINT FK_ComponentePR1 FOREIGN KEY (NumeroSerie) REFERENCES Componente(NumeroSerie),
	CONSTRAINT FK_ComponentePR2 FOREIGN KEY (CodigoProveedor) REFERENCES Proveedor(Codigo)
)

CREATE TABLE Precio_Componente(
	NumeroSerie varchar(10),
	PrecioVenta float,
	PrecioCompra float NOT NULL,
	CONSTRAINT PK_NumeroSerie_PrecioVenta PRIMARY KEY (NumeroSerie,PrecioVenta,PrecioCompra),
	CONSTRAINT FK_NumeroSerie_Componente FOREIGN KEY (NumeroSerie) REFERENCES Componente(NumeroSerie)
);

CREATE TABLE Combo(
	Nombre varchar(35),
	Precio float,
	Descuento int,
	NumeroDD varchar(10),
	NumeroMicro varchar(10),
	NumeroMother varchar(10),
	NumeroRAM varchar(10)
	CONSTRAINT PK_Combo_Nombre PRIMARY KEY (Nombre),
	CONSTRAINT FK_Combo_ComponenteDD FOREIGN KEY (NumeroDD) REFERENCES Componente(NumeroSerie),
	CONSTRAINT FK_Combo_ComponenteMicro FOREIGN KEY (NumeroMicro) REFERENCES Componente(NumeroSerie),
	CONSTRAINT FK_Combo_ComponenteMother FOREIGN KEY (NumeroMother) REFERENCES Componente(NumeroSerie),
	CONSTRAINT FK_Combo_ComponenteRAM FOREIGN KEY (NumeroRAM) REFERENCES Componente(NumeroSerie)
);

CREATE TABLE Factura(
	Codigo varchar(10),
	Costo float,
	Fecha datetime,
	CodCliente varchar(15),
	Tipo bit
	CONSTRAINT PK_Factura_Nombre PRIMARY KEY (Codigo),
);

CREATE TABLE Factura_Componente(
	CodigoFactura1 varchar(10),
	NumeroSerieComponente varchar(10),
	CantidadComponente int NOT NULL
	CONSTRAINT PK_Factura_Componente PRIMARY KEY (CodigoFactura1,NumeroSerieComponente),
	CONSTRAINT FK_FactComp_CodigoFact FOREIGN KEY (CodigoFactura1) REFERENCES Factura(Codigo),
	CONSTRAINT FK_FactComp_NumSerieComp FOREIGN KEY (NumeroSerieComponente) REFERENCES Componente(NumeroSerie)
);

CREATE TABLE Factura_Combo(
	CodigoFactura2 varchar(10),
	NombreCombo varchar(35),
	CantidadCombo int NOT NULL
	CONSTRAINT PK_Factura_Combo PRIMARY KEY (CodigoFactura2,NombreCombo),
	CONSTRAINT FK_FactCombo_CodigoFact FOREIGN KEY (CodigoFactura2) REFERENCES Factura(Codigo),
	CONSTRAINT FK_FactCombo_NumSerieComp FOREIGN KEY (NombreCombo) REFERENCES Combo(Nombre)
);