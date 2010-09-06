package tyler.breisacher.scribe;

import tyler.breisacher.scribe.model.DefaultMiniGridListener;
import tyler.breisacher.scribe.model.MiniGrid;
import tyler.breisacher.scribe.model.ScribeMark;
import tyler.breisacher.scribe.model.XY;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

public class MiniGridDialog extends Dialog {

  private MiniGrid miniGrid;
  public MiniGridDialog(Context context) {
    super(context);
  }

  @Override
  protected void onCreate(Bundle args) {
    super.onCreate(args);
    this.setCanceledOnTouchOutside(true);
  }

  public void setValues(MiniGrid miniGrid, ScribeMark whoseTurn) {
    this.miniGrid = miniGrid;
    this.setTitle(Util.scribeMarkName(this.getContext(), whoseTurn) + ", " +
                  this.getContext().getString(R.string.make_move));
    this.setup();
  }

  private void setup() {
    MiniGridView mgv = new MiniGridView(this.getContext(), Constants.MiniGridViewSize.LARGE);
    mgv.setMiniGrid(this.miniGrid);
    this.setContentView(mgv);
    this.miniGrid.addMiniGridListener(new DefaultMiniGridListener() {
      @Override
      public void miniGridMarked(MiniGrid miniGrid, XY xy, ScribeMark mark) {
        MiniGridDialog.this.dismiss();
      }
    });
  }
}
