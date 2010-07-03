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
import android.util.AndroidRuntimeException;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Main extends Activity implements View.OnClickListener, ScribeListener, DialogInterface.OnClickListener {

  private ScribeBoard scribeBoard;

  private CellView turnIndicator;
  private MiniGrid lastClickedMiniGrid;
  private ScribeBoardView scribeBoardView;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    Log.i(Constants.LOG_TAG, "Main.onCreate called");
    super.onCreate(savedInstanceState);

    setContentView(R.layout.portrait);
    Button testingButton = (Button) findViewById(R.id.testingButton);
    turnIndicator = (CellView) findViewById(R.id.whoseTurn);
    scribeBoardView = (ScribeBoardView) findViewById(R.id.scribeBoard);

    startNewGame();
  }

  private void startNewGame() {
    Log.i(Constants.LOG_TAG, "Starting new game");

    scribeBoard = new ScribeBoard();
    scribeBoardView.setScribeBoard(scribeBoard);
    scribeBoard.addListener(this);
  }

  private void alertIllegalMove() { // TODO decide on Toast vs. Dialog for this
    Toast.makeText(this, R.string.msg_illegal_move, Toast.LENGTH_SHORT).show();
    // or:
    //showDialog(Constants.DialogId.ILLEGAL_MOVE);
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

      break;
    }
    return true;
  }

  @Override
  protected void onPrepareDialog(int id, Dialog dialog) {
    super.onPrepareDialog(id, dialog);
    if (id == Constants.DialogId.MINIGRID) {
      if (dialog instanceof MiniGridDialog) {
        ((MiniGridDialog) dialog).setMiniGrid(this.lastClickedMiniGrid);
      }
      else {
        throw new AndroidRuntimeException("id was " + Constants.DialogId.MINIGRID +
            " so dialog should have been of type MiniGridDialog");
      }
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
                 .setPositiveButton(android.R.string.yes, this)
                 .setNegativeButton(android.R.string.no, this)
                 .create();
    case Constants.DialogId.ILLEGAL_MOVE:
      return new AlertDialog.Builder(this).setMessage(R.string.msg_illegal_move).create();
    default:
      return null;
    }
  }

  @Override
  public void scribeBoardWon(ScribeBoard scribeBoard, ScribeMark winner) {
    if (this.scribeBoard == scribeBoard) {
      // showDialog("You won!") or something
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
    if (v instanceof MiniGridView) {
      this.lastClickedMiniGrid = ((MiniGridView) v).getMiniGrid();
      if (this.lastClickedMiniGrid.isEnabled()) {
        this.showDialog(Constants.DialogId.MINIGRID);
      }
      else {
        this.alertIllegalMove();
      }
    }
  }

  @Override
  public void onClick(DialogInterface dialog, int which) {
    switch (which) {
    case Dialog.BUTTON_POSITIVE:
      startNewGame();
      break;
    case Dialog.BUTTON_NEGATIVE:
    default:
      dialog.cancel();
    }
  }
}