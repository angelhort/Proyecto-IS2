package integracion.dao.venta;

import negocio.venta.TransferVenta;

public interface DAOVenta {
	public int alta(TransferVenta transfer);
	public TransferVenta getVenta(int ID);
}
