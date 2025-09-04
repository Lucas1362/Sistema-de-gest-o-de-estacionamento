package negocio.excecao.vaga;

@SuppressWarnings("serial")
public class VagaExistenteException extends VagaException {

    public VagaExistenteException() {
        super("Essa Vaga já existe, não é possível adicioná-la novamente.");
    }
}