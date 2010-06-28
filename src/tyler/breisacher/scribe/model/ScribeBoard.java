package tyler.breisacher.scribe.model;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/** 
 * The gameboard and some other data, such as whose turn it is.
 */
public class ScribeBoard implements MiniGridListener {
  private MiniGrid[][] data = new MiniGrid[3][3];
  private Map<ScribeMark, GridPosition> lastMove = new EnumMap<ScribeMark, GridPosition>(ScribeMark.class);
  private ScribeMark whoseTurn = ScribeMark.RED;
  private List<ScribeListener> listeners = new ArrayList<ScribeListener>();

  public ScribeBoard() {
    for (XY xy : XY.allXYs()) {
      MiniGrid miniGrid = new MiniGrid();
      miniGrid.addChangeListener(this);
      data[xy.x][xy.y] = miniGrid;
    }
  }
  
  public MiniGrid get(XY xy) {
    return data[xy.x][xy.y];
  }

  public MiniGrid get(int x, int y) {
    return this.get(new XY(x,y));
  }
  
  public ScribeMark whoseTurn() {
    return whoseTurn;
  }
  
  @Override
  public void miniGridChanged(MiniGrid miniGrid, XY xy, ScribeMark mark) {
    lastMove.put(mark, new GridPosition(miniGrid, xy));
    whoseTurn = mark.other();
    update();
  }

  public boolean isFull() {
    for (XY xy : XY.allXYs()) {
      if (!this.get(xy).isFull()) {
        return false;
      }
    }
    return true;
  }

  private void update() {
    checkForGameOver();
    enableMiniGrids();
    
    for (XY xy : XY.allXYs()) {
      MiniGrid miniGrid = this.get(xy);
      miniGrid.clearLastMoves(); 
    }
    for (GridPosition gp : lastMove.values()) {
      gp.miniGrid.addLastMove(gp.xy);
    }
  }

  private void enableMiniGrids() {
    GridPosition gridPosition = lastMove.get(whoseTurn);
    if (gridPosition == null) {
      setAllMiniGridsEnabled(true);
      return;
    }
    XY xy = gridPosition.xy;
    MiniGrid miniGrid = this.get(xy);
    if (miniGrid.isFull()) {
      setAllMiniGridsEnabled(true);
    }
    else {
      setAllMiniGridsEnabled(false);
      miniGrid.setEnabled(true);
    }
  }

  private void checkForGameOver() {
    if (this.isFull()) {
      notifyListeners(winner());
    }
  }

  private void notifyListeners(ScribeMark winner) {
    for (ScribeListener listener : listeners) {
      listener.notifyWinner(winner);
    }
  }

  public void addListener(ScribeListener listener) {
    listeners.add(listener);
  }
  
  private ScribeMark winner() {
    assert this.isFull();
    switch(Settings.getInstance().mode) {
    case SuperGlyph:
      MiniGrid superGrid = new MiniGrid();
      for (XY xy : XY.allXYs()) {
        superGrid.set(xy, this.get(xy).winner());
      }
      return superGrid.winner();
    case Majority:
    default:
      Map<ScribeMark, Integer> points = new EnumMap<ScribeMark, Integer>(ScribeMark.class);
      points.put(ScribeMark.RED, 0);
      points.put(ScribeMark.BLUE, 0);
      for (XY xy : XY.allXYs()) {
        ScribeMark winner = this.get(xy).winner();
        points.put(winner, points.get(winner)+1);
      }
      if (points.get(ScribeMark.RED) > points.get(ScribeMark.BLUE)) {
        return ScribeMark.RED;
      }
      else {
        return ScribeMark.BLUE;
      }
    }
  }

  private void setAllMiniGridsEnabled(boolean enable) {
    for (XY xy : XY.allXYs()) {
      this.get(xy).setEnabled(enable);
    }
  }

  public void tryMove(MiniGrid miniGrid, XY xy) {
    if (miniGrid.isEnabled()) {
      miniGrid.set(xy, whoseTurn);
    }
    else {
      throw new ScribeException("You cannot play in that minigrid.");
    }
  }
  
  // Probably not to be used in production.
  public static ScribeBoard generateRandomBoard() {
    ScribeBoard board = new ScribeBoard();
    for (XY miniGridXY : XY.allXYs()) {
      for (XY cellXY : XY.allXYs()) {
        ScribeMark randomMark = new Random().nextBoolean() ? ScribeMark.RED : ScribeMark.BLUE;
        board.get(miniGridXY).set(cellXY, randomMark);
      }
    }
    return board;
  }
}