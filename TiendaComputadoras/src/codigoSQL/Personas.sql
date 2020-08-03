USE Proyecto

GO
CREATE PROCEDURE SP_Crear_Modificar_Administrador @Codigo varchar(15),
@Nombre varchar(25),@Telefono varchar(14),
@Direccion varchar(35), @Contraseña varchar(20)
AS
IF NOT EXISTS (SELECT * FROM Persona WHERE Codigo = @Codigo)
BEGIN
	INSERT INTO Persona
	VALUES(@Codigo,@Nombre,@Telefono,@Direccion,'ADM')
	INSERT INTO Administrador
	VALUES(@Codigo,@Contraseña)
END
ELSE
BEGIN
	UPDATE Persona SET Nombre = @Nombre, Telefono = @Telefono, Direccion = @Direccion
	WHERE Codigo = @Codigo
	UPDATE Administrador SET Contraseña = @Contraseña
	WHERE Codigo = @Codigo
END
GO

GO
CREATE PROCEDURE SP_Crear_Modificar_Vendedor @Codigo varchar(15),
@Nombre varchar(25),@Telefono varchar(14),
@Direccion varchar(35), @Contraseña varchar(20), @Ventas float
AS
IF NOT EXISTS (SELECT * FROM Persona WHERE Codigo = @Codigo)
BEGIN
	PRINT @Codigo
	INSERT INTO Persona
	VALUES(@Codigo,@Nombre,@Telefono,@Direccion,'VEN')
	INSERT INTO Vendedor
	VALUES(@Codigo,@Contraseña,0)
END
ELSE
BEGIN
	UPDATE Persona SET Nombre = @Nombre, Telefono = @Telefono, Direccion = @Direccion
	WHERE Codigo = @Codigo
	UPDATE Vendedor SET Contraseña = @Contraseña, Ventas = @Ventas
	WHERE Codigo = @Codigo
END
GO

GO
CREATE PROCEDURE SP_Crear_Modificar_Cliente @Codigo varchar(15),
@Nombre varchar(25),@Telefono varchar(14),
@Direccion varchar(35), @Credito float, @LimCredito float
AS
IF NOT EXISTS (SELECT * FROM Persona WHERE Codigo = @Codigo)
BEGIN
	INSERT INTO Persona
	VALUES(@Codigo,@Nombre,@Telefono,@Direccion,'CLI')
	INSERT INTO Cliente
	VALUES(@Codigo,@Credito,@LimCredito)
END
ELSE
BEGIN
	UPDATE Persona SET Nombre = @Nombre, Telefono = @Telefono, Direccion = @Direccion
	WHERE Codigo = @Codigo
	UPDATE Cliente SET Credito = @Credito, LimCredito = @LimCredito
	WHERE Codigo = @Codigo
END
GO

GO
CREATE PROCEDURE SP_Crear_Modificar_Proveedor @Codigo varchar(15),
@Nombre varchar(25),@Telefono varchar(14),
@Direccion varchar(35), @Debito float
AS
IF NOT EXISTS (SELECT * FROM Persona WHERE Codigo = @Codigo)
BEGIN
	INSERT INTO Persona
	VALUES(@Codigo,@Nombre,@Telefono,@Direccion,'PROV')
	INSERT INTO Proveedor
	VALUES(@Codigo,@Debito)
END
ELSE
BEGIN
	UPDATE Persona SET Nombre = @Nombre, Telefono = @Telefono, Direccion = @Direccion
	WHERE Codigo = @Codigo
	UPDATE Proveedor SET Debito = @Debito
	WHERE Codigo = @Codigo
END
GO



GO
CREATE FUNCTION F_Obtener_Administrador()  
RETURNS TABLE AS    
RETURN(
    SELECT Persona.Codigo, Nombre, Direccion, Telefono, Contraseña FROM Persona
    INNER JOIN Administrador
    ON Persona.Codigo = Administrador.Codigo
)
GO

GO
CREATE FUNCTION F_Obtener_Vendedor()  
RETURNS TABLE AS    
RETURN(
    SELECT Persona.Codigo, Nombre, Direccion, Telefono, Contraseña, Ventas FROM Persona
    INNER JOIN Vendedor
    ON Persona.Codigo = Vendedor.Codigo
)
GO

GO
CREATE FUNCTION F_Obtener_Proveedor()  
RETURNS TABLE AS    
RETURN(
    SELECT Persona.Codigo, Nombre, Direccion, Telefono, Debito FROM Persona
    INNER JOIN Proveedor
    ON Persona.Codigo = Proveedor.Codigo
)
GO

GO
CREATE FUNCTION F_Obtener_Cliente()  
RETURNS TABLE AS    
RETURN(
    SELECT Persona.Codigo, Nombre, Direccion, Telefono, Credito, LimCredito FROM Persona
    INNER JOIN Cliente
    ON Persona.Codigo = Cliente.Codigo
)
GO




GO
CREATE PROCEDURE SP_Eliminar_Usuario @Codigo varchar(15)
AS
DELETE FROM Administrador WHERE Codigo = @Codigo;
DELETE FROM Vendedor WHERE Codigo = @Codigo;
DELETE FROM Persona WHERE Codigo = @Codigo;
GO

GO
CREATE PROCEDURE SP_Eliminar_Cliente @Codigo varchar(15)
AS
DELETE FROM Cliente WHERE Codigo = @Codigo;
DELETE FROM Persona WHERE Codigo = @Codigo;
GO


GO
CREATE PROCEDURE SP_Relacion_Proveedor_Componente 
@NumeroSerie varchar(10), @CodigoProveedor varchar(15), @PrecioCompra float, @PrecioVenta float
AS
IF NOT EXISTS (SELECT * FROM Componente_Proveedor WHERE NumeroSerie = @NumeroSerie and CodigoProveedor = @CodigoProveedor)
BEGIN
	INSERT INTO Componente_Proveedor
	VALUES(@NumeroSerie, @CodigoProveedor, @PrecioCompra,@PrecioVenta)
END
ELSE
BEGIN
	UPDATE Componente_Proveedor SET PrecioVenta = @PrecioVenta, PrecioCompra = @PrecioCompra
	WHERE NumeroSerie = @NumeroSerie and CodigoProveedor = @CodigoProveedor
END
GO