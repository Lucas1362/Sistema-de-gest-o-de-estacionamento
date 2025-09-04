package negocio.excecao.vaga;

@SuppressWarnings("serial")
public class VagaOcupadaException extends VagaException {

    public VagaOcupadaException() {
        super("A vaga já está ocupada.");
    }

}