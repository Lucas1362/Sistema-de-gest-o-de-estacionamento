package negocio.excecao.vaga;

@SuppressWarnings("serial")
public class VagaNaoExisteException extends VagaException {

    public VagaNaoExisteException() {
        super("Vaga não existe.");
    }

}