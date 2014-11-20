package views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import controllers.HomeController;
import controllers.LocarController;
import models.Cliente;
import models.Media;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Vector;

/**
 * Created by andre on 18/11/14.
 */
public class LocarWindow extends JDialog {
    private JPanel mainPanel;
    private LocarController controller;
    private JTable mediaTable;
    private JTable clientTable;
    private JButton cancel;
    private JButton save;

    public LocarWindow(LocarController controller){
        this.controller = controller;

        setLayout();
        setComponents();
        setEvents();

        setMinimumSize(new Dimension(800, 600));
        pack();
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void setLayout() {
        mainPanel = new JPanel(new BorderLayout());
        setContentPane(mainPanel);
    }

    private void setComponents() {
        clientTable = new JTable();
        mediaTable = new JTable();

        prepareMediaTable();
        prepareClientTable();
        mainPanel.add(createInternalPanel(), BorderLayout.CENTER);
        mainPanel.add(createToolbar(), BorderLayout.SOUTH);
    }

    private void prepareMediaTable() {
        DefaultTableModel model = new DefaultTableModel(new Object[]{"BarCODE", "Nome", "Genero"},0);

        for(Media m : controller.getMedias()) {
            model.addRow(new Object[]{m.getId(), m.getTitulo(), m.getGenero()});
        }
        mediaTable.setModel(model);
    }

    private void prepareClientTable() {
        DefaultTableModel model = new DefaultTableModel(new Object[]{"Nome", "CPF"}, 0);

        for(Cliente c : Cliente.all()){
            model.addRow(new Object[]{c.getNome(), c.getCpf()});
        }

        clientTable.setModel(model);
    }

    private Component createToolbar() {
        JPanel panel = new JPanel();

        save = new JButton("Locar");
        cancel = new JButton("Cancelar");

        panel.add(save);
        panel.add(cancel);

        return panel;
    }

    private void setEvents() {
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    public JPanel createInternalPanel() {

        JPanel loclPanel = new JPanel(new BorderLayout());

        GridLayout dataGrid = new GridLayout(0,2);
        dataGrid.setHgap(30);

        JPanel centerPanel = new JPanel(dataGrid);

        GridLayout titleGrid = new GridLayout(0,2);
        titleGrid.setHgap(30);

        JPanel topPanel = new JPanel(titleGrid);

        topPanel.add(new JLabel("Selecionar Cliente:"));
        topPanel.add(new JLabel("Selecionar Medias:"));

        clientTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(clientTable);
        JScrollPane scrollPane2 = new JScrollPane(mediaTable);

        centerPanel.add(scrollPane);
        centerPanel.add(scrollPane2);

        loclPanel.add(topPanel, BorderLayout.NORTH);
        loclPanel.add(centerPanel, BorderLayout.CENTER);

        return loclPanel;
    }
}
