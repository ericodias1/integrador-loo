package models;

import java.util.Date;

/**
 * Created by erico on 09/11/14.
 */
public class Pessoa {
    private String nome;
    private String cpf;
    private String telefone;
    private String rg;
    private Date data_nascimento;
    private Character sexo;
    private String email;

    public Pessoa(String nome, String cpf, String telefone, String rg, Date data_nascimento, Character sexo, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.rg = rg;
        this.data_nascimento = data_nascimento;
        this.sexo = sexo;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public Date getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(Date data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public Character getSexo() {
        return sexo;
    }

    public void setSexo(Character sexo) {
        this.sexo = sexo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
