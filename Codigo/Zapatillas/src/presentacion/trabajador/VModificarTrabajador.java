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
import javax.swing.JTextField;

import negocio.trabajador.TransferTrabajador;
import presentacion.controller.Controller;
import presentacion.controller.Evento;
import presentacion.vista.ComponentsBuilder;
import presentacion.vista.IGUI;

public class VModificarTrabajador extends JFrame implements IGUI{

	private static final long serialVersionUID = 1L;
	
	public VModificarTrabajador() {
		super("Foot World");
		this.setBounds(100, 100, 330, 350);
		this.setContentPane(new JLabel(new ImageIcon("resources/330x350.png")));
		this.setLayout(null); this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void initGUI(TransferTrabajador trabajador) {

		JLabel labelTrabajador = ComponentsBuilder.createLabel("Modificar Trabajador", 85, 20, 190, 50, Color.BLACK, new Font("Serif", Font.PLAIN, 30));
		this.add(labelTrabajador);
		
		JLabel labelNombre = ComponentsBuilder.createLabel("Nombre:", 10, 100, 60, 20, Color.BLACK, new Font("Serif", Font.PLAIN, 14));
		this.add(labelNombre);
		
		JTextField fieldNombre = ComponentsBuilder.createTextField(80, 100, 220, 20);
		fieldNombre.setText(trabajador.getNombre());
		this.add(fieldNombre);
		
		JLabel labelTelef = ComponentsBuilder.createLabel("Telefono:", 10, 150, 60, 20, Color.BLACK, new Font("Serif", Font.PLAIN, 14));
		this.add(labelTelef);
		
		JTextField fieldTelef = ComponentsBuilder.createTextField(80, 150, 220, 20);
		fieldTelef.setText(trabajador.getTelefono() + "");
		this.add(fieldTelef);
		
		JLabel labelDNI = ComponentsBuilder.createLabel("DNI:", 10, 200, 60, 20, Color.BLACK, new Font("Serif", Font.PLAIN, 14));
		this.add(labelDNI);
		
		JTextField fieldDNI = ComponentsBuilder.createTextField(80, 200, 220, 20);
		fieldDNI.setText(trabajador.getDNI());
		this.add(fieldDNI);
		
		JButton atrasButton = ComponentsBuilder.createBackButtonSmall();
		this.add(atrasButton);
		
		ActionListener lAtras = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().action(Evento.MostrarGUITrabajador, null);
				VModificarTrabajador.this.dispose();
			}
			
		};
		
		atrasButton.addActionListener(lAtras);
		
		JButton aceptarButton = ComponentsBuilder.createButton("Aceptar", 115, 250, 100, 30, new Font("Serif", Font.PLAIN, 14));
		this.add(aceptarButton);
		
		ActionListener lAceptar = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Controller.getInstance().action(Evento.ModificarTrabajador, new TransferTrabajador(trabajador.getID(), Integer.parseInt(fieldTelef.getText()), fieldDNI.getText(), fieldNombre.getText(), trabajador.getActivo()));					
					VModificarTrabajador.this.dispose();
				}
				catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Telefono trabajador tiene que ser un numero entero", "ERROR Modificar Trabajador", JOptionPane.ERROR_MESSAGE);
				}
			}
			
		};
		
		aceptarButton.addActionListener(lAceptar);
	}

	@Override
	public void actualizar(int evento, Object datos) {
		switch(evento){
		case Evento.MostrarModificarTrabajador:
			initGUI((TransferTrabajador) datos);	
			this.setVisible(true);
			break;
		case Evento.WrongDataInput:
			JOptionPane.showMessageDialog(this,"Los datos fueron mal introducidos", "ERROR Modificar Trabajador", JOptionPane.ERROR_MESSAGE);
			this.dispose();
			break;
		case Evento.EntidadSiNoExiste:
			JOptionPane.showMessageDialog(this,"El Trabajador no existe", "ERROR Modificar Trabajador", JOptionPane.ERROR_MESSAGE);
			Controller.getInstance().action(Evento.MostrarGUITrabajador, null);
			this.dispose();
			break;
		case Evento.ClaveEntidadYaExistente:
			JOptionPane.showMessageDialog(this,"Ya hay un Trabajador registrado con ese DNI", "ERROR Modificar Trabajador", JOptionPane.ERROR_MESSAGE);
			this.dispose();
			break;
		default: JOptionPane.showMessageDialog(this,"El Trabajador se modifico con exito", "Modificar Trabajador", JOptionPane.INFORMATION_MESSAGE);
		this.dispose();
		}
	}
}
