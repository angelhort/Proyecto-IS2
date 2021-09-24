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

public class VAniadirProveedor extends JFrame implements IGUI{

	private static final long serialVersionUID = 1L;
	
	public VAniadirProveedor() {
		super("Foot World");
		this.setBounds(100, 100, 330, 350);
		this.setContentPane(new JLabel(new ImageIcon("resources/330x350.png")));
		this.setLayout(null); this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void initGUI() {
		JLabel label = ComponentsBuilder.createLabel("<html>Aniadir proveedor<br>a producto</html>", 85, 20, 190, 70, Color.BLACK, new Font("Serif", Font.PLAIN, 25));
		this.add(label);

		JLabel labelIDProducto = ComponentsBuilder.createLabel("ID Producto:", 10, 100, 100, 20, Color.BLACK, new Font("Serif", Font.PLAIN, 14));
		this.add(labelIDProducto);
		
		JTextField fieldIDProducto = ComponentsBuilder.createTextField(120, 100, 180, 20);
		this.add(fieldIDProducto);
		
		JLabel labelIDProveedor = ComponentsBuilder.createLabel("ID Proveedor:", 10, 150, 100, 20, Color.BLACK, new Font("Serif", Font.PLAIN, 14));
		this.add(labelIDProveedor);
		
		JTextField fieldIDProveedor = ComponentsBuilder.createTextField(120, 150, 180, 20);
		this.add(fieldIDProveedor);
		
		JLabel labelPrecio = ComponentsBuilder.createLabel("Precio Suministro:", 10, 200, 120, 20, Color.BLACK, new Font("Serif", Font.PLAIN, 14));
		this.add(labelPrecio);
		
		JTextField fieldPrecio = ComponentsBuilder.createTextField(140, 200, 160, 20);
		this.add(fieldPrecio);
		
		JButton atrasButton = ComponentsBuilder.createBackButtonSmall();
		this.add(atrasButton);
		
		ActionListener lAtras = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VAniadirProveedor.this.dispose();
				Controller.getInstance().action(Evento.MostrarGUIProveedorProducto, null);
			}
			
		};
		
		atrasButton.addActionListener(lAtras);
		
		JButton aceptarButton = ComponentsBuilder.createButton("Aceptar", 115, 250, 100, 30, new Font("Serif", Font.PLAIN, 14));
		this.add(aceptarButton);
		
		ActionListener lAceptar = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Controller.getInstance().action(Evento.AniadirProveedor, new TransferProveedor_producto(
							Integer.parseInt(fieldIDProveedor.getText()), Integer.parseInt(fieldIDProducto.getText()), Double.parseDouble(fieldPrecio.getText())));
					VAniadirProveedor.this.dispose();
				}
				catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Los IDs deben ser numeros enteros y el precio decimal", "ERROR Añadir Proveedor a Producto", JOptionPane.ERROR_MESSAGE);
				}
			}
			
		};
		
		aceptarButton.addActionListener(lAceptar);
	}

	@Override
	public void actualizar(int evento, Object datos) {
		switch(evento) {
		case Evento.GUIAniadirProveedor:
			initGUI();
			this.setVisible(true);
			break;
		case Evento.EntidadSiNoExiste:
			JOptionPane.showMessageDialog(this,"El producto o el proveedor no esta registrado en la BD o esta inactivo", "ERROR Añadir proveedor a producto", JOptionPane.ERROR_MESSAGE);
			this.dispose();
			break;
		default: JOptionPane.showMessageDialog(this,"La relacion se añadio con exito", "Añadir proveedor a producto", JOptionPane.INFORMATION_MESSAGE);
		this.dispose();
		}
	}
}
