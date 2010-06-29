package tyler.breisacher.scribe;

import tyler.breisacher.scribe.model.MiniGrid;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

public class MiniGridDialog extends Dialog {

  private MiniGrid miniGrid;
  public MiniGridDialog(Context context, MiniGrid miniGrid) {
    super(context);
    this.miniGrid = miniGrid;
  }
  
  @Override
  protected void onCreate(Bundle args) {
    super.onCreate(args);
    this.setMGV();
  }

  public void setMiniGrid(MiniGrid miniGrid) {
    this.miniGrid = miniGrid;
    setMGV();
  }

  private void setMGV() {
    MiniGridView mgv = new MiniGridView(this.getContext(), this.miniGrid);
    this.setContentView(mgv);
  } 
}
