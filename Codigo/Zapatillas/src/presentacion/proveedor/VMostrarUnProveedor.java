package presentacion.proveedor;

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

import negocio.proveedor.TransferProveedor;
import presentacion.controller.Controller;
import presentacion.controller.Evento;
import presentacion.vista.ComponentsBuilder;
import presentacion.vista.IGUI;

public class VMostrarUnProveedor extends JFrame implements IGUI{

	private static final long serialVersionUID = 1L;
	
	public VMostrarUnProveedor(){
		super("Foot World");
		this.setBounds(100, 100, 1000, 230);
		this.setContentPane(new JLabel(new ImageIcon("resources/1000x230.png")));
		this.setLayout(null); this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void initGUI(TransferProveedor proveedor) {
		JLabel label = ComponentsBuilder.createLabel("Mostrar Proveedor", 355, 20, 290, 50, Color.BLACK, new Font("Serif", Font.PLAIN, 30));
		this.add(label);
		
		JButton atrasButton = ComponentsBuilder.createBackButtonSmall();
		this.add(atrasButton);
		
		ActionListener lAtras = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VMostrarUnProveedor.this.dispose();
				Controller.getInstance().action(Evento.MostrarGUIProveedor, null);
			}
			
		};
		
		atrasButton.addActionListener(lAtras);
		
		String[] columnNames = {"ID", "Nombre", "Direccion", "Telefono", "Activo"};		
		JTable table = ComponentsBuilder.creteTable(1, 5, columnNames);
		
		table.setValueAt(proveedor.getID(), 0, 0);
		table.setValueAt(proveedor.getNombre(), 0, 1);
		table.setValueAt(proveedor.getDireccion(), 0, 2);
		table.setValueAt(proveedor.getTelefono(), 0, 3);
		table.setValueAt(proveedor.getActivo() ? "SI" : "NO", 0, 4);
	
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(50, 115, 900, 39);
		this.add(scroll);
	}

	@Override
	public void actualizar(int evento, Object datos) {
		switch(evento) {
		case Evento.MostrarUnProveedor:
			initGUI((TransferProveedor) datos);
			this.setVisible(true);
			break;
		case Evento.EntidadSiNoExiste:
			JOptionPane.showMessageDialog(this,"El Proveedor no existe", "ERROR Mostrar Un Proveedor", JOptionPane.ERROR_MESSAGE);
			this.dispose();
			Controller.getInstance().action(Evento.MostrarGUIProveedor, null);
			break;
		
		}
	}

}
