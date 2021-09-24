package negocio.proveedor_producto;

public class TransferProveedor_producto {
	private int idProveedor;
	private int idProducto;
	private double precioSuministro;
	

	public TransferProveedor_producto(int idProveedor, int idProducto, double precioSuministro){
		this.idProveedor = idProveedor;
		this.idProducto = idProducto;
		this.precioSuministro = precioSuministro;
	}
	
	public TransferProveedor_producto(int idProveedor, int idProducto) {
		this.idProveedor = idProveedor;
		this.idProducto = idProducto;
	}

	public int getIdProveedor() {
		return idProveedor;
	}

	public int getIdProducto() {
		return idProducto;
	}
	
	public double getPrecioSuministro() {
		return precioSuministro;
	}
	
	public void setPrecioSuministro(double precioSuministro) {
		this.precioSuministro = precioSuministro;
	}
}
