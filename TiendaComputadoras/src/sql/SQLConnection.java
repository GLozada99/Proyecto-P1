package sql;

import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import logica.Administrador;
import logica.Cliente;
import logica.Combo;
import logica.Componente;
import logica.DiscoDuro;
import logica.Factura;
import logica.Micro;
import logica.MotherBoard;
import logica.OrdenCompra;
import logica.Persona;
import logica.Precio;
import logica.Proveedor;
import logica.RAM;
import logica.Tienda;
import logica.Vendedor;

public class SQLConnection {
	private static String connectionURL;

	public static void setConnectionURL(String serverName, String database) throws ClassNotFoundException{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String URL = "jdbc:sqlserver://"+serverName+"\\SQLEXPRESS;database="+database+";integratedSecurity=true;";
		connectionURL = URL;
	}


	public static String getConnectionURL() {
		return connectionURL;
	}

	public static void getData() {

		try (Connection con = DriverManager.getConnection(SQLConnection.getConnectionURL()); Statement stmt = con.createStatement();) {
			ResultSet rs = null;

			rs = stmt.executeQuery("SELECT * FROM F_Obtener_Administrador()");//Administradores
			while (rs.next()) {
				Persona aux = new Administrador(rs.getString("Nombre"), rs.getString("Telefono"), rs.getString("Direccion"), rs.getString("Codigo"), rs.getString("Contraseña"));
				Tienda.getInstance().getLosUsuarios().add(aux);
			}
			if(Tienda.getInstance().getLosUsuarios().isEmpty()) {
				Persona aux = new Administrador("Admin", "(000) 000-0000", "N/A", "Admin", "Admin");
				Tienda.getInstance().getLosUsuarios().add(aux);
				insertUpdateAdmin((Administrador) aux);
			}

			rs = stmt.executeQuery("SELECT * FROM F_Obtener_Vendedor()");//Vendedores
			while (rs.next()) {
				Persona aux = new Vendedor(rs.getString("Nombre"), rs.getString("Telefono"), rs.getString("Direccion"), rs.getString("Codigo"), rs.getString("Contraseña"), rs.getFloat("Ventas"));
				Tienda.getInstance().getLosUsuarios().add(aux);
			}

			rs = stmt.executeQuery("SELECT * FROM F_Obtener_Cliente()");//Clientes
			while (rs.next()) {
				Persona aux = new Cliente(rs.getString("Nombre"), rs.getString("Telefono"), rs.getString("Direccion"), rs.getString("Codigo"));
				((Cliente)aux).setCredito(rs.getFloat("Credito"));
				Tienda.getInstance().getLosClientes().add((Cliente) aux);
			}

			rs = stmt.executeQuery("SELECT * FROM F_Obtener_Proveedor()");//Proveedores
			while (rs.next()) {
				Persona aux = new Proveedor(rs.getString("Nombre"), rs.getString("Telefono"), rs.getString("Direccion"), rs.getString("Codigo"), new ArrayList<Componente>(), new ArrayList<Float>());
				Tienda.getInstance().getLosProveedores().add((Proveedor) aux);
			}

			{
				rs = stmt.executeQuery("SELECT * FROM F_Obtener_DiscoDuro()");//Discos Duros
				while (rs.next()) {
					Componente aux = new DiscoDuro(rs.getString("NumeroSerie"),rs.getString("Marca"),rs.getString("Modelo"),rs.getInt("CantMin"), rs.getInt("CantMax"), rs.getFloat("PVActual"), rs.getString("CapAlmacenamiento"), rs.getString("TipoConexion"));
					aux.setCantDisponible(rs.getInt("CantDisponible"));
					aux.setPCActual(rs.getFloat("PCActual"));
					aux.setCantidadVentas(rs.getInt("CantidadVentas"));
					Tienda.getInstance().agregarComponente(aux);
				}

				rs = stmt.executeQuery("SELECT * FROM F_Obtener_Micro()");//Microprocesadores
				while (rs.next()) {
					Componente aux = new Micro(rs.getString("NumeroSerie"),rs.getString("Marca"),rs.getString("Modelo"),rs.getInt("CantMin"), rs.getInt("CantMax"), rs.getFloat("PVActual"), rs.getString("TipoConexion"), rs.getString("Velocidad"));
					aux.setCantDisponible(rs.getInt("CantDisponible"));
					aux.setPCActual(rs.getFloat("PCActual"));
					aux.setCantidadVentas(rs.getInt("CantidadVentas"));
					Tienda.getInstance().agregarComponente(aux);
				}

				rs = stmt.executeQuery("SELECT * FROM F_Obtener_Motherboard()");//Motherboards
				while (rs.next()) {
					Componente aux = new MotherBoard(rs.getString("NumeroSerie"),rs.getString("Marca"),rs.getString("Modelo"),rs.getInt("CantMin"), rs.getInt("CantMax"), rs.getFloat("PVActual"), rs.getString("TipoConector"), rs.getString("TipoRAM"));
					aux.setCantDisponible(rs.getInt("CantDisponible"));
					aux.setPCActual(rs.getFloat("PCActual"));
					aux.setCantidadVentas(rs.getInt("CantidadVentas"));
					Tienda.getInstance().agregarComponente(aux);
				}

				rs = stmt.executeQuery("SELECT * FROM F_Obtener_RAM()");//RAM
				while (rs.next()) {
					Componente aux = new RAM(rs.getString("NumeroSerie"),rs.getString("Marca"),rs.getString("Modelo"),rs.getInt("CantMin"), rs.getInt("CantMax"), rs.getFloat("PVActual"), rs.getString("CantMemoria"), rs.getString("TipoMemoria"));
					aux.setCantDisponible(rs.getInt("CantDisponible"));
					aux.setPCActual(rs.getFloat("PCActual"));
					aux.setCantidadVentas(rs.getInt("CantidadVentas"));
					Tienda.getInstance().agregarComponente(aux);
				}
				for (Componente aux : Tienda.getInstance().getLosComponentes()) {//Precios para componentes
					rs = stmt.executeQuery("SELECT * FROM F_PrecioComp_ByNumeroSerie('"+aux.getNumeroSerie()+"')");
					while(rs.next()) {
						Precio auxPrecio = new Precio(rs.getFloat("PrecioVenta"),rs.getFloat("PrecioCompra") , false);
						aux.getPrecios().add(auxPrecio);
					}
				}
				for (Componente aux : Tienda.getInstance().getLosComponentes()) {//Proveedores para componentes
					rs = stmt.executeQuery("SELECT * FROM F_ProveedorComp_ByNumeroSerie('"+aux.getNumeroSerie()+"')");
					while(rs.next()) {
						Proveedor auxProv = Tienda.getInstance().findProveedrobyRNC(rs.getString("CodigoProveedor"));
						Componente auxComp = Tienda.getInstance().findComponentebyNumeroSerie(rs.getString("NumeroSerie"));
						auxProv.getMisCompos().add(auxComp);
						auxProv.getPreciosCompos().add(rs.getFloat("PrecioCompra"));
						auxComp.getLosQueVenden().add(auxProv);
					}
				}
			}
			rs = stmt.executeQuery("SELECT * FROM F_Obtener_OrdenCompra()");//OrdenesCompra
			while (rs.next()) {
				OrdenCompra aux = new OrdenCompra(rs.getString("Codigo"), Tienda.getInstance().findComponentebyNumeroSerie(rs.getString("NumeroSerieComponente")), rs.getInt("CantiCompos"));
				Tienda.getInstance().agregarOrden(aux);
			}

			rs = stmt.executeQuery("SELECT * FROM F_Obtener_OrdenCompraProcesada()");//OrdenesCompraProcesadas
			while (rs.next()) {
				OrdenCompra auxOrd = Tienda.getInstance().findOrdenComprabyCodigo(rs.getString("Codigo"));
				Proveedor auxProv = Tienda.getInstance().findProveedrobyRNC(rs.getString("CodigoProveedor"));
				auxOrd.setCostoTotal(auxOrd.getCantiCompos()*auxProv.getPrecioCompo(auxOrd.getCompCompra()));
				auxOrd.setRealizada(true);
				
				Tienda.getInstance().getLasOrdenes().add(auxOrd);
				auxProv.getMisOrdenes().add(auxOrd);
				Tienda.getInstance().getOrdenesSinProcesar().remove(auxOrd);
			}

			rs = stmt.executeQuery("SELECT * FROM F_Obtener_Combo()");//OrdenesCompraProcesadas
			while (rs.next()) {
				Componente auxDD = Tienda.getInstance().findComponentebyNumeroSerie(rs.getString("NumeroDD"));
				Componente auxMicro = Tienda.getInstance().findComponentebyNumeroSerie(rs.getString("NumeroMicro"));
				Componente auxMother = Tienda.getInstance().findComponentebyNumeroSerie(rs.getString("NumeroMother"));
				Componente auxRAM = Tienda.getInstance().findComponentebyNumeroSerie(rs.getString("NumeroRAM"));

				ArrayList<Componente> auxComps = new ArrayList<>();
				auxComps.add(auxDD);
				auxComps.add(auxMicro);
				auxComps.add(auxMother);
				auxComps.add(auxRAM);

				Combo aux = new Combo(auxComps, rs.getString("Nombre"),rs.getFloat("Precio"),rs.getInt("Descuento"));

				Tienda.getInstance().agregarCombo(aux);
			}
			
			rs = stmt.executeQuery("SELECT * FROM F_Obtener_Factura()");
			while(rs.next()) {
				ArrayList<Componente> auxCompos = new ArrayList<Componente>();
				ArrayList<Combo> auxCombos = new ArrayList<Combo>();
				ArrayList<Integer> cantiCompos = new ArrayList<Integer>();
				ArrayList<Integer> cantiCombos = new ArrayList<Integer>();
				
				
				Factura aux = new Factura(rs.getString("Codigo"),rs.getFloat("Costo"), Tienda.getInstance().findClientebyCedula(rs.getString("CodCliente")), auxCompos, auxCombos, cantiCompos, cantiCombos, rs.getBoolean("Tipo"));
				//System.out.println(rs.getDate("Fecha"));
				aux.setFecha(rs.getString("Fecha"));
				Tienda.getInstance().agregarFactura(aux);
				System.out.println("Facturas");
			}
			
			for (Factura aux : Tienda.getInstance().getLasFacturas()) {
				System.out.println("Numero");
				rs = stmt.executeQuery("SELECT * FROM F_Obtener_Componentes_Factura('"+aux.getCodigo()+"')");
				while(rs.next()) {
					Componente auxComp = Tienda.getInstance().findComponentebyNumeroSerie(rs.getString("NumeroSerieComponente"));
					aux.getLosComponentes().add(auxComp);
					aux.getCantiComponentes().add(rs.getInt("CantidadComponente"));
					System.out.println("Hola");
				}
				rs = stmt.executeQuery("SELECT * FROM F_Obtener_Combos_Factura('"+aux.getCodigo()+"')");
				while(rs.next()) {
					Combo auxCombo = Tienda.getInstance().findCombobyCodigo(rs.getString("NombreCombo"));
					aux.getLosCombos().add(auxCombo);
					aux.getCantiCombos().add(rs.getInt("CantidadCombo"));
				}
			}



		}catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void insertUpdateCliente(Cliente aux){//if true, insert; else, update
		try (Connection con = DriverManager.getConnection(getConnectionURL()); Statement stmt = con.createStatement();) {
			String statement = "";
			statement = "EXEC SP_Crear_Modificar_Cliente ";
			statement += " @Codigo = '"+aux.getCodigo()+"',";
			statement += " @Nombre = '"+aux.getNombre()+"',";
			statement += " @Telefono = '"+aux.getTelefono()+"',";
			statement += " @Direccion = '"+aux.getDireccion()+"',";
			statement += " @Credito = "+aux.getCredito()+", ";
			statement += " @LimCredito = "+aux.getLimCredito()+" ";

			System.out.println(statement);
			stmt.executeUpdate(statement);

		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void insertUpdateAdmin(Administrador aux){//if true, insert; else, update
		try (Connection con = DriverManager.getConnection(getConnectionURL()); Statement stmt = con.createStatement();) {
			String statement = "";
			statement = "EXEC SP_Crear_Modificar_Administrador ";
			statement += " @Codigo = '"+aux.getCodigo()+"',";
			statement += " @Nombre = '"+aux.getNombre()+"',";
			statement += " @Telefono = '"+aux.getTelefono()+"',";
			statement += " @Direccion = '"+aux.getDireccion()+"',";
			statement += " @Contraseña = '"+aux.getContraseña()+"' ";

			System.out.println(statement);
			stmt.executeUpdate(statement);

		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void insertUpdateVendedor(Vendedor aux){//if true, insert; else, update
		try (Connection con = DriverManager.getConnection(getConnectionURL()); Statement stmt = con.createStatement();) {
			String statement = "";
			statement = "EXEC SP_Crear_Modificar_Vendedor ";
			statement += " @Codigo = '"+aux.getCodigo()+"',";
			statement += " @Nombre = '"+aux.getNombre()+"',";
			statement += " @Telefono = '"+aux.getTelefono()+"',";
			statement += " @Direccion = '"+aux.getDireccion()+"',";
			statement += " @Contraseña = '"+aux.getContraseña()+"', ";
			statement += " @Ventas = "+aux.getVentas()+"";

			System.out.println(statement);
			stmt.executeUpdate(statement);

		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void insertUpdateProveedor(Proveedor aux){//if true, insert; else, update
		try (Connection con = DriverManager.getConnection(getConnectionURL()); Statement stmt = con.createStatement();) {
			String statement = "";
			statement = "EXEC SP_Crear_Modificar_Proveedor ";
			statement += " @Codigo = '"+aux.getCodigo()+"',";
			statement += " @Nombre = '"+aux.getNombre()+"',";
			statement += " @Telefono = '"+aux.getTelefono()+"',";
			statement += " @Direccion = '"+aux.getDireccion()+"',";
			statement += " @Debito = "+aux.getDebito()+" ";

			System.out.println(statement);
			stmt.executeUpdate(statement);

		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void insertUpdatePrecio(Componente aux){
		try (Connection con = DriverManager.getConnection(getConnectionURL()); Statement stmt = con.createStatement();) {

			for (Precio auxPrecio : aux.getPrecios()) {
				String statement = "";
				statement = "EXEC SP_Agregar_Precio_Componente ";
				statement += " @NumeroSerie = '"+aux.getNumeroSerie()+"',";
				statement += " @PrecioVenta = "+auxPrecio.getPrecioVenta()+", ";
				statement += " @PrecioCompra = "+auxPrecio.getPrecioCompra()+" ";
				System.out.println(statement);
				stmt.executeUpdate(statement);
			}


		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void insertUpdateRelacionProveedorComponente(Componente auxComp) {
		try (Connection con = DriverManager.getConnection(getConnectionURL()); Statement stmt = con.createStatement();) {

			for (Proveedor auxProv : auxComp.getLosQueVenden()) {
				String statement = "";
				statement = "EXEC SP_Relacion_Proveedor_Componente ";
				statement += " @NumeroSerie = '"+auxComp.getNumeroSerie()+"',";
				statement += " @CodigoProveedor = '"+auxProv.getCodigo()+"', ";
				statement += " @PrecioCompra = "+auxProv.getPrecioCompo(auxComp)+", ";
				statement += " @PrecioVenta = "+auxComp.getPVActual()+" ";
				System.out.println(statement);
				stmt.executeUpdate(statement);
			}


		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void insertOrdenCompra(OrdenCompra aux) {
		try (Connection con = DriverManager.getConnection(getConnectionURL()); Statement stmt = con.createStatement();) {

			String statement = "";
			statement += "EXEC SP_Crear_OrdenCompra ";
			statement += " @Codigo = '"+aux.getCodigo()+"', ";
			statement += " @NumeroSerieComponente = '"+aux.getCompCompra().getNumeroSerie()+"', ";
			statement += " @CantiCompos = "+aux.getCantiCompos()+" ";

			System.out.println(statement);
			stmt.executeUpdate(statement);
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void processOrdenCompra(OrdenCompra auxOrd,Proveedor auxProv) {
		try (Connection con = DriverManager.getConnection(getConnectionURL()); Statement stmt = con.createStatement();) {

			String statement = "";
			statement += "EXEC SP_Procesar_OrdenCompra ";
			statement += " @CodigoOrden = '"+auxOrd.getCodigo()+"', ";
			statement += " @CodigoProveedor = '"+auxProv.getCodigo()+"', ";
			statement += " @CostoTotal = "+auxOrd.getCostoTotal()+" ";

			System.out.println(statement);
			stmt.executeUpdate(statement);
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void insertUpdateDiscoDuro(Componente aux) {
		try (Connection con = DriverManager.getConnection(getConnectionURL()); Statement stmt = con.createStatement();) {
			String statement = "";
			statement = "EXEC SP_Crear_Modificar_DiscoDuro ";
			statement += " @PrecioVenta = "+aux.getPrecioVentaActual()+", ";
			statement += " @PrecioCompra = "+aux.getPrecioCompraActual()+", ";
			statement += " @CantidadVentas = "+aux.getCantidadVentas()+", ";
			statement += " @Modelo = '"+aux.getModelo()+"',";
			statement += " @NumeroSerie = '"+aux.getNumeroSerie()+"',";
			statement += " @Marca = '"+aux.getMarca()+"',";
			statement += " @CantDisponible = "+aux.getCantDisponible()+",";
			statement += " @CantMin = "+aux.getCantMin()+", ";
			statement += " @CantMax = "+aux.getCantMax()+", ";
			statement += " @TipoConexion = '"+((DiscoDuro)aux).getTipoConexion()+"', ";
			statement += " @CapAlmacenamiento = '"+((DiscoDuro)aux).getCapacidadAlma()+"' ";

			System.out.println(statement);
			stmt.executeUpdate(statement);

		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void insertUpdateMicro(Componente aux) {//if true, insert; else, update
		try (Connection con = DriverManager.getConnection(getConnectionURL()); Statement stmt = con.createStatement();) {
			String statement = "";
			statement = "EXEC SP_Crear_Modificar_Micro ";
			statement += " @PrecioVenta = "+aux.getPrecioVentaActual()+", ";
			statement += " @PrecioCompra = "+aux.getPrecioCompraActual()+", ";
			statement += " @CantidadVentas = "+aux.getCantidadVentas()+", ";
			statement += " @Modelo = '"+aux.getModelo()+"',";
			statement += " @NumeroSerie = '"+aux.getNumeroSerie()+"',";
			statement += " @Marca = '"+aux.getMarca()+"',";
			statement += " @CantDisponible = "+aux.getCantDisponible()+",";
			statement += " @CantMin = "+aux.getCantMin()+", ";
			statement += " @CantMax = "+aux.getCantMax()+", ";
			statement += " @TipoConexion = '"+((Micro)aux).getTipoConexion()+"', ";
			statement += " @Velocidad = '"+((Micro)aux).getVelocidad()+"' ";

			System.out.println(statement);
			stmt.executeUpdate(statement);

		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void insertUpdateMotherBoard(Componente aux) {
		try (Connection con = DriverManager.getConnection(getConnectionURL()); Statement stmt = con.createStatement();) {
			String statement = "";
			statement = "EXEC SP_Crear_Modificar_Motherboard ";
			statement += " @PrecioVenta = "+aux.getPrecioVentaActual()+", ";
			statement += " @PrecioCompra = "+aux.getPrecioCompraActual()+", ";
			statement += " @CantidadVentas = "+aux.getCantidadVentas()+", ";
			statement += " @Modelo = '"+aux.getModelo()+"',";
			statement += " @NumeroSerie = '"+aux.getNumeroSerie()+"',";
			statement += " @Marca = '"+aux.getMarca()+"',";
			statement += " @CantDisponible = "+aux.getCantDisponible()+",";
			statement += " @CantMin = "+aux.getCantMin()+", ";
			statement += " @CantMax = "+aux.getCantMax()+", ";
			statement += " @TipoConector = '"+((MotherBoard)aux).getTipoConector()+"', ";
			statement += " @TipoRAM = '"+((MotherBoard)aux).getTipoRAM()+"' ";

			System.out.println(statement);
			stmt.executeUpdate(statement);

		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void insertUpdateRAM(Componente aux) {
		try (Connection con = DriverManager.getConnection(getConnectionURL()); Statement stmt = con.createStatement();) {
			String statement = "";
			statement = "EXEC SP_Crear_Modificar_RAM ";
			statement += " @PrecioVenta = "+aux.getPrecioVentaActual()+", ";
			statement += " @PrecioCompra = "+aux.getPrecioCompraActual()+", ";
			statement += " @CantidadVentas = "+aux.getCantidadVentas()+", ";
			statement += " @Modelo = '"+aux.getModelo()+"',";
			statement += " @NumeroSerie = '"+aux.getNumeroSerie()+"',";
			statement += " @Marca = '"+aux.getMarca()+"',";
			statement += " @CantDisponible = "+aux.getCantDisponible()+",";
			statement += " @CantMin = "+aux.getCantMin()+", ";
			statement += " @CantMax = "+aux.getCantMax()+", ";
			statement += " @CantMemoria = '"+((RAM)aux).getCantMemoria()+"', ";
			statement += " @TipoMemoria = '"+((RAM)aux).getTipoMemoria()+"' ";

			System.out.println(statement);
			stmt.executeUpdate(statement);

		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void EliminarUsuario(Persona aux) {
		try (Connection con = DriverManager.getConnection(getConnectionURL()); Statement stmt = con.createStatement();) {
			String statement = "";
			statement = "EXEC SP_Eliminar_Usuario ";
			statement += " @Codigo = '"+aux.getCodigo()+"'";
			System.out.println(statement);
			stmt.executeUpdate(statement);

		}catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void EliminarCliente(Cliente aux) {
		try (Connection con = DriverManager.getConnection(getConnectionURL()); Statement stmt = con.createStatement();) {
			String statement = "";
			statement = "EXEC SP_Eliminar_Cliente ";
			statement += " @Codigo = '"+aux.getCodigo()+"'";
			System.out.println(statement);
			stmt.executeUpdate(statement);

		}catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void EliminarComponente(Componente aux) {
		try (Connection con = DriverManager.getConnection(getConnectionURL()); Statement stmt = con.createStatement();) {
			String statement = "";
			statement = "EXEC SP_Eliminar_Componente ";
			statement += " @NumeroSerie = '"+aux.getNumeroSerie()+"'";
			System.out.println(statement);
			stmt.executeUpdate(statement);

		}catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void insertUpdateCombo(Combo aux) {
		try (Connection con = DriverManager.getConnection(getConnectionURL()); Statement stmt = con.createStatement();) {
			String statement = "";
			statement = "EXEC SP_Crear_Modificar_Combo ";
			statement += " @Nombre = '"+aux.getNombre()+"', ";
			statement += " @Precio = "+aux.getPrecio()+", ";
			

			for (Componente auxComp : aux.getComponentes()) {
				if(auxComp instanceof DiscoDuro) {
					statement += " @NumeroDD = '"+auxComp.getNumeroSerie()+"',";
				}else if(auxComp instanceof Micro) {
					statement += " @NumeroMicro = '"+auxComp.getNumeroSerie()+"',";
				}else if(auxComp instanceof MotherBoard) {
					statement += " @NumeroMother = '"+auxComp.getNumeroSerie()+"',";
				}else if(auxComp instanceof RAM) {
					statement += " @NumeroRAM = '"+auxComp.getNumeroSerie()+"',";
				}
			}
			statement += " @Descuento = "+aux.getDescuento()+" ";

			System.out.println(statement);
			stmt.executeUpdate(statement);

		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void insertFactura(Factura aux) {
		try (Connection con = DriverManager.getConnection(getConnectionURL()); Statement stmt = con.createStatement();) {
			String statement = "";
			statement = "EXEC SP_Crear_Factura ";
			statement += " @Codigo = '"+aux.getCodigo()+"', ";
			statement += " @Costo = "+aux.getCosto()+", ";			
			statement += " @Fecha = '"+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(aux.getFecha())+"', ";
			statement += " @CodCliente = '"+aux.getElCliente().getCodigo()+"', ";
			statement += " @Tipo = "+aux.isTipo()+" ";
			
			System.out.println(statement);
			stmt.executeUpdate(statement);
			
			
			for (Componente auxComp : aux.getLosComponentes()) {
				statement = "";
				statement = "EXEC SP_Factura_Componentes ";
				statement += " @Codigo = '"+aux.getCodigo()+"', ";
				statement += " @NumeroSerieComponente = '"+auxComp.getNumeroSerie()+"', ";
				statement += " @CantidadComponente = "+aux.getCantiCompo(auxComp)+" ";
				
				System.out.println(statement);
				stmt.executeUpdate(statement);
			}
			
			for (Combo auxCombo : aux.getLosCombos()) {
				statement = "";
				statement = "EXEC SP_Factura_Combos ";
				statement += " @Codigo = '"+aux.getCodigo()+"', ";
				statement += " @NombreCombo = '"+auxCombo.getNombre()+"', ";
				statement += " @CantidadCombo = "+aux.getCantiUnCombo(auxCombo)+" ";	
				
				System.out.println(statement);
				stmt.executeUpdate(statement);
			}

		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

