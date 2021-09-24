package test.negocio.SAMarca;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import integracion.connection.DatabaseConnection;
import negocio.marca.SAMarcaImp;
import negocio.marca.TransferMarca;

public class TestSAMarca {
	private SAMarcaImp saMarca = new SAMarcaImp();
	private TransferMarca transferMarca = new TransferMarca("TestMARCA");
		
	@Test
	public void altaOK() {		
		int id = saMarca.alta(transferMarca);

		assertTrue(String.format("Se esperaba >0 y se encontró %s", id), id > 0);
		
		deleteRegister(id);
	}
	
	@Test
	public void borrarOK() {
		int id = saMarca.alta(transferMarca);

		int idBaja = saMarca.borrar(id);
		
		assertEquals(String.format("Se esperaba 1 y se encontró %s", idBaja), 1, idBaja);
		
		deleteRegister(id);
	}
	
	@Test
	public void borrarFail() {
		int idBaja = saMarca.borrar(-1);
		
		assertEquals(String.format("Se esperaba -2 y se encontró %s", idBaja), -2, idBaja);	
	}
	
	@Test
	public void modificarOK() {
		int id = saMarca.alta(transferMarca);

		TransferMarca t1 = new TransferMarca("testMARCA2");
		int idModificar = saMarca.modificar(t1);
		
		assertEquals(String.format("Se esperaba 0 y se encontró %s", idModificar), 0, idModificar);	
	
		deleteRegister(id);
	}
	
	@Test
	public void modificarFail() {
		TransferMarca t1 = new TransferMarca(-1, "Test", true);
		int idModificar = saMarca.modificar(t1);
		
		assertEquals(String.format("Se esperaba 0 y se encontró %s", idModificar), 0, idModificar);
	}
	
	@Test
	public void mostrarUno() {
		int id = saMarca.alta(transferMarca);
		
		TransferMarca t2 = saMarca.mostrarUno(id);
		areEqual(transferMarca, t2);
		
		deleteRegister(id);
	}
	
	@Test
	public void mostrarTodos() {		
		int id = saMarca.alta(transferMarca);
		
		List<TransferMarca> marca = saMarca.mostrarTodos();
		
		assertNotNull(marca);
		
		deleteRegister(id);
	}
	
	private void areEqual(TransferMarca m1, TransferMarca m2) {
		assertEquals(m1.getNombre(), m2.getNombre());
	}
	
	private void deleteRegister(int ID) {
		String sql = "DELETE FROM Marca WHERE idMarca=?";
		DatabaseConnection.dropRegisterWithID(sql, ID);
	}
}
