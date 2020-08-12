USE Proyecto

--Todos los componentes que ofrecen todos los proveedores
GO
CREATE FUNCTION F_Proveedores_Y_Componentes()
RETURNS TABLE
AS
RETURN
(SELECT Nombre, CodigoProveedor, C.NumeroSerie as NumeroSerieComponente, Marca + ' ' + Modelo as Componente, Descripcion, PrecioCompra
FROM Persona AS Pe
RIGHT JOIN Proveedor AS Pr
ON Pe.Codigo = Pr.Codigo
LEFT JOIN Componente_Proveedor AS Cp
ON Pr.Codigo = Cp.CodigoProveedor
LEFT JOIN Componente AS C
ON Cp.NumeroSerie = C.NumeroSerie
LEFT JOIN Tipo_Componente AS Tc
ON C.Tipo = Tc.Codigo_Tipo
--ORDER BY Pe.Nombre
);
GO


--Todas las facturas de todos los clientes con su componente o combo especifico y cantidad
GO
CREATE FUNCTION F_Facturas_De_Clientes()
RETURNS TABLE 
AS
RETURN
(SELECT Cl.Codigo as Cedula, Pe.Nombre,  F.Codigo as CodigoFactura, Marca + ' ' + Modelo as Componente, C.Tipo, CantidadComponente, NombreCombo, CantidadCombo, Fecha
FROM Persona AS Pe
JOIN Cliente AS Cl
ON Pe.Codigo = Cl.Codigo
JOIN Factura AS F
ON Cl.Codigo = F.CodCliente
FULL JOIN Factura_Componente AS Fcp
ON F.Codigo = Fcp.CodigoFactura1
LEFT JOIN Componente AS C
ON Fcp.NumeroSerieComponente = C.NumeroSerie
LEFT JOIN Tipo_Componente AS Tc
ON C.Tipo = Tc.Codigo_Tipo
FULL JOIN Factura_Combo AS Fcb
ON F.Codigo = Fcb.CodigoFactura2
);
GO



--Recibe el nombre de un cliente y devuelve lo que ha comprado
GO
CREATE FUNCTION F_Facturas_De_Cliente(@cliente varchar(15))
RETURNS TABLE 
AS
RETURN
(SELECT Cl.Codigo as Cedula, Pe.Nombre,  F.Codigo as CodigoFactura, Marca + ' ' + Modelo as Componente, C.Tipo, CantidadComponente, NombreCombo, CantidadCombo, Fecha
FROM Persona AS Pe
JOIN Cliente AS Cl
ON Pe.Codigo = Cl.Codigo
JOIN Factura AS F
ON Cl.Codigo = F.CodCliente
FULL JOIN Factura_Componente AS Fcp
ON F.Codigo = Fcp.CodigoFactura1
LEFT JOIN Componente AS C
ON Fcp.NumeroSerieComponente = C.NumeroSerie
LEFT JOIN Tipo_Componente AS Tc
ON C.NumeroSerie = Tc.Codigo_Tipo
FULL JOIN Factura_Combo AS Fcb
ON F.Codigo = Fcb.CodigoFactura2
WHERE CodCliente = @cliente
);
GO


GO
CREATE FUNCTION F_Combos_Mas_Vendidos()
RETURNS TABLE
RETURN
(SELECT NombreCombo, Precio, SUM(CantidadCombo) AS CantidadesVendidas
FROM Factura_Combo AS Fcb
JOIN Combo AS C
ON Fcb.NombreCombo = C.Nombre
GROUP BY NombreCombo, Precio
);
GO

SELECT * FROM F_Combos_Mas_Vendidos()