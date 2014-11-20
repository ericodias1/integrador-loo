package views;

import controllers.MediaController;
import models.Media;

import javax.swing.*;
import javax.swing.text.html.parser.Parser;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * Created by andre on 19/11/14.
 */
public class MediaWindow extends JDialog {
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
    private JTextField dataTf;
    private JTextField generoTf;
    private JTextField classificacaoTf;
    private JTextField precoTf;
    /*Checkbox*/
    private JCheckBox disponivelTf;


    public MediaWindow(MediaController controller, Media m){
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

        this.idTf = new JTextField();
        this.tituloTf =  new JTextField();
        this.dataTf = new JTextField();
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
        centerPanel.add(disponivelTf);
        FlowLayout bottomFlow = new FlowLayout();
        JPanel bottomLayout = new JPanel(bottomFlow);

        saveButtom = new JButton("Salvar");
        cancelButtom = new JButton("Cancelar");

        bottomLayout.add(saveButtom);
        bottomLayout.add(cancelButtom);
        southPanel.add(new JLabel());
        southPanel.add(bottomLayout);
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
                Date data = new Date(2014,11,20);
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
                    media.copy(new Media(titulo, data, genero, 12, 2.50, disponivel, 12));
                }
                media.save();
                Integer result = JOptionPane.showConfirmDialog(centerPanel,"Media Salva com sucesso!\nDeseja salvar outra media?","Cadastrar Media - Locafix", 0);
                System.out.print(result);
            }
        });

    }

}
