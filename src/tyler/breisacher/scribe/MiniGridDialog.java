package tyler.breisacher.scribe;

import tyler.breisacher.scribe.model.MiniGrid;
import tyler.breisacher.scribe.model.MiniGridListener;
import tyler.breisacher.scribe.model.ScribeMark;
import tyler.breisacher.scribe.model.XY;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

public class MiniGridDialog extends Dialog implements MiniGridListener {

  private MiniGrid miniGrid;

  public MiniGridDialog(Context context) {
    super(context);
  }

  @Override
  protected void onCreate(Bundle args) {
    super.onCreate(args);
    if (this.miniGrid == null) {
      throw new IllegalStateException("MiniGrid should have been set prior to onCreate being called.");
    }
    super.onCreate(args);
    this.setTitle("Choose a square");
    this.setup();
  }

  public void setMiniGrid(MiniGrid miniGrid) {
    this.miniGrid = miniGrid;
    this.setup();
  }
  
  private void setup() {
    MiniGridView mgv = new MiniGridView(this.getContext(), Constants.MiniGridViewSize.LARGE);
    mgv.setMiniGrid(this.miniGrid);
    this.setContentView(mgv);
    this.miniGrid.addChangeListener(this);
  }

  @Override
  public void miniGridMarked(MiniGrid miniGrid, XY xy, ScribeMark mark) {
    this.dismiss();
  }

  @Override
  public void miniGridEnabled(MiniGrid miniGrid, boolean enabled) {
    // No action
  }
}
