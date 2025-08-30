package fachada;

import dados.cliente.RepositorioClientes;
import dados.ticket.RepositorioTickets;
import dados.vaga.RepositorioVagas;
import dados.veiculo.RepositorioVeiculos;
import negocio.NegocioCliente;
import negocio.NegocioTicket;
import negocio.NegocioVaga;
import negocio.NegocioVeiculo;
import negocio.entidade.Cliente;
import negocio.entidade.Ticket;
import negocio.entidade.Vaga;
import negocio.entidade.Veiculo;

import java.util.ArrayList;
import java.util.Scanner;

public class FachadaCliente {
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

    public FachadaCliente(){}

    //menu
    private static void menuCliente() {
        System.out.println("\n--- ÁREA DO CLIENTE ---");
        System.out.println("1. Login (Entrada/Saída)");
        System.out.println("2. Cadastro");
        System.out.println("3. Voltar");
        System.out.print("Escolha uma opção: ");

        int escolha = scanner.nextInt();

        switch (escolha) {
            case 1:
                fluxoLogin();
                break;
            case 2:
                fluxoCadastro();
                break;
            case 3:
                return;
            default:
                System.out.println("Opção inválida.");
        }
    }

    //Cadastro
    private static void fluxoCadastro() {
        try {
            System.out.println("\n--- CADASTRO DE CLIENTE E VEÍCULO ---");
            System.out.print("Digite seu CPF (apenas números): ");
            String cpf = scanner.nextLine();

            System.out.print("Digite a placa do seu veículo (ex: ABC-1234): ");
            String placa = scanner.nextLine();

            System.out.print("Você é PCD? (s/n): ");
            boolean pcd = scanner.nextLine().equalsIgnoreCase("s");

            Cliente novoCliente = new Cliente(cpf, pcd);
            negocioCliente.adicionar(novoCliente);

            Veiculo novoVeiculo = new Veiculo(placa, novoCliente);
            negocioVeiculo.adicionar(novoVeiculo);

            novoCliente.setVeiculo(novoVeiculo); // Associa o veículo ao cliente

            System.out.println("\n>>> Cadastro realizado com sucesso! Faça o login para continuar.");

        } catch (Exception e) {
            System.err.println("\nERRO AO CADASTRAR: " + e.getMessage());
        }
    }

    //Login, já com a lógica de indentificar entrada ou saída
    private static void fluxoLogin() {
        try {
            System.out.println("\n--- LOGIN ---");
            System.out.print("Digite seu CPF: ");
            String cpf = scanner.nextLine();
            System.out.print("Digite a placa do veículo: ");
            String placa = scanner.nextLine();

            // Lógica do Login Inteligente
            Cliente cliente = negocioCliente.consultarCPF(cpf);
            Veiculo veiculo = negocioVeiculo.consultar(placa);
            if (!veiculo.getDono().getCpf().equals(cliente.getCpf())) {
                throw new Exception("Esta placa não pertence ao CPF informado.");
            }

            Ticket ticketAtivo = repoTickets.consultar(placa);

            if (ticketAtivo == null) {
                // FLUXO DE ENTRADA
                System.out.println("\n>>> Login bem-sucedido. Nenhum ticket ativo encontrado. Iniciando processo de ENTRADA.");
                ArrayList<Vaga> vagasLivres = negocioVaga.consultarVagasLivres();

                if (vagasLivres.isEmpty()) {
                    System.out.println("\nDESCULPE, NÃO HÁ VAGAS DISPONÍVEIS NO MOMENTO.");
                    return;
                }

                System.out.println("\nVagas disponíveis:");
                for (Vaga vaga : vagasLivres) {
                    System.out.println("- Vaga " + vaga.getNumeroID() + (vaga.isPCD() ? " (PCD)" : ""));
                }

                System.out.print("\nEscolha o ID da vaga: ");
                String idVaga = scanner.nextLine();

                negocioTicket.gerarTicketEntrada(placa, idVaga);
                System.out.println("\n>>> ENTRADA REGISTRADA COM SUCESSO! Bem-vindo!");

            } else {
                // FLUXO DE SAÍDA
                System.out.println("\n>>> Login bem-sucedido. Ticket ativo encontrado. Iniciando processo de SAÍDA.");
                double valor = negocioTicket.registrarSaida(ticketAtivo);

                ticketAtivo.getVaga().setOcupada(false);

                System.out.printf("\n--- RECIBO DE PAGAMENTO ---\n");
                System.out.printf("Valor a pagar: R$ %.2f\n", valor);
                System.out.print("Digite 'pagar' para confirmar o pagamento: ");

                String confirmacao = scanner.nextLine();
                if(confirmacao.equalsIgnoreCase("pagar")) {
                    System.out.println("\n>>> PAGAMENTO CONFIRMADO. SAÍDA LIBERADA! Volte sempre!");
                } else {
                    System.out.println("\nPagamento não confirmado. A saída não foi liberada.");
                }
            }

        } catch (Exception e) {
            System.err.println("\nERRO NO LOGIN: " + e.getMessage());
        }
    }
}
