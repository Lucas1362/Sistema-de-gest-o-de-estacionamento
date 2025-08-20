package negocio.entidade;

public class Usuario{
    private String nome;
    private String cpf;

    public Cliente(String nome, String cpf){
        this.cpf = cpf;
    }

    public String getCpf(){
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}