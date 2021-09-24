package presentacion.trabajador;

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

import negocio.trabajador.TransferTrabajador;
import presentacion.controller.Controller;
import presentacion.controller.Evento;
import presentacion.vista.ComponentsBuilder;
import presentacion.vista.IGUI;

public class VMostrarUnTrabajador extends JFrame implements IGUI{

	private static final long serialVersionUID = 1L;
	
	public VMostrarUnTrabajador() {
		super("Foot World");
		this.setBounds(100, 100, 1000, 230);
		this.setContentPane(new JLabel(new ImageIcon("resources/1000x230.png")));
		this.setLayout(null); this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void initGUI(TransferTrabajador trabajador) {
		JLabel label = ComponentsBuilder.createLabel("Mostrar Trabajador", 355, 20, 290, 50, Color.BLACK, new Font("Serif", Font.PLAIN, 30));
		this.add(label);
		
		JButton atrasButton = ComponentsBuilder.createBackButtonSmall();
		this.add(atrasButton);
		
		ActionListener lAtras = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VMostrarUnTrabajador.this.dispose();
				Controller.getInstance().action(Evento.MostrarGUITrabajador, null);
			}
			
		};
		
		atrasButton.addActionListener(lAtras);
		
		String[] columnNames = {"ID", "Nombre", "DNI", "Telefono", "Activo"};
		JTable table = ComponentsBuilder.creteTable(1, 5, columnNames);

		table.setValueAt(trabajador.getID(), 0, 0);
		table.setValueAt(trabajador.getNombre(), 0, 1);
		table.setValueAt(trabajador.getDNI(), 0, 2);
		table.setValueAt(trabajador.getTelefono(), 0, 3);
		table.setValueAt(trabajador.getActivo() ? "SI" : "NO", 0, 4);
	
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(50, 115, 900, 39);
		this.add(scroll);
	}

	@Override
	public void actualizar(int evento, Object datos) {
		switch(evento) {
		case Evento.MostrarUnTrabajador:
			initGUI((TransferTrabajador) datos);
			this.setVisible(true);
			break;
		case Evento.EntidadSiNoExiste:
			JOptionPane.showMessageDialog(this,"El Trabajador no existe", "ERROR Mostrar Un Trabajador", JOptionPane.ERROR_MESSAGE);
			this.dispose();
			Controller.getInstance().action(Evento.MostrarGUITrabajador, null);
			break;	
		}
	}


}
