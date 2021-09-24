package test.integracion.DAOTrabajador;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import integracion.dao.trabajador.DAOTrabajadorImp;
import negocio.trabajador.TransferTrabajador;

public class TestDAOTrabajador {
	public static final TransferTrabajador trabajador1 = new TransferTrabajador(675645360, "87976532N", "Antonio Fernandez");
	public static final TransferTrabajador trabajador2 = new TransferTrabajador(638273645, "72649384B", "Federico Perez");
	
	@Test
	public void alta() {
		DAOTrabajadorImp daoTrabajador = new DAOTrabajadorImp();
		
		int id = daoTrabajador.alta(trabajador1);
		
		assertTrue(String.format("Se ha devuelto el id %d",  id), id >= 1);
	}
	
	
	@Test
	public void baja() {
		DAOTrabajadorImp daoTrabajador = new DAOTrabajadorImp();
		
		int id = daoTrabajador.alta(trabajador1);
		int idBaja = daoTrabajador.baja(id);
		
		assertEquals(String.format("Se esperaba 1 como salida y se encontro %d", idBaja), 1, idBaja);
	}
	
	@Test 
	public void modificar() {
		DAOTrabajadorImp daoTrabajador = new DAOTrabajadorImp();
		
		int id = daoTrabajador.alta(trabajador1);
		
		TransferTrabajador trabajadorModificado = new TransferTrabajador(id, 675098888, "43334554P", "Hector Garcia", false);
		int modificar = daoTrabajador.modificar(trabajadorModificado);
		
		assertEquals(String.format("Se esperaba un 1 como salida y se encontro %d", modificar), 1, modificar);
	}

	@Test
	public void getTrabajador() {
		DAOTrabajadorImp daoTrabajador = new DAOTrabajadorImp();
		
		int id = daoTrabajador.alta(trabajador1);
		TransferTrabajador trabajador = daoTrabajador.getTrabajador(id);
		
		areEqual(trabajador1, trabajador);
		
	}
	
	@Test 
	public void activarTrabajador() {
		DAOTrabajadorImp daoTrabajador = new DAOTrabajadorImp();
		
		int id = daoTrabajador.alta(trabajador1);	
		int idActivado = daoTrabajador.activarTrabajador(id);
		
		assertEquals(String.format("Se esperaba 0 como salida y se encontro %d", idActivado), 0, idActivado);
	}
	
	@Test 
	public void getAllTrabajadores() {
		DAOTrabajadorImp daoTrabajador = new DAOTrabajadorImp();
		
		int id1 = daoTrabajador.alta(trabajador1);
		int id2 = daoTrabajador.alta(trabajador2);
		
		List<TransferTrabajador> trabajadores = daoTrabajador.getAllTrabajadores();
		
		assertNotNull(trabajadores);
	}

	private void areEqual(TransferTrabajador t1, TransferTrabajador t2) {
		assertEquals(t1.getTelefono(), t2.getTelefono());
		assertEquals(t1.getDNI(), t2.getDNI());
		assertEquals(t1.getNombre(), t2.getNombre());
	}
}
