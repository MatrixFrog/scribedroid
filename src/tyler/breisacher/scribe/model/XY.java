package tyler.breisacher.scribe.model;

import java.util.ArrayList;
import java.util.List;

/**
 * An (x,y) pair. Both x and y will always be in the range [0, 3)
 */
// TODO make a "pool" like the Java String pool. stop using new
public class XY {
  public final int x, y;
  public XY(int x, int y) {
    if (0 <= x && x < 3) 
      this.x = x;
    else throw new IllegalArgumentException();
    if (0 <= y && y < 3)
      this.y = y;
    else throw new IllegalArgumentException();
  }
  
  public static List<XY> allXYs() {
    List<XY> list = new ArrayList<XY>(9);
    for (int y=0; y<3; y++) {
      for (int x=0; x<3; x++) {
        list.add(new XY(x,y));
      }
    }
    return list;
  }
  
  public List<XY> neighbors() {
    List<XY> neighbors = new ArrayList<XY>(4);
    for (int i : new int[] {-1, 0, 1}) {
      for (int j : new int[] {-1, 0, 1}) {
        try {
          if (i==0 ^ j==0) neighbors.add(new XY(x+i, y+j));
        }
        catch (IllegalArgumentException e) {
          // Do nothing
        }
      }
    }
    return neighbors;
  }
  
  @Override
  public boolean equals(Object other) {
    if (other instanceof XY) {
      return this.x == ((XY)other).x && this.y == ((XY) other).y;
    }
    else return false;
  }
  
  @Override
  public int hashCode() {
    return 3*x + y;
  }
  
  @Override
  public String toString() {
    return x + "" + y;
  }
  
  // FOR TESTING ONLY
  static XY fromString(String string) {
    return new XY(Integer.parseInt(string.substring(0,1)),
                  Integer.parseInt(string.substring(1,2)));
  }
}
