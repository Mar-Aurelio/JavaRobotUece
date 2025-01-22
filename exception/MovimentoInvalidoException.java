package exception;

public class MovimentoInvalidoException extends Exception {
  public MovimentoInvalidoException(String movimento) {
    super("Movimento: " + movimento + " inválido");
  }
}
