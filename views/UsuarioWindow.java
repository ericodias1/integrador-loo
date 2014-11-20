package views;

import controllers.UsuarioController;
import models.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by erico on 20/11/14.
 */
public class UsuarioWindow extends JDialog{
    private UsuarioController controller;
    private JPanel mainPanel;
    private JTable mainTable;
    private JButton toolbarNew;
    private JButton toolbarEdit;
    private JButton toolbarDetail;
    private JButton toolbarDelete;
    private JScrollPane scroolPanel;

    public UsuarioWindow(UsuarioController controller){
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

    private void setEvents() {
        toolbarNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new UsuarioSaveDialog(UsuarioWindow.this, controller, null);
                updateTable();
            }
        });

        toolbarDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(mainTable.getSelectedRow() != -1){
                    String cpf = (String) mainTable.getValueAt(mainTable.getSelectedRow(), 0);
                    Usuario u = Usuario.findByCpf(cpf);
                    u.delete();
                    updateTable();
                }
            }
        });

        toolbarDetail.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(mainTable.getSelectedRow() == -1){
                    JOptionPane.showMessageDialog(null, "Selecione um usuário na tabela", "Atenção", 2);
                }else{
                    String cpf = (String) mainTable.getValueAt(mainTable.getSelectedRow(), 0);
                    Usuario u = Usuario.findByCpf(cpf);
                    JOptionPane.showMessageDialog(null, u.toString(), "Usuário - " + cpf, 1);
                }
            }
        });

        toolbarEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(mainTable.getSelectedRow() != -1){
                    String cpf = (String) mainTable.getValueAt(mainTable.getSelectedRow(), 0);
                    Usuario u = Usuario.findByCpf(cpf);

                    new UsuarioSaveDialog(UsuarioWindow.this, controller, u);
                    updateTable();
                }
            }
        });
    }

    private void updateTable() {
        DefaultTableModel model = new DefaultTableModel(new Object[] {"CPF", "Nome", "E-mail", "Login", "Senha"}, 0);
        for(Usuario u : Usuario.all()){
            model.addRow(new Object[]{u.getCpf(), u.getNome(), u.getEmail(), u.getLogin(), u.getSenha()});
        }
        mainTable.setModel(model);
    }

    private void setComponents() {
        mainTable = new JTable();
        scroolPanel = new JScrollPane(mainTable);
        mainPanel.add(scroolPanel, BorderLayout.CENTER);
        mainPanel.add(createToolbar(), BorderLayout.SOUTH);
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

    private void setLayout() {
        mainPanel = new JPanel(new BorderLayout());
        setContentPane(mainPanel);
    }
}
