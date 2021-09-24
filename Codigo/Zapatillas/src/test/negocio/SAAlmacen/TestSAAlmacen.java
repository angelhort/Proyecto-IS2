package test.negocio.SAAlmacen;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import integracion.connection.DatabaseConnection;
import negocio.almacen.SAAlmacenImp;
import negocio.almacen.TransferAlmacen;

public class TestSAAlmacen {
	
	private SAAlmacenImp saAlmacen = new SAAlmacenImp();
	private TransferAlmacen transferAlmacen = new TransferAlmacen(666777888, 23, "Calle test");
		
	@Test
	public void altaOK() {		
		int id = saAlmacen.alta(transferAlmacen);

		assertTrue(String.format("Se esperaba >0 y se encontró %s", id), id >= 1);
		
		deleteRegister(id);
	}
	
	@Test
	public void altaFail() {
		TransferAlmacen t1 = new TransferAlmacen(0, 23, "Calle test");
		
		int id1 = saAlmacen.alta(t1);
		
		assertEquals(String.format("Se esperaba -1 y se encontró %s", id1), -1, id1);	
	}
	
	@Test
	public void borrarOK() {
		int id = saAlmacen.alta(transferAlmacen);

		int idBaja = saAlmacen.borrar(id);
		
		assertEquals(String.format("Se esperaba 1 y se encontró %s", idBaja), 1, idBaja);
		
		deleteRegister(id);
	}
	
	@Test
	public void borrarFail() {
		int idBaja = saAlmacen.borrar(-1);
		
		assertEquals(String.format("Se esperaba -2 y se encontró %s", idBaja), -2, idBaja);	
	}
	
	@Test
	public void modificarOK() {
		int id = saAlmacen.alta(transferAlmacen);

		TransferAlmacen t1 = new TransferAlmacen(id, 111222333, 32, "Calle Otra", true);
		int idModificar = saAlmacen.modificar(t1);
		
		assertEquals(String.format("Se esperaba 1 y se encontró %s", idModificar), 1, idModificar);	
	
		deleteRegister(id);
	}
	
	@Test
	public void modificarFail() {
		TransferAlmacen t1 = new TransferAlmacen(-1, 111222333, 32, "Calle Otra", true);
		int idModificar1 = saAlmacen.modificar(t1);
		
		assertEquals(String.format("Se esperaba 0 y se encontró %s", idModificar1), 0, idModificar1);
		
		TransferAlmacen t2 = new TransferAlmacen(-1, 0, 32, "Calle Otra", true);
		int idModificar2 = saAlmacen.modificar(t2);
		
		assertEquals(String.format("Se esperaba -1 y se encontró %s", idModificar2), -1, idModificar2);	
	}
	
	@Test
	public void mostrarUno() {
		int id = saAlmacen.alta(transferAlmacen);
		
		TransferAlmacen t2 = saAlmacen.mostrarUno(id);
		areEqual(transferAlmacen, t2);
		
		deleteRegister(id);
	}
	
	@Test
	public void mostrarTodos() {		
		int id = saAlmacen.alta(transferAlmacen);
		
		List<TransferAlmacen> almacenes = saAlmacen.mostrarTodos();
		
		assertNotNull(almacenes);
		
		deleteRegister(id);
	}
	
	private void areEqual(TransferAlmacen a1, TransferAlmacen a2) {
		assertEquals(a1.getTelefono(), a2.getTelefono());
		assertEquals(a1.getCapacidad(), a2.getCapacidad());
		assertEquals(a1.getDireccion(), a2.getDireccion());
	}
	
	private void deleteRegister(int ID) {
		String sql = "DELETE FROM Almacen WHERE idAlmacen=?";
		DatabaseConnection.dropRegisterWithID(sql, ID);
	}
}