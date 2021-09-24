package test.integracion.DAOVenta;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import integracion.connection.DatabaseConnection;
import integracion.dao.almacen.DAOAlmacenImp;
import integracion.dao.cliente.DAOClienteImp;
import integracion.dao.marca.DAOMarcaImp;
import integracion.dao.producto.DAOProductoImp;
import integracion.dao.trabajador.DAOTrabajadorImp;
import integracion.dao.venta.DAOVentaImp;
import negocio.almacen.TransferAlmacen;
import negocio.cliente.TransferCliente;
import negocio.marca.TransferMarca;
import negocio.producto.TransferZapatillas;
import negocio.trabajador.TransferTrabajador;
import negocio.venta.TProductoEnFactura;
import negocio.venta.TransferVenta;

public class TestDAOVenta {
	
	DAOClienteImp daoCliente = new DAOClienteImp();
	DAOTrabajadorImp daoTrabajador = new DAOTrabajadorImp();
	DAOAlmacenImp daoAlmacen = new DAOAlmacenImp();
	DAOMarcaImp daoMarca = new DAOMarcaImp();
	DAOProductoImp daoProducto = new DAOProductoImp();
	DAOVentaImp daoVenta = new DAOVentaImp();

	@Test
	public void alta() {
		TransferCliente cliente = new TransferCliente("Test", true, "Test");
		int idCliente = daoCliente.alta(cliente);
		
		TransferTrabajador trabajador = new TransferTrabajador(666666666, "00000000W", "Test");
		int idTrabajador = daoTrabajador.alta(trabajador);
		
		TransferAlmacen almacen = new TransferAlmacen(675948395, 754, "Test");
		int idAlmacen = daoAlmacen.alta(almacen);
		
		TransferMarca marca = new TransferMarca("Test");
		int idMarca = daoMarca.alta(marca);
		
		TransferZapatillas zapatillas = new TransferZapatillas(41, 34.99, "Test", "Test", "Test", 20, idAlmacen, idMarca);
		int idProducto = daoProducto.alta(zapatillas);
		
		TransferVenta venta = new TransferVenta(daoCliente.getCliente(idCliente), daoTrabajador.getTrabajador(idTrabajador));
		
		TProductoEnFactura p = new TProductoEnFactura(daoProducto.getProducto(idProducto), 3); 
		p.setPrecio(33.44);
		venta.addProduct(p);
		venta.setPrecioTotal();
		
		int idVenta = daoVenta.alta(venta);
		
		assertTrue(String.format("Se esperaba >0 y se encontró %s", idVenta), idVenta > 0);
		
		deleteRegister(idProducto, idAlmacen, idMarca, idTrabajador, idCliente, idVenta);
	}
	
	@Test
	public void getVenta() {
		TransferCliente cliente = new TransferCliente("Test", true, "Test");
		int idCliente = daoCliente.alta(cliente);
		
		TransferTrabajador trabajador = new TransferTrabajador(666666666, "00000000W", "Test");
		int idTrabajador = daoTrabajador.alta(trabajador);
		
		TransferAlmacen almacen = new TransferAlmacen(675948395, 754, "Test");
		int idAlmacen = daoAlmacen.alta(almacen);
		
		TransferMarca marca = new TransferMarca("Test");
		int idMarca = daoMarca.alta(marca);
		
		TransferZapatillas zapatillas = new TransferZapatillas(41, 34.99, "Test", "Test", "Test", 20, idAlmacen, idMarca);
		int idProducto = daoProducto.alta(zapatillas);
		
		TransferVenta venta = new TransferVenta(daoCliente.getCliente(idCliente), daoTrabajador.getTrabajador(idTrabajador));
		
		TProductoEnFactura p = new TProductoEnFactura(daoProducto.getProducto(idProducto), 3); 
		p.setPrecio(33.44);
		venta.addProduct(p);
		venta.setPrecioTotal();
		
		int idVenta = daoVenta.alta(venta);
		
		daoVenta.getVenta(idVenta);
		
		assertNotNull(idVenta);
		
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
