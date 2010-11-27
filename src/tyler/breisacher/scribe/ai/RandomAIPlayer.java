package tyler.breisacher.scribe.ai;

import tyler.breisacher.scribe.AIPlayer;
import tyler.breisacher.scribe.Util;
import tyler.breisacher.scribe.model.MiniGrid;
import tyler.breisacher.scribe.model.XY;
import android.os.AsyncTask;

/**
 * A very stupid AI, simply to demonstrate the way an AI player could be written.
 */
public class RandomAIPlayer extends AIPlayer {
  private MiniGrid miniGrid;
  private XY xy;

  private final AsyncTask<Void, Void, Void> getTask() {
    return new AsyncTask<Void, Void, Void>() {
      @Override
      protected Void doInBackground(Void... params) {
        try {
          Thread.sleep(750);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
        return null;
      }

      @Override
      protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        RandomAIPlayer.this.move(miniGrid, xy);
      }
    };
  }

  @Override
  public void itsYourTurn() {
    miniGrid = Util.choice(board.getEnabledMinigrids());
    xy = Util.choice(miniGrid.getEmptyCells());
    getTask().execute();
  }

}
