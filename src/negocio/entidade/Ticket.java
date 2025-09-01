package negocio.entidade;
import java.time.LocalDateTime;

public class Ticket {
    private LocalDateTime horarioEntrada;
    private LocalDateTime horarioSaida;
    private boolean ativo;
    private double valor;
    private Vaga vaga;
    private Veiculo veiculo;

    public Ticket(Veiculo veiculo, Vaga vaga) {
        this.veiculo = veiculo;
        this.vaga = vaga;
        this.horarioEntrada = LocalDateTime.now();
        this.ativo = true;

    }

    // --- Getters ---

    public Vaga getVaga() {
        return vaga;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public boolean isAtivo() {
        return ativo;
    }

    // GETTERS ADICIONADOS CONFORME SUA SUGESTÃO
    public LocalDateTime getHorarioEntrada() {
        return horarioEntrada;
    }

    public LocalDateTime getHorarioSaida() {
        return horarioSaida;
    }

    public double getValor() {
        return valor;
    }


    /**
     * Finaliza o ticket, registra o valor, o horário de saída
     * e libera a vaga associada.
     * @param valor O valor final a ser pago.
     * @param horarioSaida O momento exato da saída.
     */
    public void registrarSaida(double valor, LocalDateTime horarioSaida) {
        this.valor = valor;
        this.horarioSaida = horarioSaida;
        this.ativo = false;
        // CORREÇÃO APLICADA CONFORME SUA SUGESTÃO
        this.vaga.liberar();
    }
}