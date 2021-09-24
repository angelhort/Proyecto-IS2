package presentacion.marca;

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

import negocio.marca.TransferMarca;
import presentacion.controller.Controller;
import presentacion.controller.Evento;
import presentacion.vista.ComponentsBuilder;
import presentacion.vista.IGUI;

public class VModificarMarca extends JFrame implements IGUI{

	private static final long serialVersionUID = 1L;
	
	public VModificarMarca() {
		super("Foot World");
		this.setBounds(100, 100, 330, 230);
		this.setContentPane(new JLabel(new ImageIcon("resources/330x230.png")));
		this.setLayout(null); this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void initGUI(TransferMarca marca) {
		JLabel labelMarca = ComponentsBuilder.createLabel("Modificar Marca", 85, 20, 190, 50, Color.BLACK, new Font("Serif", Font.PLAIN, 30));
		this.add(labelMarca);
		
		JLabel labelNombre = ComponentsBuilder.createLabel("Nombre:", 10, 100, 60, 20, Color.BLACK, new Font("Serif", Font.PLAIN, 14));
		this.add(labelNombre);
		
		JTextField fieldNombre = ComponentsBuilder.createTextField(80, 100, 220, 20);
		fieldNombre.setText(marca.getNombre());
		this.add(fieldNombre);
		
		JButton atrasButton = ComponentsBuilder.createBackButtonSmall();
		this.add(atrasButton);
		
		ActionListener lAtras = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().action(Evento.MostrarGUIMarca, null);
				VModificarMarca.this.dispose();
			}
			
		};
		
		atrasButton.addActionListener(lAtras);
		
		JButton aceptarButton = ComponentsBuilder.createButton("Aceptar", 115, 140, 100, 30, new Font("Serif", Font.PLAIN, 14));
		this.add(aceptarButton);
		
		ActionListener lAceptar = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().action(Evento.ModificarMarca, new TransferMarca(marca.getID(), fieldNombre.getText(), marca.getActivo()));
				VModificarMarca.this.dispose();
			}
			
		};
		
		aceptarButton.addActionListener(lAceptar);
	}

	@Override
	public void actualizar(int evento, Object datos) {
		switch(evento) {
			case Evento.MostrarModificarMarca:
				initGUI((TransferMarca) datos);	
				this.setVisible(true);
				break;
				
			case Evento.WrongDataInput:
				JOptionPane.showMessageDialog(this,"Los datos fueron mal introducidos", "ERROR Modificar Marca", JOptionPane.ERROR_MESSAGE);
				this.dispose();
				break;
			case Evento.EntidadSiNoExiste:
				JOptionPane.showMessageDialog(this,"La Marca no existe", "ERROR Modificar Marca", JOptionPane.ERROR_MESSAGE);
				Controller.getInstance().action(Evento.MostrarGUIMarca, null);
				this.dispose();
				break;
			case Evento.ClaveEntidadYaExistente:
				JOptionPane.showMessageDialog(this,"Ya hay una Marca registrada con ese nombre", "ERROR Modificar Marca", JOptionPane.ERROR_MESSAGE);
				this.dispose();
				break;
			default: JOptionPane.showMessageDialog(this,"La Marca se modifico con exito", "Modificar Marca", JOptionPane.INFORMATION_MESSAGE);
			this.dispose();
		}
	}
}
