package integracion.dao.marca;

import java.util.List;
import negocio.marca.TransferMarca;

public interface DAOMarca {
	public int alta(TransferMarca transfer);
	public int baja(int ID);
	public int modificar(TransferMarca transfer);
	public TransferMarca getMarca(int ID);
	public TransferMarca getMarca(String nombre);
	public List<TransferMarca> getAllMarcas();
	public int activarMarca(int id);
}
