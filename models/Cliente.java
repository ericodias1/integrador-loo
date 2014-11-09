package models;

import com.db4o.ObjectSet;

import java.util.Date;

/**
 * Created by erico on 09/11/14.
 */
public class Cliente extends Pessoa{
    private ClienteDB db = new ClienteDB();
    private String profissao;
    private Double score;

    public Cliente(String nome, String cpf, String telefone, String rg, Date data_nascimento, Character sexo, String email, String profissao) {
        super(nome, cpf, telefone, rg, data_nascimento, sexo, email);
        this.profissao = profissao;
        this.score = 0.0;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Cliente copy(Cliente c){
        setNome(c.getNome());
        setCpf(c.getCpf());
        setTelefone(c.getTelefone());
        setRg(c.getRg());
        setData_nascimento(c.getData_nascimento());
        setSexo(c.getSexo());
        setEmail(c.getEmail());
        setProfissao(c.getProfissao());
        setScore(c.getScore());
        return this;
    }

    public void save(){

    }
}
