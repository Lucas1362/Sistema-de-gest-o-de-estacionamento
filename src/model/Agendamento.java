package model
import java.time.LocalDateTime;


public class Agendamento{
    private Veiculo veiculo;
    private Vaga vaga;
    private localDateTime horario;

    public Agendamento(Veiculo veiculo, Vaga vaga, LocalDateTime horario){
        this.veiculo = veiculo;
        this.vaga = vaga;
        this.horario = horario;

    }

    //getters


    public Veiculo getVeiculo() {
        return veiculo;
    }

    public Vaga getVaga() {
        return vaga;
    }

    public localDateTime getHorario() {
        return horario;
    }

}
