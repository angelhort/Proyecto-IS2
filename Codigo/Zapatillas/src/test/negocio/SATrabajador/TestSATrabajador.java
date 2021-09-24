package test.negocio.SATrabajador;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import integracion.connection.DatabaseConnection;
import negocio.trabajador.SATrabajadorImp;
import negocio.trabajador.TransferTrabajador;

public class TestSATrabajador {
	private SATrabajadorImp saTrabajador = new SATrabajadorImp();
	private TransferTrabajador transferTrabajador = new TransferTrabajador(657342333, "34323454N", "TestNombre");
		
	@Test
	public void altaOK() {		
		int id = saTrabajador.alta(transferTrabajador);

		assertTrue(String.format("Se esperaba >0 y se encontró %s", id), id >= 1);
		
		deleteRegister(id);
	}
	
	@Test
	public void altaFail() {
		TransferTrabajador t1 = new TransferTrabajador(4, "45", "TestTrabajador2");
		
		int id1 = saTrabajador.alta(t1);
		
		assertEquals(String.format("Se esperaba -1 y se encontró %s", id1), -1, id1);	
	}
	
	@Test
	public void borrarOK() {
		int id = saTrabajador.alta(transferTrabajador);

		int idBaja = saTrabajador.borrar(id);
		
		assertEquals(String.format("Se esperaba 1 y se encontró %s", idBaja), 1, idBaja);
		
		deleteRegister(id);
	}
	
	@Test
	public void borrarFail() {
		int idBaja = saTrabajador.borrar(-1);
		
		assertEquals(String.format("Se esperaba -2 y se encontró %s", idBaja), -2, idBaja);	
	}
	
	@Test
	public void modificarOK() {
		int id = saTrabajador.alta(transferTrabajador);

		TransferTrabajador t1 = new TransferTrabajador(id, 657483940, "34464737M", "TestTrabajador", true);
		int idModificar = saTrabajador.modificar(t1);
		
		assertEquals(String.format("Se esperaba 1 y se encontró %s", idModificar), 1, idModificar);	
	
		deleteRegister(id);
	}
	
	@Test
	public void modificarFail() {
		TransferTrabajador t1 = new TransferTrabajador(-1, 657744888, "74658890N", "TestTrabajador", false);
		int idModificar1 = saTrabajador.modificar(t1);
		
		assertEquals(String.format("Se esperaba 0 y se encontró %s", idModificar1), 0, idModificar1);
		
		TransferTrabajador t2 = new TransferTrabajador(-1, 657473, "00443737M", "TestTrabjador", true);
		int idModificar2 = saTrabajador.modificar(t2);
		
		assertEquals(String.format("Se esperaba -1 y se encontró %s", idModificar2), -1, idModificar2);
		
		TransferTrabajador t3 = new TransferTrabajador(-1, 657473888, "03M", "TestTrabjador", true);
		int idModificar3 = saTrabajador.modificar(t3);
		
		assertEquals(String.format("Se esperaba -1 y se encontró %s", idModificar3), -1, idModificar3);
	}
	
	@Test
	public void mostrarUno() {
		int id = saTrabajador.alta(transferTrabajador);
		
		TransferTrabajador t2 = saTrabajador.mostrarUno(id);
		areEqual(transferTrabajador, t2);
		
		deleteRegister(id);
	}
	
	@Test
	public void mostrarTodos() {		
		int id = saTrabajador.alta(transferTrabajador);
		
		List<TransferTrabajador> trabajadores = saTrabajador.mostrarTodos();
		
		assertNotNull(trabajadores);
		
		deleteRegister(id);
	}
	
	private void areEqual(TransferTrabajador t1, TransferTrabajador t2) {
		assertEquals(t1.getTelefono(), t2.getTelefono());
		assertEquals(t1.getDNI(), t2.getDNI());
		assertEquals(t1.getNombre(), t2.getNombre());
	}
	
	private void deleteRegister(int ID) {
		String sql = "DELETE FROM Trabajadores WHERE idTrabajador=?";
		DatabaseConnection.dropRegisterWithID(sql, ID);
	}
}
