package tyler.breisacher.scribe.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tyler.breisacher.scribe.Constants;
import android.util.Log;

/**
 * An (x,y) pair. Both x and y will always be in the range [0, 3)
 */
public class XY {
  public final int x, y;

  private static Map<Integer, XY> map = new HashMap<Integer, XY>(9);

  static {
    for (XY xy : createAllXYs()) {
      map.put(xy.hashCode(), xy);
    }
  }

  private XY(int x, int y) {
    Log.w(Constants.LOG_TAG, "Creating XY:" + x + y);
    if (0 <= x && x < 3)
      this.x = x;
    else throw new IllegalArgumentException();
    if (0 <= y && y < 3)
      this.y = y;
    else throw new IllegalArgumentException();
  }

  public static XY at(int x, int y) {
    return map.get(10*x + y);
  }

  private static List<XY> createAllXYs() {
    List<XY> list = new ArrayList<XY>(9);
    for (int y=0; y<3; y++) {
      for (int x=0; x<3; x++) {
        list.add(new XY(x,y));
      }
    }
    return list;
  }

  public static Collection<XY> allXYs() {
    return map.values();
  }

  public Collection<XY> neighbors() {
    Collection<XY> neighbors = new ArrayList<XY>(4);
    for (int i : new int[] {-1, 0, 1}) {
      for (int j : new int[] {-1, 0, 1}) {
        if (i==0 ^ j==0) {
          XY neighbor = XY.at(x+i, y+j);
          if (neighbor != null) neighbors.add(neighbor);
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
    return 10*x + y;
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
