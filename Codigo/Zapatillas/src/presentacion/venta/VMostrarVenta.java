package presentacion.venta;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import negocio.venta.TProductoEnFactura;
import negocio.venta.TransferVenta;
import presentacion.controller.Controller;
import presentacion.controller.Evento;
import presentacion.vista.ComponentsBuilder;
import presentacion.vista.IGUI;

public class VMostrarVenta extends JFrame implements IGUI{

	private static final long serialVersionUID = 1L;
	
	public VMostrarVenta() {
		super("Foot World");
		this.setBounds(100, 100, 1010, 500);
		this.setContentPane(new JLabel(new ImageIcon("resources/1010x500.png")));
		this.setLayout(null); this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void initGUI(TransferVenta venta) {
		JLabel label = ComponentsBuilder.createLabel("Mostrar venta", 325, 20, 350, 50, Color.BLACK, new Font("Serif", Font.PLAIN, 30));
		this.add(label);
		
		JLabel labelInfo = ComponentsBuilder.createLabel("Factura del cliente con ID: " + venta.getCliente().getID() + ", realizadad por el trabajador con ID: "
				+ venta.getTrabajador().getID(), 10, 70, 400, 50, Color.BLACK, new Font("Serif", Font.PLAIN, 14));
		this.add(labelInfo);
		
		JButton atrasButton = ComponentsBuilder.createBackButtonSmall();
		this.add(atrasButton);
		
		ActionListener lAtras = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VMostrarVenta.this.dispose();
				Controller.getInstance().action(Evento.MostrarGUIPrincipal, null);
			}
			
		};
		
		atrasButton.addActionListener(lAtras);
		
		String[] columnNames = {"ID Producto", "Unidades", "Precio"};
		JTable table = ComponentsBuilder.creteTable(venta.getProductos().size(), 3, columnNames);
		
		int i = 0;
		for(TProductoEnFactura p: venta.getProductos()) {
			table.setValueAt(p.getProducto().getID(), i, 0);
			table.setValueAt(p.getUnidades(), i, 1);
			table.setValueAt(p.getPrecio(), i, 2);
			i++;
		}
			
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(50, 120, 900, 288);
		this.add(scroll);
		
		JLabel labelExtraInfo = ComponentsBuilder.createLabel("Precio total: " + venta.getPrecioTotal() + 
				"   Fecha: " + venta.getFecha(), 0, 405, 400, 50, Color.BLACK, new Font("Serif", Font.PLAIN, 14));
		this.add(labelExtraInfo);
	}
	
	@Override
	public void actualizar(int evento, Object datos) {
		switch(evento) {
		case Evento.MostrarVenta:
			initGUI((TransferVenta)datos);
			this.setVisible(true);
			break;
		case Evento.EntidadSiNoExiste:
			JOptionPane.showMessageDialog(this,"La venta no existe", "ERROR Mostrar Venta", JOptionPane.ERROR_MESSAGE);
			Controller.getInstance().action(Evento.MostrarGUIPrincipal, null);
			break;
		}

	}
}
