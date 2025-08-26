package negocio.entidade;
import java.io.Serializable;

public class Gerente extends Usuario implements Serializable{
    private static final long serialVersionUID = 1L;
    //Construtor
    public Gerente(String cpf){
        super(cpf);
    }

    public void gerarRelatorio(int mes){

    }

    public void modificarCliente(Cliente cliente){

    }

    public void modificarVaga(Vaga vaga){

    }

}