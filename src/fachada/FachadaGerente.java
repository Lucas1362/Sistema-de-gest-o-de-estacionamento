package fachada;

import dados.cliente.RepositorioClientes;
import dados.ticket.RepositorioTickets;
import dados.vaga.RepositorioVagas;
import dados.veiculo.RepositorioVeiculos;
import negocio.NegocioCliente;
import negocio.NegocioTicket;
import negocio.NegocioVaga;
import negocio.NegocioVeiculo;
import negocio.entidade.Ticket;

import java.util.ArrayList;
import java.util.Scanner;

public class FachadaGerente {
    //repositórios
    private static RepositorioClientes repoClientes = new RepositorioClientes();
    private static RepositorioVeiculos repoVeiculos = new RepositorioVeiculos();
    private static RepositorioVagas repoVagas = new RepositorioVagas();
    private static RepositorioTickets repoTickets = new RepositorioTickets();
    //regras de negócio
    private static NegocioCliente negocioCliente = new NegocioCliente(repoClientes);
    private static NegocioVeiculo negocioVeiculo = new NegocioVeiculo(repoVeiculos);
    private static NegocioVaga negocioVaga = new NegocioVaga(repoVagas);
    private static NegocioTicket negocioTicket = new NegocioTicket(repoTickets, negocioVaga, negocioVeiculo);
    //scanner
    private static Scanner scanner = new Scanner(System.in);

    public FachadaGerente(){}

    private static void menuGerente() {
        System.out.println("\n--- ÁREA DO GERENTE ---");
        System.out.println("1. Gerar Relatório Financeiro");
        System.out.println("2. Gerenciar Clientes");
        System.out.println("3. Gerenciar Vagas");
        System.out.println("4. Gerenciar Tarifa");
        System.out.println("5. Voltar");
        System.out.print("Escolha uma opção: ");

        int escolha = scanner.nextInt();

        switch (escolha) {
            case 1:
                gerarRelatório();
                break;
            case 2:
                modificarCliente();
                break;
            case 3:
                modificarVaga();
                break;
            case 4:
                modificarTarifa();
                break;
            case 5:
                return;
            default:
                System.out.println("Opção inválida.");
        }
    }


}
