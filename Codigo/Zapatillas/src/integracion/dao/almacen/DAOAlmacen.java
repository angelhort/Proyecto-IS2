package integracion.dao.almacen;

import java.util.List;

import negocio.almacen.TransferAlmacen;

public interface DAOAlmacen {
	public int alta(TransferAlmacen transfer);
	public int baja(int ID);
	public int modificar(TransferAlmacen transfer);
	public TransferAlmacen getAlmacen(int ID);
	public TransferAlmacen getAlmacen(String direccion);
	public List<TransferAlmacen> getAllAlmacenes();
	public int activarAlmacen(int id);
}
