package test.negocio.SAProducto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import integracion.connection.DatabaseConnection;
import negocio.almacen.SAAlmacenImp;
import negocio.almacen.TransferAlmacen;
import negocio.marca.SAMarcaImp;
import negocio.marca.TransferMarca;
import negocio.producto.SAProductoImp;
import negocio.producto.TransferProducto;
import negocio.producto.TransferZapatillas;

public class TestSAProducto {

	private SAAlmacenImp saAlmacen = new SAAlmacenImp();
	private SAMarcaImp saMarca = new SAMarcaImp();
	private SAProductoImp saProducto = new SAProductoImp();
	
	private TransferAlmacen almacen = new TransferAlmacen(675948395, 754, "Calle de la fortuna");
	private TransferMarca marca = new TransferMarca("Asics");
	
	@Test
	public void altaOK() {
		int idAlmacen = saAlmacen.alta(almacen);
		int idMarca = saMarca.alta(marca);
		
		TransferProducto zapatillas = new TransferZapatillas(41, 34.99, "Test", "Test", "Test", 20, idAlmacen, idMarca);
		
		int idProducto = saProducto.alta(zapatillas);
		
		assertTrue(String.format("Se esperaba >0 y se encontró %s", idProducto), idProducto > 0);
		
		deleteRegister(idProducto, idAlmacen, idMarca);
	}
	
	@Test
	public void altaFail() {
		int idAlmacen = saAlmacen.alta(almacen);
		int idMarca = saMarca.alta(marca);
		
		TransferProducto zapatillas = new TransferZapatillas(41, 34.99999, "Test", "Test", "Test", 20, idAlmacen, idMarca);
		
		int idProducto = saProducto.alta(zapatillas);
		
		assertEquals(String.format("Se esperaba -1 y se encontró %s", idProducto), -1, idProducto);
		
		deleteRegister(idProducto, idAlmacen, idMarca);
	}
	
	@Test
	public void borrarOK() {
		int idAlmacen = saAlmacen.alta(almacen);
		int idMarca = saMarca.alta(marca);
		
		TransferProducto zapatillas = new TransferZapatillas(41, 34.99, "Test", "Test", "Test", 20, idAlmacen, idMarca);
		
		int idProducto = saProducto.alta(zapatillas);
		
		int baja = saProducto.borrar(idProducto);
		
		assertEquals(String.format("Se esperaba 1 y se encontró %s", baja), 1, baja);
		
		deleteRegister(idProducto, idAlmacen, idMarca);
	}
	
	@Test
	public void borrarFail() {
		int idAlmacen = saAlmacen.alta(almacen);
		int idMarca = saMarca.alta(marca);
		
		TransferProducto zapatillas = new TransferZapatillas(41, 34.99, "Test", "Test", "Test", 20, idAlmacen, idMarca);
		
		int idProducto = saProducto.alta(zapatillas);
				
		int baja = saProducto.borrar(idProducto);
		
		assertEquals(String.format("Se esperaba 1 y se encontró %s", baja), 1, baja);
		
		deleteRegister(idProducto, idAlmacen, idMarca);
	}

	@Test
	public void modificarOK() {
		int idAlmacen = saAlmacen.alta(almacen);
		int idMarca = saMarca.alta(marca);
		
		TransferProducto zapatillas = new TransferZapatillas(41, 34.99, "Test", "Test", "Test", 20, idAlmacen, idMarca);
		int idProducto = saProducto.alta(zapatillas);
		
		TransferProducto t = new TransferZapatillas(idProducto, 41, 3.4, "Test", "Test", "Test", 20, idAlmacen, idMarca, false);		
		int modificado = saProducto.modificar(t);
		
		assertEquals(String.format("Se esperaba 1 y se encontró %s", idProducto), 1, modificado);
		
		deleteRegister(idProducto, idAlmacen, idMarca);
	}
	
	@Test
	public void modificarFail() {
		TransferProducto t = new TransferZapatillas(-1, 41, 3.4, "Test", "Test", "Test", 20, -1, -1, false);		
		int modificado = saProducto.modificar(t);
		
		assertEquals(String.format("Se esperaba -1 y se encontró %s", modificado), -1, modificado);
		
		TransferProducto t2= new TransferZapatillas(-1, 41, 3.444, "Test", "Test", "Test", 20, -1, -1, false);		
		int modificado2 = saProducto.modificar(t2);
		
		assertEquals(String.format("Se esperaba -1 y se encontró %s", modificado2), -1, modificado2);
	}
	
	@Test
	public void mostrarUno() {
		int idAlmacen = saAlmacen.alta(almacen);
		int idMarca = saMarca.alta(marca);
		
		TransferProducto zapatillas = new TransferZapatillas(41, 34.99, "Test", "Test", "Test", 20, idAlmacen, idMarca);
		
		int idProducto = saProducto.alta(zapatillas);
		
		TransferProducto t2 = saProducto.mostrarUno(idProducto);
		areEqual(zapatillas, t2);
		
		deleteRegister(idProducto, idAlmacen, idMarca);
	}
	
	@Test
	public void mostrarTodos() {
		int idAlmacen = saAlmacen.alta(almacen);
		int idMarca = saMarca.alta(marca);
		
		TransferProducto zapatillas = new TransferZapatillas(41, 34.99, "Test", "Test", "Test", 20, idAlmacen, idMarca);
		int idProducto = saProducto.alta(zapatillas);
		
		List<TransferProducto> productos = saProducto.mostrarTodos();
		assertNotNull(productos);
		
		deleteRegister(idProducto, idAlmacen, idMarca);
	}

	private void areEqual(TransferProducto t1, TransferProducto t2) {
		assertEquals(t1.getTalla(), t2.getTalla());
		assertTrue(t1.getPrecio() == t2.getPrecio());
		assertEquals(t1.getNombre(), t2.getNombre());
		assertEquals(t1.getColor(), t2.getColor());
		assertEquals(t1.getStock(), t2.getStock());
	}
	
	private void deleteRegister(int idProducto, int idAlmacen, int idMarca) {
		String sql1 = "DELETE FROM Productos WHERE idProducto=?";
		String sql2 = "DELETE FROM Almacen WHERE idAlmacen=?";
		String sql3 = "DELETE FROM Marca WHERE idMarca=?";
		
		DatabaseConnection.dropRegisterWithID(sql1, idProducto);
		DatabaseConnection.dropRegisterWithID(sql2, idAlmacen);
		DatabaseConnection.dropRegisterWithID(sql3, idMarca);
	}

}
