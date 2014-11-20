package views;

import controllers.HomeController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by andre on 19/11/14.
 */
public class HomeWindow extends JFrame {
    private JPanel mainPanel;
    private HomeController controller;
    private JMenuBar menuBar;
    private JMenu LocarMenu;
    private JMenu DevolverMenu;

    public HomeWindow(HomeController controller){
        super("LocaFix - Gerenciamento de video-locadoras");
        this.controller = controller;

        setLayout();
        //setComponents();
        //setEvents();

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
        DevolverMenu = new JMenu("Devolver");
        // sub = new JMenuItem("name");

        menuBar.add(LocarMenu);
        menuBar.add(DevolverMenu);

        mainPanel.add(menuBar, BorderLayout.NORTH);
    }

    private void setComponents(){

    }

    private void setEvents(){

    }
}
