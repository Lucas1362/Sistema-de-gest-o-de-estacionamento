package negocio.entidade;

public abstract class Usuario{
    private String nome;
    private String cpf;

    public Usuario(String nome, String cpf){ //  CORRIGIDO
        this.nome = nome;
        this.cpf = cpf;
    }

    public String getNome(){
        return nome;
    }
    public String getCpf(){
        return cpf;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}