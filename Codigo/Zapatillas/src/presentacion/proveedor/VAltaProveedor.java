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
import javax.swing.JTextField;

import negocio.proveedor.TransferProveedor;
import presentacion.controller.Controller;
import presentacion.controller.Evento;
import presentacion.vista.ComponentsBuilder;
import presentacion.vista.IGUI;

public class VAltaProveedor extends JFrame implements IGUI{

	private static final long serialVersionUID = 1L;
	
	public VAltaProveedor() {
		super("Foot World");
		this.setBounds(100, 100, 330, 350);
		this.setContentPane(new JLabel(new ImageIcon("resources/330x350.png")));
		this.setLayout(null); this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void initGUI() {
		JLabel labelProveedor = ComponentsBuilder.createLabel("Alta Proveedor", 85, 20, 190, 50, Color.BLACK, new Font("Serif", Font.PLAIN, 30));
		this.add(labelProveedor);
		
		JLabel labelDireccion = ComponentsBuilder.createLabel("Direccion:", 10, 100, 60, 20, Color.BLACK, new Font("Serif", Font.PLAIN, 14));
		this.add(labelDireccion);
		
		JTextField fieldDireccion = ComponentsBuilder.createTextField(80, 100, 220, 20);
		this.add(fieldDireccion);
		
		JLabel labelTelef = ComponentsBuilder.createLabel("Telefono:", 10, 150, 60, 20, Color.BLACK, new Font("Serif", Font.PLAIN, 14));
		this.add(labelTelef);
		
		JTextField fieldTelef = ComponentsBuilder.createTextField(80, 150, 220, 20);
		this.add(fieldTelef);
		
		JLabel labelNombre = ComponentsBuilder.createLabel("Nombre:", 10, 200, 60, 20, Color.BLACK, new Font("Serif", Font.PLAIN, 14));
		this.add(labelNombre);
		
		JTextField fieldNombre = ComponentsBuilder.createTextField(80, 200, 220, 20);
		this.add(fieldNombre);
		
		JButton atrasButton = ComponentsBuilder.createBackButtonSmall();
		this.add(atrasButton);
		
		ActionListener lAtras = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().action(Evento.MostrarGUIProveedor, null);
				VAltaProveedor.this.dispose();
			}
			
		};
		
		atrasButton.addActionListener(lAtras);
		
		JButton aceptarButton = ComponentsBuilder.createButton("Aceptar", 115, 250, 100, 30, new Font("Serif", Font.PLAIN, 14));
		this.add(aceptarButton);
		
		ActionListener lAceptar = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Controller.getInstance().action(Evento.AltaProveedor, new TransferProveedor(
							Integer.parseInt(fieldTelef.getText()),
							fieldDireccion.getText(), 
							fieldNombre.getText()));					
					VAltaProveedor.this.dispose();
				}
				catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Telefono proveedor tiene que ser un numero entero", "ERROR Alta proveedor", JOptionPane.ERROR_MESSAGE);
				}
			}
			
		};
		
		aceptarButton.addActionListener(lAceptar);
	}

	@Override
	public void actualizar(int evento, Object datos) {
		switch(evento) {
		case Evento.GUIAltaProveedor:
			initGUI();
			this.setVisible(true);
			break;
		
		case Evento.EntidadSiNoExiste:
			JOptionPane.showMessageDialog(this,"El Proveedor ya estaba registrado y activo", "Alta Proveedor", JOptionPane.INFORMATION_MESSAGE);
			this.dispose();
			break;
		case Evento.WrongDataInput:
			JOptionPane.showMessageDialog(this,"Los datos fueron mal introducidos", "ERROR Alta Proveedor", JOptionPane.ERROR_MESSAGE);
			this.dispose();
			break;
		case Evento.ActivacionEntidad:
			JOptionPane.showMessageDialog(this,"El Proveedor ya existia y se ha activado", "Alta Proveedor", JOptionPane.INFORMATION_MESSAGE);
			this.dispose();
			break;
		default:
			JOptionPane.showMessageDialog(this,"El Proveedor se dio de alta con ID: " + datos, "Alta Proveedor", JOptionPane.INFORMATION_MESSAGE);
			this.dispose();
	}
	}
}
