package negocio.proveedor;

import java.util.List;

public interface SAProveedor {

	public int alta(TransferProveedor t);
	public int borrar(int id);
	public int modificar(TransferProveedor t);
	public TransferProveedor mostrarUno(int id);
	public List<TransferProveedor> mostrarTodos();
}
