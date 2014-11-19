package views;

import controllers.HomeController;

import javax.swing.*;
import java.awt.*;

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

        // build the File menu
        LocarMenu = new JMenu("Locar");
        // build the Edit menu
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
