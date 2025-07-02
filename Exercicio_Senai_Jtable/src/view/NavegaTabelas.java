package view;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import BD.BD;

public class NavegaTabelas extends JFrame {

    private JLabel label1, label2, label3;
    private JButton btSair;
    private JTable tabela;
    private DefaultTableModel modelo;
    private BD bd;
    private PreparedStatement stm;
    private ResultSet result;

    public static void main(String[] args) {
        JFrame frame = new NavegaTabelas();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public NavegaTabelas() {
        inicializarComponentes();
        definirEventos();
    }

    private void inicializarComponentes() {
        setLayout(null); // usar posicionamento absoluto
        btSair = new JButton("Sair");
        btSair.setBounds(330, 130, 80, 30);
        add(btSair);

        // Configurações da janela
        setTitle("Navegação com Tabela");
        setBounds(200, 400, 450, 200);
        setResizable(false);

        // Conectar ao banco
        bd = new BD();
        if (!bd.getConnection()) {
            JOptionPane.showMessageDialog(null, "Falha ao conectar no banco de dados");
        } else {
            carregarTabela(); // ✅ só se conectou
        }


        // Inicializa a tabela
        modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new String[] { "Código", "Nome", "Email" });

        tabela = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBounds(10, 10, 400, 100);
        add(scroll);

        carregarTabela();
    }

    private void definirEventos() {
        btSair.addActionListener(e -> {
            dispose(); // Fecha só a janela
            // Ou System.exit(0); para encerrar tudo
        });
    }

    private void carregarTabela() {
        String sql = "SELECT * FROM usuarios";
        try {
            stm = bd.conn.prepareStatement(sql,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            result = stm.executeQuery();

            while (result.next()) {
                int codigo = result.getInt("codigo");
                String nome = result.getString("nome");
                String email = result.getString("email");

                modelo.addRow(new Object[] { codigo, nome, email });
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar dados: " + e.toString());
        }
    }
}
