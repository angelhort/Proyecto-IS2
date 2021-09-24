package negocio.trabajador;

import java.util.List;

import integracion.dao.DAOFactory.DAOAbstractFactory;

public class SATrabajadorImp implements SATrabajador{

	@Override
	public int alta(TransferTrabajador t) {
 		boolean correctDNI = true;
		
		if(t.getDNI().length() == 9) {
			for(int i = 0; i < 8; i++) {
				if(!Character.isDigit(t.getDNI().charAt(i)))
					correctDNI = false;
			}
			if(correctDNI && Character.isLetter(t.getDNI().charAt(8)))
				if(t.getNombre().length() < 35) {
					if (String.valueOf(t.getTelefono()).length() == 9) {
						TransferTrabajador trabajador = DAOAbstractFactory.getInstance().getDAOTrabajador().getTrabajador(t.getDNI());
						if(trabajador == null)
							return DAOAbstractFactory.getInstance().getDAOTrabajador().alta(t);
						else if(!trabajador.getActivo())
							return DAOAbstractFactory.getInstance().getDAOTrabajador().activarTrabajador(trabajador.getID());
						else return -2;
					}
				}
		}
		
		return -1;
	}

	@Override
	public int borrar(int id) {
		if(DAOAbstractFactory.getInstance().getDAOTrabajador().getTrabajador(id) != null) {
			return DAOAbstractFactory.getInstance().getDAOTrabajador().baja(id);
		}
		return -2;
		
	}

	@Override
	public int modificar(TransferTrabajador t) {
		if(t.getDNI().length() == 9) {
			if(t.getNombre().length() < 35) {
				if (String.valueOf(t.getTelefono()).length() == 9) {
					TransferTrabajador trabajador = DAOAbstractFactory.getInstance().getDAOTrabajador().getTrabajador(t.getDNI());
					if(trabajador != null) {
						if(trabajador.getID() == t.getID())
							return (DAOAbstractFactory.getInstance().getDAOTrabajador().modificar(t));	
						else return -3;
					}
					else return (DAOAbstractFactory.getInstance().getDAOTrabajador().modificar(t));		
				}
			}
			
		}
		return -1;
	}

	@Override
	public TransferTrabajador mostrarUno(int id) {
		return  DAOAbstractFactory.getInstance().getDAOTrabajador().getTrabajador(id);
	}

	@Override
	public List<TransferTrabajador> mostrarTodos() {
		return DAOAbstractFactory.getInstance().getDAOTrabajador().getAllTrabajadores();
	}

}
