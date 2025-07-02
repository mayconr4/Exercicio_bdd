package BD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ConsultaUsuarios {

	public static void main(String[] args) {
		BD bd = new BD();
		
		if(bd.getConnection()) {
			Connection connection = bd.conn;
			
			String sql = "SELECT codigo, email FROM exercicio ORDER BY codigo";
			try {
				PreparedStatement statement = connection.prepareStatement(sql);
				ResultSet resultSet = statement.executeQuery();
				
				System.out.println("Código Título");
				System.out.println("______ __________________________________");
				
				while(resultSet.next()) {
					String codigo = resultSet.getString("codigo");
					String email = resultSet.getString("email");
					System.out.println(codigo+"    "+email);
				}
				
				resultSet.close();
				statement.close();    
				
			}catch (SQLException erro) {
				JOptionPane.showMessageDialog(null, "Usuariio ou senha inválido");
			}finally {
				bd.close();
			} 			
			
		}


	}

}
