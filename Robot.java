import exception.MovimentoInvalidoException;

public class Robot {
  protected int[] pos;
  protected String color;
  protected String undoMovement;
  protected boolean canMove;

  public Robot(String color) {
    pos = new int[2];
    pos[0] = 0;
    pos[1] = 0;

    canMove = true;
    this.color = color;
  }

  public void move(String direction) throws MovimentoInvalidoException {
    if (!canMove)
      return;

    if (direction.equals("up")) {
      pos[1]++;
      undoMovement = "down";
    }
   
    if (direction.equals("right")) {
      pos[0]++;
      undoMovement = "left";
    }

    if (direction.equals("left")) {
      if (pos[0] == 0)
        throw new MovimentoInvalidoException(direction);
      else {
        pos[0]--;
        undoMovement = "right";
      }
    }

    if (direction.equals("down")) {
      if (pos[1] == 0)
        throw new MovimentoInvalidoException(direction);
      else {
        pos[1]--;
        undoMovement = "down";
      }
    }
  }

  public void move(int direction) throws MovimentoInvalidoException {
    switch (direction) {
      case 1:
        move("up");
        break;
      case 2:
        move("down");
        break;
      case 3:
        move("right");
        break;
      case 4:
        move("left");
        break;
    }
  }

  public void undo() throws MovimentoInvalidoException {
    move(undoMovement);
  }

  public void kill() {
    canMove = false;
  }

  public void printPosition() {
    System.out.println("Robô " + color + " posição: (" + pos[0] + ", " + pos[1] + ")");
  }

  public boolean hasFoundFood(int[] foodPostision) {
    return (pos[0] == foodPostision[0] && pos[1] == foodPostision[1]);
  }

  public int[] getPos() {
    return pos;
  }

  public String getColor() {
    return color;
  }
}
