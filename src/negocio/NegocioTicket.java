package negocio;

import dados.ticket.IRepositorioTickets;
import negocio.entidade.Cliente;
import negocio.entidade.Ticket;
import negocio.entidade.Vaga;
import negocio.entidade.Veiculo;
import negocio.excecao.ticket.TicketNaoExisteException;

import java.time.Duration;
import java.time.LocalDateTime;

public class NegocioTicket {

    private IRepositorioTickets repositorioTickets;
    private NegocioVaga negocioVaga; // Depende de outras camadas de negócio
    private NegocioVeiculo negocioVeiculo;
    private static final double tarifaPorHora = 5.00;

    public NegocioTicket(IRepositorioTickets repositorioTickets, NegocioVaga negocioVaga, NegocioVeiculo negocioVeiculo) {
        this.repositorioTickets = repositorioTickets;
        this.negocioVaga = negocioVaga;
        this.negocioVeiculo = negocioVeiculo;
    }

    public double registrarSaida(Ticket ticket) throws TicketNaoExisteException {
        if (ticket == null) {
            throw new TicketNaoExisteException();
        }

        //Cálculo de valor
        LocalDateTime entrada = ticket.getHorarioEntrada();
        LocalDateTime saida = LocalDateTime.now();

        // Calcula a duração em minutos
        long minutos = Duration.between(entrada, saida).toMinutes();

        double valorAPagar;

        // REGRA 1: Se ficou 60 minutos ou menos, cobra o valor de 1 hora cheia.
        if (minutos <= 60) {
            valorAPagar = tarifaPorHora;
        } else {
            valorAPagar = (minutos / 60.0) * tarifaPorHora;
        }

        // Atualiza o ticket com os dados da saída
        ticket.registrarSaida(valorAPagar, saida);

        return valorAPagar;
    }

    public void gerarTicketEntrada(String placa, String idVaga) throws Exception {
        Veiculo veiculo = negocioVeiculo.consultar(placa);
        Cliente cliente = veiculo.getDono();
        Vaga vaga = negocioVaga.getRepositorio().consultar(idVaga);

        if (vaga.isPCD() && !cliente.isPreferencial()) {
            throw new Exception("Vaga exclusiva para clientes PCD.");
        }

        negocioVaga.ocuparVaga(idVaga);
        Ticket novoTicket = new Ticket(veiculo, vaga);
        repositorioTickets.adicionar(novoTicket);
    }

    public IRepositorioTickets getRepositorioTickets() {
        return this.repositorioTickets;
    }
}
