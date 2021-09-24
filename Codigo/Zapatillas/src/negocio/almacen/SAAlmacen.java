package negocio.almacen;

import java.util.List;

public interface SAAlmacen {
	public int alta(TransferAlmacen t);
	public int borrar(int id);
	public int modificar(TransferAlmacen t);
	public TransferAlmacen mostrarUno(int id);
	public List<TransferAlmacen> mostrarTodos();
}
