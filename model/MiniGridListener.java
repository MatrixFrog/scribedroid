package tyler.breisacher.scribe.model;

public interface MiniGridListener {

  /**
   * @param miniGrid the MiniGrid that has changed 
   * @param xy the position on the grid that got set.
   * @param mark the mark that got placed at that position
   */
  void miniGridChanged(MiniGrid miniGrid, XY xy, ScribeMark mark);


}
