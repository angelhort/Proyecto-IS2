package integracion.dao.marca;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import integracion.connection.DatabaseConnection;
import negocio.marca.TransferMarca;

public class DAOMarcaImp implements DAOMarca {
	
	public int alta(TransferMarca transfer) {
		Connection conn = DatabaseConnection.getConnection();
		String insert = "INSERT INTO Marca(nombre) VALUES (?)";
		int result = -1;
		
		try {
			PreparedStatement statement = conn.prepareStatement(insert, PreparedStatement.RETURN_GENERATED_KEYS);
			
			statement.setString(1, transfer.getNombre());
			
			statement.executeUpdate();
			
			ResultSet resultSet = statement.getGeneratedKeys();
			
			if(resultSet.next())
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
	public int baja(int idMarca) {
		Connection conn = DatabaseConnection.getConnection();
		String insert = "UPDATE Marca SET activo=? WHERE idMarca=?";
		int result = -1;
	
		try {
			PreparedStatement statement = conn.prepareStatement(insert);
			
			statement.setBoolean(1, false);
			statement.setInt(2, idMarca);
			
			result = statement.executeUpdate();
			
			statement .close();
			conn.close();
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		DatabaseConnection.killConnection(conn);
		return result;
	}

	@Override
	public int modificar(TransferMarca transfer) {
		Connection conn = DatabaseConnection.getConnection();
		String insert = "UPDATE Marca SET nombre=?, activo=? WHERE idMarca=?";
		int result = -1;
		
		try {
			PreparedStatement statement = conn.prepareStatement(insert);
			
			statement.setString(1, transfer.getNombre());
			statement.setBoolean(2, transfer.getActivo());
			statement.setInt(3, transfer.getID());
			
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
	public TransferMarca getMarca(int idMarca) {
		Connection conn = DatabaseConnection.getConnection();
		String query = String.format("SELECT * FROM Marca WHERE idMarca = %d", idMarca);
		
		TransferMarca marca = null;
		
		try {
			Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			
			if (resultSet.next()) {
				marca = new TransferMarca(resultSet.getInt("idMarca"),
											  resultSet.getString("nombre"),
											  resultSet.getBoolean("activo")
											  );
			}
			
			resultSet.close();
			statement.close();
			conn.close();
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		DatabaseConnection.killConnection(conn);
		return marca;
	}

	@Override
	public TransferMarca getMarca(String nombre) {
		Connection conn = DatabaseConnection.getConnection();
		String query = String.format("SELECT * FROM Marca WHERE nombre = \"%s\"", nombre);
		
		TransferMarca marca = null;
		
		try {
			Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			
			if (resultSet.next()) {
				marca = new TransferMarca(resultSet.getInt("idMarca"),
											  resultSet.getString("nombre"),
											  resultSet.getBoolean("activo")
											  );
			}
			
			resultSet.close();
			statement.close();
			conn.close();
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		DatabaseConnection.killConnection(conn);
		return marca;
	}
	
	@Override
	public int activarMarca(int id) {
		Connection conn = DatabaseConnection.getConnection();
		String insert = "UPDATE Marca SET activo=? WHERE idMarca=?";
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
	public List<TransferMarca> getAllMarcas() {
		Connection conn = DatabaseConnection.getConnection();
		String query = "SELECT * FROM Marca";
		
		List<TransferMarca> marcas = new ArrayList<>();

		try {
			Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			
			
			while (resultSet.next()) {
				TransferMarca marca = new TransferMarca(resultSet.getInt("idMarca"),
															  resultSet.getString("nombre"),
															  resultSet.getBoolean("activo"));
				marcas.add(marca);
			}
			
			resultSet.close();
			statement.close();
			conn.close();
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		DatabaseConnection.killConnection(conn);
		return marcas;
	}
}
