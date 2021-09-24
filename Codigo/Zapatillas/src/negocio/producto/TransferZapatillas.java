package negocio.producto;

public class TransferZapatillas extends TransferProducto{

	private String tipo;

	public TransferZapatillas(int ID, int talla, double precio, String nombre, String color, String tipo, int stock,
			int almacen, int marca, boolean activo) {
		super(ID, talla, precio, nombre, color, stock, almacen, marca, activo);
		this.tipo = tipo;
			
	}
	
	public TransferZapatillas(int talla, double precio, String nombre, String color, String tipo, int stock,
			int almacen, int marca) {
		super(talla, precio, nombre, color, stock, almacen, marca);
		this.tipo = tipo;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	@Override
	public String toString() {
		return "zapatillas";
	}
}
