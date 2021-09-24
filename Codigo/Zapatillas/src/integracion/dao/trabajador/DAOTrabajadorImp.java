package integracion.dao.trabajador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import integracion.connection.DatabaseConnection;
import negocio.trabajador.TransferTrabajador;

public class DAOTrabajadorImp implements DAOTrabajador{

	@Override
	public int alta(TransferTrabajador transfer) {
		Connection conn = DatabaseConnection.getConnection();
		String insert = "INSERT INTO Trabajadores(DNI, telefono, nombre) VALUES (?, ?, ?)";
		int result = -1;
		
		try {
			PreparedStatement statement = conn.prepareStatement(insert, PreparedStatement.RETURN_GENERATED_KEYS);
			
			statement.setString(1, transfer.getDNI());
			statement.setInt(2, transfer.getTelefono());
			statement.setString(3, transfer.getNombre());
			
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
		String insert = "UPDATE Trabajadores SET activo=? WHERE idTrabajador=?";
		int result = -1;
		
		try {
			PreparedStatement statement = conn.prepareStatement(insert);
			
			statement.setBoolean(1, false);
			statement.setInt(2, ID);
			
			result = statement.executeUpdate();
			
			statement.close();
			conn.close();
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		DatabaseConnection.killConnection(conn);
		return result;
	}

	@Override
	public int modificar(TransferTrabajador transfer) {
		Connection conn = DatabaseConnection.getConnection();
		String insert = "UPDATE Trabajadores SET DNI=?, telefono=?, nombre=?, activo=? WHERE idTrabajador=?";
		int result = -1;
		
		try {
			PreparedStatement statement = conn.prepareStatement(insert);
			
			statement.setString(1, transfer.getDNI());
			statement.setInt(2, transfer.getTelefono());
			statement.setString(3, transfer.getNombre());
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
	
	@Override
	public int activarTrabajador(int id) {
		Connection conn = DatabaseConnection.getConnection();
		String insert = "UPDATE Trabajadores SET activo=? WHERE idTrabajador=?";
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
	public TransferTrabajador getTrabajador(int ID) {
		Connection conn = DatabaseConnection.getConnection();
		String query = String.format("SELECT * FROM Trabajadores WHERE idTrabajador = %d", ID);
		
		TransferTrabajador trabajador = null;

		try {
			Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			
			if (resultSet.next()) {
				trabajador = new TransferTrabajador(resultSet.getInt("idTrabajador"),
											  resultSet.getInt("telefono"),
											  resultSet.getString("DNI"),
											  resultSet.getString("nombre"),
											  resultSet.getBoolean("activo"));
			}
			
			resultSet.close();
			statement.close();
			conn.close();
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		DatabaseConnection.killConnection(conn);
		return trabajador;
	}

	@Override
	public TransferTrabajador getTrabajador(String dni) {
		Connection conn = DatabaseConnection.getConnection();
		String query = String.format("SELECT * FROM Trabajadores WHERE DNI = \"%s\"", dni);
		
		TransferTrabajador trabajador = null;

		try {
			Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			
			if (resultSet.next()) {
				trabajador = new TransferTrabajador(resultSet.getInt("idTrabajador"),
												  resultSet.getInt("telefono"),
												  resultSet.getString("DNI"),
												  resultSet.getString("nombre"),
												  resultSet.getBoolean("activo"));
			}
			
			resultSet.close();
			statement.close();
			conn.close();
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		DatabaseConnection.killConnection(conn);
		return trabajador;
	}

	@Override
	public List<TransferTrabajador> getAllTrabajadores() {
		Connection conn = DatabaseConnection.getConnection();
		String query = "SELECT * FROM Trabajadores";
		
		List<TransferTrabajador> trabajadores = new ArrayList<>();

		try {
			Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			
			
			while (resultSet.next()) {
				TransferTrabajador trabajador = new TransferTrabajador(resultSet.getInt("idTrabajador"),
																	  resultSet.getInt("telefono"),
																	  resultSet.getString("DNI"),
																	  resultSet.getString("nombre"),
																	  resultSet.getBoolean("activo"));
				trabajadores.add(trabajador);
			}
			
			resultSet.close();
			statement.close();
			conn.close();
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		DatabaseConnection.killConnection(conn);
		return trabajadores;
	}

}
