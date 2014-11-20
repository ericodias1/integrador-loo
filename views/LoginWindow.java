package views;

import controllers.ClienteController;
import controllers.HomeController;
import controllers.LocarController;
import controllers.LoginController;
import models.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by andre on 16/11/14.
 */
public class LoginWindow extends JFrame{
    private LoginController controller;
    private JButton toolbarCancel;
    private JButton toolbarLogin;
    private GridLayout formLayout;
    private JPanel internalPanel;
    private JTextField tfUser = new JTextField();
    private JPasswordField tfPassword = new JPasswordField();


    public LoginWindow(LoginController controller){
        this.controller= controller;

        setLayout();
        setComponents();
        setEvents();

        setMinimumSize(new Dimension(200, 100));
        pack();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void setEvents() {
        toolbarLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String user = tfUser.getText();
                String passwd = tfPassword.getText();
                boolean validate = true;
                if (user == null || user.length() < 3) {
                    JOptionPane.showMessageDialog(internalPanel, "Usuário em branco ou inválido", "Atenção", 2);
                    validate = false;
                }
                if(user == null || user.length() < 3){
                    JOptionPane.showMessageDialog(internalPanel, "Senha em branco ou inválida", "Atenção", 2);
                    validate =  false;
                }
                if(validate){
                    boolean login = Usuario.login(user, passwd);
                    if(login){
                        new HomeController();
                        dispose();
                    }else{
                        JOptionPane.showMessageDialog(internalPanel, "Erro ao logar, verifique usuário e senha", "Erro", 0);
                    }
                }
            }
        });

        toolbarCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private void setComponents() {

        add(internalPanel, BorderLayout.CENTER);
        internalPanel.add(new JLabel("Usuário:"),0);
        internalPanel.add(tfUser,1);
        internalPanel.add(new JLabel("Senha:"),2);
        internalPanel.add(tfPassword,3);
        add(createToolbar(), BorderLayout.SOUTH);
    }

    private JPanel createToolbar() {
        JPanel panel = new JPanel();

        toolbarLogin = new JButton("Logar");
        toolbarCancel = new JButton("Cancelar");

        panel.add(toolbarLogin);
        panel.add(toolbarCancel);

        return panel;
    }

    private void setLayout() {
        getContentPane().setLayout(new BorderLayout());
        internalPanel = new JPanel();
        formLayout = new GridLayout(2,2);
        internalPanel.setLayout(formLayout);
    }
}
