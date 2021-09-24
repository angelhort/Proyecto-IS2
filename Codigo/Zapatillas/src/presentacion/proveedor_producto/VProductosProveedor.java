package presentacion.proveedor_producto;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import negocio.producto.TransferProducto;
import presentacion.controller.Controller;
import presentacion.controller.Evento;
import presentacion.vista.ComponentsBuilder;
import presentacion.vista.IGUI;

public class VProductosProveedor extends JFrame implements IGUI{
	
	private static final long serialVersionUID = 1L;
	
	public VProductosProveedor() {
		super("Foot World");
		this.setBounds(100, 100, 1010, 500);
		this.setContentPane(new JLabel(new ImageIcon("resources/1010x500.png")));
		this.setLayout(null); this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void initGUI(List<TransferProducto> productos) {
		JLabel label = ComponentsBuilder.createLabel("Productos del proveedor", 325, 20, 350, 50, Color.BLACK, new Font("Serif", Font.PLAIN, 30));
		this.add(label);
		
		JButton atrasButton = ComponentsBuilder.createBackButtonSmall();
		this.add(atrasButton);
		
		ActionListener lAtras = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VProductosProveedor.this.dispose();
				Controller.getInstance().action(Evento.MostrarGUIProveedorProducto, null);
			}
			
		};
		
		atrasButton.addActionListener(lAtras);
		
		String[] columnNames = {"ID", "Nombre Producto", "Precio Suministro"};
		JTable table = ComponentsBuilder.creteTable(productos.size(), 3, columnNames);
				
		int i = 0;
		for(TransferProducto t: productos) {
			table.setValueAt(t.getID(), i, 0);
			table.setValueAt(t.getNombre(), i, 1);
			table.setValueAt(t.getPrecio(), i, 2);
			i++;
		}
			
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(50, 115, 900, 288);
		this.add(scroll);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void actualizar(int evento, Object datos) {
		switch(evento) {
		case Evento.MostrarProductosProveedor:
			initGUI((List<TransferProducto>) datos);
			this.setVisible(true);
			break;
		case Evento.EntidadSiNoExiste:
			JOptionPane.showMessageDialog(this,"El proveedor no esta registrado en la BD o esta inactivo", "ERROR Mostrar productos proveedor", JOptionPane.ERROR_MESSAGE);
			Controller.getInstance().action(Evento.MostrarGUIProveedorProducto, null);
			this.dispose();
			break;
		}
	}
}
