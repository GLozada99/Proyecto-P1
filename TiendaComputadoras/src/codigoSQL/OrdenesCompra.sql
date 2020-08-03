
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


