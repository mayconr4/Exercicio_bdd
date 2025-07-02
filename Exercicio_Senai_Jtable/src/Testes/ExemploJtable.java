package Testes;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ExemploJtable {

	public static void main(String[] args) {
		String[][] dados = {
				{"1","ana","25"},
				{"2","Bruno","30"},
				{"3","Carlos","22"},
				{"1","ana","25"},
				{"2","Bruno","30"},
				{"3","Carlos","22"},
				{"1","ana","25"},
				{"2","Bruno","30"},
				{"3","Carlos","22"}
				
		};
		
		String[] colunas = {"ID","Nome","Idade"};
		
		
		JTable tabela = new JTable(dados,colunas);
		
		JScrollPane scroll = new JScrollPane(tabela);
		
		JFrame frame = new JFrame("Exemplo tabela");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400,200);
		frame.add(scroll);
		frame.setVisible(true);
		
		
		
		
		

	}

}
