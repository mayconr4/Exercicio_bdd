package view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import BD.BD;

public class NavegaUsuarios extends JFrame {

	private JLabel label1, label2,label3;
	private JButton btProximo,btAnterior,btPrimeiro, btUltimo,btSair;
	private JTextField tfCodigo, tfNome, tfEmail;
	private BD bd;
	private PreparedStatement stm;
	private ResultSet result;
	
	public static void main(String[] args) {
		JFrame frame = new NavegaUsuarios();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public NavegaUsuarios() {
		inicializarComponentes();
		definirEventos();
	}

	

	private void inicializarComponentes() {
		setLayout(new FlowLayout(FlowLayout.LEFT));
		label1 = new JLabel("Código");
		add(label1);
		tfCodigo= new JTextField(10);
		add(tfCodigo);
		label2 = new JLabel("Nome");
		add(label2);
		tfNome = new JTextField(70);
		add(tfNome);
		label3 = new JLabel("Email");
		add(label3);
		tfEmail = new JTextField(50);
		add(tfEmail);
		
		btPrimeiro = new JButton("Primeiro");
		add(btPrimeiro);
		btUltimo = new JButton("Ultimo");
		add(btUltimo);
		btProximo = new JButton(">");
		add(btProximo);
		btAnterior = new JButton("<");
		add(btAnterior);
		btSair = new JButton("Sair");
		add(btSair);
		
		
		btProximo.setToolTipText("Proximo");
		btAnterior.setToolTipText("Anterior");
		btPrimeiro.setToolTipText("Primeiro");
		btUltimo.setToolTipText("Ultimo");
		
		setTitle("Navegando na Tabela de Usuarios");
		setBounds(200,400,620,150);
		setResizable(false);
		bd = new BD();
		if(!bd.getConnection()) {
			JOptionPane.showMessageDialog(null, "Falha ao conectar no banco de dados");
		}
		carregarTabela();
		atualizarCampos();
		
		
	}
	
	private void definirEventos() {
		
		btProximo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					result.next();
					atualizarCampos();
					
				} catch (Exception e2) {
					
				}
				
			}
		});  
		
		btAnterior.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					result.previous();
					atualizarCampos();
				} catch (Exception e2) {
					// TODO: handle exception
				}
				
			}
		});  
		
		btPrimeiro.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					result.first();
					atualizarCampos();
				} catch (Exception e2) {
					
				}
				
			}
			});
		
		btUltimo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					result.last();
					atualizarCampos();
				} catch (Exception e2) {
					
				}
				
			}
				});
		
		
		
		btSair.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					result.close();
					stm.close();
					
				} catch (Exception e2) {
					
				}
				bd.close();
				System.exit(0);
				
			}
				});
				
			}
		
		
		
	
	
	

	 
	
	private void carregarTabela() {
		
		String sql = "SELECT * FROM usuarios";
		try {
			stm = bd.conn.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,// Neccessario para navegação
					ResultSet.CONCUR_READ_ONLY
					);
			result = stm.executeQuery();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Erro!"+e.toString());
		}
		
	}
	
	
	private void atualizarCampos() {
		try {
			if (result.isAfterLast()) {
				result.last();
			}
			
			if (result.isBeforeFirst()) {
				result.first();
			} 			
			tfCodigo.setText(result.getString("codigo"));
			tfNome.setText(result.getString("nome"));
			tfEmail.setText(result.getString("email"));
			
			
		} catch (Exception e) {
			
		}
		
	}


}