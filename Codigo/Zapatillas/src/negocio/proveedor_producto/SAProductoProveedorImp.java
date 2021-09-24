package negocio.proveedor_producto;

import java.util.List;

import integracion.dao.DAOFactory.DAOAbstractFactory;
import negocio.producto.TransferProducto;
import negocio.proveedor.TransferProveedor;

public class SAProductoProveedorImp implements SAProductoProveedor{
	@Override
	public int aniadirProveedor(TransferProveedor_producto t) {	
		TransferProducto producto = DAOAbstractFactory.getInstance().getDAOProducto().getProducto(t.getIdProducto());
		if(producto != null && producto.getActivo()) {
			TransferProveedor proveedor = DAOAbstractFactory.getInstance().getDAOProveedor().getProveedor(t.getIdProveedor());
			if(proveedor != null && proveedor.getActivo())
				return DAOAbstractFactory.getInstance().getDAOProveedorProducto().alta(t);			
		}
		return -2;
	}

	//NO ES NECESARIO COMPROBAR SI ESTAN ACTIVOS PARA ELIMINAR LA RELACION YA QUE SOLO SE PUEDEN DAR DE BAJA TANTO
	//PRODUCTOS COMO PROVEEDORES SI NO TIENEN NINGUNA RELACION
	@Override
	public int eliminarProveedor(TransferProveedor_producto t) {
		TransferProducto producto = DAOAbstractFactory.getInstance().getDAOProducto().getProducto(t.getIdProducto());
		if(producto != null) {
			TransferProveedor proveedor = DAOAbstractFactory.getInstance().getDAOProveedor().getProveedor(t.getIdProveedor());
			if(proveedor != null)
				return DAOAbstractFactory.getInstance().getDAOProveedorProducto().baja(t);
		}
		return -2;
	}

	@Override
	public List<TransferProducto> getProveedor_producto(int idProveedor) {
		TransferProveedor proveedor = DAOAbstractFactory.getInstance().getDAOProveedor().getProveedor(idProveedor);
		if(proveedor != null && proveedor.getActivo())
			return DAOAbstractFactory.getInstance().getDAOProveedorProducto().getProductosFromProveedor(idProveedor);
		return null;
	}

}
