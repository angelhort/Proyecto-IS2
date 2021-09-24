package test.integracion.DAOMarca;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import integracion.dao.cliente.DAOClienteImp;
import integracion.dao.marca.DAOMarcaImp;
import negocio.cliente.TransferCliente;
import negocio.marca.TransferMarca;

public class TestDAOMarca {
	public static final TransferMarca marca1 = new TransferMarca("Pablosky");
	public static final TransferMarca marca2 = new TransferMarca("Geox");

	@Test
	public void alta() {
		DAOMarcaImp daoMarca = new DAOMarcaImp();
		
		int id = daoMarca.alta(marca1);
		
		assertTrue(String.format("Se ha devuelto el id %d",  id), id >= 1);
	}
	
	@Test
	public void baja() {
		DAOMarcaImp daoMarca = new DAOMarcaImp();
		
		int id = daoMarca.alta(marca1);
		int idBaja = daoMarca.baja(id);
		
		assertEquals(String.format("Se esperaba 1 como salida y se encontro %d", idBaja), 1, idBaja);
	}
	
	@Test
	public void modificar() {
		DAOMarcaImp daoMarca = new DAOMarcaImp();
		
		int id = daoMarca.alta(marca1);
		
		TransferMarca marcaModificada = new TransferMarca(id, "Alpinestars", true);
		int modificar = daoMarca.modificar(marcaModificada);
		
		assertEquals(String.format("Se esperaba un 1 como salida y se encontro %d", modificar), 1, modificar);
	}
	
	@Test
	public void getMarca() {
		DAOMarcaImp daoMarca = new DAOMarcaImp();
		
		int id = daoMarca.alta(marca1);
		TransferMarca marca = daoMarca.getMarca(id);
		TransferMarca marca2 = daoMarca.getMarca(marca1.getNombre());
		
		areEqual(marca1, marca);
		areEqual(marca1, marca2);
	}
	
	@Test 
	public void activarMarca() {
		DAOMarcaImp daoMarca = new DAOMarcaImp();
		
		int id = daoMarca.alta(marca1);	
		int idActivado = daoMarca.activarMarca(id);
		
		assertEquals(String.format("Se esperaba 0 como salida y se encontro %d", idActivado), 0, idActivado);
	}
	
	@Test 
	public void getAllMarcas() {
		DAOMarcaImp daoMarca = new DAOMarcaImp();
		
		int id1 = daoMarca.alta(marca1);
		int id2 = daoMarca.alta(marca2);
		
		List<TransferMarca> marcas = daoMarca.getAllMarcas();
		
		assertNotNull(marcas);
	}
	
	
	private void areEqual(TransferMarca m1, TransferMarca m2) {
		assertEquals(m1.getNombre(), m2.getNombre());
	}
	
}
