package presentacion.proveedor_producto;

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

import negocio.proveedor_producto.TransferProveedor_producto;
import presentacion.controller.Controller;
import presentacion.controller.Evento;
import presentacion.vista.ComponentsBuilder;
import presentacion.vista.IGUI;

public class VEliminarProveedor extends JFrame implements IGUI{

	private static final long serialVersionUID = 1L;
	
	public VEliminarProveedor() {
		super("Foot World");
		this.setBounds(100, 100, 330, 350);
		this.setContentPane(new JLabel(new ImageIcon("resources/330x350.png")));
		this.setLayout(null); this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void initGUI() {
		JLabel labelTrabajador = ComponentsBuilder.createLabel("<html>Eliminar proveedor<br>de producto</html>", 85, 20, 190, 70, Color.BLACK, new Font("Serif", Font.PLAIN, 24));
		this.add(labelTrabajador);
		
		JLabel labelIDProducto = ComponentsBuilder.createLabel("ID Producto:", 10, 120, 80, 20, Color.BLACK, new Font("Serif", Font.PLAIN, 14));
		this.add(labelIDProducto);
		
		JTextField fieldIDProducto = ComponentsBuilder.createTextField(100, 122, 200, 20);
		this.add(fieldIDProducto);
		
		JLabel labelIDProveedor = ComponentsBuilder.createLabel("ID Proveedor:", 10, 180, 80, 20, Color.BLACK, new Font("Serif", Font.PLAIN, 14));
		this.add(labelIDProveedor);
		
		JTextField fieldIDProveedor = ComponentsBuilder.createTextField(100, 182, 200, 20);
		this.add(fieldIDProveedor);
		
		JButton atrasButton = ComponentsBuilder.createBackButtonSmall();
		this.add(atrasButton);
		
		ActionListener lAtras = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().action(Evento.MostrarGUIProveedorProducto, null);
				VEliminarProveedor.this.dispose();
			}
			
		};
		
		atrasButton.addActionListener(lAtras);
		
		JButton aceptarButton = ComponentsBuilder.createButton("Aceptar", 115, 250, 100, 30, new Font("Serif", Font.PLAIN, 14));
		this.add(aceptarButton);
		
		ActionListener lAceptar = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Controller.getInstance().action(Evento.EliminarProveedor, new TransferProveedor_producto(
							Integer.parseInt(fieldIDProveedor.getText()), Integer.parseInt(fieldIDProducto.getText())));
					VEliminarProveedor.this.dispose();
				}
				catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Los IDs deben ser numeros enteros", "ERROR Eliminar Proveedor de producto", JOptionPane.ERROR_MESSAGE);
				}
			}
			
		};
		
		aceptarButton.addActionListener(lAceptar);
	}

	@Override
	public void actualizar(int evento, Object datos) {
		switch(evento) {
		case Evento.GUIEliminarProveedor:
			initGUI();
			this.setVisible(true);
			break;
		case Evento.EntidadSiNoExiste:
			JOptionPane.showMessageDialog(this,"El producto o el proveedor no esta registrado en la BD", "ERROR Eliminar proveedor a producto", JOptionPane.ERROR_MESSAGE);
			this.dispose();
			break;
		default: JOptionPane.showMessageDialog(this,"La relacion se elimino con exito", "Eliminar proveedor a producto", JOptionPane.INFORMATION_MESSAGE);
			this.dispose();
		}
	}
}
