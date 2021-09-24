package integracion.dao.DAOFactory;

import integracion.dao.almacen.DAOAlmacen;
import integracion.dao.cliente.DAOCliente;
import integracion.dao.marca.DAOMarca;
import integracion.dao.producto.DAOProducto;
import integracion.dao.proveedor.DAOProveedor;
import integracion.dao.proveedor_producto.DAOProveedorProducto;
import integracion.dao.trabajador.DAOTrabajador;
import integracion.dao.venta.DAOVenta;

public abstract class DAOAbstractFactory {
	private static DAOAbstractFactory instance;
	
	public static DAOAbstractFactory getInstance() {
		if(instance == null) instance = new DAOFactoryImp();
		return instance;
	}
	
	public abstract DAOCliente getDAOCliente();
	public abstract DAOProveedor getDAOProveedor();
	public abstract DAOMarca getDAOMarca();
	public abstract DAOTrabajador getDAOTrabajador();
	public abstract DAOAlmacen getDAOAlmacen();
	public abstract DAOProducto getDAOProducto();
	public abstract DAOProveedorProducto getDAOProveedorProducto();
	public abstract DAOVenta getDAOVenta();
}
