package test.negocio.SAProveedor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import integracion.connection.DatabaseConnection;
import negocio.proveedor.SAProveedorImp;
import negocio.proveedor.TransferProveedor;


public class TestSAProveedor {
	private SAProveedorImp saProveedor = new SAProveedorImp();
	private TransferProveedor transferProveedor = new TransferProveedor(746372875, "Calle test", "TESTProveedor");
		
	@Test
	public void altaOK() {		
		int id = saProveedor.alta(transferProveedor);

		assertTrue(String.format("Se esperaba >0 y se encontró %s", id), id >= 1);
		
		deleteRegister(id);
	}
	
	@Test
	public void altaFail() {
		TransferProveedor t1 = new TransferProveedor(0, "Calle test2", "TestProveedor2");
		
		int id1 = saProveedor.alta(t1);
		
		assertEquals(String.format("Se esperaba -1 y se encontró %s", id1), -1, id1);	
	}
	
	@Test
	public void borrarOK() {
		int id = saProveedor.alta(transferProveedor);

		int idBaja = saProveedor.borrar(id);
		
		assertEquals(String.format("Se esperaba 1 y se encontró %s", idBaja), 1, idBaja);
		
		deleteRegister(id);
	}
	
	@Test
	public void borrarFail() {
		int idBaja = saProveedor.borrar(-1);
		
		assertEquals(String.format("Se esperaba -2 y se encontró %s", idBaja), -2, idBaja);	
	}
	
	@Test
	public void modificarOK() {
		int id = saProveedor.alta(transferProveedor);

		TransferProveedor t1 = new TransferProveedor(id, 657473647, "Calle test3", "testProveedor4", true);
		int idModificar = saProveedor.modificar(t1);
		
		assertEquals(String.format("Se esperaba 1 y se encontró %s", idModificar), 1, idModificar);	
	
		deleteRegister(id);
	}
	
	@Test
	public void modificarFail() {
		TransferProveedor t1 = new TransferProveedor(-1, 56, "Calle test", "testProveedor", true);
		int idModificar1 = saProveedor.modificar(t1);
		
		assertEquals(String.format("Se esperaba -1 y se encontró %s", idModificar1), -1, idModificar1);
		
		TransferProveedor t2 = new TransferProveedor(-1, 657483774, "Calle test", "TestProveedor", true);
		int idModificar2 = saProveedor.modificar(t2);
		
		assertEquals(String.format("Se esperaba 0 y se encontró %s", idModificar2), 0, idModificar2);
	}
	
	@Test
	public void mostrarUno() {
		int id = saProveedor.alta(transferProveedor);
		
		TransferProveedor t2 = saProveedor.mostrarUno(id);
		areEqual(transferProveedor, t2);
		
		deleteRegister(id);
	}
	
	@Test
	public void mostrarTodos() {		
		int id = saProveedor.alta(transferProveedor);
		
		List<TransferProveedor> proveedores = saProveedor.mostrarTodos();
		
		assertNotNull(proveedores);
		
		deleteRegister(id);
	}
	
	private void areEqual(TransferProveedor p1, TransferProveedor p2) {
		assertEquals(p1.getTelefono(), p2.getTelefono());
		assertEquals(p1.getDireccion(), p2.getDireccion());
		assertEquals(p1.getNombre(), p2.getNombre());
	}
	
	private void deleteRegister(int ID) {
		String sql = "DELETE FROM Proveedores WHERE idProveedor=?";
		DatabaseConnection.dropRegisterWithID(sql, ID);
	}
	
}
