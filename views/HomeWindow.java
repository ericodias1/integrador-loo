package views;

import controllers.*;
import models.Cliente;
import models.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by andre on 19/11/14.
 */
public class HomeWindow extends JFrame {
    private JPanel mainPanel;
    private HomeController controller;
    private JMenuBar menuBar;
    private JMenu locarMenu;
    private JMenu listMenu;
    private JMenu crud;
    private JMenu exitMenu;
    private JMenuItem exitSubMenu;
    private JMenuItem locSubmenu;
    private JMenuItem devSubMenu;
    private JMenuItem listUsuario;
    private JMenuItem listCliente;
    private JMenuItem listMedia;
    private JMenuItem crudMedia;
    private JMenuItem crudCliente;
    private JMenuItem crudUsuario;

    public HomeWindow(HomeController controller){
        super("LocaFix - Gerenciamento de video-locadoras");
        this.controller = controller;

        setLayout();
        //setComponents();
        setEvents();

        setMinimumSize(new Dimension(500, 450));
        pack();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void setLayout(){

        mainPanel = new JPanel(new BorderLayout());
        setContentPane(mainPanel);

        menuBar = new JMenuBar();

        locarMenu = new JMenu("Locar");
        locSubmenu = new JMenuItem("Nova Locação");
        devSubMenu = new JMenuItem("Devolução");
        locarMenu.add(locSubmenu);
        locarMenu.add(devSubMenu);

        listMenu = new JMenu("Listar");

        exitMenu = new JMenu("Sistema");
        exitSubMenu = new JMenuItem("Sair");
        exitMenu.add(exitSubMenu);


        listUsuario = new JMenuItem ("Usuario");
        listCliente = new JMenuItem ("Cliente");
        listMedia   = new JMenuItem ("Media");
        listMenu.add(listUsuario);
        listMenu.add(listCliente);
        listMenu.add(listMedia);

        crud = new JMenu("Cadastros");
        crudMedia = new JMenuItem("Media");
        crudUsuario = new JMenuItem("Usuario");
        crudCliente = new JMenuItem("Cliente");

        crud.add(crudCliente);
        crud.add(crudMedia);
        crud.add(crudUsuario);

        menuBar.add(locarMenu);
        menuBar.add(crud);
        menuBar.add(listMenu);
        menuBar.add(exitMenu);
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.X_AXIS));
        centerPanel.add(new JLabel(new ImageIcon("logo-locafix.jpg")));
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(menuBar, BorderLayout.NORTH);
    }

    private void setComponents(){

    }

    private void setEvents(){
        crudCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClienteController c = new ClienteController();
                c.createDialog(HomeWindow.this);
            }
        });
        crudMedia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MediaController mc = new MediaController();
                mc.createDialog(HomeWindow.this);
            }
        });
        crudUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UsuarioController u = new UsuarioController();
                u.createDialog(HomeWindow.this);
            }
        });
        locSubmenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocarController lc = new LocarController();
                lc.ListDialog();
            }
        });
        devSubMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DevolverController dvc = new DevolverController();
                dvc.ListDialog();
            }
        });
        exitSubMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer result = JOptionPane.showConfirmDialog(mainPanel,"Deseja realmente sair?","Sair - LocaFix",0);
                System.out.println(result);
                if(result == 0){
                    System.exit(0);
                }
            }
        });
        listCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClienteController cc = new ClienteController();
                cc.listCliente();
            }
        });

        listMedia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MediaController mc = new MediaController();
                mc.listDialog();
            }
        });

        listUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UsuarioController uc = new UsuarioController();
                uc.ListController();
            }
        });
    }
}
