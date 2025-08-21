package negocio;

import dados.ticket.IRepositorioTickets;
import negocio.entidade.Ticket;
import negocio.entidade.Vaga;
import negocio.entidade.Veiculo;
import negocio.excecao.ticket.TicketNaoExisteException;
import java.time.LocalDateTime;

public class NegocioTicket {

    private IRepositorioTickets repositorioTickets;
    private NegocioVaga negocioVaga; // Depende de outras camadas de negócio
    private NegocioVeiculo negocioVeiculo;

    public NegocioTicket(IRepositorioTickets repositorioTickets, NegocioVaga negocioVaga, NegocioVeiculo negocioVeiculo) {
        this.repositorioTickets = repositorioTickets;
        this.negocioVaga = negocioVaga;
        this.negocioVeiculo = negocioVeiculo;
    }

    public void gerarTicketEntrada(String placa, String idVaga) throws Exception {
        // Regra 1: O veículo deve existir no sistema.
        Veiculo veiculo = negocioVeiculo.consultar(placa);
        // Regra 2: A vaga deve existir e estar livre.
        negocioVaga.ocuparVaga(idVaga);
        Vaga vaga = negocioVaga.getRepositorio().consultar(idVaga);
        Ticket novoTicket = new Ticket(veiculo, vaga);
        repositorioTickets.adicionar(novoTicket);
    }

    public void registrarSaida(String placa) throws TicketNaoExisteException {
        // Regra 1: Deve existir um ticket ativo para o veículo.
        Ticket ticket = repositorioTickets.consultar(placa);
        if (ticket == null || !ticket.isAtivo()) {
            throw new TicketNaoExisteException();
        }
        // Regra 2: Calcular o valor a ser pago
        // (Lógica de cálculo de valor vai aqui)
        double valorAPagar = 10.0;
        ticket.registrarSaida(valorAPagar, LocalDateTime.now());
    }
}
