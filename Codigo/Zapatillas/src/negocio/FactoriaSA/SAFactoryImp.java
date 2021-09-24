package negocio.FactoriaSA;

import negocio.almacen.SAAlmacen;
import negocio.almacen.SAAlmacenImp;
import negocio.cliente.SACliente;
import negocio.cliente.SAClienteImp;
import negocio.marca.SAMarca;
import negocio.marca.SAMarcaImp;
import negocio.producto.SAProducto;
import negocio.producto.SAProductoImp;
import negocio.proveedor.SAProveedor;
import negocio.proveedor.SAProveedorImp;
import negocio.proveedor_producto.SAProductoProveedor;
import negocio.proveedor_producto.SAProductoProveedorImp;
import negocio.trabajador.SATrabajador;
import negocio.trabajador.SATrabajadorImp;
import negocio.venta.SAVenta;
import negocio.venta.SAVentaImp;

public class SAFactoryImp  extends SAAbstractFactory {

	@Override
	public SACliente getSACliente() {
		return new SAClienteImp();
	}
	
	@Override
	public SAProveedor getSAProveedor() {
		return new SAProveedorImp();
	}

	@Override
	public SAMarca getSAMarca() {
		return new SAMarcaImp();
	}

	@Override
	public SATrabajador getSATrabajador() {
		return new SATrabajadorImp();
	}

	@Override
	public SAAlmacen getSAAlmacen() {
		return new SAAlmacenImp();
	}

	@Override
	public SAProducto getSAProducto() {
		return new SAProductoImp();
	}

	@Override
	public SAProductoProveedor getSAProductoProveedor() {
		return new SAProductoProveedorImp();
	}

	@Override
	public SAVenta getSAVenta() {
		return new SAVentaImp();
	}
}
