package tyler.breisacher.scribe;

import tyler.breisacher.scribe.model.MiniGrid;
import tyler.breisacher.scribe.model.ScribeBoard;
import tyler.breisacher.scribe.model.ScribeListener;
import tyler.breisacher.scribe.model.ScribeMark;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

// TODO useful images in rules.xml
// TODO make a WinnerDialog class with a CellView, instead of text
// TODO separate classes for the two types of MiniGridViews?

public class Main extends Activity implements View.OnClickListener,
    ScribeListener, DialogInterface.OnClickListener {

  private ScribeBoard scribeBoard;
  private ScribeMark winner;

  private MiniGrid lastClickedMiniGrid;
  private ScribeBoardView scribeBoardView;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    Log.i(Constants.LOG_TAG, "Main.onCreate called");
    super.onCreate(savedInstanceState);

    setContentView(R.layout.main);
    scribeBoardView = (ScribeBoardView) findViewById(R.id.scribeBoard);

    startNewGame();
  }

  void startNewGame() {
    Log.i(Constants.LOG_TAG, "Starting new game");
    winner = null;
    scribeBoard = new ScribeBoard();
    scribeBoardView.setScribeBoard(scribeBoard);
    scribeBoard.addListener(this);
    currentPlayerIs(scribeBoard.whoseTurn());
  }

  private void currentPlayerIs(ScribeMark currentPlayer) {
    switch (currentPlayer) {
    case RED:
      ((TextView) findViewById(R.id.player1_text)).setText(R.string.its_your_turn);
      ((TextView) findViewById(R.id.player2_text)).setText(R.string.its_not_your_turn);
      break;
    case BLUE:
      ((TextView) findViewById(R.id.player1_text)).setText(R.string.its_not_your_turn);
      ((TextView) findViewById(R.id.player2_text)).setText(R.string.its_your_turn);
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.options, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    super.onOptionsItemSelected(item);
    switch (item.getItemId()) {
    case R.id.menuitem_glyphs:
      Log.i(Constants.LOG_TAG, "Creating GlyphsActivity");
      startActivity(new Intent(this, GlyphActivity.class));
      Log.i(Constants.LOG_TAG, "Started GlyphsActivity");
      break;
    case R.id.menuitem_rules:
      Log.i(Constants.LOG_TAG, "Creating RulesActivity");
      startActivity(new Intent(this, RulesActivity.class));
      Log.i(Constants.LOG_TAG, "Started RulesActivity");
      break;
    case R.id.menuitem_new_game:
      showDialog(Constants.DialogId.NEW_GAME);
      break;
    case R.id.menuitem_settings:

      break;
    case R.id.menuitem_about:
      showDialog(Constants.DialogId.ABOUT);
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
    switch (id) {
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
    case Constants.DialogId.ABOUT:
      return new AlertDialog.Builder(this)
                .setMessage(R.string.about_dialog_text)
                .setPositiveButton(android.R.string.ok, null)
                .create();
    case Constants.DialogId.MINIGRID:
      MiniGridDialog miniGridDialog = new MiniGridDialog(this);
      miniGridDialog.setMiniGrid(this.lastClickedMiniGrid);
      return miniGridDialog;
    case Constants.DialogId.NEW_GAME:
      return new AlertDialog.Builder(this)
                .setMessage(R.string.msg_confirm_new_game)
                .setPositiveButton(android.R.string.yes, this)
                .setNegativeButton(android.R.string.no, this).create();
    case Constants.DialogId.ILLEGAL_MOVE:
      return new AlertDialog.Builder(this)
          .setMessage(R.string.msg_illegal_move).create();
    case Constants.DialogId.WINNER:
      return new AlertDialog.Builder(this)
                .setMessage(this.winner + " wins! Play again?")
                .setPositiveButton(android.R.string.yes, this)
                .setNegativeButton(android.R.string.no, this).create();
    case Constants.DialogId.EXIT:
      return new AlertDialog.Builder(this)
          .setMessage(R.string.msg_confirm_exit).setPositiveButton(
              R.string.exit_scribe, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                  finish();
                }
              }).setNegativeButton(android.R.string.cancel,
              new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                  dialog.cancel();
                }
              }).create();
    default:
      return null;
    }
  }

  @Override
  public void onBackPressed() {
    showDialog(Constants.DialogId.EXIT);
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
      currentPlayerIs(currentPlayer);
    }
  }

  /**
   * For the "New Game" and "Winner" dialogs.
   */
  @Override
  public void onClick(DialogInterface dialog, int which) {
    switch (which) {
    case DialogInterface.BUTTON_POSITIVE:
      startNewGame();
      break;
    case DialogInterface.BUTTON_NEGATIVE:
    default:
      dialog.cancel();
    }
  }

  /**
   * This activity serves as the OnClickListener for each of the MiniGridViews
   * it contains. When one of them is clicked, it creates a dialog which shows a
   * view of the same MiniGrid.
   */
  @Override
  public void onClick(View v) {
    if (v instanceof MiniGridView && v.isEnabled()) {
      this.lastClickedMiniGrid = ((MiniGridView) v).getMiniGrid();
      this.showDialog(Constants.DialogId.MINIGRID);
    }
  }
}