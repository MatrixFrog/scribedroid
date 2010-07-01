package tyler.breisacher.scribe;

import tyler.breisacher.scribe.model.MiniGrid;
import tyler.breisacher.scribe.model.ScribeBoard;
import tyler.breisacher.scribe.model.ScribeListener;
import tyler.breisacher.scribe.model.ScribeMark;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.AndroidRuntimeException;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Main extends Activity implements OnClickListener, ScribeListener {

  //private ScribeBoard scribeBoard = ScribeBoard.generateRandomBoard();
  private ScribeBoard scribeBoard = new ScribeBoard();
  private CellView turnIndicator;
  private MiniGrid lastClickedMiniGrid;
  
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    
    setContentView(R.layout.portrait);

    ScribeBoardView scribeBoardView = (ScribeBoardView) findViewById(R.id.scribeBoard);
    turnIndicator = (CellView) findViewById(R.id.whoseTurn);
    Button testingButton = (Button) findViewById(R.id.testingButton);
    
    scribeBoardView.setScribeBoard(scribeBoard);
    scribeBoard.addListener(this);
    
    testingButton.setOnClickListener(new OnClickListener() {
      
      @Override
      public void onClick(View v) {
        Log.e("scribedroid", "Hey! You clicked that button!");
      }
    });
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
  
  private void alertIllegalMove() { // TODO decide on Toast vs. Dialog for this
    Toast.makeText(this, R.string.illegalMove, Toast.LENGTH_SHORT).show();
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
    case Constants.DialogId.ILLEGAL_MOVE:
      return new AlertDialog.Builder(this).setMessage(R.string.illegalMove).create();
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
}