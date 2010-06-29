package tyler.breisacher.scribe;

import tyler.breisacher.scribe.model.MiniGrid;
import tyler.breisacher.scribe.model.MiniGridListener;
import tyler.breisacher.scribe.model.ScribeMark;
import tyler.breisacher.scribe.model.XY;
import android.content.Context;
import android.widget.TableLayout;
import android.widget.TableRow;

public class MiniGridView extends TableLayout implements MiniGridListener{
  private MiniGrid miniGrid;

  public MiniGridView(Context context) {
    super(context);
    if (context instanceof Main) {
      this.setOnClickListener((Main) context);
    }
  }

  public MiniGridView(Context context, MiniGrid miniGrid) {
    this(context);
    this.setMiniGrid(miniGrid);
  }
  
  public MiniGrid getMiniGrid() {
    return this.miniGrid;
  }
  
  public void setMiniGrid(MiniGrid miniGrid) {
    this.miniGrid = miniGrid;
    rebuildLayout();
    miniGrid.addChangeListener(this);
  }

  private void rebuildLayout() {
    this.removeAllViews();
    for (int y=0; y<3; y++) {
      TableRow row = new TableRow(this.getContext());
      this.addView(row);
      for (int x=0; x<3; x++) {
        CellView cell = new CellView(this.getContext());
        cell.setMark(this.miniGrid.get(x, y));
        row.addView(cell);
      }
    }
  }

  public CellView get(XY xy) {
    TableRow row = (TableRow) this.getChildAt(xy.y);
    return (CellView) row.getChildAt(xy.x);
  }
  
  @Override
  public void miniGridChanged(MiniGrid miniGrid, XY xy, ScribeMark mark) {
    if (miniGrid == this.miniGrid) {
      this.get(xy).setMark(mark);
    }
    else {
      // TODO error handling
    }
  }

}
