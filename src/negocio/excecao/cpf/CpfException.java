package negocio.excecao.cpf;

@SuppressWarnings("serial")
public class CpfException extends Exception {

    private String msg;

    public CpfException(String msg) {
        super(msg);
        this.msg = msg;
    }

    @Override
    public String getMessage() {
        return msg;
    }

}