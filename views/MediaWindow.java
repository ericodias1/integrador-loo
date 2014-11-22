package views;

import controllers.MediaController;
import models.Media;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by andre on 20/11/14.
 */
public class MediaWindow extends JDialog{
    private MediaController controller;
    private JPanel mainPanel;
    private JTable mainTable;
    private JButton toolbarNew;
    private JButton toolbarEdit;
    private JButton toolbarDetail;
    private JButton toolbarDelete;
    private JScrollPane scroolPanel;


    public MediaWindow(MediaController controller){
        this.controller = controller;

        setLayout();
        setComponents();
        setEvents();

        setMinimumSize(new Dimension(800, 600));
        pack();
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        updateTable();
    }

    private void updateTable(){
        DefaultTableModel model = new DefaultTableModel(new Object[] {"ID", "Titulo", "Genero", "Valor", "Status"}, 0);
        for(Media m : Media.all()){
            model.addRow(new Object[]{m.getId(), m.getTitulo(), m.getGenero(), m.getPreco(), m.getStatus()});
        }
        mainTable.setModel(model);
    }

    private void setLayout(){
        mainPanel = new JPanel(new BorderLayout());
        setContentPane(mainPanel);
    }

    private JPanel createToolbar() {
        JPanel panel = new JPanel();

        toolbarNew = new JButton("Novo");
        toolbarEdit = new JButton("Editar");
        toolbarDetail = new JButton("Detalhar");
        toolbarDelete = new JButton("Excluir");

        panel.add(toolbarNew);
        panel.add(toolbarEdit);
        panel.add(toolbarDetail);
        panel.add(toolbarDelete);

        return panel;
    }

    private void setComponents(){
        mainTable = new JTable();
        scroolPanel = new JScrollPane(mainTable);
        mainPanel.add(scroolPanel, BorderLayout.CENTER);
        mainPanel.add(createToolbar(), BorderLayout.SOUTH);
    }

    private void setEvents(){
        toolbarNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new MediaSaveDialog(MediaWindow.this, controller, null);
                updateTable();
            }
        });

        toolbarDetail.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(mainTable.getSelectedRow() == -1){
                    JOptionPane.showMessageDialog(null, "Selecione um usuário na tabela", "Atenção", 2);
                }else{
                    Integer id = (Integer) mainTable.getValueAt(mainTable.getSelectedRow(), 0);

                    Media m = Media.findById(id);
                    JOptionPane.showMessageDialog(null, m.toString(), "Media - " + id, 1);
                }
            }
        });

        toolbarEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(mainTable.getSelectedRow() != -1){
                    Integer id = (Integer) mainTable.getValueAt(mainTable.getSelectedRow(), 0);
                    Media m = Media.findById(id);
                    System.out.println(m.toString());

                    new MediaSaveDialog(MediaWindow.this, controller, m);
                    updateTable();
                }
            }
        });

        toolbarDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(mainPanel, "Deseja realmente excluir esta media?", "Excluir media - LocaFix", 2);
                System.out.println(result);
                if(result == 0) {
                    if(mainTable.getSelectedRow() != -1){
                        Integer id = (Integer) mainTable.getValueAt(mainTable.getSelectedRow(), 0);
                        Media m = Media.findById(id);
                        m.delete();
                        updateTable();
                    }
                }
            }
        });
    }
}

