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
import javax.swing.JTextField;

import presentacion.controller.Controller;
import presentacion.controller.Evento;
import presentacion.vista.ComponentsBuilder;
import presentacion.vista.IGUI;

public class VBajaAlmacen extends JFrame implements IGUI{

	private static final long serialVersionUID = 1L;
	
	public VBajaAlmacen() {
		super("Foot World");
		this.setBounds(100, 100, 330, 230);
		this.setContentPane(new JLabel(new ImageIcon("resources/330x230.png")));
		this.setLayout(null); this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void initGUI() {
		JLabel labelAlmacen = ComponentsBuilder.createLabel("Baja Almacen", 85, 20, 190, 50, Color.BLACK, new Font("Serif", Font.PLAIN, 30));
		this.add(labelAlmacen);
		
		JButton atrasButton = ComponentsBuilder.createBackButtonSmall();
		this.add(atrasButton);
		
		JLabel labelID = ComponentsBuilder.createLabel("ID Almacen:", 10, 100, 80, 20, Color.BLACK, new Font("Serif", Font.PLAIN, 14));
		this.add(labelID);
		
		JTextField fieldID = ComponentsBuilder.createTextField(100, 100, 200, 20);
		this.add(fieldID);
		
		ActionListener lAtras = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().action(Evento.MostrarGUIAlmacen, null);
				VBajaAlmacen.this.dispose();
			}
			
		};
		
		atrasButton.addActionListener(lAtras);
		
		JButton aceptarButton = ComponentsBuilder.createButton("Aceptar", 115, 140, 100, 30, new Font("Serif", Font.PLAIN, 14));
		this.add(aceptarButton);
		
		ActionListener lAceptar = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Controller.getInstance().action(Evento.BajaAlmacen, fieldID.getText());					
					VBajaAlmacen.this.dispose();
				}
				catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "ID almacen tiene que ser un numero entero", "ERROR Baja Almacen", JOptionPane.ERROR_MESSAGE);
				}
			}
			
		};
		
		aceptarButton.addActionListener(lAceptar);
	}

	@Override
	public void actualizar(int evento, Object datos) {
		switch(evento) {
			case Evento.GUIBajaAlmacen:
				initGUI();
				this.setVisible(true);
				break;
			
			case Evento.EntidadSiNoExiste:
				JOptionPane.showMessageDialog(this,"El almacen no existe", "ERROR Baja Almacen", JOptionPane.ERROR_MESSAGE);
				this.dispose();
				break;
			case Evento.EntidadesAsociadas:
				JOptionPane.showMessageDialog(this,"El almacen tiene productos activos", "ERROR Baja Almacen", JOptionPane.ERROR_MESSAGE);
				this.dispose();
				break;
			default: JOptionPane.showMessageDialog(this,"El almacen se dio de baja", "Baja Almacen", JOptionPane.INFORMATION_MESSAGE);
			this.dispose();
		}
	}
}
