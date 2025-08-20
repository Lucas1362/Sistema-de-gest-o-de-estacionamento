package negocio.excecao.cliente;

@SuppressWarnings("serial")
public class ClienteExistenteException extends ClienteException {

    public ClienteExistenteException() {
        super("Esse cliente já existe, não é possível adicioná-lo novamente.");
    }
}