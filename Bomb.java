public class Bomb extends Obstacle {
  boolean detonated;

  public Bomb(int[] pos) {
    super(0, pos);
    detonated = false;
  }

  public void hit(Robot robot) {
    if (!detonated)
      robot.kill();
  }

  public boolean hasDetonated() {
    return detonated;
  }
}
