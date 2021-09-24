package integracion.dao.proveedor;

import java.util.List;

import negocio.proveedor.TransferProveedor;

public interface DAOProveedor {

	public int alta(TransferProveedor transfer);
	public int baja(int ID);
	public int modificar(TransferProveedor transfer);
	public TransferProveedor getProveedor(int ID);
	public TransferProveedor getProveedor(String direccion);
	public List<TransferProveedor> getAllProveedores();
	public int activarProveedor(int id);
}
