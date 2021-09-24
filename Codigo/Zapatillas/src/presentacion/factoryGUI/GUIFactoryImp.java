package presentacion.factoryGUI;

import presentacion.VComunes.*;
import presentacion.almacen.*;
import presentacion.cliente.*;
import presentacion.controller.Evento;
import presentacion.marca.*;
import presentacion.producto.*;
import presentacion.proveedor.*;
import presentacion.proveedor_producto.*;
import presentacion.trabajador.*;
import presentacion.venta.*;
import presentacion.vista.GUIPrincipal;
import presentacion.vista.IGUI;

public class GUIFactoryImp extends GUIFactory{

	@Override
	public IGUI getFrame(int evento) {
		switch(evento) {
		//GUIPrincipal
		case Evento.MostrarGUIPrincipal:
			return new GUIPrincipal();
		
		//CLIENTE
		case Evento.MostrarGUICliente:
			return new GUICliente();
		case Evento.GUIAltaCliente:
		case Evento.AltaCliente:
			return new VAltaCliente();
		case Evento.BajaCliente:
		case Evento.GUIBajaCliente:
			return new VBajaCliente();
		case Evento.GUIMostrarUnCliente:
			return new VMostrarUno("Cliente");
		case Evento.GUIModificarCliente:
			return new VModificar("Cliente");
		case Evento.ModificarCliente:
		case Evento.MostrarModificarCliente:
			return new VModificarCliente();
		case Evento.MostrarUnCliente:
			return new VMostrarUnCliente();
		case Evento.MostrarTodosLosClientes:
			return new VMostrarTodosLosClientes();
		
		//TRABAJADOR
		case Evento.MostrarGUITrabajador:
			return new GUITrabajador();
		case Evento.GUIAltaTrabajador:
		case Evento.AltaTrabajador:
			return new VAltaTrabajador();
		case Evento.GUIBajaTrabajador:
		case Evento.BajaTrabajador:
			return new VBajaTrabajador();
		case Evento.GUIMostrarUnTrabajador:
			return new VMostrarUno("Trabajador");
		case Evento.GUIModificarTrabajador:
			return new VModificar("Trabajador");
		case Evento.MostrarTodosLosTrabajadores:
			return new VMostrarTodosLosTrabajadores();
		case Evento.ModificarTrabajador:
		case Evento.MostrarModificarTrabajador:
			return new VModificarTrabajador();
		case Evento.MostrarUnTrabajador:
			return new VMostrarUnTrabajador();
		
		//VENTA
		case Evento.MostrarGUIVenta:
		case Evento.CerrarVenta:
			return new GUIVenta();
		case Evento.GUIAbrirVenta:
			return new VAbrirVenta();
		case Evento.MostrarCarrito:
			return new VCarrito();
		case Evento.MostrarVenta:
			return new VMostrarVenta();
		
		//ALMACEN
		case Evento.MostrarGUIAlmacen:
			return new GUIAlmacen();
		case Evento.GUIAltaAlmacen:
		case Evento.AltaAlmacen:
			return new VAltaAlmacen();
		case Evento.GUIBajaAlmacen:
		case Evento.BajaAlmacen:
			return new VBajaAlmacen();
		case Evento.GUIMostrarUnAlmacen:
			return new VMostrarUno("Almacen");
		case Evento.GUIModificarAlmacen:
			return new VModificar("Almacen");
		case Evento.MostrarTodosLosAlmacenes:
			return new VMostrarTodosLosAlmacenes();
		case Evento.MostrarModificarAlmacen:
		case Evento.ModificarAlmacen:
			return new VModificarAlmacen();
		case Evento.MostrarUnAlmacen:
			return new VMostrarUnAlmacen();
			
		//PRODUCTO
		case Evento.MostrarGUIProducto:
			return new GUIProducto();
		case Evento.GUIAltaProducto:
		case Evento.AltaProducto:
			return new VAltaProducto();
		case Evento.GUIBajaProducto:
		case Evento.BajaProducto:
			return new VBajaProducto();
		case Evento.GUIMostrarUnProducto:
			return new VMostrarUno("Producto");
		case Evento.GUIModificarProducto:
			return new VModificar("Producto");
		case Evento.MostrarTodosLosProductos:
			return new VMostrarTodosLosProductos();
		case Evento.MostrarUnProducto:
			return new VMostrarUnProducto();
		case Evento.MostrarModificarProducto:
		case Evento.ModificarProducto:
			return new VModificarProducto();
		
		//PROVEEDOR
		case Evento.MostrarGUIProveedor:
			return new GUIProveedor();
		case Evento.GUIAltaProveedor:
		case Evento.AltaProveedor:
			return new VAltaProveedor();
		case Evento.GUIBajaProveedor:
		case Evento.BajaProveedor:
			return new VBajaProveedor();
		case Evento.GUIMostrarUnProveedor:
			return new VMostrarUno("Proveedor");
		case Evento.GUIModificarProveedor:
			return new VModificar("Proveedor");
		case Evento.MostrarUnProveedor:
			return new VMostrarUnProveedor();
		case Evento.MostrarTodosLosProveedores:
			return new VMostrarTodosLosProveedores();
		case Evento.MostrarModificarProveedor:
		case Evento.ModificarProveedor:
			return new VModificarProveedor();
		
		//MARCA
		case Evento.MostrarGUIMarca:
			return new GUIMarca();
		case Evento.GUIAltaMarca:
		case Evento.AltaMarca:
			return new VAltaMarca();
		case Evento.BajaMarca:
		case Evento.GUIBajaMarca:
			return new VBajaMarca();
		case Evento.GUIMostrarUnaMarca:
			return new VMostrarUno("Marca");
		case Evento.GUIModificarMarca:
			return new VModificar("Marca");
		case Evento.ModificarMarca:
		case Evento.MostrarModificarMarca:
			return new VModificarMarca();
		case Evento.MostrarUnaMarca:
			return new VMostrarUnaMarca();
		case Evento.MostrarTodasLasMarcas:
			return new VMostrarTodasLasMarcas();
		
		//PROVEEDOR-PRODUCTO
		case Evento.MostrarGUIProveedorProducto:
			return new GUIProveedorProducto();
		case Evento.GUIAniadirProveedor:
		case Evento.AniadirProveedor:
			return new VAniadirProveedor();
		case Evento.EliminarProveedor:
		case Evento.GUIEliminarProveedor:
			return new VEliminarProveedor();
		case Evento.MostrarProductosProveedor:
			return new VProductosProveedor();
		}
		return null;
	}

}
