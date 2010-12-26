package tyler.breisacher.scribe;

import java.util.Collection;

import tyler.breisacher.scribe.model.MiniGrid;
import tyler.breisacher.scribe.model.MiniGridListener;
import tyler.breisacher.scribe.model.ScribeBoard;
import tyler.breisacher.scribe.model.ScribeListener;
import tyler.breisacher.scribe.model.ScribeMark;
import tyler.breisacher.scribe.model.XY;

public class MoveLogger implements ScribeListener, MiniGridListener {
  private static MoveLogger singleton;
  private ScribeBoard scribeBoard;

  public static MoveLogger getInstance() {
    if (singleton == null) singleton = new MoveLogger();
    return singleton;
  }

  public void setScribeBoard(ScribeBoard scribeBoard) {
    this.scribeBoard = scribeBoard;
    this.scribeBoard.addListener(this);
    for (XY xy : XY.allXYs()) {
      this.scribeBoard.get(xy).addMiniGridListener(this);
    }
  }

  @Override
  public void scribeBoardWon(ScribeBoard scribeBoard, ScribeMark winner) {
    Log.v("scribeBoardWon: winner = " + winner);
  }

  @Override
  public void whoseTurnChanged(ScribeBoard scribeBoard, ScribeMark currentPlayer) {
    Log.v("whoseTurnChanged: currentPlayer = " + currentPlayer);
  }

  @Override
  public void miniGridEnabled(MiniGrid miniGrid, boolean enabled) {
    Log.v(String.format("miniGridEnabled(%b): \n" + miniGrid, enabled));
  }

  @Override
  public void miniGridLastMovesChanged(MiniGrid miniGrid, Collection<XY> lastMoves) {
    Log.v("lastMovesChanged (" + lastMoves + ") \n" + miniGrid);
  }

  @Override
  public void miniGridMarked(MiniGrid miniGrid, XY xy, ScribeMark mark) {
    Log.v(String.format("miniGridMarked(%s, %s) \n" + miniGrid, xy, mark));

  }

  @Override
  public void miniGridWon(MiniGrid miniGrid, ScribeMark winner) {
    Log.i("miniGridWon by " + winner + ":\n" + miniGrid);
    Log.i("points: " + miniGrid.points());
  }

}
