package test.integracion.DAOProducto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import integracion.connection.DatabaseConnection;
import integracion.dao.almacen.DAOAlmacenImp;
import integracion.dao.marca.DAOMarcaImp;
import integracion.dao.producto.DAOProductoImp;
import negocio.almacen.TransferAlmacen;
import negocio.marca.TransferMarca;
import negocio.producto.TransferCalcetines;
import negocio.producto.TransferProducto;
import negocio.producto.TransferZapatillas;

public class TestDAOProducto {
	
	private DAOAlmacenImp daoAlmacen = new DAOAlmacenImp();
	private DAOMarcaImp daoMarca = new DAOMarcaImp();
	private DAOProductoImp daoProducto = new DAOProductoImp();
	
	private TransferAlmacen almacen = new TransferAlmacen(675948395, 754, "Calle de la fortuna");
	private TransferMarca marca = new TransferMarca("Asics");	
	
	@Test
	public void altaZapatillas() {
		int idAlmacen = daoAlmacen.alta(almacen);
		int idMarca = daoMarca.alta(marca);
		
		TransferZapatillas zapatillas = new TransferZapatillas(41, 34.99, "Test", "Test", "Test", 20, idAlmacen, idMarca);
		
		int idProducto = daoProducto.alta(zapatillas);
		
		assertTrue(String.format("Se esperaba >0 y se encontró %s", idProducto), idProducto > 0);
		
		deleteRegister(idProducto, idAlmacen, idMarca);
	}
	
	@Test
	public void altaCalcetines() {
		int idAlmacen = daoAlmacen.alta(almacen);
		int idMarca = daoMarca.alta(marca);
		
		TransferCalcetines calcetines = new TransferCalcetines(41, 34.99, "Test", "Test", 20, idAlmacen, idMarca, "Test");
		
		int idProducto = daoProducto.alta(calcetines);
		
		assertTrue(String.format("Se esperaba >0 y se encontró %s", idProducto), idProducto > 0);
		
		deleteRegister(idProducto, idAlmacen, idMarca);
	}
	
	@Test
	public void bajaProducto() {
		int idAlmacen = daoAlmacen.alta(almacen);
		int idMarca = daoMarca.alta(marca);
		
		TransferCalcetines calcetines = new TransferCalcetines(41, 34.99, "Test", "Test", 20, idAlmacen, idMarca, "Test");
		int idProducto = daoProducto.alta(calcetines);
		
		int baja = daoProducto.bajaProducto(idProducto);
		
		assertEquals(String.format("Se esperaba 1 y se encontró %s", baja), 1, baja);
		
		deleteRegister(idProducto, idAlmacen, idMarca);
	}
	
	@Test
	public void modificarZapatillas() {
		int idAlmacen = daoAlmacen.alta(almacen);
		int idMarca = daoMarca.alta(marca);
		
		TransferZapatillas zapatillas = new TransferZapatillas(41, 34.99, "Test", "Test", "Test", 20, idAlmacen, idMarca);
		int idProducto = daoProducto.alta(zapatillas);
		
		TransferZapatillas modificado = new TransferZapatillas(idProducto, 41, 34.99, "Test", "Test", "Test", 20, idAlmacen, idMarca, true);
		int modificar = daoProducto.modificar(modificado);
		
		assertEquals(String.format("Se esperaba 1 y se encontró %s", modificar), 1, modificar);
		
		deleteRegister(idProducto, idAlmacen, idMarca);
	}
	
	@Test
	public void modificarCaletines() {
		int idAlmacen = daoAlmacen.alta(almacen);
		int idMarca = daoMarca.alta(marca);
		
		TransferCalcetines calcetines = new TransferCalcetines(41, 34.99, "Test", "Test", 20, idAlmacen, idMarca, "Test");		
		int idProducto = daoProducto.alta(calcetines);
		
		TransferCalcetines modificado = new TransferCalcetines(idProducto, 41, 34.99, "Test", "Test", "Test", 20, idAlmacen, idMarca, true);
		int modificar = daoProducto.modificar(modificado);
		
		assertEquals(String.format("Se esperaba 1 y se encontró %s", modificar), 1, modificar);
		
		deleteRegister(idProducto, idAlmacen, idMarca);
	}
	
