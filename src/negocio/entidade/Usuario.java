package negocio.entidade;
import java.io.Serializable;

public class Usuario implements Serializable{
    private static final long serialVersionUID = 1L;
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