package negocio.almacen;

import java.util.List;

import integracion.dao.DAOFactory.DAOAbstractFactory;
import negocio.producto.TransferProducto;

public class SAAlmacenImp implements SAAlmacen{

	@Override
	public int alta(TransferAlmacen t) {
		if(String.valueOf(t.getTelefono()).length() == 9)
			if(t.getDireccion().length() <= 30) {
				TransferAlmacen almacen = DAOAbstractFactory.getInstance().getDAOAlmacen().getAlmacen(t.getDireccion());
				if(almacen == null)
					return DAOAbstractFactory.getInstance().getDAOAlmacen().alta(t);
				else if (!almacen.getActivo()) return DAOAbstractFactory.getInstance().getDAOAlmacen().activarAlmacen(almacen.getID());
				else return -2;
			}
		
		return -1;
	}

	@Override
	public int borrar(int id) {
		List<TransferProducto> productos = DAOAbstractFactory.getInstance().getDAOProducto().getProductosAlmacen(id);
		
		for(TransferProducto p : productos) {
			if(p.getActivo()) return -5;
		}
		
		if(DAOAbstractFactory.getInstance().getDAOAlmacen().getAlmacen(id) != null)
			return DAOAbstractFactory.getInstance().getDAOAlmacen().baja(id);
		else return -2;			
	}

	@Override
	public int modificar(TransferAlmacen t) {
		if(String.valueOf(t.getTelefono()).length() == 9)
			if(t.getDireccion().length() <= 30) {
				TransferAlmacen almacen = DAOAbstractFactory.getInstance().getDAOAlmacen().getAlmacen(t.getDireccion());
				if(almacen != null) {
					if(almacen.getID() == t.getID())
						return DAOAbstractFactory.getInstance().getDAOAlmacen().modificar(t);	
					else return -3;
				}
				else return DAOAbstractFactory.getInstance().getDAOAlmacen().modificar(t);
			}
		return -1;
	}

	@Override
	public TransferAlmacen mostrarUno(int id) {
		return DAOAbstractFactory.getInstance().getDAOAlmacen().getAlmacen(id);
	}

	@Override
	public List<TransferAlmacen> mostrarTodos() {
		return DAOAbstractFactory.getInstance().getDAOAlmacen().getAllAlmacenes();
	}

}
