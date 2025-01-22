public abstract class Obstacle {
  protected int id;
  protected int[] pos;
  
  public Obstacle(int id, int[] pos) {
    this.id = id;
    this.pos = pos;
  }

  public abstract void hit(Robot robot);

  public int[] getPos() {
    return pos;
  }
}
