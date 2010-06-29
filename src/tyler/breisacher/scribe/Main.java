package tyler.breisacher.scribe;

import tyler.breisacher.scribe.model.GridPosition;
import tyler.breisacher.scribe.model.MiniGrid;
import tyler.breisacher.scribe.model.ScribeBoard;
import tyler.breisacher.scribe.model.ScribeMark;
import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Main extends Activity implements OnClickListener {

  //private ScribeBoard scribeBoard = ScribeBoard.generateRandomBoard();
  private ScribeBoard scribeBoard = new ScribeBoard();
  private View button;
  private MiniGrid currentMiniGrid;
  
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.main);

    ScribeBoardView scribeBoardView = (ScribeBoardView) findViewById(R.id.scribeBoard);
    button = (Button) findViewById(R.id.button);
    
    scribeBoardView.setScribeBoard(scribeBoard);
    
    button.setOnClickListener(this);
  }

  @Override
  public void onClick(View v) {
    if (v == button) {
      for (GridPosition gp : GridPosition.allPositionsOn(scribeBoard)) {
        if (gp.miniGrid.get(gp.xy) == ScribeMark.EMPTY) {
          ScribeMark mark = ScribeMark.RED;
          gp.miniGrid.set(gp.xy, mark);
          return;
        }
      }
    }
    else if (v instanceof MiniGridView) {
      this.currentMiniGrid = ((MiniGridView) v).getMiniGrid();
      this.showDialog(Constants.MINIGRID_DIALOG);
    }
  }
  
  @Override
  protected Dialog onCreateDialog(int id) {
    switch (id) {
    case Constants.MINIGRID_DIALOG:
      Dialog dialog = new MiniGridDialog(this, this.currentMiniGrid);
      return dialog;
    default:
      return null;
    }
  }
}