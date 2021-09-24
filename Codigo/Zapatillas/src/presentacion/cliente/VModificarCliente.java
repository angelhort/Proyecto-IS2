package presentacion.cliente;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import negocio.cliente.TransferCliente;
import presentacion.controller.Controller;
import presentacion.controller.Evento;
import presentacion.vista.ComponentsBuilder;
import presentacion.vista.IGUI;

public class VModificarCliente extends JFrame implements IGUI{

	private static final long serialVersionUID = 1L;
	
	public VModificarCliente() {
		super("Foot World");
		this.setBounds(100, 100, 330, 350);
		this.setContentPane(new JLabel(new ImageIcon("resources/330x350.png")));
		this.setLayout(null); this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void initGUI(TransferCliente cliente) {
		JLabel labelCliente = ComponentsBuilder.createLabel("Modificar Cliente", 85, 20, 190, 50, Color.BLACK, new Font("Serif", Font.PLAIN, 30));
		this.add(labelCliente);
		
		JLabel labelNombre = ComponentsBuilder.createLabel("Nombre:", 10, 100, 60, 20, Color.BLACK, new Font("Serif", Font.PLAIN, 14));
		this.add(labelNombre);
		
		JTextField fieldNombre = ComponentsBuilder.createTextField(80, 100, 220, 20);
		fieldNombre.setText(cliente.getNombre());
		this.add(fieldNombre);
		
		JLabel labelDNI = ComponentsBuilder.createLabel("DNI:", 10, 150, 60, 20, Color.BLACK, new Font("Serif", Font.PLAIN, 14));
		this.add(labelDNI);
		
		JTextField fieldDNI = ComponentsBuilder.createTextField(80, 150, 220, 20);
		fieldDNI.setText(cliente.getDNI());
		this.add(fieldDNI);
		
		JLabel labelSocio = ComponentsBuilder.createLabel("Socio:", 10, 200, 60, 20, Color.BLACK, new Font("Serif", Font.PLAIN, 14));
		this.add(labelSocio);
		
		JCheckBox checkSocio = ComponentsBuilder.createCheckBox(80, 197, 30, 30);
		checkSocio.setSelected(cliente.isSocio());
		this.add(checkSocio);
		
		JButton atrasButton = ComponentsBuilder.createBackButtonSmall();
		this.add(atrasButton);
		
		ActionListener lAtras = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VModificarCliente.this.dispose();
				Controller.getInstance().action(Evento.MostrarGUICliente, null);
			}
			
		};
		
		atrasButton.addActionListener(lAtras);
		
		JButton aceptarButton = ComponentsBuilder.createButton("Aceptar", 115, 250, 100, 30, new Font("Serif", Font.PLAIN, 14));
		this.add(aceptarButton);
		
		ActionListener lAceptar = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().action(Evento.ModificarCliente,  new TransferCliente(cliente.getID(), fieldNombre.getText(), checkSocio.isSelected(), fieldDNI.getText(), cliente.getActivo()));
				VModificarCliente.this.dispose();
			}
			
		};
		
		aceptarButton.addActionListener(lAceptar);
	}

	@Override
	public void actualizar(int evento, Object datos) {
		switch(evento){
		case Evento.MostrarModificarCliente:
			initGUI((TransferCliente) datos);	
			this.setVisible(true);
			break;
		case Evento.WrongDataInput:
			JOptionPane.showMessageDialog(this,"Los datos fueron mal introducidos", "ERROR Modificar Cliente", JOptionPane.ERROR_MESSAGE);
			this.dispose();
			break;
		case Evento.EntidadSiNoExiste:
			JOptionPane.showMessageDialog(this,"El cliente no existe", "ERROR Modificar Cliente", JOptionPane.ERROR_MESSAGE);
			Controller.getInstance().action(Evento.MostrarGUICliente, null);
			this.dispose();
			break;
		case Evento.ClaveEntidadYaExistente:
			JOptionPane.showMessageDialog(this,"Ya hay un cliente registrado con ese DNI", "ERROR Modificar Cliente", JOptionPane.ERROR_MESSAGE);
			this.dispose();
			break;
		default: JOptionPane.showMessageDialog(this,"El cliente se modifico con exito", "Modificar Cliente", JOptionPane.INFORMATION_MESSAGE);
		this.dispose();
		}
	}
}