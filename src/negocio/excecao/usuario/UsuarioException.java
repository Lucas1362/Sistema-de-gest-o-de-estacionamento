package negocio.excecao.cliente;

@SuppressWarnings("serial")
public class ClienteException extends Exception {

    private String msg;

    public ClienteException(String msg) {
        super(msg);
        this.msg = msg;
    }

    @Override
    public String getMessage() {
        return msg;
    }

}