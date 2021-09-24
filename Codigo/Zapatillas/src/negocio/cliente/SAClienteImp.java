package negocio.cliente;

import java.util.List;

import integracion.dao.DAOFactory.DAOAbstractFactory;

public class SAClienteImp implements SACliente {

	@Override
	public int alta(TransferCliente t) {
		boolean correctDNI = true;
		
		if(t.getDNI().length() == 9) {
			for(int i = 0; i < 8; i++) {
				if(!Character.isDigit(t.getDNI().charAt(i)))
					correctDNI = false;
			}
			if(correctDNI && Character.isLetter(t.getDNI().charAt(8)))
				if(t.getNombre().length() < 35) {
					TransferCliente cliente = DAOAbstractFactory.getInstance().getDAOCliente().getCliente(t.getDNI());
					if(cliente == null)
						return DAOAbstractFactory.getInstance().getDAOCliente().alta(t);
					else if(!cliente.getActivo())
						return DAOAbstractFactory.getInstance().getDAOCliente().activarCliente(cliente.getID());
					else return -2;
				}
		}
		
		return -1;
	}

	@Override
	public int borrar(int id) {
		if(DAOAbstractFactory.getInstance().getDAOCliente().getCliente(id) != null) {
			return (DAOAbstractFactory.getInstance().getDAOCliente().baja(id));
		}
		return -2;
	}

	@Override
	public int modificar(TransferCliente t) {
		if(t.getDNI().length() == 9) {
			if(t.getNombre().length() < 35) {
				TransferCliente cliente = DAOAbstractFactory.getInstance().getDAOCliente().getCliente(t.getDNI());
				if(cliente != null) {
					if(cliente.getID() == t.getID())
						return (DAOAbstractFactory.getInstance().getDAOCliente().modificar(t));		
					else return -3;
				}
				else return (DAOAbstractFactory.getInstance().getDAOCliente().modificar(t));		
			}
			
		}
		return -1;
	}

	@Override
	public TransferCliente mostrarUno(int id) {
		return DAOAbstractFactory.getInstance().getDAOCliente().getCliente(id);
	}

	@Override
	public List<TransferCliente> mostrarTodos() {
		return DAOAbstractFactory.getInstance().getDAOCliente().getAllClientes();
	}

}
