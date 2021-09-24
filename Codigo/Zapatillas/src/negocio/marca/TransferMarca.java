package negocio.marca;

public class TransferMarca {
	
	private int ID;
	private String nombre;
	private boolean activo;
	
	public TransferMarca(String nombre) {
		this.nombre = nombre;
	}
	
	public TransferMarca(int ID, String nombre, boolean activo) {
		this.ID = ID;
		this.nombre = nombre;
		this.activo = activo;
	}
	
	// GETTERS
	public int getID() {
		return ID;
	}
	
	public String getNombre() {
		return nombre;
	}
 	
	public boolean getActivo() {
		return activo;
	}
}
