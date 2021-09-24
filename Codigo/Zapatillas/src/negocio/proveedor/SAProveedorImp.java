package negocio.proveedor;

import java.util.List;

import integracion.dao.DAOFactory.DAOAbstractFactory;

public class SAProveedorImp implements SAProveedor {
	
	// TODO: Comprobar validez de los datos

	@Override
	public int alta(TransferProveedor t) {
		if(t.getDireccion().length() <= 30) 
			if(t.getNombre().length() <= 30)
				if(String.valueOf(t.getTelefono()).length() == 9) {
					TransferProveedor proveedor = DAOAbstractFactory.getInstance().getDAOProveedor().getProveedor(t.getDireccion());
					if(proveedor == null) 
						return DAOAbstractFactory.getInstance().getDAOProveedor().alta(t);	
					else if(!proveedor.getActivo())
						return DAOAbstractFactory.getInstance().getDAOProveedor().activarProveedor(t.getID());
					else return -2;
				}
		return -1;
	}

	@Override
	public int borrar(int id) {
		if(DAOAbstractFactory.getInstance().getDAOProveedorProducto().getProductosFromProveedor(id) == null) {
			if(DAOAbstractFactory.getInstance().getDAOProveedor().getProveedor(id) != null) 
				return (DAOAbstractFactory.getInstance().getDAOProveedor().baja(id));
			else return -2;		
		}
		
		else return -5;
	}

	@Override
	public int modificar(TransferProveedor t) {
		if(t.getDireccion().length() <= 30) 
			if(t.getNombre().length() <= 30)
				if(String.valueOf(t.getTelefono()).length() == 9) {
					TransferProveedor proveedor = DAOAbstractFactory.getInstance().getDAOProveedor().getProveedor(t.getDireccion());
					if(proveedor != null) {
						if(proveedor.getID() == t.getID())
							return DAOAbstractFactory.getInstance().getDAOProveedor().modificar(t);
						else return -3;
					}
					else return DAOAbstractFactory.getInstance().getDAOProveedor().modificar(t);					
				}
					
		return -1;
	}

	@Override
	public TransferProveedor mostrarUno(int id) {
		return DAOAbstractFactory.getInstance().getDAOProveedor().getProveedor(id);
	}

	@Override
	public List<TransferProveedor> mostrarTodos() {
		return DAOAbstractFactory.getInstance().getDAOProveedor().getAllProveedores();
	}

}
