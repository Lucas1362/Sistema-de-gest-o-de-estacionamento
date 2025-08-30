package negocio.excecao.cpf;

public class CpfTamanhoException extends CpfException {

    public CpfTamanhoException() {
        super("O CPF deve conter 11 dígitos.");
    }
}