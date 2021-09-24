package negocio.producto;

import java.util.List;

public interface SAProducto {
	public int alta(TransferProducto t);
	public int borrar(int id);
	public int modificar(TransferProducto t);
	public TransferProducto mostrarUno(int id);
	public List<TransferProducto> mostrarTodos();
}
