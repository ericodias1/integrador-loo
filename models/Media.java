package models;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by erico on 09/11/14.
 */
public class Media {
    private static MediaDatabase db = new MediaDatabase();
    private String titulo;
    private Date data_lancamento;
    private String genero;
    private Integer classificacao;
    private Integer id;
    private Double preco;
    private Boolean disponivel;

    public Media(String titulo, Date data_lancamento, String genero, Integer classificacao, Double preco, Boolean disponivel, Integer id) {
        this.titulo = titulo;
        this.data_lancamento = data_lancamento;
        this.genero = genero;
        this.classificacao = classificacao;
        this.preco = preco;
        this.disponivel = disponivel;
        this.id = id;
    }

    public Media copy(Media m){
        setClassificacao(m.getClassificacao());
        setData_lancamento(m.getData_lancamento());
        setDisponivel(m.isDisponivel());
        setGenero(m.getGenero());
        setPreco(m.getPreco());
        setTitulo(m.getTitulo());
        return this;
    }

    public void setId(Integer id){ this.id = id; }

    public Integer getId(){ return id; }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getData_lancamento() {
        return data_lancamento;
    }

    public void setData_lancamento(Date data_lancamento) {
        this.data_lancamento = data_lancamento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Integer getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(Integer classificacao) {
        this.classificacao = classificacao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(Boolean disponivel) {
        this.disponivel = disponivel;
    }

    public String getStatus(){
        if(this.disponivel){
            return "Disponível";
        }
        return "Locada";
    }

    public void save(){
        if(getId() == null){
            db.insert(this);
        }else{
            if(db.findById(getId()) != null){
                db.update(this);
            }else{
                db.insert(this);
            }
        }
    }

    public void delete(){
        db.delete(this);
    }

    public static Media findById(Integer id){
        return db.findById(id);
    }

    public static ArrayList<Media> all(){
        return db.all();
    }

    public String toString(){
        return "ID: "+getId()+"\nTítulo: "+getTitulo()+"\nPreço: "+getPreco()+"\nGenero: "+getGenero()+"\n Status: "+getStatus()+"\nClassificação: "+getClassificacao()+"\nData lançamento: "+getData_lancamento();
    }

    public static ArrayList<Media> find(Media m) {
        ArrayList<Media> medias = db.find(m);
        return medias;
    }
}
