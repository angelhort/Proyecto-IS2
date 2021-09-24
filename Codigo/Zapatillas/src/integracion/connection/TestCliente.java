package integracion.connection;

import java.util.List;

import integracion.dao.cliente.DAOClienteImp;
import negocio.cliente.TransferCliente;

public class TestCliente {

	public static void main(String[] args) {
		DAOClienteImp dao = new DAOClienteImp();
		
		//TransferCliente c1 = new TransferCliente("Juan", true, "48573984d");
		//TransferCliente c2 = new TransferCliente("Pedro", true, "20938490d");
		//TransferCliente c3 = new TransferCliente("paco", true, "21342345f");
		
		//int r1 = dao.alta(c1);
		//int r2 = dao.alta(c2);
		//int r3 = dao.alta(c3);
		
		//System.out.println(String.format("%d, %d, %d", r1, r2, r3));
		
		System.out.println(dao.baja(32));
		
		List<TransferCliente> clientes = dao.getAllClientes();
		
		for (TransferCliente cliente : clientes)
			System.out.println(String.format("%d, %s, %s, %b, %b", cliente.getID(), cliente.getDNI(),
																	cliente.getNombre(), cliente.isSocio(),
																	cliente.getActivo()));
		
		TransferCliente cliente = dao.getCliente(1);
		
		if (cliente != null)
			System.out.println(String.format("%d, %s, %s, %b, %b", cliente.getID(), cliente.getDNI(),
																	cliente.getNombre(), cliente.isSocio(),
																	cliente.getActivo()));
	}

}
