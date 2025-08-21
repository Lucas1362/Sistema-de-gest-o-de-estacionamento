package negocio.excecao.usuario;

@SuppressWarnings("serial")
public class UsuarioNaoExisteException extends UsuarioException {

    public UsuarioNaoExisteException() {
        super("Usuário não existe.");
    }

}