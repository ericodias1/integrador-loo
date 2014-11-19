package views;

import controllers.ClienteController;
import models.Cliente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JTextField tfData_nascimento = new JTextField();
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

    private void setEvents() {
        btSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try{
                    String nome = tfNome.getText();
                    String cpf = tfCpf.getText();
                    String rg = tfRg.getText();
                    Character sexo = tfSexo.getText().charAt(0);
                    String email = tfEmail.getText();
                    String telefone = tfTelefone.getText();
                    String profissao = tfProfissao.getText();

                    if(cliente == null){
                        cliente = new Cliente(nome, cpf, telefone, rg, null, sexo, email, profissao);
                    }else{
                        cliente.copy(new Cliente(nome, cpf, telefone, rg, null, sexo, email, profissao));
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
        btSave = new JButton("Salvar");
        panel.add(btSave, BorderLayout.SOUTH);

        internalPanel.add(new JLabel("Nome"));
        internalPanel.add(tfNome);

        internalPanel.add(new JLabel("CPF"));
        internalPanel.add(tfCpf);

        internalPanel.add(new JLabel("RG"));
        internalPanel.add(tfRg);

        internalPanel.add(new JLabel("Data de nascimento"));
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
//            tfData_nascimento.setText(cliente.getData_nascimento().toString());
            tfSexo.setText(cliente.getSexo().toString());
            tfEmail.setText(cliente.getEmail());
            tfTelefone.setText(cliente.getTelefone());
            tfProfissao.setText(cliente.getProfissao());
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
