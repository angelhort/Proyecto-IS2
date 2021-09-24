package test.negocio.SAVenta;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import integracion.connection.DatabaseConnection;
import negocio.almacen.SAAlmacenImp;
import negocio.almacen.TransferAlmacen;
import negocio.cliente.SAClienteImp;
import negocio.cliente.TransferCliente;
import negocio.marca.SAMarcaImp;
import negocio.marca.TransferMarca;
import negocio.producto.SAProductoImp;
import negocio.producto.TransferZapatillas;
import negocio.trabajador.SATrabajadorImp;
import negocio.trabajador.TransferTrabajador;
import negocio.venta.SAVentaImp;
import negocio.venta.TProductoEnFactura;
import negocio.venta.TransferVenta;

public class TestSAVenta {

	SAClienteImp saCliente = new SAClienteImp();
	SATrabajadorImp saTrabajador = new SATrabajadorImp();
	SAAlmacenImp saAlmacen = new SAAlmacenImp();
	SAMarcaImp saMarca = new SAMarcaImp();
	SAProductoImp saProducto = new SAProductoImp();
	SAVentaImp saVenta = new SAVentaImp();
	
	@Test
	public void altaOK() {
		TransferCliente cliente = new TransferCliente("Test", true, "54736485C");
		int idCliente = saCliente.alta(cliente);
		
		TransferTrabajador trabajador = new TransferTrabajador(657342333, "34323454N", "TestNombre");
		int idTrabajador = saTrabajador.alta(trabajador);
		
		TransferAlmacen almacen = new TransferAlmacen(666777888, 23, "Calle test");
		int idAlmacen = saAlmacen.alta(almacen);
		
		TransferMarca marca = new TransferMarca("Test");
		int idMarca = saMarca.alta(marca);
		
		TransferZapatillas zapatillas = new TransferZapatillas(41, 34.99, "Test", "Test", "Test", 20, idAlmacen, idMarca);
		int idProducto = saProducto.alta(zapatillas);
		
		TransferVenta venta = new TransferVenta(saCliente.mostrarUno(idCliente), saTrabajador.mostrarUno(idTrabajador));
		
		TProductoEnFactura p = new TProductoEnFactura(saProducto.mostrarUno(idProducto), 3); 
		p.setPrecio(33.44);
		venta.addProduct(p);
		venta.setPrecioTotal();
		
		int idVenta = saVenta.altaVenta(venta);
		
		assertTrue(String.format("Se esperaba >0 y se encontró %s", idVenta), idVenta > 0);
		
		deleteRegister(idProducto, idAlmacen, idMarca, idTrabajador, idCliente, idVenta);
	}
	
	@Test
	public void altaFail() {		
		TransferCliente cliente = new TransferCliente("Test", true, "54736485C");
		int idCliente = saCliente.alta(cliente);
		
		TransferTrabajador trabajador = new TransferTrabajador(657342333, "34323454N", "TestNombre");
		int idTrabajador = saTrabajador.alta(trabajador);
		
		TransferAlmacen almacen = new TransferAlmacen(666777888, 23, "Calle test");
		int idAlmacen = saAlmacen.alta(almacen);
		
		TransferMarca marca = new TransferMarca("Test");
		int idMarca = saMarca.alta(marca);
		
		TransferZapatillas zapatillas = new TransferZapatillas(41, 34.99, "Test", "Test", "Test", 20, idAlmacen, idMarca);
		int idProducto = saProducto.alta(zapatillas);
		
		TransferVenta v1 = new TransferVenta(new TransferCliente(-1), saTrabajador.mostrarUno(idTrabajador));
		
		TProductoEnFactura p = new TProductoEnFactura(saProducto.mostrarUno(idProducto), 3); 
		p.setPrecio(33.44);
		v1.addProduct(p);
		v1.setPrecioTotal();
		
		int idVenta = saVenta.altaVenta(v1);
		
		assertEquals(String.format("Se esperaba -2 y se encontró %s", idVenta), -2, idVenta);
		
		TransferVenta v2 = new TransferVenta(saCliente.mostrarUno(idCliente), new TransferTrabajador(-1));
		int idVenta2 = saVenta.altaVenta(v2);
		
		assertEquals(String.format("Se esperaba -2 y se encontró %s", idVenta2), -2, idVenta2);
		
		deleteRegister(idProducto, idAlmacen, idMarca, idTrabajador, idCliente, idVenta);
	}
	
	private void deleteRegister(int idProducto, int idAlmacen, int idMarca, int idTrabajador, int idCliente, int idFactura) {
		String sql1 = "DELETE FROM Productos WHERE idProducto=?";
		String sql2 = "DELETE FROM Almacen WHERE idAlmacen=?";
		String sql3 = "DELETE FROM Marca WHERE idMarca=?";
		String sql4 = "DELETE FROM Trabajadores WHERE idTrabajador=?";
		String sql5 = "DELETE FROM Clientes WHERE idCliente=?";
		String sql6 = "DELETE FROM Facturas WHERE idFactura=?";
		
		DatabaseConnection.dropRegisterWithID(sql1, idProducto);
		DatabaseConnection.dropRegisterWithID(sql6, idFactura);
		DatabaseConnection.dropRegisterWithID(sql2, idAlmacen);
		DatabaseConnection.dropRegisterWithID(sql3, idMarca);
		DatabaseConnection.dropRegisterWithID(sql4, idTrabajador);
		DatabaseConnection.dropRegisterWithID(sql5, idCliente);
	}
}
