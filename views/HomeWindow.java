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
    private JMenu fileMenu;
    private JMenu editMenu;
    private JMenuItem openMenuItem;
    private JMenuItem cutMenuItem;
    private JMenuItem copyMenuItem;
    private JMenuItem pasteMenuItem;

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
        fileMenu = new JMenu("File");
        openMenuItem = new JMenuItem("Open");
        //openMenuItem.addActionListener(this);
        fileMenu.add(openMenuItem);

        // build the Edit menu
        editMenu = new JMenu("Edit");
        cutMenuItem = new JMenuItem("Cut");
        copyMenuItem = new JMenuItem("Copy");
        pasteMenuItem = new JMenuItem("Paste");
        editMenu.add(cutMenuItem);
        editMenu.add(copyMenuItem);
        editMenu.add(pasteMenuItem);

        // add menus to menubar
        menuBar.add(fileMenu);
        menuBar.add(editMenu);

        mainPanel.add(menuBar, BorderLayout.NORTH);
    }

    private void setComponents(){

    }

    private void setEvents(){

    }
}
