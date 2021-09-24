package test.negocio.SACliente;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import integracion.connection.DatabaseConnection;
import negocio.cliente.SAClienteImp;
import negocio.cliente.TransferCliente;

public class TestSACliente {
	
	private SAClienteImp saCliente = new SAClienteImp();
	private TransferCliente transferCliente = new TransferCliente("Test", true, "54736485C");
		
	@Test
	public void altaOK() {		
		int id = saCliente.alta(transferCliente);

		assertTrue(String.format("Se esperaba >0 y se encontró %s", id), id >= 1);
		
		deleteRegister(id);
	}
	
	@Test
	public void altaFail() {
		TransferCliente t1 = new TransferCliente("Test", true, "674");
		
		int id1 = saCliente.alta(t1);
		
		assertEquals(String.format("Se esperaba -1 y se encontró %s", id1), -1, id1);	
	}
	
	@Test
	public void borrarOK() {
		int id = saCliente.alta(transferCliente);

		int idBaja = saCliente.borrar(id);
		
		assertEquals(String.format("Se esperaba 1 y se encontró %s", idBaja), 1, idBaja);
		
		deleteRegister(id);
	}
	
	@Test
	public void borrarFail() {
		int idBaja = saCliente.borrar(-1);
		
		assertEquals(String.format("Se esperaba -2 y se encontró %s", idBaja), -2, idBaja);	
	}
	
	@Test
	public void modificarOK() {
		int id = saCliente.alta(transferCliente);

		TransferCliente t1 = new TransferCliente(id, "Test2", false, "36764737M", true);
		int idModificar = saCliente.modificar(t1);
		
		assertEquals(String.format("Se esperaba 1 y se encontró %s", idModificar), 1, idModificar);	
	
		deleteRegister(id);
	}
	
	@Test
	public void modificarFail() {
		TransferCliente t1 = new TransferCliente(-1, "Test2", false, "97X", true);
		int idModificar1 = saCliente.modificar(t1);
		
		assertEquals(String.format("Se esperaba -1 y se encontró %s", idModificar1), -1, idModificar1);
		
		TransferCliente t2 = new TransferCliente(-1, "Test2", false, "36764737M", true);
		int idModificar2 = saCliente.modificar(t2);
		
		assertEquals(String.format("Se esperaba 0 y se encontró %s", idModificar2), 0, idModificar2);
	}
	
	@Test
	public void mostrarUno() {
		int id = saCliente.alta(transferCliente);
		
		TransferCliente t2 = saCliente.mostrarUno(id);
		areEqual(transferCliente, t2);
		
		deleteRegister(id);
	}
	
	@Test
	public void mostrarTodos() {		
		int id = saCliente.alta(transferCliente);
		
		List<TransferCliente> almacenes = saCliente.mostrarTodos();
		
		assertNotNull(almacenes);
		
		deleteRegister(id);
	}
	
	private void areEqual(TransferCliente c1, TransferCliente c2) {
		assertEquals(c1.getNombre(), c2.getNombre());
		assertEquals(c1.isSocio(), c2.isSocio());
		assertEquals(c1.getDNI(), c2.getDNI());
	}
	
	private void deleteRegister(int ID) {
		String sql = "DELETE FROM Clientes WHERE idCliente=?";
		DatabaseConnection.dropRegisterWithID(sql, ID);
	}
	
}
