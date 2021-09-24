package integracion.dao.producto;

import java.util.List;

import negocio.producto.TransferZapatillas;
import negocio.producto.TransferCalcetines;
import negocio.producto.TransferProducto;

public interface DAOProducto {
	public int alta(TransferZapatillas transfer);
	public int alta(TransferCalcetines transfer);
	public int bajaProducto(int ID);
	public int modificar(TransferZapatillas transfer);
	public int modificar(TransferCalcetines transfer);
	public double getPrecioProducto(int ID);
	public TransferProducto getProducto(int ID);
	public TransferProducto getProducto(String nombre);
	public List<TransferProducto> getAllProductos();
	public int activarProducto(int id);
	public int restarStock(int id, int cantidad);
	public List<TransferProducto> getProductosAlmacen(int idAlmacen);
	public List<TransferProducto> getProductosMarca(int idMarca);
}
