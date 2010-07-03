package tyler.breisacher.scribe;

import tyler.breisacher.scribe.model.MiniGrid;
import tyler.breisacher.scribe.model.ScribeBoard;
import tyler.breisacher.scribe.model.ScribeListener;
import tyler.breisacher.scribe.model.ScribeMark;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

// TODO rules
// TODO glyphs
// TODO about
// TODO make a WinnerDialog class with a CellView, instead of text, and the option to start a new game
// TODO separate classes for the two types of MiniGridViews?

public class Main extends Activity implements View.OnClickListener, ScribeListener {

  private ScribeBoard scribeBoard;
  private ScribeMark winner;

  private CellView turnIndicator;
  private MiniGrid lastClickedMiniGrid;
  private ScribeBoardView scribeBoardView;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    Log.i(Constants.LOG_TAG, "Main.onCreate called");
    super.onCreate(savedInstanceState);

    setContentView(R.layout.main);
    turnIndicator = (CellView) findViewById(R.id.whoseTurn);
    scribeBoardView = (ScribeBoardView) findViewById(R.id.scribeBoard);

    startNewGame();
  }

  private void startNewGame() {
    Log.i(Constants.LOG_TAG, "Starting new game");
    winner = null;
    scribeBoard = new ScribeBoard();
    scribeBoardView.setScribeBoard(scribeBoard);
    scribeBoard.addListener(this);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.options, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    super.onOptionsItemSelected(item);
    switch(item.getItemId()) {
    case R.id.menuitem_new_game:
      showDialog(Constants.DialogId.NEW_GAME);
      break;
    case R.id.menuitem_settings:

      break;
    case R.id.menuitem_exit:
      showDialog(Constants.DialogId.EXIT);
      break;
    }
    return true;
  }

  @Override
  protected void onPrepareDialog(int id, Dialog dialog) {
    super.onPrepareDialog(id, dialog);
    switch(id) {
    case Constants.DialogId.MINIGRID:
      ((MiniGridDialog) dialog).setMiniGrid(this.lastClickedMiniGrid);
      break;
    case Constants.DialogId.WINNER:
      ((AlertDialog) dialog).setMessage(this.winner + " wins! Play again?");
    }
  }

  @Override
  protected Dialog onCreateDialog(int id) {
    super.onCreateDialog(id);
    switch (id) {
    case Constants.DialogId.MINIGRID:
      MiniGridDialog miniGridDialog = new MiniGridDialog(this);
      miniGridDialog.setMiniGrid(this.lastClickedMiniGrid);
      return miniGridDialog;
    case Constants.DialogId.NEW_GAME:
      return new AlertDialog.Builder(this)
                .setMessage(R.string.msg_confirm_new_game)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                  public void onClick(DialogInterface dialog, int which) {
                    startNewGame();
                  }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                  public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                  }
                })
                .create();
    case Constants.DialogId.ILLEGAL_MOVE:
      return new AlertDialog.Builder(this).setMessage(R.string.msg_illegal_move).create();
    case Constants.DialogId.WINNER:
      return new AlertDialog.Builder(this)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                  public void onClick(DialogInterface dialog, int which) {
                    startNewGame();
                  }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                  public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                  }
                })
                .setMessage(this.winner + " wins! Play again?")
                .create();
    case Constants.DialogId.EXIT:
      return new AlertDialog.Builder(this)
                .setMessage(R.string.msg_confirm_exit)
                .setPositiveButton(R.string.exit_scribe, new DialogInterface.OnClickListener() {
                  public void onClick(DialogInterface dialog, int which) {
                    finish();
                  }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                  public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                  }
                })
                .create();
    default:
      return null;
    }
  }

  @Override
  public void scribeBoardWon(ScribeBoard scribeBoard, ScribeMark winner) {
    if (this.scribeBoard == scribeBoard) {
      this.winner = winner;
      showDialog(Constants.DialogId.WINNER);
    }
  }

  @Override
  public void whoseTurnChanged(ScribeBoard scribeBoard, ScribeMark currentPlayer) {
    if (this.scribeBoard == scribeBoard) {
      this.turnIndicator.setMark(currentPlayer);
    }
  }

  /**
   * This activity serves as the OnClickListener for each of the MiniGridViews
   * it contains. When one of them is clicked, it creates a dialog which shows
   * a view of the same MiniGrid.
   */
  @Override
  public void onClick(View v) {
    Log.v(Constants.LOG_TAG, "onClick(" + v + ") called");
    if (v instanceof MiniGridView && v.isEnabled()) {
      this.lastClickedMiniGrid = ((MiniGridView) v).getMiniGrid();
      this.showDialog(Constants.DialogId.MINIGRID);
    }
  }
}