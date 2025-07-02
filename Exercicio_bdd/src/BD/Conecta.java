package BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Conecta {

	public static void main(String[] args) {
		final String DRIVER = "com.mysql.cj.jdbc.Driver";
		final String URL = "jdbc:mysql://localhost:3306/banco";
		
		try {
			
			Class.forName(DRIVER);
			Connection connection = DriverManager.getConnection(URL,"root","senai@126");
			JOptionPane.showMessageDialog(null,"Conexão realizada com sucesso!!!!");	
			connection.close();
		} catch (ClassNotFoundException erro) {
			JOptionPane.showMessageDialog(null,"Driver não encotrado!\n"+erro.toString());
		}catch (SQLException erro) {
			JOptionPane.showMessageDialog(null,"Usuario ou senha inválidos erro na conexão "+erro.toString());
		}
		

	}

}
