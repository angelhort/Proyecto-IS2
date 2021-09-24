package integracion.connection;

import java.sql.*;

import negocio.trabajador.TransferTrabajador;

public class TestConn2 {

   public static void main(String[] args) {	   
	   try {
		   Connection conn = DatabaseConnection.getConnection();
		   Statement st = conn.createStatement();
		   
		   String sql = "SELECT * FROM Clientes";
		   ResultSet rs = st.executeQuery(sql);
		   
		   while (rs.next()) {
			   int idCliente = rs.getInt("idCliente");
			   String dni = rs.getString("dni");
			   String nombre = rs.getString("nombre");
			   
			   System.out.println(String.format("ID: %d  DNI: %s  Nombre: %s", 
					   								idCliente, dni, nombre));
		   }
		 
		   rs.close();
		   st.close();
		   
		   String dni = "54698823J";
		   String query = String.format("SELECT * FROM Trabajadores WHERE DNI = \"%s\"", dni);
			
		   TransferTrabajador trabajador = null;

			Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
				
				if (resultSet.next()) {
					System.out.println(String.format("ID trabajador: %d", resultSet.getInt("idCliente")));
				}
				
				resultSet.close();
				statement.close();
		   conn.close();
	   } catch (SQLException e) {
		   e.printStackTrace();
	   }
   }
}
