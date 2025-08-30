package negocio.entidade;
import java.io.Serializable;


public class Veiculo implements Serializable {

    private static final long serialVersionUID = 1L;
    private String placa;
    private ClienteUsuario dono; // Alterado de Cliente para ClienteUsuario

    /**
     * Construtor para a classe Veiculo.
     * @param placa A placa do veículo.
     * @param dono  O ClienteUsuario que é o dono do veículo.
     */
    public Veiculo(String placa, ClienteUsuario dono) { // Alterado o tipo do parâmetro
        this.placa = placa;
        this.dono = dono;
    }

    //Getters

    public String getPlaca() {
        return placa;
    }

    public ClienteUsuario getDono() { // Alterado o tipo de retorno
        return dono;
    }

    //Setters

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public void setDono(ClienteUsuario dono) { // Alterado o tipo do parâmetro
        this.dono = dono;
    }
}