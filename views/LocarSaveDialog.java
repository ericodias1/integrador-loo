package views;

import controllers.LocarController;
import models.Cliente;
import models.Media;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by andre on 21/11/14.
 */
public class LocarSaveDialog extends JDialog {
    private ArrayList<Media> medias = new ArrayList<Media>();
    private Cliente c;
    private LocarController controller;

    public LocarSaveDialog(JFrame parent, ArrayList<Media> medias, Cliente c, LocarController controller){

        super(parent, "Locar media - LocaFix", true);
        this.c = c;
        this.medias = medias;
        this.controller = controller;

        setLayout();
        setComponents();
        setEvents();

        setMinimumSize(new Dimension(500,400));
        setVisible(true);

    }

    private void setLayout(){

    }

    private void setComponents(){

    }

    private void setEvents(){

    }
}
