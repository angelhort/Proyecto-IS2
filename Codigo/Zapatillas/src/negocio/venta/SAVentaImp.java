package negocio.venta;

import integracion.dao.DAOFactory.DAOAbstractFactory;
import negocio.cliente.TransferCliente;
import negocio.producto.TransferProducto;
import negocio.trabajador.TransferTrabajador;

public class SAVentaImp implements SAVenta{

	@Override
	public int altaVenta(TransferVenta venta) {
		TransferCliente cliente = DAOAbstractFactory.getInstance().getDAOCliente().getCliente(venta.getCliente().getID());
		if(cliente != null && cliente.getActivo()) {
			TransferTrabajador trabajador = DAOAbstractFactory.getInstance().getDAOTrabajador().getTrabajador(venta.getTrabajador().getID());
			if(trabajador != null && trabajador.getActivo()) {			
				for(TProductoEnFactura p : venta.getProductos()) {
					TransferProducto producto = DAOAbstractFactory.getInstance().getDAOProducto().getProducto(p.getProducto().getID());
					if(producto == null) return -2;
					if(producto.getStock() >= p.getUnidades() && producto.getActivo()) {
						double precioUnidad = DAOAbstractFactory.getInstance().getDAOProducto().getPrecioProducto(p.getProducto().getID());
						p.setPrecio(precioUnidad);						
					}
					else return -4;
				}
				
				for(TProductoEnFactura p : venta.getProductos()) 
					DAOAbstractFactory.getInstance().getDAOProducto().restarStock(p.getProducto().getID(), p.getUnidades());
				
				venta.setPrecioTotal();
				
				return DAOAbstractFactory.getInstance().getDAOVenta().alta(venta);							
			}
		}
		return -2;
	}

	@Override
	public TransferVenta getVenta(int id) {
		return DAOAbstractFactory.getInstance().getDAOVenta().getVenta(id);
	}

}
