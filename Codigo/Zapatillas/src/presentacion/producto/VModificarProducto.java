package presentacion.producto;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import negocio.producto.TransferCalcetines;
import negocio.producto.TransferProducto;
import negocio.producto.TransferZapatillas;
import presentacion.controller.Controller;
import presentacion.controller.Evento;
import presentacion.vista.ComponentsBuilder;
import presentacion.vista.IGUI;

public class VModificarProducto extends JFrame implements IGUI{

	private static final long serialVersionUID = 1L;
	
	public VModificarProducto() {
		super("Foot World");
		this.setBounds(100, 100, 330, 450);
		this.setContentPane(new JLabel(new ImageIcon("resources/330x450.png")));
		this.setLayout(null); this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void initGUI(TransferProducto producto) {
		JLabel labelProducto = ComponentsBuilder.createLabel("Modificar " + producto.toString(), 85, 20, 190, 50, Color.BLACK, new Font("Serif", Font.PLAIN, 30));
		this.add(labelProducto);
		
		JLabel labelNombre = ComponentsBuilder.createLabel("Nombre:", 10, 100, 60, 20, Color.BLACK, new Font("Serif", Font.PLAIN, 14));
		this.add(labelNombre);
		
		JTextField fieldNombre = ComponentsBuilder.createTextField(80, 100, 220, 20);
		fieldNombre.setText(producto.getNombre());
		this.add(fieldNombre);
		
		JLabel labelStock = ComponentsBuilder.createLabel("Stock:", 10, 150, 60, 20, Color.BLACK, new Font("Serif", Font.PLAIN, 14));
		this.add(labelStock);
		
		JTextField fieldStock = ComponentsBuilder.createTextField(80, 150, 55, 20);
		fieldStock.setText(producto.getStock() + "");
		this.add(fieldStock);
		
		JLabel labelPrecio = ComponentsBuilder.createLabel("Precio:", 10, 200, 60, 20, Color.BLACK, new Font("Serif", Font.PLAIN, 14));
		this.add(labelPrecio);
		
		JTextField fieldPrecio = ComponentsBuilder.createTextField(80, 200, 55, 20);
		fieldPrecio.setText(producto.getPrecio() + "");
		this.add(fieldPrecio);
		
		JLabel labelTalla = ComponentsBuilder.createLabel("Talla:", 145, 150, 50, 20, Color.BLACK, new Font("Serif", Font.PLAIN, 14));
		this.add(labelTalla);
		
		JLabel labelColor = ComponentsBuilder.createLabel("Color:", 145, 200, 50, 20, Color.BLACK, new Font("Serif", Font.PLAIN, 14));
		this.add(labelColor);
		
		@SuppressWarnings("serial")
		ArrayList<String> talla = new ArrayList<String>() {{add("34"); add("35"); add("36"); add("37"); add("38"); add("39"); add("40");
													add("41"); add("42"); add("43"); add("44"); add("45");}};
		JComboBox<String> comboTalla = ComponentsBuilder.createComboBox(talla, 200, 152, 100, 20);
		comboTalla.setSelectedItem(producto.getTalla() + "");
		this.add(comboTalla);
		
		@SuppressWarnings("serial")
		ArrayList<String> colors = new ArrayList<String>(){{add("Negro"); add("Blanco"); add("Morado");add("Azul"); add("Rojo"); add("Verde");add("Amarillo"); add("Naranja"); add("Gris");}};
		JComboBox<String> comboColor = ComponentsBuilder.createComboBox(colors, 200, 202, 100, 20);
		comboColor.setSelectedItem(producto.getColor());
		this.add(comboColor);
		
		JLabel labelMarca = ComponentsBuilder.createLabel("ID Marca:", 10, 250, 60, 20, Color.BLACK, new Font("Serif", Font.PLAIN, 14));
		this.add(labelMarca);
		
		JTextField fieldMarca = ComponentsBuilder.createTextField(80, 250, 55, 20);
		fieldMarca.setText(producto.getMarca() + "");
		this.add(fieldMarca);
		
		
		@SuppressWarnings("serial")
		ArrayList<String> tipo = new ArrayList<String>() {{add("Deportivas"); add("Casual"); add("Futbol");}};		
		JComboBox<String> comboTipo = ComponentsBuilder.createComboBox(tipo, 200, 252, 100, 20);
		
		@SuppressWarnings("serial")
		ArrayList<String> tejido = new ArrayList<String>() {{add("Seda"); add("Algodon"); add("Lana");}};
		JComboBox<String> comboTejido = ComponentsBuilder.createComboBox(tejido, 200, 252, 100, 20);
		
		if(producto.toString() == "zapatillas") {
			JLabel labelTipo = ComponentsBuilder.createLabel("Tipo:", 145, 250, 50, 20, Color.BLACK, new Font("Serif", Font.PLAIN, 14));
			this.add(labelTipo);
			comboTipo.setSelectedItem(((TransferZapatillas) producto).getTipo());
			this.add(comboTipo);
		}
		
		else if(producto.toString() == "calcetines") {
			JLabel labelTejido = ComponentsBuilder.createLabel("Tejido:", 145, 250, 50, 20, Color.BLACK, new Font("Serif", Font.PLAIN, 14));
			this.add(labelTejido);
			comboTipo.setSelectedItem(((TransferCalcetines) producto).getTejido());
			this.add(comboTejido);
		}
		
		
		JLabel labelAlmacen = ComponentsBuilder.createLabel("ID Almacen:", 10, 300, 70, 20, Color.BLACK, new Font("Serif", Font.PLAIN, 14));
		this.add(labelAlmacen);
		
		JTextField fieldAlmacen = ComponentsBuilder.createTextField(90, 300, 210, 20);
		fieldAlmacen.setText(producto.getAlmacen() + "");
		this.add(fieldAlmacen);
		
		
		JButton atrasButton = ComponentsBuilder.createBackButtonSmall();
		this.add(atrasButton);
		
		ActionListener lAtras = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().action(Evento.MostrarGUIProducto, null);
				VModificarProducto.this.dispose();
			}
			
		};
		
