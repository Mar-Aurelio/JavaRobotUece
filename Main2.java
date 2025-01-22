import java.util.Random;
import java.util.Scanner;

public class Main2 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    int[] food = {0, 0};

    Robot[] robots = new Robot[2];
    robots[0] = new Robot("Azul");
    robots[1] = new Robot("Vermelho");

    int[][] moveInfo = {{0, 0}, {0, 0}};

    Random random = new Random();

    String winnerColor = "";

    System.out.print("Posição do alimento:\nx: ");
    food[0] = input.nextInt();
    System.out.print("y: ");
    food[1] = input.nextInt();

    while (winnerColor.isEmpty()) {
      for (int i = 0; i < 2; i++) {
        try {
          robots[i].move(random.nextInt(4) + 1);
          moveInfo[i][0]++;
        } catch (Exception e) {
          System.out.println(e);
          moveInfo[i][1]++;
        }
      }

      winnerColor = checkIfFoodWasFound(robots, food);
      printBoard(robots, food);

      try {
        Thread.sleep(50);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    System.out.println(winnerColor + " encontrou o alimento");

    for (int i = 0; i < 2; i++) {
      System.out.println("Robo " + robots[i].getColor() + " fez " + moveInfo[i][0] + " movimentos válidos e " + moveInfo[i][1] + " movimentos inválidos");
    }

    input.close();
  }

  private static String checkIfFoodWasFound(Robot[] robots, int[] foodPostision) {
    for (Robot r : robots)
      if (r.hasFoundFood(foodPostision)) return r.getColor();

    return "";
  }

  private static void printBoard(Robot[] robots, int[] foodPostision) {
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
    System.out.print(grid[0][6]+"."+grid[1][6]+"...."+grid[2][6]+"...."+grid[3][6]+"...."+grid[4][6]+"...."+grid[5][6]+"."+grid[6][6]+"\n.   ..   ..   ..   ..   .\n");
    System.out.print(grid[0][5]+" "+grid[1][5]+" .. "+grid[2][5]+" .. "+grid[3][5]+" .. "+grid[4][5]+" .. "+grid[5][5]+" "+grid[6][5]+"\n.   ..   ..   ..   ..   .\n.........................\n.........................\n.   ..   ..   ..   ..   .\n");
    System.out.print(grid[0][4]+" "+grid[1][4]+" .. "+grid[2][4]+" .. "+grid[3][4]+" .. "+grid[4][4]+" .. "+grid[5][4]+" "+grid[6][4]+"\n.   ..   ..   ..   ..   .\n.........................\n.........................\n.   ..   ..   ..   ..   .\n");
    System.out.print(grid[0][3]+" "+grid[1][3]+" .. "+grid[2][3]+" .. "+grid[3][3]+" .. "+grid[4][3]+" .. "+grid[5][3]+" "+grid[6][3]+"\n.   ..   ..   ..   ..   .\n.........................\n.........................\n.   ..   ..   ..   ..   .\n");
    System.out.print(grid[0][2]+" "+grid[1][2]+" .. "+grid[2][2]+" .. "+grid[3][2]+" .. "+grid[4][2]+" .. "+grid[5][2]+" "+grid[6][2]+"\n.   ..   ..   ..   ..   .\n.........................\n.........................\n.   ..   ..   ..   ..   .\n");
    System.out.print(grid[0][1]+" "+grid[1][1]+" .. "+grid[2][1]+" .. "+grid[3][1]+" .. "+grid[4][1]+" .. "+grid[5][1]+" "+grid[6][1]+"\n.   ..   ..   ..   ..   .\n");
    System.out.print(grid[0][0]+"."+grid[1][0]+"...."+grid[2][0]+"...."+grid[3][0]+"...."+grid[4][0]+"...."+grid[5][0]+"."+grid[6][0]+'\n');
  }
}
