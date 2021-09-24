package negocio.trabajador;

import java.util.List;

public interface SATrabajador {
	public int alta(TransferTrabajador t);
	public int borrar(int id);
	public int modificar(TransferTrabajador t);
	public TransferTrabajador mostrarUno(int id);
	public List<TransferTrabajador> mostrarTodos();
}
