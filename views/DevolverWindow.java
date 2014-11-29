package views;

import controllers.DevolverController;
import models.Locacao;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by andre on 27/11/14.
 */
public class DevolverWindow extends JDialog {
    private JPanel mainPanel;
    private DevolverController controller;
    private JTable locacationTable;
    private JButton cancel;
    private JButton devolution;

    public DevolverWindow(DevolverController controller){
        this.controller = controller;

        setLayout();
        setComponents();
        setEvents();

        setMinimumSize(new Dimension(800, 600));
        pack();
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void setLayout(){
        mainPanel = new JPanel(new BorderLayout());
        setContentPane(mainPanel);
    }

    private void setComponents(){

        locacationTable = new JTable();

        preparLocacaoTable();
        mainPanel.add(createInternalPanel(), BorderLayout.CENTER);
        mainPanel.add(createToolbar(), BorderLayout.SOUTH);
    }

    private void preparLocacaoTable() {
        DefaultTableModel model = new DefaultTableModel(new Object[]{"Identificador", "Nome do cliente", "Locado / Devolvido", "Valor da Locação"},0);

        for(Locacao loc : controller.getActiveLocation()) {
            model.addRow(new Object[]{loc.getKey() ,loc.getC().getNome(), loc.getStatus(), loc.getValor()});
        }
        locacationTable.setModel(model);
    }

    private JPanel createInternalPanel(){
        JPanel devPanel = new JPanel(new BorderLayout());
        locacationTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(locacationTable);
        devPanel.add(scrollPane);
        return devPanel;
    }

    private JPanel createToolbar(){
        JPanel panel = new JPanel();

        devolution = new JButton("Devolver");
        cancel = new JButton("Cancelar");

        panel.add(devolution);
        panel.add(cancel);

        return panel;
    }


    private void setEvents(){

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        devolution.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer selec = locacationTable.getSelectedRow();
                String id = (String) locacationTable.getValueAt(selec, 0);

                controller.preparToDevolution(id);
            }
        });
    }
}
