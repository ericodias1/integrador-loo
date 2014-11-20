package views;

import controllers.UsuarioController;
import models.Usuario;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by erico on 20/11/14.
 */
public class UsuarioSaveDialog extends JDialog{
    UsuarioController controller;
    Usuario usuario;

    //panels
    private JPanel panel;
    private JPanel internalPanel;
    //layouts
    private BorderLayout container;
    private GridLayout formContainer;
    //buttons and inputs
    private JButton btSave;
    private JTextField tfNome = new JTextField();
    private JTextField tfCpf = new JTextField();
    private JTextField tfTelefone = new JTextField();
    private JTextField tfRg = new JTextField();
    // data de nascimento
    private JTextField tfSexo = new JTextField();
    private JTextField tfEmail = new JTextField();
    private JTextField tfCarteira_trabalho = new JTextField();
    private JTextField tfLogin = new JTextField();
    private JTextField tfSenha = new JTextField();

    public UsuarioSaveDialog(UsuarioWindow parent, UsuarioController controller, Usuario u) {
        super(parent,"Salvar Usuário", true);
        this.controller = controller;
        this.usuario = u;

        setLayout();
        setComponents();
        setEvents();

        setMinimumSize(new Dimension(500, 400));
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
                    String telefone = tfTelefone.getText();
                    String carteira_trabalho = tfCarteira_trabalho.getText();
                    String sexo = tfSexo.getText();
                    String email = tfEmail.getText();
                    String login = tfLogin.getText();
                    String senha = tfSenha.getText();

                    if(usuario == null){
                        usuario = new Usuario(nome, cpf, telefone, rg, null, (Character)sexo.charAt(0), email, carteira_trabalho, login, senha);
                    }else{
                        usuario.copy(new Usuario(nome, cpf, telefone, rg, null, (Character)sexo.charAt(0), email, carteira_trabalho, login, senha));
                    }
                    usuario.save();
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

        internalPanel.add(new JLabel("Telefone"));
        internalPanel.add(tfTelefone);

        internalPanel.add(new JLabel("Carteira de trabalho"));
        internalPanel.add(tfCarteira_trabalho);

        internalPanel.add(new JLabel("Sexo"));
        internalPanel.add(tfSexo);

        internalPanel.add(new JLabel("E-mail"));
        internalPanel.add(tfEmail);

        internalPanel.add(new JLabel("Login"));
        internalPanel.add(tfLogin);

        internalPanel.add(new JLabel("Senha"));
        internalPanel.add(tfSenha);

        if(usuario != null){
            tfNome.setText(usuario.getNome());
            tfCpf.setText(usuario.getCpf());
            tfRg.setText(usuario.getRg());
            tfTelefone.setText(usuario.getTelefone());
            tfCarteira_trabalho.setText(usuario.getCarteira_trabalho());
            tfSexo.setText(usuario.getSexo().toString());
            tfEmail.setText(usuario.getEmail());
            tfLogin.setText(usuario.getLogin());
            tfSenha.setText(usuario.getSenha());
        }
    }

    private void setLayout() {
        panel = new JPanel();
        internalPanel = new JPanel();

        container = new BorderLayout();
        formContainer = new GridLayout(9,9);

        panel.setLayout(container);
        internalPanel.setLayout(formContainer);

        panel.add(internalPanel, BorderLayout.CENTER);
        setContentPane(panel);
    }
}
