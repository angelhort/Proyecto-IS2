package presentacion.almacen;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import negocio.almacen.TransferAlmacen;
import presentacion.controller.Controller;
import presentacion.controller.Evento;
import presentacion.vista.ComponentsBuilder;
import presentacion.vista.IGUI;

public class VMostrarTodosLosAlmacenes extends JFrame implements IGUI{

	private static final long serialVersionUID = 1L;

	public VMostrarTodosLosAlmacenes() {
		super("Foot World");
		this.setBounds(100, 100, 1010, 500);
		this.setContentPane(new JLabel(new ImageIcon("resources/1010x500.png")));
		this.setLayout(null); this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void initGUI(List<TransferAlmacen> almacenes) {
		JLabel label = ComponentsBuilder.createLabel("Mostrar todos los Almacenes", 325, 20, 350, 50, Color.BLACK, new Font("Serif", Font.PLAIN, 30));
		this.add(label);
		
		JButton atrasButton = ComponentsBuilder.createBackButtonSmall();
		this.add(atrasButton);
		
		ActionListener lAtras = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VMostrarTodosLosAlmacenes.this.dispose();
				Controller.getInstance().action(Evento.MostrarGUIAlmacen, null);
			}
			
		};
		
		atrasButton.addActionListener(lAtras);
		
		String[] columnNames = {"ID", "Direccion", "Telefono", "Capacidad", "Activo"};
		JTable table = ComponentsBuilder.creteTable(almacenes.size(), 5, columnNames);

		int i = 0;
		for(TransferAlmacen t: almacenes) {
			table.setValueAt(t.getID(), i, 0);
			table.setValueAt(t.getDireccion(), i, 1);
			table.setValueAt(t.getTelefono(), i, 2);
			table.setValueAt(t.getCapacidad(), i, 3);
			table.setValueAt(t.getActivo() ? "SI" : "NO", i, 4);
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
		case Evento.MostrarTodosLosAlmacenes:
			initGUI((List<TransferAlmacen>) datos);
			this.setVisible(true);
			break;
		}
	}


}
