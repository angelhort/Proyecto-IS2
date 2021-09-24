package presentacion.venta;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import negocio.venta.TProductoEnFactura;
import negocio.venta.TransferVenta;
import presentacion.controller.Controller;
import presentacion.controller.Evento;
import presentacion.vista.ComponentsBuilder;
import presentacion.vista.IGUI;

public class VCarrito extends JFrame implements IGUI{

	private static final long serialVersionUID = 1L;

	public VCarrito() {
		super("Foot World");
		this.setBounds(100, 100, 1010, 500);
		this.setContentPane(new JLabel(new ImageIcon("resources/1010x500.png")));
		this.setLayout(null); this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void initGUI(TransferVenta venta) {
		JLabel label = ComponentsBuilder.createLabel("Carrito del cliente: " + venta.getCliente().getID() + " por el trabajador: " + 
											venta.getTrabajador().getID(), 250, 20, 500, 50, Color.BLACK, new Font("Serif", Font.PLAIN, 30));
		this.add(label);
		
		String[] columnNames = {"ID Producto", "Unidades"};
		JTable table = ComponentsBuilder.creteTable(venta.getProductos().size(), 2, columnNames);
		
		int i = 0;
		
		for(TProductoEnFactura p : venta.getProductos()) {
			table.setValueAt(p.getProducto().getID(), i, 0);
			table.setValueAt(p.getUnidades(), i, 1);
			i++;
		}
		
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(50, 115, 900, 288);
		this.add(scroll);
		
		JButton atrasButton = ComponentsBuilder.createBackButtonSmall();
		this.add(atrasButton);
		
		ActionListener lAtras = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().action(Evento.MostrarGUIVenta, venta);
				VCarrito.this.dispose();
			}
			
		};
		
		atrasButton.addActionListener(lAtras);
	}
	
	@Override
	public void actualizar(int evento, Object datos) {
		switch(evento) {
		case Evento.MostrarCarrito:
			initGUI((TransferVenta) datos);
			this.setVisible(true);
			break;
		}
	}


}
