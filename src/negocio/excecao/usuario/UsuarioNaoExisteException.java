package negocio.excecao.cliente;

@SuppressWarnings("serial")
public class ClienteNaoExisteException extends ClienteException {

    public ClienteNaoExisteException() {
        super("Cliente não existe.");
    }

}