package views;

import controllers.ClienteController;
import models.Cliente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by erico on 11/11/14.
 */
public class ClienteWindow extends JFrame{
    private ClienteController controller;
    private JTable mainTable;
    private JButton toolbarNew;
    private JButton toolbarUpdate;
    private JButton toolbarDetail;
    private JButton toolbarDelete;

    public ClienteWindow(ClienteController controller){
        this.controller = controller;

        setLayout();
        setComponents();
        setEvents();

        setMinimumSize(new Dimension(800, 600));
        pack();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        updateTable();
    }

    private void setEvents() {
        toolbarNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new ClienteSaveDialog(ClienteWindow.this, controller, null);
                updateTable();
            }
        });

        toolbarUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(mainTable.getSelectedRow() != -1){
                    String nome = (String) mainTable.getValueAt(mainTable.getSelectedRow(), 0);
                    String cpf = (String) mainTable.getValueAt(mainTable.getSelectedRow(), 1);
                    String rg = (String)  mainTable.getValueAt(mainTable.getSelectedRow(), 3);
//                    String sexo_ = (String) mainTable.getValueAt(mainTable.getSelectedRow(), 5);
//                    Character sexo = sexo_.charAt(0);
                    String email = (String) mainTable.getValueAt(mainTable.getSelectedRow(), 6);
                    String telefone = (String) mainTable.getValueAt(mainTable.getSelectedRow(), 2);
                    String profissao = (String) mainTable.getValueAt(mainTable.getSelectedRow(), 7);

                    Cliente cliente = new Cliente(nome, cpf, telefone, rg, null, 'M', email, profissao);

                    new ClienteSaveDialog(ClienteWindow.this, controller, cliente);
                    updateTable();
                }
            }
        });

        toolbarDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(mainTable.getSelectedRow() != -1){
                    String cpf = (String) mainTable.getValueAt(mainTable.getSelectedRow(), 1);

                    Cliente cliente = Cliente.findByCpf(cpf);
                    cliente.delete();

                    updateTable();
                }
            }
        });
    }

    private void updateTable() {
        DefaultTableModel model = new DefaultTableModel(new Object[]{"Nome", "CPF", "Telefone", "RG", "Data de nascimento", "Sexo", "E-mail","Profissao", "Score"}, 0);
        for(Cliente c : Cliente.all()){
            model.addRow(new Object[]{c.getNome(), c.getCpf(), c.getTelefone(), c.getRg(), c.getData_nascimento(), c.getSexo(), c.getEmail(), c.getProfissao(), c.getScore()});
        }

        mainTable.setModel(model);
    }

    private void setComponents() {
        mainTable = new JTable();
        add(mainTable, BorderLayout.CENTER);
        add(createToolbar(), BorderLayout.SOUTH);
    }

    private JPanel createToolbar() {
        JPanel panel = new JPanel();

        toolbarNew = new JButton("Novo");
        toolbarDetail = new JButton("Detalhar");
        toolbarUpdate = new JButton("Editar");
        toolbarDelete = new JButton("Excluir");

        panel.add(toolbarNew);
        panel.add(toolbarDetail);
        panel.add(toolbarUpdate);
        panel.add(toolbarDelete);

        return panel;
    }

    private void setLayout() {
        getContentPane().setLayout(new BorderLayout());
    }
}
