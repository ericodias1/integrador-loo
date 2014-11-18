package models;

import java.util.Date;

/**
 * Created by erico on 09/11/14.
 */
public class Usuario extends Pessoa{
    private static UsuarioDatabase db = new UsuarioDatabase();
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

    public static boolean login(String login, String password){
        return db.login(login, password);
    }

    public Usuario copy(Usuario u){
        setNome(u.getNome());
        setCpf(u.getCpf());
        setTelefone(u.getTelefone());
        setRg(u.getRg());
        setData_nascimento(u.getData_nascimento());
        setSexo(u.getSexo());
        setEmail(u.getEmail());
        return this;
    }
    public void save(){
        if(db.findByCpf(getCpf()) != null){
            db.update(this);
        }else{
            db.insert(this);
        }
    }

}
