package views;

import controllers.MediaController;
import models.Media;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import javax.swing.text.html.parser.Parser;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * Created by andre on 19/11/14.
 */
public class MediaSaveDialog extends JDialog {
    private Media media;
    private MediaController controller;
    /*Panels*/
    private JPanel geralPanel;
    private JPanel northPanel;
    private JPanel southPanel;
    private JPanel centerPanel;
    /*Buttons*/
    private JButton saveButtom;
    private JButton cancelButtom;
    /*Texfiels*/
    private JTextField idTf;
    private JTextField tituloTf;
    private MaskFormatter maskDate;
    private JFormattedTextField dataTf;
    private JTextField generoTf;
    private JTextField classificacaoTf;
    private JTextField precoTf;
    /*Checkbox*/
    private JCheckBox disponivelTf;


    public MediaSaveDialog(JFrame parent ,MediaController controller, Media m){
        super(parent, "Salvar media", true);
        this.media = m;
        this.controller = controller;

        setLayout();
        setComponents();
        setEvents();

        setMinimumSize(new Dimension(500, 400));
        pack();
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public MediaSaveDialog(MediaWindow parent ,MediaController controller, Media m){
        super(parent, "Salvar media", true);
        this.media = m;
        this.controller = controller;

        setLayout();
        setComponents();
        setEvents();

        setMinimumSize(new Dimension(500, 400));
        pack();
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void setLayout(){
        geralPanel = new JPanel(new BorderLayout());
        southPanel = new JPanel(new FlowLayout());
        GridLayout grid = new GridLayout(0,2);
        grid.setVgap(20);
        grid.setVgap(20);

        centerPanel = new JPanel(grid);

        northPanel = new JPanel(new FlowLayout());
        northPanel.add(new JLabel("CADASTRO DE MEDIAS - LOCAFIX"));

        geralPanel.add(northPanel, BorderLayout.NORTH);
        geralPanel.add(centerPanel, BorderLayout.CENTER);
        geralPanel.add(southPanel, BorderLayout.SOUTH);

        setContentPane(geralPanel);
    }

    private void setComponents(){
        try{
            this.idTf = new JTextField();
            this.tituloTf =  new JTextField();
            maskDate = new MaskFormatter("##/##/####");
            maskDate.setPlaceholderCharacter('_');
            this.dataTf = new JFormattedTextField(maskDate);
            this.generoTf = new JTextField();
            this.classificacaoTf = new JTextField();
            this.precoTf = new JTextField();
            this.disponivelTf = new JCheckBox();

            centerPanel.add(new JLabel("Código de barras:"));
            centerPanel.add(idTf);
            centerPanel.add(new JLabel("Titulo:"));
            centerPanel.add(tituloTf);
            centerPanel.add(new JLabel("Data lançamento:"));
            centerPanel.add(dataTf);
            centerPanel.add(new JLabel("Genero:"));
            centerPanel.add(generoTf);
            centerPanel.add(new JLabel("Classificação:"));
            centerPanel.add(classificacaoTf);
            centerPanel.add(new JLabel("Valor da Locação:"));
            centerPanel.add(precoTf);
            centerPanel.add(new JLabel("Disponível"));
            if(media.getStatus().equals("Disponível")){
                disponivelTf.setSelected(true);
            }else{
                disponivelTf.setSelected(false);
            }
            centerPanel.add(disponivelTf);
            FlowLayout bottomFlow = new FlowLayout();
            JPanel bottomLayout = new JPanel(bottomFlow);

            saveButtom = new JButton("Salvar");
            cancelButtom = new JButton("Cancelar");

            bottomLayout.add(saveButtom);
            bottomLayout.add(cancelButtom);
            southPanel.add(new JLabel());
            southPanel.add(bottomLayout);

            if(media != null){
                idTf.setText(media.getId().toString());
                tituloTf.setText(media.getTitulo());
                dataTf.setText(media.getData_lancamento().getDate()+"/"+(media.getData_lancamento().getMonth()+1)+"/"+media.getData_lancamento().getYear());
                generoTf.setText(media.getGenero());
                classificacaoTf.setText(media.getClassificacao().toString());
                precoTf.setText(media.getPreco().toString());
                if(media.getStatus().equals("Locada")) {
                    disponivelTf.setSelected(false);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void setEvents(){
        cancelButtom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(centerPanel, "Deseja realmente cancelar o cadastro de medias?", "Cadastro de Medias - Locafix",2);
                if(result == 0){
                    dispose();
                }
            }
        });

        saveButtom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer id = Integer.parseInt(idTf.getText());
                String titulo = tituloTf.getText();
                String[] date_split = dataTf.getText().split("/");
                Date data = new Date(Integer.parseInt(date_split[2]),(Integer.parseInt(date_split[1])-1),Integer.parseInt(date_split[0]));
                String genero = generoTf.getText();
                Integer classificacao = Integer.parseInt(classificacaoTf.getText());
                Double preco = Double.parseDouble(precoTf.getText());
                Object objDisponivel = disponivelTf.getSelectedObjects();

                Boolean disponivel;
                if(objDisponivel != null){
                    disponivel = true;
                }else{
                    disponivel = false;
                }

                if(media == null){
                    media = new Media(titulo, data, genero, classificacao, preco, disponivel, id);
                }else{
                    media.copy(new Media(titulo, data, genero, 12, preco, disponivel, media.getId()));
                }
                media.save();
                Integer result = JOptionPane.showConfirmDialog(centerPanel,"Media Salva com sucesso!\nDeseja salvar outra media?","Cadastrar Media - Locafix", 0);
                System.out.println(result);
                if(result == 0){
                    idTf.setText("");
                    tituloTf.setText("");
                    dataTf.setText("");
                    generoTf.setText("");
                    classificacaoTf.setText("");
                    precoTf.setText("");
                    disponivelTf.setSelected(false);
                }else {
                    if (result == 1) MediaSaveDialog.this.dispose();
                }
            }
        });

    }

}
