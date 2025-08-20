package negocio.excecao.cliente;

public class CpfTamanhoException extends ClienteException {

    public CpfTamanhoException() {
        super("O CPF deve conter 11 dígitos.");
    }
}