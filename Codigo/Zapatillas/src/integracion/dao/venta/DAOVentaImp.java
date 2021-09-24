package integracion.dao.venta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import integracion.connection.DatabaseConnection;
import negocio.cliente.TransferCliente;
import negocio.producto.TransferProducto;
import negocio.trabajador.TransferTrabajador;
import negocio.venta.TProductoEnFactura;
import negocio.venta.TransferVenta;

public class DAOVentaImp implements DAOVenta {

	@Override
	public int alta(TransferVenta transfer) {
		Connection conn = DatabaseConnection.getConnection();
		
		String insertFacturas = "INSERT INTO Facturas(precioTotal, idTrabajador, idCliente) VALUES (?, ?, ?)";
		String insertContiene = "INSERT INTO Contiene(unidades, precio, idProducto, idFactura) VALUES (?, ?, ?, ?)";
		
		int result = -1;
		
		try {
			PreparedStatement statement = conn.prepareStatement(insertFacturas, PreparedStatement.RETURN_GENERATED_KEYS);
			
			statement.setDouble(1, transfer.getPrecioTotal());
			statement.setInt(2, transfer.getTrabajador().getID());
			statement.setInt(3, transfer.getCliente().getID());
			
			statement.executeUpdate();
			
			ResultSet resultSet = statement.getGeneratedKeys();
			
			if (resultSet.next())
				result = resultSet.getInt(1);
			
			for (TProductoEnFactura producto : transfer.getProductos()) {
				statement = conn.prepareStatement(insertContiene);
				
				statement.setInt(1, producto.getUnidades());
				statement.setDouble(2, producto.getPrecio());
				statement.setInt(3, producto.getProducto().getID());
				statement.setInt(4, result);
				
				statement.executeUpdate();
			}
			
			resultSet.close();
			statement.close();
			conn.close();
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		DatabaseConnection.killConnection(conn);
		return result;
	}

	@Override
	public TransferVenta getVenta(int ID) {
		Connection conn = DatabaseConnection.getConnection();
		String queryFactura = String.format("SELECT * FROM Facturas WHERE idFactura = %d", ID);
		String queryProductos = String.format("SELECT * FROM Contiene WHERE idFactura = %d", ID);
		
		TransferVenta venta = null;

		try {
			Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery(queryFactura);
			
			if (resultSet.next()) {
				venta = new TransferVenta(new TransferCliente(resultSet.getInt("idCliente")),
										  new TransferTrabajador(resultSet.getInt("idTrabajador")),
											resultSet.getTimestamp("fecha").toString());
			}
			
			resultSet = statement.executeQuery(queryProductos);
			
			while (resultSet.next()) {
				TProductoEnFactura p = new TProductoEnFactura(new TransferProducto(resultSet.getInt("idProducto")),
															  resultSet.getInt("unidades"));
				p.setPrecioCalculado(resultSet.getDouble("precio"));
				venta.addProduct(p);
			}
			
			venta.setPrecioTotal();
			
			resultSet.close();
			statement.close();
			conn.close();
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		DatabaseConnection.killConnection(conn);
		return venta;
	}

}
