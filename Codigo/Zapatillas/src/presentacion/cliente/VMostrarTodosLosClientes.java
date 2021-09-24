package presentacion.cliente;

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

import negocio.cliente.TransferCliente;
import presentacion.controller.Controller;
import presentacion.controller.Evento;
import presentacion.vista.ComponentsBuilder;
import presentacion.vista.IGUI;

public class VMostrarTodosLosClientes extends JFrame implements IGUI {

	private static final long serialVersionUID = 1L;
	
	public VMostrarTodosLosClientes() {
		super("Foot World");
		this.setBounds(100, 100, 1010, 500);
		this.setContentPane(new JLabel(new ImageIcon("resources/1010x500.png")));
		this.setLayout(null); this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void initGUI(List<TransferCliente> clientes) {
		JLabel label = ComponentsBuilder.createLabel("Mostrar todos los Clientes", 325, 20, 350, 50, Color.BLACK, new Font("Serif", Font.PLAIN, 30));
		this.add(label);
		
		JButton atrasButton = ComponentsBuilder.createBackButtonSmall();
		this.add(atrasButton);
		
		ActionListener lAtras = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VMostrarTodosLosClientes.this.dispose();
				Controller.getInstance().action(Evento.MostrarGUICliente, null);
			}
			
		};
		
		atrasButton.addActionListener(lAtras);
		
		String[] columnNames = {"ID", "Nombre", "DNI", "Socio", "Activo"};
		JTable table = ComponentsBuilder.creteTable(clientes.size(), 5, columnNames);
				
		int i = 0;
		for(TransferCliente t: clientes) {
			table.setValueAt(t.getID(), i, 0);
			table.setValueAt(t.getNombre(), i, 1);
			table.setValueAt(t.getDNI(), i, 2);
			table.setValueAt(t.isSocio() ? "SI" : "NO", i, 3);
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
		case Evento.MostrarTodosLosClientes:
			initGUI((List<TransferCliente>) datos);
			this.setVisible(true);
			break;
		}
	}
}
