import dados.cliente.RepositorioClientes;
import dados.veiculo.RepositorioVeiculos;
import negocio.NegocioCliente;
import negocio.entidade.Cliente;
import negocio.entidade.Veiculo;
import negocio.excecao.usuario.UsuarioExistenteException;
import negocio.excecao.usuario.UsuarioNaoExisteException;

public class Main {

    public static void main(String[] args) {
        // --- INICIALIZAÇÃO DOS REPOSITÓRIOS E CAMADA DE NEGÓCIO ---
        RepositorioClientes repositorioClientes = new RepositorioClientes();
        RepositorioVeiculos repositorioVeiculos = new RepositorioVeiculos(); // Descomente quando a classe for corrigida
        NegocioCliente negocioCliente = new NegocioCliente(repositorioClientes);

        System.out.println("--- SISTEMA DE ESTACIONAMENTO INICIADO ---");

        // --- TESTE DE CADASTRO DE CLIENTE ---
        System.out.println("\n--- Testando cadastro de cliente ---");
        try {
            Cliente cliente1 = new Cliente("12345678900", false);
            negocioCliente.adicionar(cliente1);
            System.out.println("Cliente 'João Silva' cadastrado com sucesso!");
        } catch (UsuarioExistenteException e) {
            System.err.println(e.getMessage());
        }

        // --- TENTATIVA DE CADASTRAR CLIENTE DUPLICADO ---
        System.out.println("\n--- Testando cadastro de cliente duplicado ---");
        try {
            Cliente cliente2 = new Cliente("12345678900", false);
            negocioCliente.adicionar(cliente2);
        } catch (UsuarioExistenteException e) {
            System.err.println("Erro esperado: " + e.getMessage());
        }

        // --- CONSULTA DE CLIENTE ---
        System.out.println("\n--- Testando consulta de cliente ---");
        try {
            Cliente clienteEncontrado = negocioCliente.consultarCPF("12345678900");
            System.out.println("Cliente encontrado: ");
        } catch (UsuarioNaoExisteException e) {
            System.err.println(e.getMessage());
        }

        // --- TESTE DE VEÍCULO (SIMPLIFICADO) ---
        // Para testar o veículo, as classes RepositorioVeiculos e NegocioVeiculo precisariam ser ajustadas.
        // O código abaixo é um exemplo de como poderia ser.
        System.out.println("\n--- Exemplo de criação de Veículo ---");
        try {
            Cliente dono = negocioCliente.consultarCPF("12345678900");
            Veiculo veiculo = new Veiculo("ABC-1234", dono);
            dono.setVeiculo(veiculo);
            System.out.println("Veículo com placa " + veiculo.getPlaca() + " associado ao cliente ");
        } catch (UsuarioNaoExisteException e) {
            System.err.println(e.getMessage());
        }


        // --- REMOÇÃO DE CLIENTE ---
        System.out.println("\n--- Testando remoção de cliente ---");
        try {
            negocioCliente.remover("12345678900");
            System.out.println("Cliente removido com sucesso!");
        } catch (UsuarioNaoExisteException e) {
            System.err.println(e.getMessage());
        }

        // --- TENTATIVA DE CONSULTAR CLIENTE REMOVIDO ---
        System.out.println("\n--- Testando consultar cliente removido ---");
        try {
            negocioCliente.consultarCPF("12345678900");
        } catch (UsuarioNaoExisteException e) {
            System.err.println("Erro esperado: " + e.getMessage());
        }
    }
}