USE Proyecto
GO
CREATE PROCEDURE SP_Crear_Modificar_DiscoDuro @Modelo varchar(25),
@NumeroSerie varchar(10),@Marca varchar(25),
@CantDisponible int, @CantMin int, @CantMax int, 
@CantidadVentas int, @CapAlmacenamiento varchar(15),
@TipoConexion varchar(15), @PrecioVenta float, @PrecioCompra float
AS
IF NOT EXISTS (SELECT * FROM Componente WHERE NumeroSerie = @NumeroSerie)
BEGIN
	INSERT INTO Componente
	VALUES(@Modelo, @NumeroSerie, @Marca, @CantDisponible, @CantMin, @CantMax, 0,'DD',@PrecioVenta,0)
	INSERT INTO DiscoDuro
	VALUES(@NumeroSerie,@CapAlmacenamiento,@TipoConexion)
	INSERT INTO Precio_Componente
	VALUES(@NumeroSerie, @PrecioVenta,0)
END
ELSE
BEGIN
	UPDATE Componente SET Modelo = @Modelo, Marca = @Marca, 
	CantDisponible = @CantDisponible, CantMin = @CantMin, 
	CantMax = @CantMax, CantidadVentas = @CantidadVentas, 
	PVActual = @PrecioVenta, PCActual = @PrecioCompra
	WHERE NumeroSerie = @NumeroSerie
	UPDATE DiscoDuro SET CapAlmacenamiento = @CapAlmacenamiento, TipoConexion = @TipoConexion
	WHERE NumeroSerie = @NumeroSerie
END
GO

GO
CREATE PROCEDURE SP_Crear_Modificar_Micro @Modelo varchar(25),
@NumeroSerie varchar(10),@Marca varchar(25),
@CantDisponible int, @CantMin int, @CantMax int, 
@CantidadVentas int,@TipoConexion varchar(15), 
@Velocidad varchar(15), @PrecioVenta float, @PrecioCompra float
AS
IF NOT EXISTS (SELECT * FROM Componente WHERE NumeroSerie = @NumeroSerie)
BEGIN
	INSERT INTO Componente
	VALUES(@Modelo, @NumeroSerie, @Marca, @CantDisponible, @CantMin, @CantMax, 0,'Micro',@PrecioVenta,0)
	INSERT INTO Micro
	VALUES(@NumeroSerie,@TipoConexion,@Velocidad)
	INSERT INTO Precio_Componente
	VALUES(@NumeroSerie, @PrecioVenta,0)
END
ELSE
BEGIN
	UPDATE Componente SET Modelo = @Modelo, Marca = @Marca, 
	CantDisponible = @CantDisponible, CantMin = @CantMin, 
	CantMax = @CantMax, CantidadVentas = @CantidadVentas, 
	PVActual = @PrecioVenta, PCActual = @PrecioCompra
	WHERE NumeroSerie = @NumeroSerie
	UPDATE Micro SET Velocidad = @Velocidad, TipoConexion = @TipoConexion
	WHERE NumeroSerie = @NumeroSerie
END
GO

GO
CREATE PROCEDURE SP_Crear_Modificar_Motherboard @Modelo varchar(25),
@NumeroSerie varchar(10),@Marca varchar(25),
@CantDisponible int, @CantMin int, @CantMax int, 
@CantidadVentas int, @TipoConector varchar(15), 
@TipoRAM varchar(15), @PrecioVenta float, @PrecioCompra float
AS
IF NOT EXISTS (SELECT * FROM Componente WHERE NumeroSerie = @NumeroSerie)
BEGIN
	INSERT INTO Componente
	VALUES(@Modelo, @NumeroSerie, @Marca, @CantDisponible, @CantMin, @CantMax, 0,'MB',@PrecioVenta,0)
	INSERT INTO Motheboard
	VALUES(@NumeroSerie,@TipoConector,@TipoRAM)
	INSERT INTO Precio_Componente
	VALUES(@NumeroSerie, @PrecioVenta,0)
END
ELSE
BEGIN
	UPDATE Componente SET Modelo = @Modelo, Marca = @Marca, 
	CantDisponible = @CantDisponible, CantMin = @CantMin, 
	CantMax = @CantMax, CantidadVentas = @CantidadVentas,
	PVActual = @PrecioVenta, PCActual = @PrecioCompra
	WHERE NumeroSerie = @NumeroSerie
	UPDATE Motheboard SET TipoConector = @TipoConector, TipoRAM = @TipoRAM
	WHERE NumeroSerie = @NumeroSerie
END
GO

GO
CREATE PROCEDURE SP_Crear_Modificar_RAM @Modelo varchar(25),
@NumeroSerie varchar(10),@Marca varchar(25),
@CantDisponible int, @CantMin int, @CantMax int, 
@CantidadVentas int, @CantMemoria varchar(15), 
@TipoMemoria varchar(15), @PrecioVenta float, @PrecioCompra float
AS
IF NOT EXISTS (SELECT * FROM Componente WHERE NumeroSerie = @NumeroSerie)
BEGIN
	INSERT INTO Componente
	VALUES(@Modelo, @NumeroSerie, @Marca, @CantDisponible, @CantMin, @CantMax, 0,'RAM',@PrecioVenta,0)
	INSERT INTO RAM
	VALUES(@NumeroSerie,@CantMemoria,@TipoMemoria)
	INSERT INTO Precio_Componente
	VALUES(@NumeroSerie, @PrecioVenta,0)