		atrasButton.addActionListener(lAtras);
		
		JButton aceptarButton = ComponentsBuilder.createButton("Aceptar", 115, 350, 100, 30, new Font("Serif", Font.PLAIN, 14));
		this.add(aceptarButton);
		
		ActionListener lAceptar = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(producto.toString() == "zapatillas") {
						Controller.getInstance().action(Evento.ModificarProducto, new TransferZapatillas(producto.getID(), Integer.parseInt((String) comboTalla.getSelectedItem()),
								Double.parseDouble(fieldPrecio.getText()), fieldNombre.getText(), (String) comboColor.getSelectedItem(), 
								(String) comboTipo.getSelectedItem(), Integer.parseInt(fieldStock.getText()), Integer.parseInt(fieldAlmacen.getText()),
								Integer.parseInt(fieldMarca.getText()), producto.getActivo()));
					}
					else if(producto.toString() == "calcetines") {
						Controller.getInstance().action(Evento.ModificarProducto, new TransferCalcetines(producto.getID(), Integer.parseInt((String) comboTalla.getSelectedItem()),
								Double.parseDouble(fieldPrecio.getText()), fieldNombre.getText(), (String) comboColor.getSelectedItem(), (String) comboTejido.getSelectedItem()
								, Integer.parseInt(fieldStock.getText()), Integer.parseInt(fieldAlmacen.getText()),
								Integer.parseInt(fieldMarca.getText()), producto.getActivo()));
					}
					
					VModificarProducto.this.dispose();
				}
				catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "<html>Stock e IDs tienen que ser un numeros enteros,<br>el precio un numero con 2 decimales</html>", "ERROR Alta Producto", JOptionPane.ERROR_MESSAGE);
				}
			}
			
		};
		
		aceptarButton.addActionListener(lAceptar);
	}
	
	@Override
	public void actualizar(int evento, Object datos) {
		switch(evento){
		case Evento.MostrarModificarProducto:
			initGUI((TransferProducto) datos);	
			this.setVisible(true);
			break;
		case Evento.WrongDataInput:
			JOptionPane.showMessageDialog(this,"Los datos fueron mal introducidos", "ERROR Modificar Producto", JOptionPane.ERROR_MESSAGE);
			this.dispose();
			break;
		case Evento.EntidadSiNoExiste:
			JOptionPane.showMessageDialog(this,"El producto no existe", "ERROR Modificar Producto", JOptionPane.ERROR_MESSAGE);
			Controller.getInstance().action(Evento.MostrarGUIProducto, null);
			this.dispose();
			break;
		case Evento.ClaveEntidadYaExistente:
			JOptionPane.showMessageDialog(this,"Ya hay un producto registrado con ese nombre", "ERROR Modificar producto", JOptionPane.ERROR_MESSAGE);
			this.dispose();
			break;
		default: JOptionPane.showMessageDialog(this,"El producto se modifico con exito", "Modificar Producto", JOptionPane.INFORMATION_MESSAGE);
		this.dispose();
		}
	}
}
