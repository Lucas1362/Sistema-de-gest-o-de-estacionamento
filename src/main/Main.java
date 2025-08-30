import dados.tarifa.RepositorioTarifa;
import fachada.FachadaCliente;
import fachada.FachadaGerente;
import dados.cliente.RepositorioClientes;
import dados.gerente.RepositorioGerentes;
import dados.ticket.RepositorioTickets;
import dados.vaga.RepositorioVagas;
import dados.veiculo.RepositorioVeiculos;
import negocio.*;
import negocio.entidade.Gerente;
import negocio.entidade.Vaga;
import ui.InterfaceConsole;

public class Main {

    public static void main(String[] args) {
        RepositorioClientes repoClientes = new RepositorioClientes();
        RepositorioVeiculos repoVeiculos = new RepositorioVeiculos();
        RepositorioVagas repoVagas = new RepositorioVagas();
        RepositorioTickets repoTickets = new RepositorioTickets();
        RepositorioGerentes repoGerentes = new RepositorioGerentes();
        RepositorioTarifa repoTarifa = new RepositorioTarifa();

        NegocioCliente negocioCliente = new NegocioCliente(repoClientes);
        NegocioVeiculo negocioVeiculo = new NegocioVeiculo(repoVeiculos);
        NegocioVaga negocioVaga = new NegocioVaga(repoVagas);
        NegocioTicket negocioTicket = new NegocioTicket(repoTickets, negocioVaga, negocioVeiculo);

        FachadaCliente fachadaCliente = new FachadaCliente(negocioCliente, negocioVeiculo, negocioVaga, negocioTicket);
        FachadaGerente fachadaGerente = new FachadaGerente(negocioCliente, negocioVeiculo, repoGerentes, repoTickets, repoTarifa);

        inicializarDados(repoVagas, fachadaGerente);

        InterfaceConsole ui = new InterfaceConsole(fachadaCliente, fachadaGerente);
        ui.executar();
    }

    private static void inicializarDados(RepositorioVagas repoVagas, FachadaGerente fachadaGerente) {
        if (repoVagas.listar().isEmpty()) {
            System.out.println("Inicializando vagas pela primeira vez...");
            repoVagas.adicionar(new Vaga("A01"));
            repoVagas.adicionar(new Vaga("A02"));
            Vaga vagaPCD = new Vaga("B01");
            vagaPCD.setPCD(true);
            repoVagas.adicionar(vagaPCD);
        }
        fachadaGerente.cadastrarGerente("admin");
    }
}