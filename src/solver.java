import java.util.LinkedList;

public class Solver {

    static int[][] maze = {
            {1, 0, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 1},
            {1, 1, 1, 0, 1, 1},
            {1, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 0, 1},
            {1, 1, 1, 1, 2, 1}
    };
    // KEY: 0 = path, 1 = wall, 2 = goal

    static LinkedList<Position> path = new LinkedList<Position>();

    public static void main(String[] args) {
        Position pos = new Position(0,1);
        path.push(pos);

        while (true) {
          int x = path.peek().x;
          int y = path.peek().y;

          maze[y][x] = 1; // mark as visited

          
          if (isValidMove(y+1, x)) {
            if (maze[y+1][x] == 2) { // down
              System.out.println("Moved Down, Goal found!");
              return;
            } else if (maze[y+1][x] == 0) { 
                System.out.println("Moving down");
                path.push(new Position(y+1, x));
                continue;
            }
          }
          

          if (isValidMove(y-1, x)) {
            if (maze[y-1][x] == 2) { // up
              System.out.println("Moved Up, Goal found!");
              return;
            } else if (maze[y-1][x] == 0) { 
                System.out.println("Moving up");
                path.push(new Position(y-1, x));
                continue;
            }
          }

          if (isValidMove(y, x+1)) {
            if (maze[y][x+1] == 2) { // right
              System.out.println("Moved Right, Goal found!");
              return;
            } else if (maze[y][x+1] == 0) { 
                System.out.println("Moving right");
                path.push(new Position(y, x+1));
                continue;
            }
          }
         

          if (isValidMove(y, x-1)) {
            if (maze[y][x-1] == 2) { // left
              System.out.println("Moved Left, Goal found!");
              return;
            } else if (maze[y][x-1] == 0) { 
                System.out.println("Moving left");
                path.push(new Position(y, x-1));
                continue;
            }
          }

          path.pop();
          System.out.println("Moved Back");
          if (path.size() <= 0) {
              System.out.println("No Path");
              return;
          }
        } 
    }

    public static boolean isValidMove(int y, int x) {
      if ( y < 0 || x < 0 || y >= maze.length || x >= maze[y].length ) {
        return false;
      }
      return true;
    }
}