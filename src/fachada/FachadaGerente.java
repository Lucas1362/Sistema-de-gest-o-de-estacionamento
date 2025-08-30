package fachada;

import dados.gerente.IRepositorioGerentes;
import dados.tarifa.IRepositorioTarifa;
import dados.tarifa.RepositorioTarifa;
import dados.ticket.IRepositorioTickets;
import negocio.NegocioCliente;
import negocio.NegocioVeiculo;
import negocio.entidade.Gerente;
import negocio.entidade.Ticket;
import negocio.excecao.usuario.UsuarioNaoExisteException;
import negocio.excecao.veiculo.VeiculoExistenteException;
import negocio.excecao.veiculo.VeiculoNaoExisteException;

import java.util.ArrayList;

public class FachadaGerente {

    private NegocioCliente negocioCliente;
    private NegocioVeiculo negocioVeiculo;
    private IRepositorioGerentes repoGerentes;
    private IRepositorioTickets repoTickets;
    private RepositorioTarifa repoTarifa;

    public FachadaGerente(NegocioCliente negocioCliente, NegocioVeiculo negocioVeiculo, IRepositorioGerentes repoGerentes, IRepositorioTickets repoTickets, RepositorioTarifa repoTarifa) {
        this.negocioCliente = negocioCliente;
        this.negocioVeiculo = negocioVeiculo;
        this.repoGerentes = repoGerentes;
        this.repoTickets = repoTickets;
        this.repoTarifa = repoTarifa;
    }

    // Adicione um gerente inicial se necessário (no Main)
    public void cadastrarGerente(String cpf) {
        if (!repoGerentes.existeCPF(cpf)) {
            repoGerentes.adicionar(new Gerente(cpf));
        }
    }

    public Gerente loginGerente(String cpf) {
        return this.repoGerentes.consultarCPF(cpf);
    }

    public String gerarRelatorioFinanceiro() {
        double faturamentoTotal = 0;
        int ticketsFinalizados = 0;
        ArrayList<Ticket> todosOsTickets = this.repoTickets.listar();

        for (Ticket t : todosOsTickets) {
            if (!t.isAtivo()) {
                faturamentoTotal += t.getValor();
                ticketsFinalizados++;
            }
        }
        return String.format(
                "\n--- RELATÓRIO FINANCEIRO ---\n" +
                        "Total de Tickets Finalizados: %d\n" +
                        "Faturamento Total: R$ %.2f\n" +
                        "----------------------------",
                ticketsFinalizados, faturamentoTotal
        );
    }

    public void alterarStatusPCDCliente(String cpf, boolean novoStatus) throws UsuarioNaoExisteException {
        negocioCliente.alterarStatusPCD(cpf, novoStatus);
    }

    public void alterarPlacaVeiculo(String placaAntiga, String placaNova) throws VeiculoNaoExisteException, VeiculoExistenteException {
        negocioVeiculo.alterarPlaca(placaAntiga, placaNova);
    }

    public void alterarTarifa(double novoValor) {
        this.repoTarifa.setNovoValor(novoValor);
    }
}
