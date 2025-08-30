package negocio.excecao.veiculo;

@SuppressWarnings("serial")
public class VeiculoNaoExisteException extends VeiculoException {

    public VeiculoNaoExisteException() {
        super("Veículo não existe.");
    }

}