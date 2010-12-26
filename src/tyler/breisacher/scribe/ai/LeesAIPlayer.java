package tyler.breisacher.scribe.ai;

import java.util.List;
import java.util.Map;

import tyler.breisacher.scribe.AIPlayer;
import tyler.breisacher.scribe.Log;
import tyler.breisacher.scribe.Util;
import tyler.breisacher.scribe.model.GridPosition;
import tyler.breisacher.scribe.model.MiniGrid;
import tyler.breisacher.scribe.model.ScribeMark;
import tyler.breisacher.scribe.model.XY;

/**
 * An experimental AI.
 */
public class LeesAIPlayer extends AIPlayer {

  /**
   * Overall approach:
   * 1. choose a minigrid for this turn, usually there's just one
   * 2. choose a minigrid for next turn
   * 3. choose a cell for this turn, based on either:
   *     a. make more glyph points in this grid
   *     b. go for next turn's minigrid
   */
  @Override
  public GridPosition itsYourTurn() {
    // This particular (first crude attempt) does:
    // 1. if there's more than one, it just picks on at random.
    // 2. skips this step.
    // 3. goes for 3a, nothing for 3b (because we didn't do #2 anyway).
    List<MiniGrid> minisThisTurn = this.board.getEnabledMinigrids();
    MiniGrid miniThisTurn;
    if (minisThisTurn.size() == 1) {
      miniThisTurn = minisThisTurn.get(0);
    } else {
      // pick one at random
      miniThisTurn = Util.choice(minisThisTurn);
    }
    Map<ScribeMark, Integer> points = miniThisTurn.points();
    int myPoints = points.get(this.mark);
    int opponentPoints = points.get(this.mark.other());
    int delta = myPoints - opponentPoints;
    Log.i("itsYourTurn: mg: " + miniThisTurn + ", delta: " + delta);
    List<XY> cellsThisTurn = miniThisTurn.getEmptyCells();
    XY max = null;
    for (XY xy : cellsThisTurn) {
      MiniGrid trial = miniThisTurn.copy();
      trial.set(xy, this.mark);
      Map<ScribeMark, Integer> trialPoints = trial.points();
      int trialDelta = trialPoints.get(this.mark) - trialPoints.get(this.mark.other());
      Log.i("itsYourTurn: xy: " + xy + ", trialPoints: " + trialPoints + ", trialDelta: " + trialDelta + ", delta: " + delta);
      if (trialDelta >= delta) {
        delta = trialDelta;
        max = xy;
      }
    }
    if (max != null) {
      return new GridPosition(miniThisTurn, max);
    }
    return new GridPosition(miniThisTurn, Util.choice(cellsThisTurn));
  }

}
