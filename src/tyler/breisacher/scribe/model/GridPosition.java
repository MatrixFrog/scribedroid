package tyler.breisacher.scribe.model;

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
}
