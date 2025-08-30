package negocio.excecao.usuario;

@SuppressWarnings("serial")
public class UsuarioException extends Exception {

    private String msg;

    public UsuarioException(String msg) {
        super(msg);
        this.msg = msg;
    }

    @Override
    public String getMessage() {
        return msg;
    }

}