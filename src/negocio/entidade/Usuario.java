package negocio.entidade;

public class Usuario{
    private String cpf;

    public Usuario(String cpf){
        this.cpf = cpf;
    }

    public String getCpf(){
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}