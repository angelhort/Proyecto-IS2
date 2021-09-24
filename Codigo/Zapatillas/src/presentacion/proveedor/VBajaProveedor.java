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

import presentacion.controller.Controller;
import presentacion.controller.Evento;
import presentacion.vista.ComponentsBuilder;
import presentacion.vista.IGUI;

public class VBajaProveedor extends JFrame implements IGUI{

	private static final long serialVersionUID = 1L;
	
	public VBajaProveedor() {
		super("Foot World");
		this.setBounds(100, 100, 330, 230);
		this.setContentPane(new JLabel(new ImageIcon("resources/330x230.png")));
		this.setLayout(null); this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void initGUI() {
		JLabel labelProveedor = ComponentsBuilder.createLabel("Baja Proveedor", 85, 20, 190, 50, Color.BLACK, new Font("Serif", Font.PLAIN, 30));
		this.add(labelProveedor);
		
		JButton atrasButton = ComponentsBuilder.createBackButtonSmall();
		this.add(atrasButton);
		
		JLabel labelID = ComponentsBuilder.createLabel("ID Proveedor:", 10, 100, 80, 20, Color.BLACK, new Font("Serif", Font.PLAIN, 14));
		this.add(labelID);
		
		JTextField fieldID = ComponentsBuilder.createTextField(100, 100, 200, 20);
		this.add(fieldID);
		
		ActionListener lAtras = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().action(Evento.MostrarGUIProveedor, null);
				VBajaProveedor.this.dispose();
			}
			
		};
		
		atrasButton.addActionListener(lAtras);
		
		JButton aceptarButton = ComponentsBuilder.createButton("Aceptar", 115, 140, 100, 30, new Font("Serif", Font.PLAIN, 14));
		this.add(aceptarButton);
		
		ActionListener lAceptar = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Controller.getInstance().action(Evento.BajaProveedor, fieldID.getText());					
					VBajaProveedor.this.dispose();
				}
				catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "ID Proveedor tiene que ser un numero entero", "ERROR Baja Proveedor", JOptionPane.ERROR_MESSAGE);
				}
			}
			
		};
		
		aceptarButton.addActionListener(lAceptar);
	}

	@Override
	public void actualizar(int evento, Object datos) {
		switch(evento) {
			case Evento.GUIBajaProveedor:
				initGUI();
				this.setVisible(true);
				break;
			
			case Evento.EntidadSiNoExiste:
				JOptionPane.showMessageDialog(this,"El Proveedor no existe", "ERROR Baja Proveedor", JOptionPane.ERROR_MESSAGE);
				this.dispose();
				break;
				
			case Evento.EntidadesAsociadas:
				JOptionPane.showMessageDialog(this,"El Proveedor tiene productos asociados", "ERROR Baja Proveedor", JOptionPane.ERROR_MESSAGE);
				this.dispose();
				break;
				
			default: JOptionPane.showMessageDialog(this,"El Proveedor se dio de baja", "Baja Proveedor", JOptionPane.INFORMATION_MESSAGE);
			this.dispose();
		}
	}
}
