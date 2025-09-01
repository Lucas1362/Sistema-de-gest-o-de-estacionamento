package fachada;

import negocio.*;
import negocio.entidade.*;
import negocio.excecao.usuario.UsuarioNaoExisteException;
import negocio.excecao.usuario.UsuarioNaoExisteException;
import negocio.excecao.veiculo.VeiculoNaoExisteException;
import negocio.excecao.veiculo.VeiculoNaoPertenceAoClienteException;
import java.util.ArrayList;

public class FachadaCliente {

    private NegocioCliente negocioCliente;
    private NegocioVeiculo negocioVeiculo;
    private NegocioVaga negocioVaga;
    private NegocioTicket negocioTicket;

    // Construtor que recebe as dependências já criadas
    public FachadaCliente(NegocioCliente negocioCliente, NegocioVeiculo negocioVeiculo, NegocioVaga negocioVaga, NegocioTicket negocioTicket) {
        this.negocioCliente = negocioCliente;
        this.negocioVeiculo = negocioVeiculo;
        this.negocioVaga = negocioVaga;
        this.negocioTicket = negocioTicket;
    }

    public void cadastrarClienteEVeiculo(String cpf, String placa, boolean pcd) throws Exception {
        Cliente novoCliente = new Cliente(cpf, pcd);
        negocioCliente.adicionar(novoCliente);
        Veiculo novoVeiculo = new Veiculo(placa, novoCliente);
        negocioVeiculo.adicionar(novoVeiculo);
        novoCliente.setVeiculo(novoVeiculo);
    }

    public Ticket realizarLogin(String cpf, String placa) throws UsuarioNaoExisteException, VeiculoNaoExisteException, VeiculoNaoPertenceAoClienteException {
        Cliente cliente = negocioCliente.consultarCPF(cpf);
        Veiculo veiculo = negocioVeiculo.consultar(placa);

        if (!veiculo.getDono().getCpf().equals(cliente.getCpf())) {
            throw new VeiculoNaoPertenceAoClienteException(placa, cpf);
        }
        return negocioTicket.getRepositorioTickets().consultar(placa);
    }

    public ArrayList<Vaga> listarVagasLivres() {
        return negocioVaga.consultarVagasLivres();
    }

    public void registrarEntrada(String placa, String idVaga) throws Exception {
        negocioTicket.gerarTicketEntrada(placa, idVaga);
    }

    public double registrarSaida(Ticket ticket) throws Exception {
        double valorPago = negocioTicket.registrarSaida(ticket);
        ticket.getVaga().setOcupada(false);
        return valorPago;
    }
}
