package negocio.marca;

import java.util.List;

public interface SAMarca {
	public int alta(TransferMarca t);
	public int borrar(int id);
	public int modificar(TransferMarca t);
	public TransferMarca mostrarUno(int id);
	public List<TransferMarca> mostrarTodos();
}
