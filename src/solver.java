import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Solver {

  static int[][] maze = {
      {1, 3, 1, 1, 1, 1},
      {1, 0, 0, 0, 0, 1},
      {1, 1, 1, 0, 1, 1},
      {1, 0, 0, 0, 0, 1},
      {1, 1, 1, 1, 0, 1},
      {1, 1, 1, 1, 2, 1}
  };
  // KEY: 0 = path, 1 = wall, 2 = goal, 3 = start, 4 = solution path

  static LinkedList<Position> path = new LinkedList<Position>();

  public static void main(String[] args) {
    int startY = -1, startX = -1;
    // Find the starting point (value 3)
    for (int y = 0; y < maze.length; y++) {
      for (int x = 0; x < maze[y].length; x++) {
        if (maze[y][x] == 3) {
          startY = y;
          startX = x;
          break;
        }
      }
      if (startY != -1) break;
    }
    if (startY == -1 || startX == -1) {
      System.out.println("No starting point found!");
      return;
    }

    path.push(new Position(startY, startX));

    boolean found = false;
    while (!path.isEmpty()) {
      int y = path.peek().y;
      int x = path.peek().x;

      if (maze[y][x] != 3) maze[y][x] = 1; // mark as visited except start

      // Down
      if (isValidMove(y + 1, x)) {
        if (maze[y + 1][x] == 2) {
          path.push(new Position(y + 1, x));
          found = true;
          break;
        } else if (maze[y + 1][x] == 0) {
          path.push(new Position(y + 1, x));
          continue;
        }
      }
      // Up
      if (isValidMove(y - 1, x)) {
        if (maze[y - 1][x] == 2) {
          path.push(new Position(y - 1, x));
          found = true;
          break;
        } else if (maze[y - 1][x] == 0) {
          path.push(new Position(y - 1, x));
          continue;
        }
      }
      // Right
      if (isValidMove(y, x + 1)) {
        if (maze[y][x + 1] == 2) {
          path.push(new Position(y, x + 1));
          found = true;
          break;
        } else if (maze[y][x + 1] == 0) {
          path.push(new Position(y, x + 1));
          continue;
        }
      }
      // Left
      if (isValidMove(y, x - 1)) {
        if (maze[y][x - 1] == 2) {
          path.push(new Position(y, x - 1));
          found = true;
          break;
        } else if (maze[y][x - 1] == 0) {
          path.push(new Position(y, x - 1));
          continue;
        }
      }

      path.pop();
    }

    if (found) {
      // Mark the solution path with 4 (except start 3 and goal 2)
      markPathAndWriteMaze(path, "maze_output.txt");
      System.out.println("Maze with path written to maze_output.txt");
    } else {
      System.out.println("No Path");
    }
  }

  public static boolean isValidMove(int y, int x) {
    if (y < 0 || x < 0 || y >= maze.length || x >= maze[y].length) {
      return false;
    }
    return maze[y][x] == 0 || maze[y][x] == 2;
  }

  // Mark path with 4 and write maze to file
  public static void markPathAndWriteMaze(List<Position> path, String filename) {
    int[][] mazeCopy = new int[maze.length][maze[0].length];
    for (int y = 0; y < maze.length; y++) mazeCopy[y] = Arrays.copyOf(maze[y], maze[y].length);

    for (Position p : path) {
      if (mazeCopy[p.y][p.x] == 0) mazeCopy[p.y][p.x] = 4;
    }

    try (PrintWriter out = new PrintWriter(filename)) {
      for (int y = 0; y < mazeCopy.length; y++) {
        for (int x = 0; x < mazeCopy[y].length; x++) {
          out.print(mazeCopy[y][x]);
          if (x < mazeCopy[y].length - 1) out.print(" ");
        }
        out.println();
      }
    } catch (IOException e) {
      System.out.println("Error writing maze: " + e.getMessage());
    }
  }

  
}
