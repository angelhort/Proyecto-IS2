package negocio.venta;

import java.util.ArrayList;
import java.util.List;

import negocio.cliente.TransferCliente;
import negocio.trabajador.TransferTrabajador;

public class TransferVenta {
	
	private TransferCliente cliente;
	private TransferTrabajador trabajador;
	private List<TProductoEnFactura> productos;
	private double precioTotal;
	private String fecha;
	
	public TransferVenta(TransferCliente cliente, 
						 TransferTrabajador trabajador) {
		this.cliente = cliente;
		this.trabajador = trabajador;
		this.productos = new ArrayList<>();
	}
	
	public TransferVenta(TransferCliente cliente, 
			 TransferTrabajador trabajador, String fecha) {
			this.cliente = cliente;
			this.trabajador = trabajador;
			this.fecha = fecha;
			this.productos = new ArrayList<>();
	}
	
	public void addProduct(TProductoEnFactura producto) {
		productos.add(producto);
	}
	
	public boolean removeProduct(int id) {
		int i = 0;
		for(TProductoEnFactura p : productos) {
			if(p.isEqual(id)) {
				productos.remove(i);
				return true;
			}
			i++;
		}
		
		return false;
	}
	
	public TransferCliente getCliente() {
		return cliente;
	}
	
	public TransferTrabajador getTrabajador() {
		return trabajador;
	}
	
	public List<TProductoEnFactura> getProductos() {
		return productos;
	}
	
	public void setPrecioTotal() {
		for(TProductoEnFactura p : productos) {
			precioTotal += p.getPrecio();
		}
		precioTotal = Math.round(precioTotal * 100)/100;
	}
	
	public String getFecha() {
		return fecha;
	}
	
	public double getPrecioTotal() {
		return precioTotal;
	}
}
