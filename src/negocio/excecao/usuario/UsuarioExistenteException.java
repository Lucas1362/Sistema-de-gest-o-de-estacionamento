package negocio.excecao.usuario;

@SuppressWarnings("serial")
public class UsuarioExistenteException extends UsuarioException {

    public UsuarioExistenteException() {
        super("Esse Usuário já existe, não é possível adicioná-lo novamente.");
    }
}