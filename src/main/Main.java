import fachada.FachadaCliente;
import fachada.FachadaGerente;
import dados.cliente.RepositorioClientes;
import dados.gerente.RepositorioGerentes;
import dados.ticket.RepositorioTickets;
import dados.vaga.RepositorioVagas;
import dados.veiculo.RepositorioVeiculos;
import negocio.*;
import negocio.entidade.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static FachadaCliente fachadaCliente;
    private static FachadaGerente fachadaGerente;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // 1. Criar todas as instâncias de repositórios UMA VEZ
        RepositorioClientes repoClientes = new RepositorioClientes();
        RepositorioVeiculos repoVeiculos = new RepositorioVeiculos();
        RepositorioVagas repoVagas = new RepositorioVagas();
        RepositorioTickets repoTickets = new RepositorioTickets();
        RepositorioGerentes repoGerentes = new RepositorioGerentes();

        // 2. Criar todas as instâncias de negócio UMA VEZ
        NegocioCliente negocioCliente = new NegocioCliente(repoClientes);
        NegocioVeiculo negocioVeiculo = new NegocioVeiculo(repoVeiculos);
        NegocioVaga negocioVaga = new NegocioVaga(repoVagas);
        NegocioTicket negocioTicket = new NegocioTicket(repoTickets, negocioVaga, negocioVeiculo);

        // 3. Injetar as dependências nas fachadas
        fachadaCliente = new FachadaCliente(negocioCliente, negocioVeiculo, negocioVaga, negocioTicket);
        fachadaGerente = new FachadaGerente(negocioCliente, negocioVeiculo, repoGerentes, repoTickets);

        // --- INICIALIZAÇÃO DE DADOS INICIAIS ---
        inicializarVagas(repoVagas);
        fachadaGerente.cadastrarGerente("admin"); // Cria um gerente padrão

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
        //... (O código do menuCliente, fluxoLogin e fluxoCadastro permanece EXATAMENTE O MESMO da sua versão anterior)
        // A única diferença é que as chamadas internas agora usam 'fachadaCliente'.
        // Ex: fachadaCliente.cadastrarClienteEVeiculo(cpf, placa, pcd);
        // E: Ticket ticketAtivo = fachadaCliente.realizarLogin(cpf, placa);
    }

    private static void menuGerente() {
        System.out.println("\n--- ÁREA DO GERENTE ---");
        System.out.print("Digite o CPF do gerente para login: ");
        String cpf = scanner.nextLine();

        if (fachadaGerente.loginGerente(cpf) == null) {
            System.out.println("Gerente não encontrado.");
            return;
        }

        while (true) {
            System.out.println("\n--- MENU GERENCIAL ---");
            System.out.println("1. Gerar Relatório Financeiro");
            System.out.println("2. Alterar Status PCD de Cliente");
            System.out.println("3. Alterar Placa de Veículo");
            System.out.println("4. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            int escolha = lerInteiro();

            switch (escolha) {
                case 1:
                    System.out.println(fachadaGerente.gerarRelatorioFinanceiro());
                    break;
                case 2:
                    alterarPCD();
                    break;
                case 3:
                    alterarPlaca();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private static void alterarPCD() {
        try {
            System.out.print("Digite o CPF do cliente a ser alterado: ");
            String cpf = scanner.nextLine();
            System.out.print("O cliente é PCD? (s/n): ");
            boolean novoStatus = scanner.nextLine().equalsIgnoreCase("s");
            fachadaGerente.alterarStatusPCDCliente(cpf, novoStatus);
            System.out.println("Status PCD alterado com sucesso!");
        } catch (Exception e) {
            System.err.println("ERRO: " + e.getMessage());
        }
    }

    private static void alterarPlaca() {
        try {
            System.out.print("Digite a placa ANTIGA do veículo: ");
            String placaAntiga = scanner.nextLine();
            System.out.print("Digite a placa NOVA do veículo: ");
            String placaNova = scanner.nextLine();
            fachadaGerente.alterarPlacaVeiculo(placaAntiga, placaNova);
            System.out.println("Placa alterada com sucesso!");
        } catch (Exception e) {
            System.err.println("ERRO: " + e.getMessage());
        }
    }

    // Método de inicialização de vagas atualizado
    private static void inicializarVagas(RepositorioVagas repoVagas) {
        if (repoVagas.listar().isEmpty()) {
            System.out.println("Inicializando vagas...");
            repoVagas.adicionar(new Vaga("A01"));
            repoVagas.adicionar(new Vaga("A02"));
            Vaga vagaPCD = new Vaga("B01");
            vagaPCD.setPCD(true);
            repoVagas.adicionar(vagaPCD);
        }
    }

    private static int lerInteiro() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}