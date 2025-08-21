import dados.cliente.RepositorioClientes;
import negocio.NegocioCliente;
import negocio.entidade.Cliente;
import negocio.entidade.Veiculo;
import negocio.excecao.usuario.UsuarioExistenteException;
import negocio.excecao.usuario.UsuarioNaoExisteException;

public class Main {

    public static void main(String[] args) {
        RepositorioClientes repositorioClientes = new RepositorioClientes();
        NegocioCliente negocioCliente = new NegocioCliente(repositorioClientes);

        System.out.println("--- SISTEMA DE ESTACIONAMENTO INICIADO ---");

        System.out.println("\n--- Testando cadastro de cliente ---");
        try {
            // Criando cliente apenas com CPF e preferência
            Cliente cliente1 = new Cliente("12345678900", false);
            negocioCliente.adicionar(cliente1);
            System.out.println("Cliente com CPF '" + cliente1.getCpf() + "' cadastrado com sucesso!");
        } catch (UsuarioExistenteException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        System.out.println("\n--- Testando cadastro de cliente duplicado ---");
        try {
            Cliente cliente2 = new Cliente("12345678900", false);
            negocioCliente.adicionar(cliente2);
        } catch (UsuarioExistenteException e) {
            System.out.println("Erro esperado: " + e.getMessage());
        }

        System.out.println("\n--- Testando consulta de cliente ---");
        try {
            Cliente clienteEncontrado = negocioCliente.consultarCPF("12345678900");
            System.out.println("Cliente encontrado: " + clienteEncontrado);
        } catch (UsuarioNaoExisteException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        System.out.println("\n--- Exemplo de criação de Veículo ---");
        try {
            Cliente dono = negocioCliente.consultarCPF("12345678900");
            Veiculo veiculo = new Veiculo("ABC-1234", dono);
            dono.setVeiculo(veiculo);
            System.out.println("Veículo com placa " + veiculo.getPlaca() + " associado ao cliente de CPF " + dono.getCpf());
        } catch (UsuarioNaoExisteException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        System.out.println("\n--- Testando remoção de cliente ---");
        try {
            negocioCliente.remover("12345678900");
            System.out.println("Cliente removido com sucesso!");
        } catch (UsuarioNaoExisteException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        System.out.println("\n--- Testando consultar cliente removido ---");
        try {
            negocioCliente.consultarCPF("12345678900");
        } catch (UsuarioNaoExisteException e) {
            System.out.println("Erro esperado: " + e.getMessage());
        }
    }
}