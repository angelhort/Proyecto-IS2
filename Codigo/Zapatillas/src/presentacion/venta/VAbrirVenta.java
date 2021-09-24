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
import javax.swing.JTextField;

import negocio.cliente.TransferCliente;
import negocio.trabajador.TransferTrabajador;
import negocio.venta.TransferVenta;
import presentacion.controller.Controller;
import presentacion.controller.Evento;
import presentacion.vista.ComponentsBuilder;
import presentacion.vista.IGUI;

public class VAbrirVenta extends JFrame implements IGUI{

	private static final long serialVersionUID = 1L;
	
	public VAbrirVenta() {
		super("Foot World");
		this.setBounds(100, 100, 330, 350);
		this.setContentPane(new JLabel(new ImageIcon("resources/330x350.png")));
		this.setLayout(null); this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initGUI();
	}
	
	private void initGUI() {
		JLabel labelVenta = ComponentsBuilder.createLabel("Abrir Venta", 85, 20, 190, 50, Color.BLACK, new Font("Serif", Font.PLAIN, 30));
		this.add(labelVenta);
		
		JLabel labelCliente = ComponentsBuilder.createLabel("ID Cliente:", 10, 115, 120, 20, Color.BLACK, new Font("Serif", Font.PLAIN, 14));
		this.add(labelCliente);
		
		JTextField fieldCliente = ComponentsBuilder.createTextField(140, 115, 160, 20);
		this.add(fieldCliente);
		
		JLabel labelTrabajador = ComponentsBuilder.createLabel("ID Trabjador:", 10, 185, 120, 20, Color.BLACK, new Font("Serif", Font.PLAIN, 14));
		this.add(labelTrabajador);
		
		JTextField fieldTrabajador = ComponentsBuilder.createTextField(140, 185, 160, 20);
		this.add(fieldTrabajador);
		
		JButton atrasButton = ComponentsBuilder.createBackButtonSmall();
		this.add(atrasButton);
		
		ActionListener lAtras = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VAbrirVenta.this.dispose();
				Controller.getInstance().action(Evento.MostrarGUIPrincipal, null);
			}
			
		};
		
		atrasButton.addActionListener(lAtras);
		
		JButton aceptarButton = ComponentsBuilder.createButton("Aceptar", 115, 250, 100, 30, new Font("Serif", Font.PLAIN, 14));
		this.add(aceptarButton);
		
		ActionListener lAceptar = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Controller.getInstance().action(Evento.MostrarGUIVenta, new TransferVenta(new TransferCliente(Integer.parseInt(fieldCliente.getText())), 
							new TransferTrabajador(Integer.parseInt(fieldTrabajador.getText()))));
					VAbrirVenta.this.dispose();					
				}
				catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Los IDs tienen que ser numeros enteros", "ERROR Abrir Venta", JOptionPane.ERROR_MESSAGE);
				}
			}
			
		};
		
		aceptarButton.addActionListener(lAceptar);
	}

	@Override
	public void actualizar(int evento, Object datos) {
		switch(evento) {
			case Evento.GUIAbrirVenta:
			this.setVisible(true);
		break;
	}
	}
}