package integracion.dao.trabajador;

import java.util.List;

import negocio.trabajador.TransferTrabajador;

public interface DAOTrabajador {
	public int alta(TransferTrabajador transfer);
	public int baja(int ID);
	public int modificar(TransferTrabajador transfer);
	public int activarTrabajador(int id);
	public TransferTrabajador getTrabajador(int ID);
	public TransferTrabajador getTrabajador(String DNI);
	public List<TransferTrabajador> getAllTrabajadores();
}
