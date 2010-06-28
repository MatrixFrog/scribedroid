package tyler.breisacher.scribe.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Specifies an exact position on a grid, both the mini-grid and the XY.
 */
public class GridPosition {
  public final MiniGrid miniGrid;
  public final XY xy;

  public GridPosition(MiniGrid miniGrid, XY xy) {
    this.miniGrid = miniGrid;
    this.xy = xy;
  }
  
  public static List<GridPosition> allPositionsOn(ScribeBoard board) {
    List<GridPosition> list = new ArrayList<GridPosition>(81);
    for (XY miniGridXY : XY.allXYs()) {
      for (XY cellXY : XY.allXYs()) {
        list.add(new GridPosition(board.get(miniGridXY), cellXY));
      }
    }
    return list;
  }
}
