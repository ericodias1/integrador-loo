package models;

import java.util.ArrayList;

/**
 * Created by andre on 22/11/14.
 */
public class Locacao {

    private Cliente c;
    private ArrayList<Media> medias;
    private Double valor;
    private Boolean pago;
    private Boolean locado;

    public Locacao(Cliente c, ArrayList<Media> medias){
        this.c = c;
        this.medias = medias;
    }

    public void locar(){

    }

    public Cliente getC() {
        return c;
    }

    public void setC(Cliente c) {
        this.c = c;
    }

    public ArrayList<Media> getMedias() {
        return medias;
    }

    public void setMedias(ArrayList<Media> medias) {
        this.medias = medias;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Boolean getPago() {
        return pago;
    }

    public void setPago(Boolean pago) {
        this.pago = pago;
    }

    public Boolean getLocado() {
        return locado;
    }

    public void setLocado(Boolean locado) {
        this.locado = locado;
    }


}
