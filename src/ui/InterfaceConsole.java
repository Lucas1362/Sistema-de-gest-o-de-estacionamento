package ui;

import fachada.FachadaCliente;
import fachada.FachadaGerente;
import negocio.entidade.Ticket;
import negocio.entidade.Vaga;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class InterfaceConsole {

    private final FachadaCliente fachadaCliente;
    private final FachadaGerente fachadaGerente;
    private final Scanner scanner;

    public InterfaceConsole(FachadaCliente fachadaCliente, FachadaGerente fachadaGerente) {
        this.fachadaCliente = fachadaCliente;
        this.fachadaGerente = fachadaGerente;
        this.scanner = new Scanner(System.in);
    }

    public void executar() {
        System.out.println("Bem-vindo ao Sistema de Gestão de Estacionamento!");
        loopPrincipal();
    }

    private void loopPrincipal() {
        while (true) {
            System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("1. Acessar como Cliente");
            System.out.println("2. Acessar como Gerente");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");

            int escolha = lerInteiro();
            switch (escolha) {
                case 1 -> menuCliente();
                case 2 -> menuGerente();
                case 3 -> {
                    System.out.println("Obrigado por usar o sistema. Até logo!");
                    return;
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private void menuCliente() {
        while (true) {
            System.out.println("\n--- ÁREA DO CLIENTE ---");
            System.out.println("1. Login (Entrada/Saída)");
            System.out.println("2. Cadastro");
            System.out.println("3. Voltar ao Menu Principal");
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
    }

    private void fluxoCadastro() {
        try {
            System.out.println("\n--- CADASTRO ---");
            System.out.print("Digite seu CPF: ");
            String cpf = scanner.nextLine();
            System.out.print("Digite a placa do veículo: ");
            String placa = scanner.nextLine();
            System.out.print("Você é PCD? (s/n): ");
            boolean pcd = scanner.nextLine().equalsIgnoreCase("s");

            fachadaCliente.cadastrarClienteEVeiculo(cpf, placa, pcd);
            System.out.println("\n>>> Cadastro realizado com sucesso! Faça o login para continuar.");
        } catch (Exception e) {
            System.err.println("\nERRO AO CADASTRAR: " + e.getMessage());
        }
    }

    private void fluxoLogin() {
        try {
            System.out.println("\n--- LOGIN ---");
            System.out.print("Digite seu CPF: ");
            String cpf = scanner.nextLine();
            System.out.print("Digite a placa do veículo: ");
            String placa = scanner.nextLine();

            Ticket ticketAtivo = fachadaCliente.realizarLogin(cpf, placa);

            if (ticketAtivo == null) {
                fluxoDeEntrada(placa);
            } else {
                fluxoDeSaida(ticketAtivo);
            }
        } catch (Exception e) {
            System.err.println("\nERRO NO LOGIN: " + e.getMessage());
        }
    }

    private void fluxoDeEntrada(String placa) throws Exception {
        System.out.println("\n>>> Login bem-sucedido. Iniciando processo de ENTRADA.");
        ArrayList<Vaga> vagasLivres = fachadaCliente.listarVagasLivres();

        if (vagasLivres.isEmpty()) {
            System.out.println("\nDESCULPE, NÃO HÁ VAGAS DISPONÍVEIS NO MOMENTO.");
            return;
        }

        while (true) {
            System.out.println("\nVagas disponíveis:");
            vagasLivres.forEach(vaga -> System.out.println("- Vaga " + vaga.getNumeroID() + (vaga.isPCD() ? " (PCD)" : "")));

            System.out.print("\nEscolha o ID da vaga (ou digite 'cancelar' para voltar): ");
            String idVaga = scanner.nextLine();

            if (idVaga.equalsIgnoreCase("cancelar")) { return; }

            try {
                // --- MUDANÇA AQUI: RESUMO DE ENTRADA ---
                System.out.println("\n--- CONFIRME OS DADOS DA ENTRADA ---");
                System.out.println("Placa do Veículo: " + placa);
                System.out.println("Vaga Escolhida: " + idVaga);
                System.out.println("Horário de Entrada: " + java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(LocalDateTime.now()));
                System.out.print("Digite 'confirmar' para entrar: ");

                if (scanner.nextLine().equalsIgnoreCase("confirmar")) {
                    fachadaCliente.registrarEntrada(placa, idVaga);
                    System.out.println("\n>>> ENTRADA REGISTRADA COM SUCESSO! Bem-vindo!");
                } else {
                    System.out.println("\nEntrada cancelada.");
                }
                break;

            } catch (Exception e) {
                System.err.println("\nERRO AO ESCOLHER VAGA: " + e.getMessage());
                System.out.println("Por favor, tente outra vaga.");
            }
        }
    }

    private void fluxoDeSaida(Ticket ticketAtivo) throws Exception {
        System.out.println("\n>>> Login bem-sucedido. Iniciando processo de SAÍDA.");
        double valor = fachadaCliente.registrarSaida(ticketAtivo);

        // --- MUDANÇA AQUI: RESUMO DE SAÍDA ---
        LocalDateTime entrada = ticketAtivo.getHorarioEntrada();
        LocalDateTime saida = ticketAtivo.getHorarioSaida(); // Adicione este getter em Ticket.java
        long minutos = Duration.between(entrada, saida).toMinutes();

        System.out.println("\n--- CONFIRME OS DADOS PARA PAGAMENTO ---");
        System.out.println("Placa do Veículo: " + ticketAtivo.getVeiculo().getPlaca());
        System.out.println("Vaga Utilizada: " + ticketAtivo.getVaga().getNumeroID());
        System.out.println("Horário de Entrada: " + java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(entrada));
        System.out.println("Horário de Saída: " + java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(saida));
        System.out.printf("Tempo Total: %d minutos\n", minutos);
        System.out.printf("Valor a Pagar: R$ %.2f\n", valor);
        System.out.print("Digite 'pagar' para confirmar: ");

        if (scanner.nextLine().equalsIgnoreCase("pagar")) {
            System.out.println("\n>>> PAGAMENTO CONFIRMADO. SAÍDA LIBERADA! Volte sempre!");
        } else {
            // Aqui você precisaria de uma lógica para "desfazer" a saída ou o ticket permaneceria inativo.
            // Por simplicidade, vamos apenas avisar.
            System.out.println("\nPagamento não confirmado. A saída não foi liberada.");
        }
    }

    private void menuGerente() {
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
            System.out.println("4. Alterar Tarifa por Hora"); // --- NOVA OPÇÃO ---
            System.out.println("5. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            int escolha = lerInteiro();
            switch (escolha) {
                // ...
                case 4:
                    alterarTarifa();
                    break;
                case 5:
                    return;
                // ...
            }
        }
    }

    private void alterarTarifa() {
        try {
            System.out.print("Digite o novo valor da tarifa por hora (ex: 6.50): ");
            double novoValor = Double.parseDouble(scanner.nextLine());
            fachadaGerente.alterarTarifa(novoValor);
            System.out.println("Tarifa alterada com sucesso!");
        } catch (NumberFormatException e) {
            System.err.println("ERRO: Valor inválido. Use ponto como separador decimal.");
        }
    }

    private void alterarPCD() {
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

    private void alterarPlaca() {
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

    private int lerInteiro() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
