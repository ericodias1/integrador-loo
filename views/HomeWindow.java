package views;

import controllers.ClienteController;
import controllers.HomeController;
import controllers.LocarController;
import controllers.MediaController;
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
    private JMenu LocarMenu;
    private JMenu Crud;
    private JMenu ExitMenu;
    private JMenuItem crudMedia;
    private JMenuItem crudCliente;
    private JMenuItem crudUsuario;
    private JMenu DevolverMenu;

    public HomeWindow(HomeController controller){
        super("LocaFix - Gerenciamento de video-locadoras");
        this.controller = controller;

        setLayout();
        //setComponents();
        setEvents();

        setMinimumSize(new Dimension(800, 600));
        pack();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void setLayout(){

        mainPanel = new JPanel(new BorderLayout());
        setContentPane(mainPanel);

        menuBar = new JMenuBar();

        LocarMenu = new JMenu("Locar");
        ExitMenu = new JMenu("Exit");
        //DevolverMenu = new JMenu("Devolver");
        Crud = new JMenu("Cadastros");
        crudMedia = new JMenuItem("Media");
        crudUsuario = new JMenuItem("Usuario");
        crudCliente = new JMenuItem("Cliente");

        Crud.add(crudCliente);
        Crud.add(crudMedia);
        Crud.add(crudUsuario);

        menuBar.add(LocarMenu);
        menuBar.add(Crud);
        menuBar.add(ExitMenu);

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
                new MediaController();
            }
        });
        crudUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //new UsuarioController();
            }
        });
        LocarMenu.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                new LocarController();
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        ExitMenu.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                System.exit(0);
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }
}
