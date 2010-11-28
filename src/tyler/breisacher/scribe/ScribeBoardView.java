package tyler.breisacher.scribe;

import static tyler.breisacher.scribe.model.ScribeBoard.GRID_SIZE;
import tyler.breisacher.scribe.model.MiniGrid;
import tyler.breisacher.scribe.model.ScribeBoard;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.TableLayout;
import android.widget.TableRow;

public class ScribeBoardView extends TableLayout {
  private ScribeBoard scribeBoard;

  public ScribeBoardView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public void setScribeBoard(ScribeBoard scribeBoard) {
    this.scribeBoard = scribeBoard;
    rebuildLayout();
  }

  private void rebuildLayout() {
    this.removeAllViews();

    for (int y=0; y<GRID_SIZE; y++) {
      TableRow row = new TableRow(this.getContext());
      this.addView(row);
      for (int x=0; x<GRID_SIZE; x++) {
        MiniGridView mgv = new MiniGridView(this.getContext(), Constants.MiniGridViewSize.SMALL);
        mgv.setOnClickListener((Main) this.getContext());

        MiniGrid miniGrid = this.scribeBoard.get(x, y);
        mgv.setMiniGrid(miniGrid);

        row.addView(mgv);
      }
    }
  }
}
