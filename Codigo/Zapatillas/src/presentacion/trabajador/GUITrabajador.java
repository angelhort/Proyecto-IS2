package presentacion.trabajador;

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

public class GUITrabajador extends JFrame implements IGUI{
	
	private static final long serialVersionUID = 1L;

	public GUITrabajador() {		
		super("Foot World");
		this.setBounds(100, 100, 1010, 500);
		this.setContentPane(new JLabel(new ImageIcon("resources/1010x500.png")));
		this.setLayout(null); this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initGUI();
	}
	

	public void initGUI() {
		JLabel label = ComponentsBuilder.createLabel("Trabajador", 250, 30, 500, 50, Color.BLACK, new Font("Serif", Font.PLAIN, 30));
		this.add(label);
		
		JButton buttonAlta = ComponentsBuilder.createButton("Alta Trabajador", 100, 120, 185, 100, new Font("Serif", Font.PLAIN, 18));
		this.add(buttonAlta);
		
		ActionListener lAlta = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().action(Evento.GUIAltaTrabajador, null);
				GUITrabajador.this.dispose();
			}
			
		};
		
		buttonAlta.addActionListener(lAlta);
		
		JButton buttonBaja = ComponentsBuilder.createButton("Baja Trabajador", 407, 120, 185, 100, new Font("Serif", Font.PLAIN, 18));
		this.add(buttonBaja);
		
		ActionListener lBaja = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().action(Evento.GUIBajaTrabajador, null);
				GUITrabajador.this.dispose();
			}
			
		};
		
		buttonBaja.addActionListener(lBaja);
		
		JButton buttonModificar = ComponentsBuilder.createButton("Modificar Trabajador", 715, 120, 185, 100, new Font("Serif", Font.PLAIN, 15));
		this.add(buttonModificar);
		
		ActionListener lModificar = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().action(Evento.GUIModificarTrabajador, null);
				GUITrabajador.this.dispose();
			}
			
		};
		
		buttonModificar.addActionListener(lModificar);
		
		JButton buttonMostrarUno = ComponentsBuilder.createButton("Mostrar un Trabajador", 213, 290, 185, 100, new Font("Serif", Font.PLAIN, 15));
		this.add(buttonMostrarUno);
		
		ActionListener lMostrarUno = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().action(Evento.GUIMostrarUnTrabajador, null);
				GUITrabajador.this.dispose();
			}
			
		};
		
		buttonMostrarUno.addActionListener(lMostrarUno);
		
		JButton buttonMostrarTodos = ComponentsBuilder.createButton("Mostrar todos los Trabajadores", 602, 290, 185, 100, new Font("Serif", Font.PLAIN, 12));
		this.add(buttonMostrarTodos);
		
		ActionListener lMostrarTodos = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				GUITrabajador.this.dispose();
				Controller.getInstance().action(Evento.MostrarTodosLosTrabajadores, null);
			}
			
		};
		
		buttonMostrarTodos.addActionListener(lMostrarTodos);
		
		JButton atrasButton = ComponentsBuilder.createBackButton();
		this.add(atrasButton);
		
		ActionListener lAtras = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().action(Evento.MostrarGUIPrincipal, null);
				GUITrabajador.this.dispose();
			}
			
		};
		
		atrasButton.addActionListener(lAtras);
	}

	@Override
	public void actualizar(int evento, Object datos) {
		switch(evento) {
		case Evento.MostrarGUITrabajador:
		this.setVisible(true);
		break;
	}
	}
}
