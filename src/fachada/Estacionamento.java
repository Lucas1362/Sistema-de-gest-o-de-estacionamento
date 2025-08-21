package negocio.entidade;

import src.model.Vaga;
import src.model.Veiculo;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.list;


public class Estacionamento {
    private List<Vaga> vagas;
    private List<Veiculo> veiculos;
    private List<Ticket> ticketsAtivos;
    private List<Ticket> ticketsEncerrados;
    private double valorhora = 5.0;


//construtores

    public Estacionamento(int totalVagas){
        this.vagas = new ArrayList<>();
        this.veiculos = new ArrayList<>();
        this.ticketsAtivos = new ArrayList<>();
        this.ticketsEncerrados = new ArrayList<>();

        // quantidade definida de vagas
        for (int i = 1; i <= 10; i++){
            vagas.add(new Vaga(i));
        }
    }

    // Registro de entrada
    public Ticket registrarEntrada(String placa) throws  EstacionamentoException {
        Vaga vagaDisponivel = vagas.stream()
                .filter(v -> v.estaLivre())
                .findFirst()
                .orElseThrow(() -> new EstacionamentoException("Desculpe, não há vagas disponiveis"))

        Veiculo veiculo = new Veiculo(placa);
        vagaDisponivel.ocupar(veiculo);

        ticket novoTicket = new Ticket(veiculo, vagaDisponivel, LocalDateTime.now());
        ticketsAtivos.add(novoTicket);

        return novoTicket;

            }
        }
    public Ticket RegistrarSaida(String placa) throws EstacionamentoException {
        Ticket ticket = ticketsAtivos.stream()
            .filter(t -> t.getVeiculo().getPlaca().equals(placa))
            .findFirst()
            .orElseThrow(() -> new EstacionamentoException ("Este veiculo não foi encontrado"))

        ticket.finalizar(LocalDateTime.now());
        ticket.getVaga().liberar();
        ticketsAtivos.remove(ticket);
        ticketsEncerrados.add(ticket);

        calcularValor(ticket)
        return ticket;


    }

    //Calculo de valor
    private void calcularValor (Ticket ticket){
    Duration duracao = duration.betwenn(ticket.getEntrada(), ticket.getSaida());
    long horas = duracao.toHours();
    long dias = duracao.toDays();

    //mecanica da cobrança
        if (dias > 0){
            ticket.setValor(dias* valorDiaria + Math.min(horas % 24 * valorHora, valorDiaria));
        }else {
            ticket.setValor(Math.max(horas, 1) * valorHora); // minimo de 1 hora de estacionamento
        }
    }

}

public List<Vaga> getVagasLivres(){
    return vagas.stream().filter(Vaga::estarLivre()).toList();
}
public double getFaturamentoDiario() {
    returnticketsEncerrados.stream()
            .filter(t -> t.getSaida().toLocalDate().equals(LocalDateTime.now().toLocalDate()))
            .mapToDouble(Ticket::getValor)
            .sum();
    }
}

// classe ticket(auxiliar)

class Ticket {
    private Veiculo veiculo;
    private Vaga vaga;
    private LocalDateTime entrada;
    private LocalDateTime saida;
    private double valor;



}

class EstacionamentoException extends Exception{
    public EstacionamentoException(String message){
        super(message);
    }
}