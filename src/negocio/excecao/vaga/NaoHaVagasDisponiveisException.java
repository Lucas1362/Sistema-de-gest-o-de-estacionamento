package negocio.excecao.vaga;

@SuppressWarnings("serial")
public class NaoHaVagasDisponiveisException extends VagaException {
  public NaoHaVagasDisponiveisException() {
    super("Não há vagas disponíveis no momento.");
  }
}