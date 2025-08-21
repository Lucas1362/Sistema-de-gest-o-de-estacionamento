package negocio.excecao.vaga;

@SuppressWarnings("serial")
public class VagaException extends Exception {

    private String msg;

    public VagaException(String msg) {
        super(msg);
        this.msg = msg;
    }

    @Override
    public String getMessage() {
        return msg;
    }

}