END
ELSE
BEGIN
	UPDATE Componente SET Modelo = @Modelo, Marca = @Marca, 
	CantDisponible = @CantDisponible, CantMin = @CantMin, 
	CantMax = @CantMax, CantidadVentas = @CantidadVentas,
	PVActual = @PrecioVenta, PCActual = @PrecioCompra
	WHERE NumeroSerie = @NumeroSerie
	UPDATE RAM SET CantMemoria = @CantMemoria, TipoMemoria = @TipoMemoria
	WHERE NumeroSerie = @NumeroSerie
END
GO

GO
CREATE PROCEDURE SP_Agregar_Precio_Componente 
@NumeroSerie varchar(10),@PrecioVenta float, @PrecioCompra float 
AS
IF NOT EXISTS (SELECT * FROM Precio_Componente WHERE NumeroSerie = @NumeroSerie and PrecioVenta = @PrecioVenta and PrecioCompra = @PrecioCompra)
BEGIN
	INSERT INTO Precio_Componente
	VALUES(@NumeroSerie, @PrecioVenta, @PrecioCompra)
END
GO





GO
CREATE FUNCTION F_Obtener_DiscoDuro()  
RETURNS TABLE AS    
RETURN(
    SELECT Componente.NumeroSerie, Modelo, Marca, CantDisponible, CantMin, 
    CantMax, CantidadVentas, CapAlmacenamiento, TipoConexion,PVActual,PCActual FROM Componente
    INNER JOIN DiscoDuro
    ON Componente.NumeroSerie = DiscoDuro.NumeroSerie
)
GO

GO
CREATE FUNCTION F_Obtener_Micro()  
RETURNS TABLE AS    
RETURN(
    SELECT Componente.NumeroSerie, Modelo, Marca, CantDisponible, CantMin, 
    CantMax, CantidadVentas, TipoConexion, Velocidad, PVActual,PCActual FROM Componente
    INNER JOIN Micro
    ON Componente.NumeroSerie = Micro.NumeroSerie
)
GO

GO
CREATE FUNCTION F_Obtener_Motherboard()  
RETURNS TABLE AS    
RETURN(
    SELECT Componente.NumeroSerie, Modelo, Marca, CantDisponible, CantMin, 
    CantMax, CantidadVentas, TipoConector, TipoRAM, PVActual,PCActual FROM Componente
    INNER JOIN Motheboard
    ON Componente.NumeroSerie = Motheboard.NumeroSerie
)
GO

GO
CREATE FUNCTION F_Obtener_RAM()  
RETURNS TABLE AS    
RETURN(
    SELECT Componente.NumeroSerie, Modelo, Marca, CantDisponible, CantMin, 
    CantMax, CantidadVentas, CantMemoria, TipoMemoria, PVActual,PCActual FROM Componente
    INNER JOIN RAM
    ON Componente.NumeroSerie = RAM.NumeroSerie
)
GO

GO
CREATE FUNCTION F_PrecioComp_ByNumeroSerie(@NumeroSerie varchar(10))  
RETURNS TABLE AS    
RETURN(
    SELECT * FROM Precio_Componente
    WHERE NumeroSerie = @NumeroSerie
)
GO

GO
CREATE FUNCTION F_ProveedorComp_ByNumeroSerie(@NumeroSerie varchar(10))  
RETURNS TABLE AS    
RETURN(
    SELECT * FROM Componente_Proveedor
    WHERE NumeroSerie = @NumeroSerie
)
GO




GO
CREATE PROCEDURE SP_Eliminar_Componente @NumeroSerie varchar(15)
AS
DELETE FROM DiscoDuro WHERE NumeroSerie = @NumeroSerie;
DELETE FROM Motheboard WHERE NumeroSerie = @NumeroSerie;
DELETE FROM Micro WHERE NumeroSerie = @NumeroSerie;
DELETE FROM RAM WHERE NumeroSerie = @NumeroSerie;
DELETE FROM Precio_Componente WHERE NumeroSerie = @NumeroSerie;
DELETE FROM Componente_Proveedor WHERE NumeroSerie = @NumeroSerie;
DELETE FROM Componente WHERE NumeroSerie = @NumeroSerie;
GO


GO
CREATE PROCEDURE SP_Crear_Modificar_Combo 
@Nombre varchar(35), @Precio float, @Descuento int,
@NumeroDD varchar(10), @NumeroMicro varchar(10),
@NumeroMother varchar(10), @NumeroRAM varchar(10)
AS
IF NOT EXISTS (SELECT * FROM Combo WHERE Nombre = @Nombre)
BEGIN
	INSERT INTO Combo
	VALUES(@Nombre,@Precio,@Descuento,@NumeroDD,@NumeroMicro,@NumeroMother,@NumeroRAM)
END
ELSE
BEGIN
	UPDATE Combo SET Precio = @Precio,Descuento = @Descuento, NumeroDD = @NumeroDD, 
	NumeroMicro = @NumeroMicro, NumeroMother = @NumeroMother, NumeroRAM = @NumeroRAM
	WHERE Nombre = @Nombre
END
GO

GO
CREATE FUNCTION F_Obtener_Combo()  
RETURNS TABLE AS    
RETURN(
    SELECT * FROM Combo
)
GO
