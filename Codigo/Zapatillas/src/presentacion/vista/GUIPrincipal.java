package presentacion.vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import presentacion.controller.Controller;
import presentacion.controller.Evento;

public class GUIPrincipal extends JFrame implements IGUI{

	private static final long serialVersionUID = 1L;
	
	
	
	public GUIPrincipal() {		
		super("Foot World");
		this.setBounds(100, 100, 1010, 500);
		this.setContentPane(new JLabel(new ImageIcon("resources/1010x500.png")));
		this.setLayout(null); this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initGUI();
		this.setVisible(true);
	}

	private void initGUI() {
		JLabel label = ComponentsBuilder.createLabel("Selecciona la entidad sobre la que trabajar", 50, 30, 900, 50, Color.BLACK, new Font("Serif", Font.PLAIN, 30));
		
		this.add(label);
		
		JButton buttonCliente = ComponentsBuilder.createButton("Cliente", 50, 120, 185, 100, new Font("Serif", Font.PLAIN, 18));
		this.add(buttonCliente);
		
		ActionListener lCliente = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				GUIPrincipal.this.dispose();
				Controller.getInstance().action(Evento.MostrarGUICliente, null);
			}
			
		};
		
		buttonCliente.addActionListener(lCliente);
		
		JButton buttonTrabajador = ComponentsBuilder.createButton("Trabajador", 288, 120, 185, 100, new Font("Serif", Font.PLAIN, 18));
		this.add(buttonTrabajador);
		
		ActionListener lTrabajador = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				GUIPrincipal.this.dispose();
				Controller.getInstance().action(Evento.MostrarGUITrabajador, null);
			}
			
		};
		
		buttonTrabajador.addActionListener(lTrabajador);
		
		JButton buttonVenta = ComponentsBuilder.createButton("Venta", 526, 120, 185, 100, new Font("Serif", Font.PLAIN, 18));
		this.add(buttonVenta);
		
		ActionListener lVenta = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String[] options = {"Abrir","Mostrar"};
				int n = JOptionPane.showOptionDialog(null,"¿Desea abrir una venta o mostrar una venta?", "Abrir-Mostrar Venta",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
				
				if (n==JOptionPane.YES_OPTION) {
					Controller.getInstance().action(Evento.GUIAbrirVenta, null);
					GUIPrincipal.this.dispose();					
				}
				else if(n == JOptionPane.NO_OPTION) {
					try {
						String idVenta = (String) JOptionPane.showInputDialog(GUIPrincipal.this, "ID Venta:",
								"Mostrar una venta", JOptionPane.PLAIN_MESSAGE, null, null, "");
						Controller.getInstance().action(Evento.MostrarVenta, idVenta);					
						GUIPrincipal.this.dispose();						
					}
					catch(NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "ID venta tiene que ser un numero entero", "ERROR Mostrar venta", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
			
		};
		
		buttonVenta.addActionListener(lVenta);
		
		JButton buttonAlmacen = ComponentsBuilder.createButton("Almacen", 764, 120, 185, 100, new Font("Serif", Font.PLAIN, 18));
		this.add(buttonAlmacen);
		
		ActionListener lAlmacen = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				GUIPrincipal.this.dispose();
				Controller.getInstance().action(Evento.MostrarGUIAlmacen, null);
			}
			
		};
		
		buttonAlmacen.addActionListener(lAlmacen);
		
		JButton buttonProducto = ComponentsBuilder.createButton("Producto", 50, 290, 185, 100, new Font("Serif", Font.PLAIN, 18));
		this.add(buttonProducto);
		
		ActionListener lProducto = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				GUIPrincipal.this.dispose();
				Controller.getInstance().action(Evento.MostrarGUIProducto, null);
			}
			
		};
		
		buttonProducto.addActionListener(lProducto);
		
		JButton buttonProveedor = ComponentsBuilder.createButton("Proveedor", 288, 290, 185, 100, new Font("Serif", Font.PLAIN, 18));
		this.add(buttonProveedor);
		
		ActionListener lProveedor = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				GUIPrincipal.this.dispose();
				Controller.getInstance().action(Evento.MostrarGUIProveedor, null);
			}
			
		};
		
		buttonProveedor.addActionListener(lProveedor);
		
		JButton buttonMarca = ComponentsBuilder.createButton("Marca", 526, 290, 185, 100, new Font("Serif", Font.PLAIN, 18));
		this.add(buttonMarca);
		
		ActionListener lMarca = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				GUIPrincipal.this.dispose();
				Controller.getInstance().action(Evento.MostrarGUIMarca, null);
			}
			
		};
		
		buttonMarca.addActionListener(lMarca);
		
		JButton buttonProdProv = ComponentsBuilder.createButton("Productos - Proveedores", 764, 290, 185, 100, new Font("Serif", Font.PLAIN, 13));
		this.add(buttonProdProv);
		
		ActionListener lProdProv = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				GUIPrincipal.this.dispose();
				Controller.getInstance().action(Evento.MostrarGUIProveedorProducto, null);
			}
			
		};
		
		buttonProdProv.addActionListener(lProdProv);
		
		this.pack();
	}

	@Override
	public void actualizar(int evento, Object datos) {
		
	}
	
}
