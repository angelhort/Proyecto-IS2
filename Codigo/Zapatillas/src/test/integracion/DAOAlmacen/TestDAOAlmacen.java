package test.integracion.DAOAlmacen;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import integracion.dao.almacen.DAOAlmacen;
import integracion.dao.almacen.DAOAlmacenImp;
import negocio.almacen.TransferAlmacen;


public class TestDAOAlmacen {
	public static final TransferAlmacen almacen1 = new TransferAlmacen(678987654, 234, "Calle Mayor 1");
	public static final TransferAlmacen almacen2 = new TransferAlmacen(387847532, 543, "Calle Luna 3");

	@Test
	public void alta() {
		DAOAlmacenImp daoAlmacen = new DAOAlmacenImp();
		
		int id = daoAlmacen.alta(almacen1);
		
		assertTrue(String.format("Se ha devuelto %d" , id ), id >= 1);
	}
	
	@Test
	public void baja() {
		DAOAlmacenImp daoAlmacen = new DAOAlmacenImp();
		
		int id = daoAlmacen.alta(almacen1);
		int idBaja = daoAlmacen.baja(id);
		
		assertEquals(String.format("Se esperaba 1 como salida y se encontro %d", idBaja), 1, idBaja);
	}
	
	@Test
	public void modificar() {
		DAOAlmacen daoAlmacen = new DAOAlmacenImp();
		
		int id = daoAlmacen.alta(almacen1);
		
		TransferAlmacen almacenModificado = new TransferAlmacen(id, 657463745, 4576,"Calle menor 2", false);
		int modificar = daoAlmacen.modificar(almacenModificado);
		
		assertEquals(String.format("Se esperaba un 1 como salida y se encontro %d", modificar), 1, modificar);
	}
	
	@Test
	public void activarAlmacen() {
		DAOAlmacen daoAlmacen = new DAOAlmacenImp();
		
		int id = daoAlmacen.alta(almacen1);	
		int idActivado = daoAlmacen.activarAlmacen(id);
		
		assertEquals(String.format("Se esperaba 0 como salida y se encontro %d", idActivado), 0, idActivado);
	}
	
	@Test
	public void getAlmacen() {
		DAOAlmacen daoAlmacen = new DAOAlmacenImp();
		
		int id = daoAlmacen.alta(almacen1);
		TransferAlmacen almacen = daoAlmacen.getAlmacen(id);
		
		areEqual(almacen1, almacen);
	}
	
	@Test
	public void getAllAlmacenes() {
		DAOAlmacenImp daoAlmacen = new DAOAlmacenImp();
		
		int id1 = daoAlmacen.alta(almacen1);
		int id2 = daoAlmacen.alta(almacen2);
		
		List<TransferAlmacen> almacenes = daoAlmacen.getAllAlmacenes();
		
		assertNotNull(almacenes);
	}
	
	private void areEqual(TransferAlmacen a1, TransferAlmacen a2) {
		assertEquals(a1.getTelefono(), a2.getTelefono());
		assertEquals(a1.getCapacidad(), a2.getCapacidad());
		assertEquals(a1.getDireccion(), a2.getDireccion());
	}
}
