package views;

import controllers.DevolverController;
import models.Locacao;
import models.Media;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        BtCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(mainPanel, "Deseja realmente cancelar a Devolução?","Devolução - LocaFix", 2);
                if(result == 0){
                    dispose();
                }
            }
        });

        BtDevolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(loc.getPago()){
                    Integer result = JOptionPane.showConfirmDialog(mainPanel, "Confirmar devolução:", "Devolução LocaFix",0);
                    if(result == 0){
                        DevolverSaveDialog.this.controller.salveDevolution(loc);
                        dispose();
                        prepareMediaTable();
                    }
                }else{
                    if(!radio_avista.isSelected() && !radio_cartao.isSelected()){
                        JOptionPane.showMessageDialog(mainPanel,"É preciso selecionar um método de pagamento!", "Devolução - LocaFix", 0);
                    }else{
                        Integer result = JOptionPane.showConfirmDialog(mainPanel, "Confirmar devolução:", "Devolução LocaFix",0);
                        if(result == 0){
                            DevolverSaveDialog.this.controller.salveDevolution(loc);
                            dispose();
                            prepareMediaTable();
                        }
                    }
                }
            }
        });
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
        DefaultTableModel model = new DefaultTableModel(new Object[]{"BarCODE", "Nome", "Genero"},0);

        for(Integer id_media : this.loc.getMedias()) {
            Media m = Media.findById(id_media);
            model.addRow(new Object[]{m.getId(), m.getTitulo(), m.getGenero()});
        }
        mediaTable.setModel(model);
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
