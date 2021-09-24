package negocio.producto;

public class TransferProducto {
	private int ID;
	private int talla;
	private double precio;
	private String nombre;
	private String color;
	private int stock;
	private int almacen;
	private int marca;
	private boolean activo;
	
	public TransferProducto(int id) {
		this.ID = id;
	}
	
	public TransferProducto(int talla, double precio, String nombre, String color, int stock, int almacen, int marca) {
			this.talla = talla;
			this.precio = precio;
			this.nombre = nombre;
			this.color = color;
			this.stock = stock;
			this.almacen = almacen;
			this.marca = marca;		
	}

	public TransferProducto(int ID, int talla, double precio, String nombre, String color, 
			int stock, int almacen, int marca, boolean activo) {
			this.ID = ID;
			this.talla = talla;
			this.precio = precio;
			this.nombre = nombre;
			this.color = color;
			this.stock = stock;
			this.almacen = almacen;
			this.marca = marca;
			this.activo = activo;
	}
	
	public TransferProducto(int ID, String nombre, double precio) {
		this.ID = ID;
		this.precio = precio;
		this.nombre = nombre;
	}

// GETTERS
	public int getID() {
	return ID;
	}
	
	public int getTalla() {
	return talla;
	}
	
	public double getPrecio() {
	return precio;
	}
	
	public String getNombre() {
	return nombre;
	}
	
	public String getColor() {
	return color;
	}
	
	public int getStock() {
	return stock;
	}
	
	public int getAlmacen() {
	return almacen;
	}
	
	public int getMarca() {
	return marca;
	}
	
	public boolean getActivo() {
	return activo;
	}
	
	@Override
	public String toString() {
		return "producto";
	}
}
