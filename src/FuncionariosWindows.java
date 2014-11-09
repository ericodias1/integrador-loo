import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;


public class FuncionariosWindows extends JFrame{
	private CardLayout cardLayout = new CardLayout();
	private static final String PANEL_CREATE = "PANEL_CREATE";
	private static final String PANEL_EDIT = "PANEL_CREATE";
	private static final String PANEL_LIST = "PANEL_LIST";
	
	public FuncionariosWindows(){
		super("SISTEMA DE GERENCIAMENTO DE LOCADORA - GESTÃO DE FUNCIONÁRIOS");	
		
		// not use pack when fix size of window
		// pack();
		createIndexPanel();
		setSize(1000, 700);
		setResizable(true);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private void createIndexPanel(){
		getContentPane().setLayout(cardLayout);
//		add(createTopMenu(), BorderLayout.NORTH);
		add(createListPanel(), PANEL_LIST);
		add(createNewPanel(), PANEL_CREATE);
	}
	
	private JPanel createListPanel(){
		JPanel listPanel = new JPanel();
		
		listPanel.setLayout(new BorderLayout());
		listPanel.add(__createTopList(), BorderLayout.NORTH);
		
		listPanel.add(__createMainList(), BorderLayout.CENTER);
		
		listPanel.add(new JLabel("SISTEMA DE GERENCIAMENTO DE LOCADORA. PROJETO INTEGRADOR. BY ÉRICO DIAS & ANDRÉ SILVEIRA"), BorderLayout.SOUTH);
		
		return listPanel;
	}
	
	private JPanel __createTopList(){
		JPanel topList = new JPanel(new FlowLayout());
		
		JButton btnVoltar = new JButton("Voltar");
		topList.add(btnVoltar);
		
		JButton btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(getContentPane(), PANEL_CREATE);
			}
		});
		topList.add(btnNovo);
		
		return topList;
	}
	
	private JPanel __createMainList(){
		JPanel mainList = new JPanel(new GridLayout(3,1));
		
		String[] columnNames = {"ID", "Nome do funcionário", "Ações"};
		Object[][] dataTable = {
				{"001", "Érico Dias", "Editar, Excluir"},
				{"002", "Érico Dias", "Editar, Excluir"},
				{"003", "Érico Dias", "Editar, Excluir"},
				{"004", "Érico Dias", "Editar, Excluir"}
		};
		
		JTable listFuncionarios = new JTable(dataTable, columnNames);
		mainList.add(listFuncionarios);
		
		return mainList;
	}
	
	private JPanel createNewPanel(){
		JPanel newPanel = new JPanel(new BorderLayout());
		newPanel.add(new JButton("Voltar"), BorderLayout.NORTH);
		newPanel.add(createNewForm(), BorderLayout.CENTER);
		newPanel.add(new JLabel("SISTEMA DE GERENCIAMENTO DE LOCADORA. PROJETO INTEGRADOR. BY ÉRICO DIAS & ANDRÉ SILVEIRA"), BorderLayout.SOUTH);
		return newPanel;
	}
	
	private JPanel createNewForm(){
		JPanel formPanel = new JPanel(new GridLayout(6,2));
		
		formPanel.add(new JLabel("Nome: "));
		formPanel.add(new JTextField());
		
		formPanel.add(new JLabel("Data de nascimento: "));
		formPanel.add(new JTextField());
		
		formPanel.add(new JLabel("Login: "));
		formPanel.add(new JFormattedTextField(DateFormat.getDateInstance()));
		
		formPanel.add(new JLabel("Senha: "));
		formPanel.add(new JPasswordField());
		
		formPanel.add(new JButton("Cadastrar"));
		
		return formPanel;
	}
}
