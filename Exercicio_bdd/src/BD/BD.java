package BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class BD {
	
	public Connection conn = null;
	private final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private final String DBNAME = "exercicio";
	private final String URL = "jdbc:mysql://localhost:3306/"+DBNAME;
	private final String LOGIN = "root";
	private final String  SENHA = "senai@126"; 
	
	public boolean getConnection() {
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL,LOGIN,SENHA);
			JOptionPane.showMessageDialog(null, "conectou");
			return true;
			
		} catch (ClassNotFoundException erro) {
			JOptionPane.showMessageDialog(null, "Driiver de conexão não encontrado "+erro.toString());
			return false;
		}catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "Usuario ou senha não enncontrado "+erro.toString());
			return false;
	
		}
	}
	
		public void close() {
			try {
				conn.close();
			}catch (Exception erro) {
				
			}
		}
	
	
}
