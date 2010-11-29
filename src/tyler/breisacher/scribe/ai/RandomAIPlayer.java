package tyler.breisacher.scribe.ai;

import tyler.breisacher.scribe.AIPlayer;
import tyler.breisacher.scribe.Util;
import tyler.breisacher.scribe.model.GridPosition;
import tyler.breisacher.scribe.model.MiniGrid;
import tyler.breisacher.scribe.model.XY;

/**
 * A very stupid AI, simply to demonstrate the way an AI player could be written.
 */
public class RandomAIPlayer extends AIPlayer {

  @Override
  public GridPosition itsYourTurn() {
    MiniGrid miniGrid = Util.choice(this.board.getEnabledMinigrids());
    XY xy = Util.choice(miniGrid.getEmptyCells());
    return new GridPosition(miniGrid, xy);
  }

}
