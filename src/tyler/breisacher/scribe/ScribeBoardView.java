package tyler.breisacher.scribe;

import tyler.breisacher.scribe.model.MiniGrid;
import tyler.breisacher.scribe.model.ScribeBoard;
import tyler.breisacher.scribe.model.ScribeListener;
import tyler.breisacher.scribe.model.ScribeMark;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.TableLayout;
import android.widget.TableRow;

public class ScribeBoardView extends TableLayout implements ScribeListener {
  private ScribeBoard scribeBoard;
  
  public ScribeBoardView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }
  
  public void setScribeBoard(ScribeBoard scribeBoard) {
    this.scribeBoard = scribeBoard;
    scribeBoard.addListener(this);
    rebuildLayout();
  }
  
  private void rebuildLayout() {
    this.removeAllViews();
    
    for (int y=0; y<3; y++) {
      TableRow row = new TableRow(this.getContext());
      this.addView(row);
      for (int x=0; x<3; x++) {
        MiniGridView mgv = new MiniGridView(this.getContext(), Constants.MiniGridViewSize.SMALL);
        
        MiniGrid miniGrid = this.scribeBoard.get(x, y);
        mgv.setMiniGrid(miniGrid);
        miniGrid.addChangeListener(mgv);
        
        row.addView(mgv);
      }
    }
  }

  @Override
  public void scribeBoardWon(ScribeBoard scribeBoard, ScribeMark winner) {
    if (this.scribeBoard == scribeBoard) {
      // showDialog("You won!") or something
    }
  }
}
