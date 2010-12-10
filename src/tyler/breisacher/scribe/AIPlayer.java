package tyler.breisacher.scribe;

import tyler.breisacher.scribe.model.GridPosition;
import tyler.breisacher.scribe.model.MiniGrid;
import tyler.breisacher.scribe.model.ScribeBoard;
import tyler.breisacher.scribe.model.ScribeException;
import tyler.breisacher.scribe.model.ScribeMark;
import tyler.breisacher.scribe.model.XY;
import android.os.AsyncTask;

/**
 * The AI that the human player is up against. The AI player is always blue. To
 * create an AI opponent, subclass this class and implement itsYourTurn().
 */
public abstract class AIPlayer {

	/**
	 * Called when a new game is starting.
	 */
	protected void restart(ScribeBoard board, ScribeMark player) {
		this.board = board;
		this.mark = player;
	}

	/**
	 * Called to inform the AI player that it is its turn.
	 */
	protected abstract GridPosition itsYourTurn();

	
	protected ScribeBoard board;
	protected ScribeMark mark;

	private void move(MiniGrid miniGrid, XY xy) {
		if (this.board.whoseTurn() == this.mark) {
			miniGrid.set(xy, this.mark);
		} else {
			throw new ScribeException(
					"The AI player cannot move now because it is not its turn.");
		}
	}

	void move() {
		this.getTask().execute();
	}

	private final AsyncTask<Void, Void, Void> getTask() {
		return new AsyncTask<Void, Void, Void>() {
			GridPosition theMove = null;

			@Override
			protected Void doInBackground(Void... params) {
				this.theMove = AIPlayer.this.itsYourTurn();
				return null;
			}

			@Override
			protected void onPostExecute(Void result) {
				super.onPostExecute(result);
				AIPlayer.this.move(this.theMove.miniGrid, this.theMove.xy);
			}
		};
	}

}