package tyler.breisacher.scribe;

import tyler.breisacher.scribe.model.MiniGrid;
import tyler.breisacher.scribe.model.ScribeBoard;
import tyler.breisacher.scribe.model.ScribeException;
import tyler.breisacher.scribe.model.ScribeMark;
import tyler.breisacher.scribe.model.XY;

/**
 * The AI that the human player is up against. The AI player is always blue. To
 * create an AI opponent, subclass this class.
 */
public abstract class AIPlayer {

  private final static ScribeMark mark = ScribeMark.BLUE;
  protected ScribeBoard board;

  /**
   * Called when a new game is starting.
   */
  public void restart(ScribeBoard board) {
    this.board = board;
  }

  protected void move(MiniGrid miniGrid, int x, int y) {
    move(miniGrid, XY.at(x, y));
  }

  protected void move(MiniGrid miniGrid, XY xy) {
    if (board.whoseTurn() == mark) {
      miniGrid.set(xy, mark);
    }
    else {
      throw new ScribeException("The AI player cannot move now because it is not its turn.");
    }
  }

  /**
   * Called to inform the AI player that it is its turn.
   */
  public abstract void itsYourTurn();
}