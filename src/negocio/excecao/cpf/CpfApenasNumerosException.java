package negocio.excecao.cliente;

@SuppressWarnings("serial")
public class CPFApenasNumerosException extends ClienteException {
    public CPFApenasNumerosException() {
        super("O CPF deve conter apenas números.");
    }

}