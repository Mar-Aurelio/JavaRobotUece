public class Rock extends Obstacle {
  public Rock(int[] pos) {
    super(1, pos);
  }

  public void hit(Robot robot) {
    try {
      robot.undo();
    } catch (Exception e) {
      System.out.println("Could not undo movement (?)");
      System.out.println(e);
    }
  }
}
