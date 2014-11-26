package models;

import java.util.ArrayList;

/**
 * Created by andre on 22/11/14.
 */
public class Locacao {

    private static LocacaoDatabase db = new LocacaoDatabase();

    private Cliente c;
    private ArrayList<Integer> medias;
    private Double valor;
    private Boolean pago;
    private Boolean locado;
    private String key;

    public Locacao(Cliente c, ArrayList<Integer> medias, Double valor, Boolean pago, Boolean locado, String key){
        this.c = c;
        this.medias = medias;
        this.valor = valor;
        this.pago = pago;
        this.locado = locado;
        this.key = key;
    }

    public void locar(){

    }

    public Cliente getC() {
        return c;
    }

    public void setC(Cliente c) {
        this.c = c;
    }

    public ArrayList<Integer> getMedias() {
        return medias;
    }

    public void setMedias(ArrayList<Integer> medias) {
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


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Locacao copy(Locacao loc){
        setMedias(loc.getMedias());
        setC(loc.getC());
        setLocado(loc.getLocado());
        setValor(loc.getValor());
        setPago(loc.getPago());
        setKey(loc.getKey());
        return this;
    }

    public void save(){
        if(db.findByKey(getKey()) != null){
            db.update(this);
        }else{
            db.insert(this);
        }
    }

    public void delete(){
        db.delete(this);
    }

    public static Locacao findByKey(String key){
        return db.findByKey(key);
    }

    public static ArrayList<Locacao> all(){
        return db.all();
    }
}
