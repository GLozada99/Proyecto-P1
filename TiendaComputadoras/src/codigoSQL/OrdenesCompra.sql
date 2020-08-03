USE Proyecto
GO
CREATE PROCEDURE SP_Crear_OrdenCompra 
@Codigo varchar(10), @NumeroSerieComponente varchar(10), @CantiCompos int
AS
IF NOT EXISTS (SELECT * FROM OrdenCompra WHERE Codigo = @Codigo)
BEGIN
	INSERT INTO OrdenCompra(Codigo,NumeroSerieComponente,CantiCompos,Realizada)
	VALUES(@Codigo, @NumeroSerieComponente, @CantiCompos,0)
END

GO
CREATE PROCEDURE SP_Procesar_OrdenCompra 
@CodigoOrden varchar(10), @CodigoProveedor varchar (15), @CostoTotal float
AS
IF EXISTS (SELECT * FROM OrdenCompra WHERE Codigo = @CodigoOrden and Realizada = 0)
BEGIN
	UPDATE OrdenCompra SET Realizada = 1, CostoTotal = @CostoTotal
	INSERT INTO OrdenCompra_Proveedor
	VALUES(@CodigoOrden, @CodigoProveedor)
END
GO

GO
CREATE FUNCTION F_Obtener_OrdenCompra() 
RETURNS TABLE AS    
RETURN(
    SELECT * FROM OrdenCompra
)
GO

GO
CREATE FUNCTION F_Obtener_OrdenCompraProcesada() 
RETURNS TABLE AS    
RETURN(
    SELECT * FROM OrdenCompra
    INNER JOIN OrdenCompra_Proveedor
    ON Codigo = CodigoOrden
)
GO


GO
CREATE PROCEDURE SP_Crear_Factura @Codigo varchar(10),
@Costo float, @Fecha datetime, @CodCliente varchar(15), @Tipo bit
AS
IF NOT EXISTS (SELECT * FROM Factura WHERE Codigo = @Codigo)
BEGIN
	INSERT INTO Factura
	VALUES(@Codigo,@Costo,@Fecha,@CodCliente,@Tipo)
END
GO



GO
CREATE PROCEDURE SP_Factura_Componentes @Codigo varchar(10),
@NumeroSerieComponente varchar(10), @CantidadComponente int
AS
IF EXISTS (SELECT * FROM Factura WHERE Codigo = @Codigo)
BEGIN
	INSERT INTO Factura_Componente
	VALUES(@Codigo,@NumeroSerieComponente,@CantidadComponente)
END
GO


GO
CREATE PROCEDURE SP_Factura_Combos @Codigo varchar(10),
@NombreCombo varchar(35),@CantidadCombo int
AS
IF  EXISTS (SELECT * FROM Factura WHERE Codigo = @Codigo)
BEGIN
	INSERT INTO Factura_Combo
	VALUES(@Codigo,@NombreCombo,@CantidadCombo)
END
GO

GO
CREATE FUNCTION F_Obtener_Factura()
RETURNS TABLE AS
RETURN(
	SELECT * FROM Factura
)
GO

GO
CREATE FUNCTION F_Obtener_Componentes_Factura(@Codigo varchar(10))
RETURNS TABLE AS
RETURN(
	SELECT * FROM Factura_Componente
	WHERE CodigoFactura1 = @Codigo
)
GO


GO
CREATE FUNCTION F_Obtener_Combos_Factura(@Codigo varchar(10))
RETURNS TABLE AS
RETURN(
	SELECT * FROM Factura_Combo
	WHERE CodigoFactura2 = @Codigo
)
GO



