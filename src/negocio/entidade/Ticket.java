package negocio.entidade;
import java.time.LocalDateTime;
import java.io.Serializable;

public class Ticket implements Serializable{
    private static final long serialVersionUID = 1L;
    private LocalDateTime horarioEntrada; //Horário de criação do ticket
    private LocalDateTime horarioSaida; //Horário em que o cliente começa o processo de saída
    private boolean ativo;
    private double valor;
    private Vaga vaga;
    private Veiculo veiculo;

    //Construtor de Ticket
    public Ticket(Veiculo veiculo, Vaga vaga){
        this.veiculo = veiculo;
        this.vaga = vaga;
        this.horarioEntrada = LocalDateTime.now();
        this.ativo = true;
    }

    //getters
    public Vaga getVaga(){
        return vaga;
    }

    public double getValor() {
        return this.valor;
    }

    public Veiculo getVeiculo(){
        return veiculo;
    }

    public LocalDateTime getHorarioEntrada() {
        return horarioEntrada;
    }

    public boolean isAtivo() {
        return ativo;
    }

    //Registra a saída, recebe o valor final da permanencia e muda os valores
    public void registrarSaida(double valor, LocalDateTime horarioSaida){
        this.valor = valor;
        this.horarioSaida = horarioSaida;
        this.ativo = false;
    }
}