package presentacion.VComunes;

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

public class VModificar extends JFrame implements IGUI{

	private static final long serialVersionUID = 1L;
	
	private String entidad = null;
	
	public VModificar(String entidad){
		super("Foot World");
		this.entidad = entidad;
		this.setBounds(100, 100, 330, 230);
		this.setContentPane(new JLabel(new ImageIcon("resources/330x230.png")));
		this.setLayout(null); this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initGUI();
		this.setVisible(true);
	}
	
	public void initGUI() {
		JLabel label = ComponentsBuilder.createLabel("Modificar un " + entidad, 85, 20, 190, 50, Color.BLACK, new Font("Serif", Font.PLAIN, 19));
		this.add(label);
		
		JButton atrasButton = ComponentsBuilder.createBackButtonSmall();
		this.add(atrasButton);
		
		JLabel labelID = ComponentsBuilder.createLabel("ID " + entidad +":", 10, 100, 80, 20, Color.BLACK, new Font("Serif", Font.PLAIN, 13));
		this.add(labelID);
		
		JTextField fieldID = ComponentsBuilder.createTextField(100, 100, 200, 20);
		this.add(fieldID);
		
		ActionListener lAtras = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				switch(entidad) {
				case "Cliente": Controller.getInstance().action(Evento.MostrarGUICliente, null);
				break;
				case "Almacen": Controller.getInstance().action(Evento.MostrarGUIAlmacen, null);
				break;
				case "Trabajador": Controller.getInstance().action(Evento.MostrarGUITrabajador, null);
				break;
				case "Marca": Controller.getInstance().action(Evento.MostrarGUIMarca, null);
				break;
				case "Producto": Controller.getInstance().action(Evento.MostrarGUIProducto, null);
				break;
				case "Proveedor": Controller.getInstance().action(Evento.MostrarGUIProveedor, null);
				break;
				
				default : Controller.getInstance().action(Evento.MostrarGUIPrincipal, null);			
				
				}
				VModificar.this.dispose();
			}
			
		};
		
		atrasButton.addActionListener(lAtras);
		
		JButton aceptarButton = ComponentsBuilder.createButton("Aceptar", 115, 140, 100, 30, new Font("Serif", Font.PLAIN, 14));
		this.add(aceptarButton);
		
		ActionListener lAceptar = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {				
					switch(entidad) {
					case "Cliente": Controller.getInstance().action(Evento.MostrarModificarCliente, fieldID.getText());
					break;
					case "Almacen": Controller.getInstance().action(Evento.MostrarModificarAlmacen, fieldID.getText());
					break;
					case "Trabajador": Controller.getInstance().action(Evento.MostrarModificarTrabajador, fieldID.getText());
					break;
					case "Marca": Controller.getInstance().action(Evento.MostrarModificarMarca, fieldID.getText());
					break;
					case "Producto": Controller.getInstance().action(Evento.MostrarModificarProducto, fieldID.getText());
					break;
					case "Proveedor": Controller.getInstance().action(Evento.MostrarModificarProveedor, fieldID.getText());
					break;
					
					default : Controller.getInstance().action(Evento.MostrarGUIPrincipal, fieldID.getText());			
					
					}
					VModificar.this.dispose();
				}
				catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "ID " + entidad + " tiene que ser un numero entero", "ERROR Modificar " + entidad,JOptionPane.ERROR_MESSAGE);
				}
			}
			
		};
		
		aceptarButton.addActionListener(lAceptar);
	}

	@Override
	public void actualizar(int evento, Object datos) {
		
	}
}
