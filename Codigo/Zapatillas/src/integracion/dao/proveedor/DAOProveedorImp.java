package integracion.dao.proveedor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import integracion.connection.DatabaseConnection;

import java.util.ArrayList;

import negocio.proveedor.TransferProveedor;

public class DAOProveedorImp implements DAOProveedor {

	@Override
	public int alta(TransferProveedor transfer) {
		Connection conn = DatabaseConnection.getConnection();
		String insert = "INSERT INTO Proveedores(telefono, direccion, nombre) VALUES (?, ?, ?)";
		int result = -1;
		
		try {
			PreparedStatement statement = conn.prepareStatement(insert, PreparedStatement.RETURN_GENERATED_KEYS);
			
			statement.setInt(1, transfer.getTelefono());
			statement.setString(2, transfer.getDireccion());
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
		String insert = "UPDATE Proveedores SET activo=? WHERE idProveedor=?";
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
	public int modificar(TransferProveedor transfer) {
		Connection conn = DatabaseConnection.getConnection();
		String insert = "UPDATE Proveedores SET telefono=?, direccion=?, nombre=?, activo=? WHERE idProveedor=?";
		int result = -1;
		
		try {
			PreparedStatement statement = conn.prepareStatement(insert);
			
			statement.setInt(1, transfer.getTelefono());
			statement.setString(2, transfer.getDireccion());
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
	public TransferProveedor getProveedor(int ID) {
		Connection conn = DatabaseConnection.getConnection();
		String query = String.format("SELECT * FROM Proveedores WHERE idProveedor = %d", ID);
		
		TransferProveedor proveedor = null;

		try {
			Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			
			if (resultSet.next()) {
				proveedor = new TransferProveedor(resultSet.getInt("idProveedor"),
												  resultSet.getInt("telefono"),
												  resultSet.getString("direccion"),
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
		return proveedor;
	}
	
	@Override
	public TransferProveedor getProveedor(String direccion) {
		Connection conn = DatabaseConnection.getConnection();
		String query = String.format("SELECT * FROM Proveedores WHERE direccion = \"%s\"", direccion);
		
		TransferProveedor proveedor = null;

		try {
			Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			
			if (resultSet.next()) {
				proveedor = new TransferProveedor(resultSet.getInt("idProveedor"),
						  resultSet.getInt("telefono"),
						  resultSet.getString("direccion"),
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
		return proveedor;			
	}
	
	@Override
	public int activarProveedor(int id) {
		Connection conn = DatabaseConnection.getConnection();
		String insert = "UPDATE Proveedores SET activo=? WHERE idProveedor=?";
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
	public List<TransferProveedor> getAllProveedores() {
		Connection conn = DatabaseConnection.getConnection();
		String query = "SELECT * FROM Proveedores";
		
		List<TransferProveedor> proveedores = new ArrayList<>();

		try {
			Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			
			while (resultSet.next()) {
				proveedores.add(new TransferProveedor(resultSet.getInt("idProveedor"),
													  resultSet.getInt("telefono"),
													  resultSet.getString("direccion"),
													  resultSet.getString("nombre"),
													  resultSet.getBoolean("activo")));
			}
			
			resultSet.close();
			statement.close();
			conn.close();
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		DatabaseConnection.killConnection(conn);
		return proveedores;
	}

}
