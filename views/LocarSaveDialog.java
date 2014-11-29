package views;

import controllers.LocarController;
import models.Cliente;
import models.Media;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by andre on 21/11/14.
 */
public class LocarSaveDialog extends JDialog {
    private ArrayList<Media> medias = new ArrayList<Media>();
    private Cliente c;
    private LocarController controller;
    private JPanel panel;
    private JPanel top_layout;
    private JPanel bottom_layout;
    private JPanel left_layout;
    private JPanel right_panel;

    private JButton locar;
    private JButton cancelar;

    private JTable medias_table;

    private JRadioButton radio_dinheiro;
    private JRadioButton radio_cartao;
    private JRadioButton radio_devolucao;

    public LocarSaveDialog(JFrame parent, ArrayList<Media> medias, Cliente c, LocarController controller){

        super(parent, "Locar media - LocaFix", true);
        this.c = c;
        this.medias = medias;
        this.controller = controller;

        setLayout();
        setComponents();
        setEvents();

        setMinimumSize(new Dimension(800,600));
        setVisible(true);

    }

    private void setLayout(){
        panel = new JPanel(new BorderLayout());
        top_layout = new JPanel(new GridLayout(5,2));
        bottom_layout = new JPanel(new FlowLayout());

        left_layout = new JPanel(new BorderLayout());
        right_panel = new JPanel(new GridLayout(4,1));

        JPanel center_layout = new JPanel(new BorderLayout());
        center_layout.add(left_layout, BorderLayout.WEST);
        center_layout.add(right_panel, BorderLayout.CENTER);

        panel.add(top_layout, BorderLayout.NORTH);
        panel.add(bottom_layout, BorderLayout.SOUTH);
        panel.add(center_layout, BorderLayout.CENTER);

        setContentPane(panel);

    }

    private void setComponents(){
        medias_table = new JTable();

        /*Top_Layout*/
        top_layout.add(new JLabel("Nome: "));
        top_layout.add(new JLabel(this.c.getNome()));
        top_layout.add(new JLabel("Cpf: "));
        top_layout.add(new JLabel(this.c.getCpf()));
        top_layout.add(new JLabel("Rg: "));
        top_layout.add(new JLabel(this.c.getRg()));
        top_layout.add(new JLabel("Pontos: "));
        top_layout.add(new JLabel(this.c.getScore().toString()));
        top_layout.add(new JLabel("Pendencias: "));
        top_layout.add(new JLabel("Não há pendencias!"));

        /*Left_layout*/
        prepareMediaTable();
        JScrollPane scrollPane = new JScrollPane(medias_table);
        left_layout.add(new JLabel("MEDIAS:"), BorderLayout.NORTH);
        left_layout.add(scrollPane, BorderLayout.CENTER);

        /*Right_layout*/
        right_panel.add(new JLabel("Pagamento :"));

        radio_cartao = new JRadioButton("Cartão de Credito");
        radio_devolucao = new JRadioButton("Pagar na devolução");
        radio_dinheiro = new JRadioButton("Dinheiro");

        ButtonGroup radio_gp = new ButtonGroup();
        radio_gp.add(radio_cartao);
        radio_gp.add(radio_devolucao);
        radio_gp.add(radio_dinheiro);

        right_panel.add(radio_cartao);
        right_panel.add(radio_devolucao);
        right_panel.add(radio_dinheiro);

        /*Bottom Layout*/
        cancelar = new JButton("Cancelar") ;
        locar = new JButton("Locar");
        bottom_layout.add(cancelar);
        bottom_layout.add(locar);
    }

    private void setEvents(){
        cancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(panel, "Deseja realmente cancelar a locação?","Locação - LocaFix", 2);
                if(result == 0){
                    dispose();
                }
            }
        });

        locar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!radio_cartao.isSelected() && !radio_dinheiro.isSelected() && !radio_devolucao.isSelected()){
                    JOptionPane.showMessageDialog(panel, "É necessário selecionar uma forma de pagamento", "Erro - LocaFix",2);
                }else{
                    boolean pagamento = true;
                    if(radio_devolucao.isSelected()){
                        pagamento = false;
                    }
                    ArrayList<Integer> ids_medias = new ArrayList<Integer>();
                    for(Media m : LocarSaveDialog.this.medias){
                        ids_medias.add(m.getId());
                    }
                    LocarSaveDialog.this.controller.createLocation(LocarSaveDialog.this.c,ids_medias ,pagamento);

                    JOptionPane.showMessageDialog(panel, "A locação foi realizada com sucesso!", "Locação - LocaFix", 1);
                    dispose();
                }
            }
        });

    }

    private void prepareMediaTable() {
        DefaultTableModel model = new DefaultTableModel(new Object[]{"BarCODE", "Nome", "Genero"},0);

        for(Media m : this.medias) {
            model.addRow(new Object[]{m.getId(), m.getTitulo(), m.getGenero()});
        }
        medias_table.setModel(model);
    }
}
