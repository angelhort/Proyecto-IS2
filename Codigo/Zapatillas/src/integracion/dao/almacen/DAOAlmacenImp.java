package integracion.dao.almacen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import integracion.connection.DatabaseConnection;
import negocio.almacen.TransferAlmacen;

public class DAOAlmacenImp implements DAOAlmacen{

	@Override
	public int alta(TransferAlmacen transfer) {
		Connection conn = DatabaseConnection.getConnection();
		String insert = "INSERT INTO Almacen(telefono, capacidad, direccion) VALUES (?, ?, ?)";
		int result = -1;
		
		try {
			PreparedStatement statement = conn.prepareStatement(insert, PreparedStatement.RETURN_GENERATED_KEYS);
			
			statement.setInt(1, transfer.getTelefono());
			statement.setInt(2, transfer.getCapacidad());
			statement.setString(3, transfer.getDireccion());
			
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
		String insert = "UPDATE Almacen SET activo=? WHERE idAlmacen=?";
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
	public int modificar(TransferAlmacen transfer) {
		Connection conn = DatabaseConnection.getConnection();
		String insert = "UPDATE Almacen SET telefono=?, capacidad=?, direccion=?, activo=? WHERE idAlmacen=?";
		int result = -1;
		
		try {
			PreparedStatement statement = conn.prepareStatement(insert);
			
			statement.setInt(1, transfer.getTelefono());
			statement.setInt(2, transfer.getCapacidad());
			statement.setString(3, transfer.getDireccion());
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
	public int activarAlmacen(int id) {
		Connection conn = DatabaseConnection.getConnection();
		String insert = "UPDATE Almacen SET activo=? WHERE idAlmacen=?";
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
	public TransferAlmacen getAlmacen(int ID) {
		Connection conn = DatabaseConnection.getConnection();
		String query = String.format("SELECT * FROM Almacen WHERE idAlmacen = %d", ID);
		
		TransferAlmacen almacen = null;

		try {
			Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			
			if (resultSet.next()) {
				almacen = new TransferAlmacen(resultSet.getInt("idAlmacen"),
											  resultSet.getInt("telefono"),
											  resultSet.getInt("capacidad"),
											  resultSet.getString("direccion"),
											  resultSet.getBoolean("activo"));
			}
			
			resultSet.close();
			statement.close();
			conn.close();
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		DatabaseConnection.killConnection(conn);
		return almacen;
	}

	@Override
	public TransferAlmacen getAlmacen(String direccion) {
		Connection conn = DatabaseConnection.getConnection();
		String query = String.format("SELECT * FROM Almacen WHERE direccion = \"%s\"", direccion);
		
		TransferAlmacen almacen = null;

		try {
			Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			
			if (resultSet.next()) {
				almacen = new TransferAlmacen(resultSet.getInt("idAlmacen"),
						  resultSet.getInt("telefono"),
						  resultSet.getInt("capacidad"),
						  resultSet.getString("direccion"),
						  resultSet.getBoolean("activo"));
			}
			
			resultSet.close();
			statement.close();
			conn.close();
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		DatabaseConnection.killConnection(conn);
		return almacen;			
	}

	@Override
	public List<TransferAlmacen> getAllAlmacenes() {
		Connection conn = DatabaseConnection.getConnection();
		String query = "SELECT * FROM Almacen";
		
		List<TransferAlmacen> almacenes = new ArrayList<>();

		try {
			Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			
			
			while (resultSet.next()) {
				TransferAlmacen almacen = new TransferAlmacen(resultSet.getInt("idAlmacen"),
															  resultSet.getInt("telefono"),
															  resultSet.getInt("capacidad"),
															  resultSet.getString("direccion"),
															  resultSet.getBoolean("activo"));
				almacenes.add(almacen);
			}
			
			resultSet.close();
			statement.close();
			conn.close();
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
			
		DatabaseConnection.killConnection(conn);
		return almacenes;
	}

}
