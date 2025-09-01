package negocio.excecao.veiculo;

@SuppressWarnings("serial")
public class VeiculoException extends Exception {

    private String msg;

    public VeiculoException(String msg) {
        super(msg);
        this.msg = msg;
    }

    @Override
    public String getMessage() {
        return msg;
    }

}