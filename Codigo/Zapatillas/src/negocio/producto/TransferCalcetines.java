package negocio.producto;

public class TransferCalcetines extends TransferProducto{
	
	private String tejido;

	public TransferCalcetines(int ID, int talla, double precio, String nombre, String color, String tejido, int stock,
			int almacen, int marca, boolean activo) {
		super(ID, talla, precio, nombre, color, stock, almacen, marca, activo);
		this.tejido = tejido;
			
	}
	
	public TransferCalcetines(int talla, double precio, String nombre, String color, int stock,
			int almacen, int marca, String tejido) {
		super(talla, precio, nombre, color, stock, almacen, marca);
		this.tejido = tejido;
	}
	
	public String getTejido() {
		return tejido;
	}
	
	@Override
	public String toString() {
		return "calcetines";
	}
}
