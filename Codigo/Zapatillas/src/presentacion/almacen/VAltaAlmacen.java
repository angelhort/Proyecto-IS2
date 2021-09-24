package presentacion.almacen;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import negocio.almacen.TransferAlmacen;
import presentacion.controller.Controller;
import presentacion.controller.Evento;
import presentacion.vista.ComponentsBuilder;
import presentacion.vista.IGUI;

public class VAltaAlmacen extends JFrame implements IGUI{

	private static final long serialVersionUID = 1L;
	
	public VAltaAlmacen() {
		super("Foot World");
		this.setBounds(100, 100, 330, 350);
		this.setContentPane(new JLabel(new ImageIcon("resources/330x350.png")));
		this.setLayout(null); this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void initGUI() {
		JLabel labelAlmacen = ComponentsBuilder.createLabel("Alta Almacen", 85, 20, 190, 50, Color.BLACK, new Font("Serif", Font.PLAIN, 30));
		this.add(labelAlmacen);
		
		JLabel labelDireccion = ComponentsBuilder.createLabel("Direccion:", 10, 100, 60, 20, Color.BLACK, new Font("Serif", Font.PLAIN, 14));
		this.add(labelDireccion);
		
		JTextField fieldDireccion = ComponentsBuilder.createTextField(80, 100, 220, 20);
		this.add(fieldDireccion);
		
		JLabel labelTelef = ComponentsBuilder.createLabel("Telefono:", 10, 150, 60, 20, Color.BLACK, new Font("Serif", Font.PLAIN, 14));
		this.add(labelTelef);
		
		JTextField fieldTelef = ComponentsBuilder.createTextField(80, 150, 220, 20);
		this.add(fieldTelef);
		
		JLabel labelCapacidad = ComponentsBuilder.createLabel("Capacidad:", 10, 200, 60, 20, Color.BLACK, new Font("Serif", Font.PLAIN, 12));
		this.add(labelCapacidad);
		
		JSpinner spinnerCapacidad = ComponentsBuilder.createSpinner(80, 202, 50, 20);
		this.add(spinnerCapacidad);
		
		JButton atrasButton = ComponentsBuilder.createBackButtonSmall();
		this.add(atrasButton);
		
		ActionListener lAtras = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().action(Evento.MostrarGUIAlmacen, null);
				VAltaAlmacen.this.dispose();
			}
			
		};
		
		atrasButton.addActionListener(lAtras);
		
		JButton aceptarButton = ComponentsBuilder.createButton("Aceptar", 115, 250, 100, 30, new Font("Serif", Font.PLAIN, 14));
		this.add(aceptarButton);
		
		ActionListener lAceptar = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Controller.getInstance().action(Evento.AltaAlmacen, new TransferAlmacen(Integer.parseInt(fieldTelef.getText()), (int)spinnerCapacidad.getValue(), fieldDireccion.getText()));
					VAltaAlmacen.this.dispose();
				}
				catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Telefono almacen tiene que ser un numero entero", "ERROR Alta Almacen", JOptionPane.ERROR_MESSAGE);
				}
			}
			
		};
		
		aceptarButton.addActionListener(lAceptar);
	}

	@Override
	public void actualizar(int evento, Object datos) {
		switch(evento) {
			case Evento.GUIAltaAlmacen:
				initGUI();
				this.setVisible(true);
				break;
			
			case Evento.EntidadSiNoExiste:
				JOptionPane.showMessageDialog(this,"El almacen ya estaba registrado y activo", "Alta Almacen", JOptionPane.INFORMATION_MESSAGE);
				this.dispose();
				break;
			case Evento.WrongDataInput:
				JOptionPane.showMessageDialog(this,"Los datos fueron mal introducidos", "ERROR Alta Almacen", JOptionPane.ERROR_MESSAGE);
				this.dispose();
				break;
			case Evento.ActivacionEntidad:
				JOptionPane.showMessageDialog(this,"El almacen ya existia y se ha activado", "Alta Almacen", JOptionPane.INFORMATION_MESSAGE);
				this.dispose();
				break;
			default:
				JOptionPane.showMessageDialog(this,"El almacen se dio de alta con ID: " + datos, "Alta Almacen", JOptionPane.INFORMATION_MESSAGE);
				this.dispose();
		}
	}
}
