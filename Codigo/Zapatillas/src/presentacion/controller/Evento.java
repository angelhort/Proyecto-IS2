package presentacion.controller;

public class Evento {
	
	// GENERAL DE -2-99
	public static final int EntidadesAsociadas = -5; //No se ha podido dar de baja una entidad porque tiene otras asociadas que estan activas
	public static final int OutOfStock = -4;
	public static final int ClaveEntidadYaExistente = -3;
	public static final int EntidadSiNoExiste = -2;
	public static final int WrongDataInput = -1;
	public static final int ActivacionEntidad = 0;
	public static final int MostrarGUIPrincipal = 1;
	
	
	// CLIENTE DE 100-199
	public static final int MostrarGUICliente = 100;
	public static final int GUIAltaCliente = 101;
	public static final int GUIBajaCliente = 102;
	public static final int GUIModificarCliente = 103;
	public static final int GUIMostrarUnCliente = 104;
	public static final int MostrarModificarCliente = 105;
	
	public static final int AltaCliente = 110;
	public static final int BajaCliente = 111;
	public static final int ModificarCliente = 112;
	public static final int MostrarUnCliente = 113;
	public static final int MostrarTodosLosClientes = 114; 
	
	
	// TRABAJADOR DE 200-299
	
	public static final int MostrarGUITrabajador = 200;
	public static final int GUIAltaTrabajador= 201;
	public static final int GUIBajaTrabajador = 202;
	public static final int GUIModificarTrabajador = 203;
	public static final int GUIMostrarUnTrabajador = 204;
	public static final int MostrarModificarTrabajador = 205;
	
	public static final int AltaTrabajador = 210;
	public static final int BajaTrabajador = 211;
	public static final int ModificarTrabajador = 212;
	public static final int MostrarUnTrabajador = 213;
	public static final int MostrarTodosLosTrabajadores = 214;
	
	
	// VENTA DE 300-399
	
	public static final int MostrarGUIVenta = 300;
	public static final int GUIAbrirVenta = 301;
	public static final int CerrarVenta = 302;
	public static final int MostrarCarrito = 303;
	public static final int MostrarVenta = 304;
	
	
	// ALMACEN DE 400-499
	
	public static final int MostrarGUIAlmacen = 400;
	public static final int GUIAltaAlmacen= 401;
	public static final int GUIBajaAlmacen = 402;
	public static final int GUIModificarAlmacen = 403;
	public static final int GUIMostrarUnAlmacen = 404;
	public static final int MostrarModificarAlmacen = 405;
	
	public static final int AltaAlmacen = 410;
	public static final int BajaAlmacen = 411;
	public static final int ModificarAlmacen = 412;
	public static final int MostrarUnAlmacen = 413;
	public static final int MostrarTodosLosAlmacenes = 414;
	
	
	// PRODUCTO DE 500-599
	
	public static final int MostrarGUIProducto = 500;
	public static final int GUIAltaProducto = 501;
	public static final int GUIBajaProducto = 502;
	public static final int GUIModificarProducto = 503;
	public static final int GUIMostrarUnProducto = 504;
	public static final int MostrarModificarProducto = 505;
	public static final int ProductoOK = 506;
	public static final int ProductoKO = 507;
	
	public static final int AltaProducto = 510;
	public static final int BajaProducto = 511;
	public static final int ModificarProducto = 512;
	public static final int MostrarUnProducto = 513;
	public static final int MostrarTodosLosProductos = 514;
	
	
	// PROVEEDOR DE 600-699
	
	public static final int MostrarGUIProveedor = 600;
	public static final int GUIAltaProveedor = 601;
	public static final int GUIBajaProveedor = 602;
	public static final int GUIModificarProveedor = 603;
	public static final int GUIMostrarUnProveedor = 604;
	public static final int MostrarModificarProveedor = 605;	
	
	public static final int AltaProveedor = 610;
	public static final int BajaProveedor = 611;
	public static final int ModificarProveedor = 612;
	public static final int MostrarUnProveedor = 613;
	public static final int MostrarTodosLosProveedores = 614;
	
	
	// MARCA DE 700-799
	
	public static final int MostrarGUIMarca = 700;
	public static final int GUIAltaMarca = 701;
	public static final int GUIBajaMarca = 702;
	public static final int GUIModificarMarca = 703;
	public static final int GUIMostrarUnaMarca = 704;
	public static final int MostrarModificarMarca = 705;
	
	public static final int AltaMarca = 710;
	public static final int BajaMarca = 711;
	public static final int ModificarMarca = 712;
	public static final int MostrarUnaMarca = 713;
	public static final int MostrarTodasLasMarcas = 714;
	
	
	//PROVEEDOR-PRODUCTP DE 800-899
	
	public static final int MostrarGUIProveedorProducto = 800;
	public static final int GUIAniadirProveedor = 801;
	public static final int GUIEliminarProveedor = 802;
	public static final int GUIMostrarProductosProveedor = 803;
	
	public static final int AniadirProveedor = 810;
	public static final int EliminarProveedor = 811;
	public static final int MostrarProductosProveedor = 812;
}
