package negocio.excecao.cpf;

@SuppressWarnings("serial")
public class CpfApenasNumerosException extends CpfException {
    public CpfApenasNumerosException() {
        super("O CPF deve conter apenas números.");
    }

}