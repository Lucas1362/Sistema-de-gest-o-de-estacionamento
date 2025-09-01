package negocio.excecao.veiculo;

@SuppressWarnings("serial")
public class VeiculoExistenteException extends VeiculoException {

    public VeiculoExistenteException() {
        super("Esse Veiculo já existe, não é possível adicioná-lo novamente.");
    }
}