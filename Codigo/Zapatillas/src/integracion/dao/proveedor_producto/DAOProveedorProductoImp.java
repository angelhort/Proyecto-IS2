package integracion.dao.proveedor_producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import integracion.connection.DatabaseConnection;
import negocio.producto.TransferProducto;
import negocio.proveedor.TransferProveedor;
import negocio.proveedor_producto.TransferProveedor_producto;

public class DAOProveedorProductoImp implements DAOProveedorProducto {

	@Override
	public int alta(TransferProveedor_producto transfer) {
		Connection conn = DatabaseConnection.getConnection();
		String insert = "INSERT INTO Suministra(idProveedor, idProducto, precioSuministro) VALUES (?, ?, ?)";
		int result = -1;
		
		try {
			PreparedStatement statement = conn.prepareStatement(insert, PreparedStatement.RETURN_GENERATED_KEYS);
			
			statement.setInt(1, transfer.getIdProveedor());
			statement.setInt(2, transfer.getIdProducto());
			statement.setDouble(3, transfer.getPrecioSuministro());
			
			statement.executeUpdate();
		
			statement.close();
			conn.close();
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		DatabaseConnection.killConnection(conn);
		return result;
	}

	@Override
	public int baja(TransferProveedor_producto transfer) {
		Connection conn = DatabaseConnection.getConnection();
		String insert = "DELETE FROM Suministra WHERE idProveedor=? AND idProducto=?";
		int result = -1;
		
		try {
			PreparedStatement statement = conn.prepareStatement(insert);
			
			statement.setInt(1, transfer.getIdProveedor());
			statement.setInt(2, transfer.getIdProducto());
			
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
	public List<TransferProducto> getProductosFromProveedor(int idProveedor) {
		Connection conn = DatabaseConnection.getConnection();
		String query = String.format("SELECT P.idProducto, P.nombre, S.precioSuministro "
									+ "FROM Suministra as S, Productos as P "
									+ "WHERE S.idProducto = P.idProducto AND S.idProveedor = %s", idProveedor);
		
		List<TransferProducto> productos = new ArrayList<>();

		try {
			Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			
			while (resultSet.next()) {
				productos.add(new TransferProducto(resultSet.getInt("P.idProducto"),
													   resultSet.getString("P.nombre"),
													   resultSet.getDouble("S.precioSuministro")));
			}
			
			resultSet.close();
			statement.close();
			conn.close();
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		DatabaseConnection.killConnection(conn);
		return productos;
	}

	@Override
	public List<TransferProveedor> getProveedoresFromProducto(int idProducto) {
		Connection conn = DatabaseConnection.getConnection();
		String query = String.format("SELECT P.idProveedor, P.nombre, S.precioSuministro "
									+ "FROM Suministra as S, Proveedores as P "
									+ "WHERE S.idProveedor = P.idProveedor AND S.idProducto = %s", idProducto);
		
		List<TransferProveedor> proveedores = new ArrayList<>();

		try {
			Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			
			while (resultSet.next()) {
				proveedores.add(new TransferProveedor(resultSet.getInt("P.idProveedor"),
													   resultSet.getString("P.nombre"),
													   resultSet.getDouble("S.precioSuministro")));
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
