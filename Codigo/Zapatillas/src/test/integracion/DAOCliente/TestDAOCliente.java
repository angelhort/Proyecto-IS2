package test.integracion.DAOCliente;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import integracion.dao.cliente.DAOClienteImp;
import negocio.cliente.TransferCliente;

public class TestDAOCliente {
	public static final TransferCliente cliente1 = new TransferCliente("Paco", true, "04567845M");
	public static final TransferCliente cliente2 = new TransferCliente("Juan", false, "34576895N");
	
	@Test
	public void alta() {
		DAOClienteImp daoCliente = new DAOClienteImp();
		
		int id = daoCliente.alta(cliente1);
		
		assertTrue(String.format("Se ha devuelto el id %d",  id), id >= 1);
	}
	
	
	@Test
	public void baja() {
		DAOClienteImp daoCliente = new DAOClienteImp();
		
		int id = daoCliente.alta(cliente1);
		int idBaja = daoCliente.baja(id);
		
		assertEquals(String.format("Se esperaba 1 como salida y se encontro %d", idBaja), 1, idBaja);
	}
	
	@Test
	public void getCliente() {
		DAOClienteImp daoCliente = new DAOClienteImp();
		
		int id = daoCliente.alta(cliente1);
		TransferCliente cliente = daoCliente.getCliente(id);
		
		areEqual(cliente1, cliente);
		
	}
	
	@Test 
	public void activarCliente() {
		DAOClienteImp daoCliente = new DAOClienteImp();
		
		int id = daoCliente.alta(cliente1);	
		int idActivado = daoCliente.activarCliente(id);
		
		assertEquals(String.format("Se esperaba 0 como salida y se encontro %d", idActivado), 0, idActivado);
	}
	
	@Test 
	public void getAllClientes() {
		DAOClienteImp daoCliente = new DAOClienteImp();
		
		int id1 = daoCliente.alta(cliente1);
		int id2 = daoCliente.alta(cliente2);
		
		List<TransferCliente> clientes = daoCliente.getAllClientes();
		
		assertNotNull(clientes);
	}
	
	@Test 
	public void modificar() {
		DAOClienteImp daoCliente = new DAOClienteImp();
		
		int id = daoCliente.alta(cliente1);
		
		TransferCliente clienteModificado = new TransferCliente(id, "Pepe", true, "45634554F", false);
		int modificar = daoCliente.modificar(clienteModificado);
		
		assertEquals(String.format("Se esperaba un 1 como salida y se encontro %d", modificar), 1, modificar);
	}
	
	private void areEqual(TransferCliente c1, TransferCliente c2) {
		assertEquals(c1.getNombre(), c2.getNombre());
		assertEquals(c1.isSocio(), c2.isSocio());
		assertEquals(c1.getDNI(), c2.getDNI());
	}
	
}
