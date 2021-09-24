package negocio.proveedor_producto;

import java.util.List;

import negocio.producto.TransferProducto;

public interface SAProductoProveedor {
	public int aniadirProveedor(TransferProveedor_producto t);
	public int eliminarProveedor(TransferProveedor_producto t);
	public List<TransferProducto> getProveedor_producto(int idProveedor);
}
