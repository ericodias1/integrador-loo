package models;

import java.util.Date;

/**
 * Created by erico on 09/11/14.
 */
public class Usuario extends Pessoa{
    private String carteira_trabalho;
    private String login;
    private String senha;

    public Usuario(String nome, String cpf, String telefone, String rg, Date data_nascimento, Character sexo, String email, String carteira_trabalho, String login, String senha) {
        super(nome, cpf, telefone, rg, data_nascimento, sexo, email);
        this.carteira_trabalho = carteira_trabalho;
        this.login = login;
        this.senha = senha;
    }

    public String getCarteira_trabalho() {
        return carteira_trabalho;
    }

    public void setCarteira_trabalho(String carteira_trabalho) {
        this.carteira_trabalho = carteira_trabalho;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
