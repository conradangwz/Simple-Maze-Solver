import java.util.LinkedList;

public class Solver {

    int[][] maze = {
            {1, 0, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 1},
            {1, 1, 1, 0, 1, 1},
            {1, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 0, 1},
            {1, 1, 1, 1, 2, 1}
    };
    // KEY: 0 = path, 1 = wall, 2 = goal

    LinkedList<Position> path = new LinkedList<Position>();

    public static void main(String[] args) {
        Position pos = new Position(3,0);
        path.add(pos);

    }
}