package integracion.dao.cliente;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import integracion.connection.DatabaseConnection;

import java.util.ArrayList;

import negocio.cliente.TransferCliente;

public class DAOClienteImp implements DAOCliente {

	@Override
	public int alta(TransferCliente transfer) {
		Connection conn = DatabaseConnection.getConnection();
		String insert = "INSERT INTO Clientes(nombre, socio, DNI) VALUES (?, ?, ?)";
		int result = -1;
		
		try {
			PreparedStatement statement = conn.prepareStatement(insert, PreparedStatement.RETURN_GENERATED_KEYS);
			
			statement.setString(1, transfer.getNombre());
			statement.setBoolean(2, transfer.isSocio());
			statement.setString(3, transfer.getDNI());
			
			statement.executeUpdate();
			
			ResultSet resultSet = statement.getGeneratedKeys();
			
			if (resultSet.next())
				result = resultSet.getInt(1);
			
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
	public int baja(int ID) {
		Connection conn = DatabaseConnection.getConnection();
		String insert = "UPDATE Clientes SET activo=? WHERE idCliente=?";
		int result = -1;
		
		try {
			PreparedStatement statement = conn.prepareStatement(insert);
			
			statement.setBoolean(1, false);
			statement.setInt(2, ID);
			
			result = statement.executeUpdate(); // Number of updated rows
			
			statement.close();
			conn.close();
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		DatabaseConnection.killConnection(conn);
		return result;
	}

	@Override
	public TransferCliente getCliente(int ID) {
		Connection conn = DatabaseConnection.getConnection();
		String query = String.format("SELECT * FROM Clientes WHERE idCliente = %d", ID);
		
		TransferCliente cliente = null;

		try {
			Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			
			if (resultSet.next()) {
				cliente = new TransferCliente(resultSet.getInt("idCliente"),
											  resultSet.getString("nombre"),
											  resultSet.getBoolean("socio"),
											  resultSet.getString("DNI"),
											  resultSet.getBoolean("activo"));
			}
			
			resultSet.close();
			statement.close();
			conn.close();
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		DatabaseConnection.killConnection(conn);
		return cliente;
	}
	
	@Override
	public TransferCliente getCliente(String DNI) {
		Connection conn = DatabaseConnection.getConnection();
		String query = String.format("SELECT * FROM Clientes WHERE DNI = \"%s\"", DNI);
		
		TransferCliente cliente = null;

		try {
			Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			
			if (resultSet.next()) {
				cliente = new TransferCliente(resultSet.getInt("idCliente"),
											  resultSet.getString("nombre"),
											  resultSet.getBoolean("socio"),
											  resultSet.getString("DNI"),
											  resultSet.getBoolean("activo"));
			}
			
			resultSet.close();
			statement.close();
			conn.close();
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		DatabaseConnection.killConnection(conn);
		return cliente;
	}
	
	@Override
	public int activarCliente(int id) {
		Connection conn = DatabaseConnection.getConnection();
		String insert = "UPDATE Clientes SET activo=? WHERE idCliente=?";
		int result = -1;
		
		try {
			PreparedStatement statement = conn.prepareStatement(insert);
			statement.setBoolean(1, true);
			statement.setInt(2, id);
			result = statement.executeUpdate();
			
			statement.close();
			conn.close();
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		DatabaseConnection.killConnection(conn);
		return result != -1? 0 : result;
	}

	@Override
	public List<TransferCliente> getAllClientes() {
		Connection conn = DatabaseConnection.getConnection();
		String query = "SELECT * FROM Clientes";
		
		List<TransferCliente> clientes = new ArrayList<>();

		try {
			Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			
			
			while (resultSet.next()) {
				TransferCliente cliente = new TransferCliente(resultSet.getInt("idCliente"),
															  resultSet.getString("nombre"),
															  resultSet.getBoolean("socio"),
															  resultSet.getString("DNI"),
															  resultSet.getBoolean("activo"));
				clientes.add(cliente);
			}
			
			resultSet.close();
			statement.close();
			conn.close();
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		DatabaseConnection.killConnection(conn);
		return clientes;
	}

	@Override
	public int modificar(TransferCliente transfer) {
		Connection conn = DatabaseConnection.getConnection();
		String insert = "UPDATE Clientes SET nombre=?, socio=?, DNI=?, activo=? WHERE idCliente=?";
		int result = -1;
		
		try {
			PreparedStatement statement = conn.prepareStatement(insert);
			
			statement.setString(1, transfer.getNombre());
			statement.setBoolean(2, transfer.isSocio());
			statement.setString(3, transfer.getDNI());
			statement.setBoolean(4, transfer.getActivo());
			statement.setInt(5, transfer.getID());
			
			result = statement.executeUpdate();
			
			statement.close();
			conn.close();
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		DatabaseConnection.killConnection(conn);
		return result;
	}

}
