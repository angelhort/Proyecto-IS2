package negocio.producto;

import java.util.List;
import java.util.StringTokenizer;

import integracion.dao.DAOFactory.DAOAbstractFactory;
import negocio.almacen.TransferAlmacen;
import negocio.marca.TransferMarca;

public class SAProductoImp implements SAProducto{
	
	@Override
	public int alta(TransferProducto t) {
		if(t.getNombre().length() <= 35) {
			
			String stringAux = String.valueOf(t.getPrecio());
			StringTokenizer sT = new StringTokenizer(stringAux, ".");
			
			if(sT.nextToken().length() <= 8 && sT.nextToken().length() <= 2) {
				TransferMarca marca = DAOAbstractFactory.getInstance().getDAOMarca().getMarca(t.getMarca());
				if(marca != null && marca.getActivo()) {
					TransferAlmacen almacen = DAOAbstractFactory.getInstance().getDAOAlmacen().getAlmacen(t.getAlmacen());
					if(almacen != null && almacen.getActivo()) {
						TransferProducto producto = DAOAbstractFactory.getInstance().getDAOProducto().getProducto(t.getNombre());
						if(producto == null) {
							if(t.getClass() == TransferZapatillas.class)
								return DAOAbstractFactory.getInstance().getDAOProducto().alta((TransferZapatillas) t);
							else if(t.getClass() == TransferCalcetines.class)
								return DAOAbstractFactory.getInstance().getDAOProducto().alta((TransferCalcetines) t);													
						}else if(!producto.getActivo()){
							return DAOAbstractFactory.getInstance().getDAOProducto().activarProducto(producto.getID());
						}
						else return -2;
					}
				}
				
			}
		}
		return -1;
	}

	@Override
	public int borrar(int id) {
		if(DAOAbstractFactory.getInstance().getDAOProveedorProducto().getProveedoresFromProducto(id) == null) {
			if(DAOAbstractFactory.getInstance().getDAOProducto().getProducto(id) != null)
				return DAOAbstractFactory.getInstance().getDAOProducto().bajaProducto(id);			
			else return -2;
		}
		else return -5;
	}

	@Override
	public int modificar(TransferProducto t) {
		if(t.getNombre().length() <= 35) {
			String stringAux = String.valueOf(t.getPrecio());
			StringTokenizer sT = new StringTokenizer(stringAux, ".");
			
			if(sT.nextToken().length() <= 8 && sT.nextToken().length() <= 2) {
				TransferMarca marca = DAOAbstractFactory.getInstance().getDAOMarca().getMarca(t.getMarca());
				if(marca != null && marca.getActivo()) {
					TransferAlmacen almacen = DAOAbstractFactory.getInstance().getDAOAlmacen().getAlmacen(t.getAlmacen());
					if(almacen != null && almacen.getActivo()) {
						TransferProducto producto = DAOAbstractFactory.getInstance().getDAOProducto().getProducto(t.getNombre());
						if(producto != null) {
							if(producto.getID() == t.getID()) {
								if(t.getClass() == TransferZapatillas.class)
									return DAOAbstractFactory.getInstance().getDAOProducto().modificar((TransferZapatillas) t);
								else if(t.getClass() == TransferCalcetines.class)
									return DAOAbstractFactory.getInstance().getDAOProducto().modificar((TransferCalcetines) t);
							}
							else return -3;
						}
						else {						
							if(t.getClass() == TransferZapatillas.class)
								return DAOAbstractFactory.getInstance().getDAOProducto().modificar((TransferZapatillas) t);
							else if(t.getClass() == TransferCalcetines.class)
								return DAOAbstractFactory.getInstance().getDAOProducto().modificar((TransferCalcetines) t);										
						}
					}
				
				}
			}
		}
		return -1;
	}

	@Override
	public TransferProducto mostrarUno(int id) {
		return DAOAbstractFactory.getInstance().getDAOProducto().getProducto(id);
	}

	@Override
	public List<TransferProducto> mostrarTodos() {
		return DAOAbstractFactory.getInstance().getDAOProducto().getAllProductos();
	}

}
