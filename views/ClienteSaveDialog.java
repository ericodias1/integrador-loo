package views;

import controllers.ClienteController;
import models.Cliente;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * Created by erico on 11/11/14.
 */
public class ClienteSaveDialog extends JDialog{
    // Layout externo
    private BorderLayout container;

    // Layout interno
    private GridLayout formLayout;

    // Painel externo
    private JPanel panel;

    // Painel interno
    private JPanel internalPanel;

    // Controlador e modelo
    private ClienteController controller;
    private Cliente cliente;

    // Botão de salvar
    private JButton btSave;

    // inputs
    private JTextField tfNome = new JTextField();
    private JTextField tfCpf = new JTextField();
    private JTextField tfTelefone = new JTextField();
    private JTextField tfRg = new JTextField();
    private MaskFormatter maskDate;
    private JFormattedTextField tfData_nascimento;
    private JTextField tfSexo = new JTextField();
    private JTextField tfEmail = new JTextField();
    private JTextField tfProfissao = new JTextField();

    public ClienteSaveDialog(ClienteWindow parent, ClienteController controller, Cliente c) {
        super(parent, "Salvar cliente", true);
        this.controller = controller;
        this.cliente = c;

        setLayout();
        setComponents();
        setEvents();

        setMinimumSize(new Dimension(500,400));
        setVisible(true);
    }

    public ClienteSaveDialog(JFrame parent, ClienteController controller, Cliente c) {
        super(parent, "Salvar cliente", true);
        this.controller = controller;
        this.cliente = c;

        setLayout();
        setComponents();
        setEvents();

        setMinimumSize(new Dimension(500,400));
        setVisible(true);
    }


    private void setEvents() {
        btSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try{
                    String nome = tfNome.getText();
                    String cpf = tfCpf.getText();
                    String[] date = tfData_nascimento.getText().split("/");
                    Date data_nasc = new Date(Integer.parseInt(date[2]), (Integer.parseInt(date[1])-1), Integer.parseInt(date[0]));
                    String rg = tfRg.getText();
                    Character sexo = tfSexo.getText().charAt(0);
                    String email = tfEmail.getText();
                    String telefone = tfTelefone.getText();
                    String profissao = tfProfissao.getText();

                    if(cliente == null){
                        cliente = new Cliente(nome, cpf, telefone, rg, data_nasc, sexo, email, profissao);
                    }else{
                        cliente.copy(new Cliente(nome, cpf, telefone, rg, data_nasc, sexo, email, profissao));
                    }
                    cliente.save();

                    dispose();

                }catch (Exception e){
                    JOptionPane.showMessageDialog(null, "Erro ao salvar. "+e);
                }
            }
        });
    }

    private void setComponents() {
        try{
            btSave = new JButton("Salvar");
            panel.add(btSave, BorderLayout.SOUTH);

            internalPanel.add(new JLabel("Nome"));
            internalPanel.add(tfNome);

            internalPanel.add(new JLabel("CPF"));
            internalPanel.add(tfCpf);

            internalPanel.add(new JLabel("RG"));
            internalPanel.add(tfRg);

            this.maskDate = new MaskFormatter("##/##/####");
            this.maskDate.setPlaceholderCharacter('_');
            internalPanel.add(new JLabel("Data de nascimento"));
            tfData_nascimento = new JFormattedTextField(maskDate);
            internalPanel.add(tfData_nascimento);

            internalPanel.add(new JLabel("Sexo"));
            internalPanel.add(tfSexo);

            internalPanel.add(new JLabel("E-mail"));
            internalPanel.add(tfEmail);

            internalPanel.add(new JLabel("Telefone"));
            internalPanel.add(tfTelefone);

            internalPanel.add(new JLabel("Profissão"));
            internalPanel.add(tfProfissao);

            if(cliente != null){
                tfNome.setText(cliente.getNome());
                tfCpf.setText(cliente.getCpf());
                tfCpf.setEnabled(false);
                tfRg.setText(cliente.getRg());
                String text_tfDataNascimento = cliente.getData_nascimento().getDay()+"/"+(cliente.getData_nascimento().getMonth()+1)+"/"+cliente.getData_nascimento().getYear();
                System.out.println(text_tfDataNascimento);
                tfData_nascimento.setText(text_tfDataNascimento);
                tfSexo.setText(cliente.getSexo().toString());
                tfEmail.setText(cliente.getEmail());
                tfTelefone.setText(cliente.getTelefone());
                tfProfissao.setText(cliente.getProfissao());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void setLayout() {
        panel = new JPanel();
        internalPanel = new JPanel();

        container = new BorderLayout();
        formLayout = new GridLayout(8,8);

        panel.setLayout(container);
        internalPanel.setLayout(formLayout);

        panel.add(internalPanel, BorderLayout.CENTER);
        setContentPane(panel);
    }
}
