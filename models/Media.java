package models;

import java.util.Date;

/**
 * Created by erico on 09/11/14.
 */
public class Media {
    private String titulo;
    private Date data_lancamento;
    private String genero;
    private int classificacao;
    private Double preco;
    private boolean disponivel;

    public Media(String titulo, Date data_lancamento, String genero, int classificacao, Double preco, boolean disponivel) {
        this.titulo = titulo;
        this.data_lancamento = data_lancamento;
        this.genero = genero;
        this.classificacao = classificacao;
        this.preco = preco;
        this.disponivel = disponivel;
    }

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

    public int getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(int classificacao) {
        this.classificacao = classificacao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
}