	@Test
	public void getPrecioProducto() {
		int idAlmacen = daoAlmacen.alta(almacen);
		int idMarca = daoMarca.alta(marca);
		
		TransferZapatillas zapatillas = new TransferZapatillas(41, 34.99, "Test", "Test", "Test", 20, idAlmacen, idMarca);
		int idProducto = daoProducto.alta(zapatillas);
		
		double precio = daoProducto.getPrecioProducto(idProducto);
		
		assertTrue(String.format("Se esperaba %s y se encontró %s", zapatillas.getPrecio(), precio), zapatillas.getPrecio() == precio);
		
		deleteRegister(idProducto, idAlmacen, idMarca);
	}
	
	@Test
	public void getProducto() {
		int idAlmacen = daoAlmacen.alta(almacen);
		int idMarca = daoMarca.alta(marca);
		
		TransferZapatillas zapatillas = new TransferZapatillas(41, 34.99, "Test", "Test", "Test", 20, idAlmacen, idMarca);
		int idProducto = daoProducto.alta(zapatillas);
		
		TransferProducto t1 = daoProducto.getProducto(idProducto);
		TransferProducto t2 = daoProducto.getProducto(zapatillas.getNombre());
		
		areEqual(zapatillas, t1);
		areEqual(zapatillas, t2);
		
		deleteRegister(idProducto, idAlmacen, idMarca);
	}
	
	@Test 
	public void getAllProductos() {
		int idAlmacen = daoAlmacen.alta(almacen);
		int idMarca = daoMarca.alta(marca);
		
		TransferZapatillas zapatillas = new TransferZapatillas(41, 34.99, "Test", "Test", "Test", 20, idAlmacen, idMarca);
		TransferCalcetines calcetines = new TransferCalcetines(41, 34.99, "Test", "Test", 20, idAlmacen, idMarca, "Test");		

		int idProducto1 = daoProducto.alta(zapatillas);
		int idProducto2 = daoProducto.alta(calcetines);
		
		List<TransferProducto> trabajadores = daoProducto.getAllProductos();
		
		assertNotNull(trabajadores);
		
		deleteRegister(idProducto1, idAlmacen, idMarca);
		deleteRegister(idProducto2, idAlmacen, idMarca);
	}
	
	@Test
	public void activarProducto() {
		int idAlmacen = daoAlmacen.alta(almacen);
		int idMarca = daoMarca.alta(marca);
		
		TransferZapatillas zapatillas = new TransferZapatillas(41, 34.99, "Test", "Test", "Test", 20, idAlmacen, idMarca);
		int idProducto = daoProducto.alta(zapatillas);
		
		TransferZapatillas modificado = new TransferZapatillas(idProducto, 41, 34.99, "Test", "Test", "Test", 20, idAlmacen, idMarca, false);
		int modificar = daoProducto.modificar(modificado);
		
		assertEquals(String.format("Se esperaba 1 y se encontró %s", modificar), 1, modificar);
		
		daoProducto.activarProducto(idProducto);
		
		TransferProducto t = daoProducto.getProducto(idProducto);
		
		assertTrue(String.format("Se esperata true y se obtuvo %b", t.getActivo()), t.getActivo() == true);
		
		deleteRegister(idProducto, idAlmacen, idMarca);
	}
	
	@Test
	public void restarStock() {
		int idAlmacen = daoAlmacen.alta(almacen);
		int idMarca = daoMarca.alta(marca);
		
		TransferZapatillas zapatillas = new TransferZapatillas(41, 34.99, "Test", "Test", "Test", 20, idAlmacen, idMarca);
		int idProducto = daoProducto.alta(zapatillas);
		
		daoProducto.restarStock(idProducto, 10);
		
		TransferProducto t = daoProducto.getProducto(idProducto);
		
		assertEquals(String.format("Se esperata 10 y se obtuvo %d", t.getStock()), 10, t.getStock());
		
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
