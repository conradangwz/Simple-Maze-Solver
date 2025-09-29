import java.util.Objects;

public class Position {
    int y, x;
    Position(int y, int x) { this.y = y; this.x = x; }
    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof Position)) return false;
      Position p = (Position) o;
      return y == p.y && x == p.x;
    }
    @Override
    public int hashCode() { 
      return Objects.hash(y, x); 
    }
  }