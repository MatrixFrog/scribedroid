package tyler.breisacher.scribe;

import tyler.breisacher.scribe.model.MiniGrid;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.TableLayout;
import android.widget.TableRow;

public class MiniGridView extends TableLayout {
  private MiniGrid miniGrid;

  public MiniGridView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public void setMiniGrid(MiniGrid miniGrid) {
    this.miniGrid = miniGrid;
    this.removeAllViews();
    for (int y=0; y<3; y++) {
      TableRow row = new TableRow(this.getContext());
      this.addView(row);
      for (int x=0; x<3; x++) {
        CellView cell = new CellView(this.getContext());
        cell.setMark(miniGrid.get(x, y));
        row.addView(cell);
      }
    }
  }
}
