package test.integracion.DAOProveedor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import integracion.dao.proveedor.DAOProveedorImp;
import negocio.proveedor.TransferProveedor;

public class TestDAOProveedor {
	public static final TransferProveedor proveedor1 = new TransferProveedor(564756857, "Calle Alcala 34", "Zapatillas Pedro");
	public static final TransferProveedor proveedor2 = new TransferProveedor(654323456, "Calle Tenerife 11", "Zapatillas y mas");
	
	@Test
	public void alta() {
		DAOProveedorImp daoProveedor = new DAOProveedorImp();
		
		int id = daoProveedor.alta(proveedor1);
		
		assertTrue(String.format("Se ha devuelto el id %d",  id), id >= 1);
	}
	
	@Test 
	public void modificar() {
		DAOProveedorImp daoProveedor = new DAOProveedorImp();
		
		int id = daoProveedor.alta(proveedor1);
		
		TransferProveedor proveedorModificado = new TransferProveedor(id, 657483765, "Calle Marte 3", "Zapatos Perico", false);
		int modificar = daoProveedor.modificar(proveedorModificado);
		
		assertEquals(String.format("Se esperaba un 1 como salida y se encontro %d", modificar), 1, modificar);
	}
	
	@Test
	public void baja() {
		DAOProveedorImp daoProveedor = new DAOProveedorImp();
		
		int id = daoProveedor.alta(proveedor1);
		int idBaja = daoProveedor.baja(id);
		
		assertEquals(String.format("Se esperaba 1 como salida y se encontro %d", idBaja), 1, idBaja);
	}

	@Test
	public void getProveedor() {
		DAOProveedorImp daoProveedor = new DAOProveedorImp();
		
		int id = daoProveedor.alta(proveedor1);
		TransferProveedor proveedor = daoProveedor.getProveedor(id);
		TransferProveedor proveedor2 = daoProveedor.getProveedor(proveedor1.getDireccion());
		
		System.out.println(proveedor);
		System.out.println(proveedor2);
		
		areEqual(proveedor1, proveedor);
		areEqual(proveedor1, proveedor2);
	}
	
	@Test 
	public void activarProveedor() {
		DAOProveedorImp daoProveedor = new DAOProveedorImp();
		
		int id = daoProveedor.alta(proveedor1);	
		int idActivado = daoProveedor.activarProveedor(id);
		
		assertEquals(String.format("Se esperaba 0 como salida y se encontro %d", idActivado), 0, idActivado);
	}
	
	@Test 
	public void getAllProveedores() {
		DAOProveedorImp daoProveedor = new DAOProveedorImp();
		
		int id1 = daoProveedor.alta(proveedor1);
		int id2 = daoProveedor.alta(proveedor2);
		
		List<TransferProveedor> proveedores = daoProveedor.getAllProveedores();
		
		assertNotNull(proveedores);
	}
	
	private void areEqual(TransferProveedor p1, TransferProveedor p2) {
		assertEquals(p1.getTelefono(), p2.getTelefono());
		assertEquals(p1.getNombre(), p2.getNombre());
		assertEquals(p1.getDireccion(), p2.getDireccion());
	}
	
}
