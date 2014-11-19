package models;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by erico on 09/11/14.
 */
public class Cliente extends Pessoa{
    private static ClienteDatabase db = new ClienteDatabase();
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
        if(db.findByCpf(getCpf()) != null){
            db.update(this);
        }else{
            db.insert(this);
        }
    }

    public void delete(){
        db.delete(this);
    }

    public static Cliente findByCpf(String cpf){
        return db.findByCpf(cpf);
    }

    public static ArrayList<Cliente> all(){
        return db.all();
    }
}
