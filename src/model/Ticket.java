package model;
import java.time.LocalDateTime;

public class Ticket{
    private LocalDateTime horarioEntrada; //Horário de criação do ticket
    private LocalDateTime horarioSaida; //Horário em que o cliente começa o processo de saída
    private LocalDateTime tempoContratado; //Tempo que o cliente solicitou para permanencia
    private boolean ativo;
    private double valor;
    private Vaga vaga;
    private Cliente cliente;

    //Construtor de Ticket
    public Ticket(Cliente cliente, Vaga vaga, LocalDateTime tempoContratado){
        this.cliente = cliente;
        this.vaga = vaga;
        this.horarioEntrada = LocalDateTime.now();
        this.tempoContratado = tempoContratado
        this.ativo = true;
    }

    //getters
    public Vaga getVaga(){
        return vaga;
    }

    public Cliente getCliente(){
        return cliente;
    }

    public boolean isAtivo() {
        return ativo;
    }

    //Registra a saída, recebe o valor final da permanencia e muda os valores
    public void registrarSaida(double valor, LocalDateTime horarioSaida){
        this.valor = valor;
        this.horarioSaida = horarioSaida;
        this.ativo = false;
        this.vaga.setOcupada(false);
    }
}