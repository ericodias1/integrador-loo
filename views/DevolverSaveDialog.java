package views;

import controllers.DevolverController;
import models.Locacao;

import javax.swing.*;
import java.awt.*;

import static java.awt.BorderLayout.*;

/**
 * Created by andre on 29/11/14.
 */
public class DevolverSaveDialog extends JDialog {
    private DevolverController controller;
    private Locacao loc;

    private JPanel mainPanel;
    private JPanel eastPanel;
    private JPanel westPanel;
    private JPanel bottomPanel;
    private JTable mediaTable;

    private JRadioButton radio_cartao;
    private JRadioButton radio_avista;

    private JButton BtDevolver;
    private JButton BtCancelar;

    public DevolverSaveDialog(DevolverController controller, Locacao loc){
        this.controller = controller;
        this.loc = loc;

        setLayout();
        setComponents();
        setEvents();

        setMinimumSize(new Dimension(800,600));
        setVisible(true);
    }

    private void setEvents() {

    }

    private void setComponents() {

        prepareMediaTable();
        JScrollPane scroll = new JScrollPane(mediaTable);

        JPanel topWest = new JPanel(new FlowLayout());
        topWest.add(new JLabel("Medias:"));
        westPanel.add(topWest, BorderLayout.NORTH);
        westPanel.add(scroll, BorderLayout.CENTER);

        JPanel topEast = new JPanel(new FlowLayout());
        topEast.add(new JLabel("Pagamento:"));
        eastPanel.add(topEast, BorderLayout.NORTH);

        if(!loc.getPago()) {

            ButtonGroup radioContent = new ButtonGroup();
            this.radio_cartao = new JRadioButton("à vista");
            this.radio_avista = new JRadioButton("Cartão");
            radioContent.add(radio_avista);
            radioContent.add(radio_cartao);
            JPanel eastCenter = new JPanel(new GridLayout(1,2));
            eastCenter.add(radio_cartao);
            eastCenter.add(radio_avista);
            eastPanel.add(eastCenter, BorderLayout.CENTER);

        }else{
            JPanel eastCenter = new JPanel(new FlowLayout());
            JLabel title = new JLabel("A locação já foi paga");
            title.setForeground(Color.red);
            eastCenter.add(title);
            eastPanel.add(eastCenter, BorderLayout.CENTER);
        }

        BtDevolver = new JButton("Devolver");
        BtCancelar = new JButton("Cancelar");
        bottomPanel.add(BtDevolver);
        bottomPanel.add(BtCancelar);

    }

    private void prepareMediaTable() {

    }

    private void setLayout() {
        this.mainPanel = new JPanel(new BorderLayout());
        eastPanel = new JPanel(new BorderLayout());
        westPanel = new JPanel(new BorderLayout());
        bottomPanel = new JPanel(new FlowLayout());

        this.mediaTable = new JTable();

        mainPanel.add(eastPanel, BorderLayout.CENTER);
        mainPanel.add(westPanel, BorderLayout.WEST);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);

    }
}
