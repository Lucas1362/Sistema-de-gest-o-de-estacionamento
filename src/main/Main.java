import dados.cliente.RepositorioClientes;
import dados.ticket.RepositorioTickets;
import dados.vaga.RepositorioVagas;
import dados.veiculo.RepositorioVeiculos;
import negocio.*;
import negocio.entidade.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    // --- INICIALIZAÇÃO DAS CAMADAS ---
    // Repositórios
    private static RepositorioClientes repoClientes = new RepositorioClientes();
    private static RepositorioVeiculos repoVeiculos = new RepositorioVeiculos();
    private static RepositorioVagas repoVagas = new RepositorioVagas();
    private static RepositorioTickets repoTickets = new RepositorioTickets();

    // Camada de Negócio
    private static NegocioCliente negocioCliente = new NegocioCliente(repoClientes);
    private static NegocioVeiculo negocioVeiculo = new NegocioVeiculo(repoVeiculos);
    private static NegocioVaga negocioVaga = new NegocioVaga(repoVagas);
    private static NegocioTicket negocioTicket = new NegocioTicket(repoTickets, negocioVaga, negocioVeiculo);

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        inicializarVagas();
        System.out.println("Bem-vindo ao Sistema de Gestão de Estacionamento!");

        while (true) {
            System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("1. Acessar como Cliente");
            System.out.println("2. Acessar como Gerente");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");

            int escolha = lerInteiro();

            switch (escolha) {
                case 1:
                    menuCliente();
                    break;
                case 2:
                    menuGerente();
                    break;
                case 3:
                    System.out.println("Obrigado por usar o sistema. Até logo!");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void menuCliente() {
        System.out.println("\n--- ÁREA DO CLIENTE ---");
        System.out.println("1. Login (Entrada/Saída)");
        System.out.println("2. Cadastro");
        System.out.println("3. Voltar");
        System.out.print("Escolha uma opção: ");

        int escolha = lerInteiro();

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

    private static void fluxoCadastro() {
        try {
            System.out.println("\n--- CADASTRO DE CLIENTE E VEÍCULO ---");
            System.out.print("Digite seu CPF (apenas números): ");
            String cpf = scanner.nextLine();
            System.out.print("Digite a placa do seu veículo (ex: ABC-1234): ");
            String placa = scanner.nextLine();
            System.out.print("Você é PCD? (s/n): ");
            boolean pcd = scanner.nextLine().equalsIgnoreCase("s");

            // Interação direta com as camadas de negócio
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

                // Libera a vaga manualmente (orquestração que a Fachada faria)
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

    private static void menuGerente() {
        System.out.println("\n--- ÁREA DO GERENTE ---");
        System.out.println("1. Gerar Relatório Financeiro");
        System.out.println("2. Voltar");
        System.out.print("Escolha uma opção: ");

        int escolha = lerInteiro();

        if (escolha == 1) {
            // Lógica do Relatório (interação direta com repositório)
            double faturamentoTotal = 0;
            int ticketsFinalizados = 0;
            ArrayList<Ticket> todosOsTickets = repoTickets.listar();

            for (Ticket t : todosOsTickets) {
                if (!t.isAtivo()) {
                    faturamentoTotal += t.getValor(); // Adicione getter getValor() ao Ticket
                    ticketsFinalizados++;
                }
            }

            System.out.printf("\n--- RELATÓRIO FINANCEIRO ---\n");
            System.out.printf("Total de Tickets Finalizados: %d\n", ticketsFinalizados);
            System.out.printf("Faturamento Total: R$ %.2f\n", faturamentoTotal);
            System.out.printf("----------------------------\n");
        }
    }

    private static int lerInteiro() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void inicializarVagas() {
        if (repoVagas.listar().isEmpty()) {
            System.out.println("Inicializando vagas pela primeira vez...");
            repoVagas.adicionar(new Vaga("A01"));
            repoVagas.adicionar(new Vaga("A02"));
            Vaga vagaPCD = new Vaga("B01");
            vagaPCD.setPCD(true);
            repoVagas.adicionar(vagaPCD);
            System.out.println("3 vagas criadas.");
        }
    }
}