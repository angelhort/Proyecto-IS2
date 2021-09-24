package negocio.trabajador;

public class TransferTrabajador {

	private int ID;
	private int telefono;
	private String DNI;
	private String nombre;
	private boolean activo;
	
	public TransferTrabajador(int ID) {
		this.ID = ID;
	}
	
	public TransferTrabajador(int telefono, String DNI, String nombre) {
		this.telefono = telefono;
		this.DNI = DNI;
		this.nombre = nombre;
	}

	public TransferTrabajador(int ID, int telefono, String DNI, String nombre, boolean activo) {
		this.ID = ID;
		this.telefono = telefono;
		this.DNI = DNI;
		this.nombre = nombre;
		this.activo = activo;
	}
	
	// GETTERS
	public int getID() {
		return ID;
	}
	
	public int getTelefono() {
		return telefono;
	}
	
	public String getDNI() {
		return DNI;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public boolean getActivo() {
		return activo;
	}
}
