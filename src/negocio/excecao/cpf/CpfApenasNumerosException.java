package negocio.excecao.cpf;

@SuppressWarnings("serial")
public class CPFApenasNumerosException extends CpfException {
    public CPFApenasNumerosException() {
        super("O CPF deve conter apenas números.");
    }

}