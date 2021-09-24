package presentacion.producto;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import negocio.producto.TransferCalcetines;
import negocio.producto.TransferProducto;
import negocio.producto.TransferZapatillas;
import presentacion.controller.Controller;
import presentacion.controller.Evento;
import presentacion.vista.ComponentsBuilder;
import presentacion.vista.IGUI;

public class VMostrarTodosLosProductos extends JFrame implements IGUI{

	private static final long serialVersionUID = 1L;

	public VMostrarTodosLosProductos() {
		super("Foot World");
		this.setBounds(100, 100, 1010, 500);
		this.setContentPane(new JLabel(new ImageIcon("resources/1010x500.png")));
		this.setLayout(null); this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void initGUI(List<TransferProducto> productos) {
		JLabel label = ComponentsBuilder.createLabel("Mostrar todos los Productos", 325, 20, 350, 50, Color.BLACK, new Font("Serif", Font.PLAIN, 30));
		this.add(label);
		
		JButton atrasButton = ComponentsBuilder.createBackButtonSmall();
		this.add(atrasButton);
		
		ActionListener lAtras = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VMostrarTodosLosProductos.this.dispose();
				Controller.getInstance().action(Evento.MostrarGUIProducto, null);
			}
			
		};
		
		atrasButton.addActionListener(lAtras);
		
		String[] columnNames = {"ID", "Nombre", "Tipo", "Stock", "Precio", "Talla", "Color", "Tejido/Tipo", "Marca", "Almacen", "Activo"};
		
		JTable table = ComponentsBuilder.creteTable(productos.size(), 11, columnNames);
		
		int i = 0;
		for(TransferProducto t: productos) {
			table.setValueAt(t.getID(), i, 0);
			table.setValueAt(t.getNombre(), i, 1);
			table.setValueAt(t.getClass() == TransferZapatillas.class ? "Zapatillas" : "Calcetines", i, 2);
			table.setValueAt(t.getStock(), i, 3);
			table.setValueAt(t.getPrecio(), i, 4);
			table.setValueAt(t.getTalla(), i, 5);
			table.setValueAt(t.getColor(), i, 6);
			table.setValueAt(t.getClass() == TransferZapatillas.class ? ((TransferZapatillas) t).getTipo() : ((TransferCalcetines) t).getTejido(), i, 7);
			table.setValueAt(t.getMarca(), i, 8);
			table.setValueAt(t.getAlmacen(), i, 9);
			table.setValueAt(t.getActivo() ? "SI" : "NO", i, 10);
			i++;
		}
	
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(50, 115, 900, 288);
		this.add(scroll);
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public void actualizar(int evento, Object datos) {
		switch(evento) {
		case Evento.MostrarTodosLosProductos:
			initGUI((List<TransferProducto>) datos);
			this.setVisible(true);
			break;
		}
	}

}
