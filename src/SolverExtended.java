import java.io.*;
import java.util.*;

public class SolverExtended {
  static int[][] maze = {
    {
      1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
      1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1
    },
    {
      1, 3, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1,
      0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1
    },
    {
      1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1,
      0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1
    },
    {
      1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1
    },
    {
      1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1,
      0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1
    },
    {
      1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1
    },
    {
      1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1,
      0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1
    },
    {
      1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1
    },
    {
      1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1,
      0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1
    },
    {
      1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1
    },
    {
      1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
      1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1
    }
  };

  static LinkedList<Position> finalPath = new LinkedList<>();

  public static void main(String[] args) {
    int l = 50; // increased depth for large maze
    int B = Integer.MAX_VALUE;
    LinkedList<Position> S = new LinkedList<>();
    Position start = findStart();
    if (start == null) {
      System.out.println("Start position (3) not found in maze.");
      return;
    }
    S.add(start);
    boolean found = BMSSP(l, B, S, new boolean[maze.length][maze[0].length]);
    if (found) {
      markPathAndWriteMaze(finalPath, "maze_output_ext.txt");
      System.out.println("Maze with path written to maze_output_ext.txt");
    } else {
      System.out.println("No path found.");
    }
  }

  static Position findStart() {
    for (int y = 0; y < maze.length; y++) {
      for (int x = 0; x < maze[y].length; x++) {
        if (maze[y][x] == 3) {
          return new Position(y, x);
        }
      }
    }
    return null;
  }

  // DFS to find path, stores path in finalPath
  public static boolean BMSSP(int l, int B, LinkedList<Position> S, boolean[][] visited) {
    Position current = S.getLast();
    if (maze[current.y][current.x] == 2) {
      finalPath.clear();
      finalPath.addAll(S);
      return true;
    }
    visited[current.y][current.x] = true;
    for (int[] d : new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}}) {
      int ny = current.y + d[0], nx = current.x + d[1];
      if (isValidMove(ny, nx) && !visited[ny][nx] && maze[ny][nx] != 1) {
        S.add(new Position(ny, nx));
        if (BMSSP(l - 1, B, S, visited)) return true;
        S.removeLast();
      }
    }
    visited[current.y][current.x] = false;
    return false;
  }

  public static boolean isValidMove(int y, int x) {
    return y >= 0 && x >= 0 && y < maze.length && x < maze[y].length;
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
