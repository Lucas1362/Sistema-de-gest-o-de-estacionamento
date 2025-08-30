package negocio.excecao.veiculo;

@SuppressWarnings("serial")
public class VeiculoNaoPertenceAoClienteException extends VeiculoException {
  public VeiculoNaoPertenceAoClienteException(String placa, String cpf) {
    super("A placa " + placa + " não pertence ao cliente com CPF " + cpf);
  }
}