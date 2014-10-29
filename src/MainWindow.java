import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.stream.IntStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainWindow extends JFrame{
	public MainWindow(){
		super("SISTEMA DE GERENCIAMENTO DE LOCADORA");
		
		// not use pack when fix size of window
		// pack();
		createIndexPanel();
		setSize(1000, 700);
		setResizable(true);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private void createIndexPanel(){
		getContentPane().setLayout(new BorderLayout());
		add(createTopMenu(), BorderLayout.NORTH);
		add(new JLabel("SISTEMA DE GERENCIAMENTO DE LOCADORA. PROJETO INTEGRADOR. BY ÉRICO DIAS & ANDRÉ SILVEIRA"), BorderLayout.SOUTH);
		add(new JLabel("Seja bem-vindo ao LocaFIX, um sistema de gerenciamento de locadoras que irá revolucionar sua vida!"), BorderLayout.CENTER);
	}

	private static JPanel createTopMenu() {
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		
		JButton funcionarios = new JButton("Gestão de funcionários");
		panel.add(funcionarios);
		
		JButton medias = new JButton("Gestão de medias");
		panel.add(medias);
		
		JButton clientes = new JButton("Gestão de clientes");
		panel.add(clientes);
		
		JButton caixa = new JButton("Caixa");
		panel.add(caixa);
		
		JButton sobreosistema = new JButton("Sobre o sistema");
		panel.add(sobreosistema);

		return panel;
	}
}
