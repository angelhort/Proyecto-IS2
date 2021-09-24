package presentacion.marca;

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

import negocio.marca.TransferMarca;
import presentacion.controller.Controller;
import presentacion.controller.Evento;
import presentacion.vista.ComponentsBuilder;
import presentacion.vista.IGUI;

public class VMostrarUnaMarca extends JFrame implements IGUI{
	private static final long serialVersionUID = 1L;
	
	public VMostrarUnaMarca(){
		super("Foot World");
		this.setBounds(100, 100, 1000, 230);
		this.setContentPane(new JLabel(new ImageIcon("resources/1000x230.png")));
		this.setLayout(null); this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void initGUI(TransferMarca marca) {
		JLabel label = ComponentsBuilder.createLabel("Mostrar Marca", 405, 20, 190, 50, Color.BLACK, new Font("Serif", Font.PLAIN, 30));
		this.add(label);
		
		JButton atrasButton = ComponentsBuilder.createBackButtonSmall();
		this.add(atrasButton);
		
		ActionListener lAtras = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VMostrarUnaMarca.this.dispose();
				Controller.getInstance().action(Evento.MostrarGUIMarca, null);
			}
			
		};

		atrasButton.addActionListener(lAtras);
		
		String[] columnNames = {"ID", "Nombre", "Activo"};
		JTable table = ComponentsBuilder.creteTable(1, 3, columnNames);
		
		table.setValueAt(marca.getID(), 0, 0);
		table.setValueAt(marca.getNombre(), 0, 1);
		table.setValueAt(marca.getActivo() ? "SI" : "NO", 0, 2);
		
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(50, 115, 900, 39);
		this.add(scroll);
	}
	
	@Override
	public void actualizar(int evento, Object datos) {
		switch(evento) {
			case Evento.MostrarUnaMarca:
				initGUI((TransferMarca) datos);
				this.setVisible(true);
				break;
				
			case Evento.EntidadSiNoExiste:
				JOptionPane.showMessageDialog(this,"El Marca no existe", "ERROR Mostrar Un Marca", JOptionPane.ERROR_MESSAGE);
				this.dispose();
				Controller.getInstance().action(Evento.MostrarGUIMarca, null);
				break;
			
		}
	}


}
