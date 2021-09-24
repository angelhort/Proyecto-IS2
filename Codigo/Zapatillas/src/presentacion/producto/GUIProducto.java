package presentacion.producto;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import presentacion.controller.Controller;
import presentacion.controller.Evento;
import presentacion.vista.ComponentsBuilder;
import presentacion.vista.IGUI;

public class GUIProducto extends JFrame implements IGUI{
	
	private static final long serialVersionUID = 1L;

	public GUIProducto() {		
		super("Foot World");
		this.setBounds(100, 100, 1010, 500);
		this.setContentPane(new JLabel(new ImageIcon("resources/1010x500.png")));
		this.setLayout(null); this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initGUI();
	}
	

	public void initGUI() {
		JLabel label = ComponentsBuilder.createLabel("Producto", 250, 30, 500, 50, Color.BLACK, new Font("Serif", Font.PLAIN, 30));
		this.add(label);
		
		JButton buttonAlta = ComponentsBuilder.createButton("Alta Producto", 100, 120, 185, 100, new Font("Serif", Font.PLAIN, 18));
		this.add(buttonAlta);
		
		ActionListener lAlta = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().action(Evento.GUIAltaProducto, null);
				GUIProducto.this.dispose();
			}
			
		};
		
		buttonAlta.addActionListener(lAlta);
		
		JButton buttonBaja = ComponentsBuilder.createButton("Baja Producto", 407, 120, 185, 100, new Font("Serif", Font.PLAIN, 18));
		this.add(buttonBaja);
		
		ActionListener lBaja = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().action(Evento.GUIBajaProducto, null);
				GUIProducto.this.dispose();
			}
			
		};
		
		buttonBaja.addActionListener(lBaja);
		
		JButton buttonModificar = ComponentsBuilder.createButton("Modificar Producto", 715, 120, 185, 100, new Font("Serif", Font.PLAIN, 18));
		this.add(buttonModificar);
		
		ActionListener lModificar = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().action(Evento.GUIModificarProducto, null);
				GUIProducto.this.dispose();
			}
			
		};
		
		buttonModificar.addActionListener(lModificar);
		
		JButton buttonMostrarUno = ComponentsBuilder.createButton("Mostrar un Producto", 213, 290, 185, 100, new Font("Serif", Font.PLAIN, 15));
		this.add(buttonMostrarUno);
		
		ActionListener lMostrarUno = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().action(Evento.GUIMostrarUnProducto, null);
				GUIProducto.this.dispose();
			}
			
		};
		
		buttonMostrarUno.addActionListener(lMostrarUno);
		
		JButton buttonMostrarTodos = ComponentsBuilder.createButton("Mostrar todos los Productos", 602, 290, 185, 100, new Font("Serif", Font.PLAIN, 12));
		this.add(buttonMostrarTodos);
		
		ActionListener lMostrarTodos = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().action(Evento.MostrarTodosLosProductos, null);
				GUIProducto.this.dispose();
			}
			
		};
		
		buttonMostrarTodos.addActionListener(lMostrarTodos);
		
		JButton atrasButton = ComponentsBuilder.createBackButton();
		this.add(atrasButton);
		
		ActionListener lAtras = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().action(Evento.MostrarGUIPrincipal, null);
				GUIProducto.this.dispose();
			}
			
		};
		
		atrasButton.addActionListener(lAtras);
	}

	@Override
	public void actualizar(int evento, Object datos) {
		switch(evento) {
		case Evento.MostrarGUIProducto:
		this.setVisible(true);
		break;
	}
	}
}
