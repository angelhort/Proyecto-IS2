package negocio.marca;

import java.util.List;

import integracion.dao.DAOFactory.DAOAbstractFactory;
import negocio.producto.TransferProducto;

public class SAMarcaImp implements SAMarca{

	@Override
	public int alta(TransferMarca t) {
		if(t.getNombre().length() <= 35) {
			TransferMarca marca = DAOAbstractFactory.getInstance().getDAOMarca().getMarca(t.getNombre());
			if(marca == null) return DAOAbstractFactory.getInstance().getDAOMarca().alta(t);
			else if (!marca.getActivo()) return DAOAbstractFactory.getInstance().getDAOMarca().activarMarca(marca.getID());
			else return -2;
		}		
		return -1;
	}

	@Override
	public int borrar(int id) {
		List<TransferProducto> productos = DAOAbstractFactory.getInstance().getDAOProducto().getProductosMarca(id);
		
		for(TransferProducto p : productos) {
			if(p.getActivo()) return -5;
		}
		
		if ( DAOAbstractFactory.getInstance().getDAOMarca().getMarca(id) != null)
			return DAOAbstractFactory.getInstance().getDAOMarca().baja(id);
		else return -2;
	}

	@Override
	public int modificar(TransferMarca t) {
		if(t.getNombre().length() <= 35) {
			TransferMarca marca = DAOAbstractFactory.getInstance().getDAOMarca().getMarca(t.getNombre());
			if(marca != null) {
				if(marca.getID() == t.getID())
					return DAOAbstractFactory.getInstance().getDAOMarca().modificar(t);	
				else return -3;
			}
			else return DAOAbstractFactory.getInstance().getDAOMarca().modificar(t);
		}
		return -1;
	}

	@Override
	public TransferMarca mostrarUno(int id) {
		return DAOAbstractFactory.getInstance().getDAOMarca().getMarca(id);
	}

	@Override
	public List<TransferMarca> mostrarTodos() {
		return DAOAbstractFactory.getInstance().getDAOMarca().getAllMarcas();
	}

}
