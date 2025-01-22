import java.util.ArrayList;
import java.util.Random;
import exception.MovimentoInvalidoException;

public class RobotSmart extends Robot {
  Random random = new Random();
  ArrayList<Integer> availableDirections;;

  public RobotSmart(String color) {
    super(color);
    availableDirections = new ArrayList<Integer>();
    resetDirections();
  }

  public void move(int direction) throws MovimentoInvalidoException {
    String movement = "";
    switch (direction) {
      case 1:
        movement = "up";
        break;
      case 2:
        movement = "down";
        break;
      case 3:
        movement = "right";
        break;
      case 4:
        movement = "left";
        break;
    }

    if (!availableDirections.contains(direction))
      move(availableDirections.get(random.nextInt(availableDirections.size())));
    else
      try {
        super.move(direction);
        resetDirections();
      } catch (Exception e) {
        availableDirections.remove((Integer)direction);
        throw new MovimentoInvalidoException(movement);
      }
  }

  private void resetDirections() {
    availableDirections.clear();
    availableDirections.add(1);
    availableDirections.add(2);
    availableDirections.add(3);
    availableDirections.add(4);
  }
}
