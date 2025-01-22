import java.util.Random;
import java.util.Scanner;

public class Main4 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    int[] food = {0, 0};

    Robot[] robots = new Robot[2];
    robots[0] = new Robot("Azul");
    robots[1] = new RobotSmart("Vermelho");

    int[][] moveInfo = {{0, 0}, {0, 0}};

    Random random = new Random();

    System.out.print("Posição do alimento:\nx: ");
    food[0] = input.nextInt();
    System.out.print("y: ");
    food[1] = input.nextInt();

    System.out.print("Quantas bombas? ");
    int numBombs = input.nextInt();
    System.out.print("Quantas rochas? ");
    int numRocks = input.nextInt();

    Obstacle[] obstacles = new Obstacle[numBombs+numRocks];
    for (int i = 0; i < numBombs; i++) {
      int[] temp = new int[2];
      System.out.print("Posição da bomba " + (i+1) + ":\nx: ");
      temp[0] = input.nextInt();
      System.out.print("y: ");
      temp[1] = input.nextInt();

      obstacles[i] = new Bomb(temp);
    }

    for (int i = numBombs; i < numRocks + numBombs; i++) {
      int[] temp = new int[2];
      System.out.print("Posição da rocha " + (i+1) + ":\nx: ");
      temp[0] = input.nextInt();
      System.out.print("y: ");
      temp[1] = input.nextInt();

      obstacles[i] = new Rock(temp);
    }

    while (!checkIfEveryoneFoundFood(robots, food)) {
      for (int i = 0; i < 2; i++) {
        if (!robots[i].hasFoundFood(food)) {
          try {
            robots[i].move(random.nextInt(4) + 1);
            moveInfo[i][0]++;
          } catch (Exception e) {
            System.out.println(e);
            moveInfo[i][1]++;
          }
        }

        for (Obstacle o : obstacles) {
          if (robots[i].getPos() == o.getPos())
            o.hit(robots[i]);
        }
      }
      
      for (int i = 0; i < 2; i++) {
        robots[i].printPosition();
      }
      printBoard(robots, food, obstacles);
      System.out.println();

      try {
        Thread.sleep(50);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    for (int i = 0; i < 2; i++) {
      System.out.println("Robo " + robots[i].getColor() + " fez " + moveInfo[i][0] + " movimentos válidos e " + moveInfo[i][1] + " movimentos inválidos");
    }

    input.close();
  }

  private static boolean checkIfEveryoneFoundFood(Robot[] robots, int[] foodPostision) {
    for (Robot r : robots)
      if (!r.hasFoundFood(foodPostision)) return false;

    return true;
  }

  private static void printBoard(Robot[] robots, int[] foodPostision, Obstacle[] obstacles) {
    char[][] grid = new char[7][7];
    for (int i = 0; i < 7; i++)
      for (int j = 0; j < 7; j++)
        grid[i][j] = '.';
    grid[3][3] = '*';

    for (Robot r : robots) {
      int x = r.getPos()[0] - foodPostision[0] + 3;
      int y = r.getPos()[1] - foodPostision[1] + 3;
      int clampX = Math.clamp(x, 0, 6);
      int clampY = Math.clamp(y, 0, 6);

      grid[clampX][clampY] = (grid[clampX][clampY] == '.') ? (r.getColor().charAt(0)) : ('o');
    }

    for (Obstacle o : obstacles) {
      int x = o.getPos()[0] - foodPostision[0] + 3;
      int y = o.getPos()[1] - foodPostision[1] + 3;
      
      if (x < 1 || x > 5) continue;
      if (y < 1 || y > 5) continue;

      grid[x][y] = (o instanceof Bomb) ? ('@') : ('#');
    }

    System.out.print(grid[0][6]+"."+grid[1][6]+"...."+grid[2][6]+"...."+grid[3][6]+"...."+grid[4][6]+"...."+grid[5][6]+"."+grid[6][6]+"\n.   ..   ..   ..   ..   .\n");
    System.out.print(grid[0][5]+" "+grid[1][5]+" .. "+grid[2][5]+" .. "+grid[3][5]+" .. "+grid[4][5]+" .. "+grid[5][5]+" "+grid[6][5]+"\n.   ..   ..   ..   ..   .\n.........................\n.........................\n.   ..   ..   ..   ..   .\n");
    System.out.print(grid[0][4]+" "+grid[1][4]+" .. "+grid[2][4]+" .. "+grid[3][4]+" .. "+grid[4][4]+" .. "+grid[5][4]+" "+grid[6][4]+"\n.   ..   ..   ..   ..   .\n.........................\n.........................\n.   ..   ..   ..   ..   .\n");
    System.out.print(grid[0][3]+" "+grid[1][3]+" .. "+grid[2][3]+" .. "+grid[3][3]+" .. "+grid[4][3]+" .. "+grid[5][3]+" "+grid[6][3]+"\n.   ..   ..   ..   ..   .\n.........................\n.........................\n.   ..   ..   ..   ..   .\n");
    System.out.print(grid[0][2]+" "+grid[1][2]+" .. "+grid[2][2]+" .. "+grid[3][2]+" .. "+grid[4][2]+" .. "+grid[5][2]+" "+grid[6][2]+"\n.   ..   ..   ..   ..   .\n.........................\n.........................\n.   ..   ..   ..   ..   .\n");
    System.out.print(grid[0][1]+" "+grid[1][1]+" .. "+grid[2][1]+" .. "+grid[3][1]+" .. "+grid[4][1]+" .. "+grid[5][1]+" "+grid[6][1]+"\n.   ..   ..   ..   ..   .\n");
    System.out.print(grid[0][0]+"."+grid[1][0]+"...."+grid[2][0]+"...."+grid[3][0]+"...."+grid[4][0]+"...."+grid[5][0]+"."+grid[6][0]+'\n');
  }
}
