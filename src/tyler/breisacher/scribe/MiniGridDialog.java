package tyler.breisacher.scribe;

import tyler.breisacher.scribe.model.MiniGrid;
import android.app.Dialog;
import android.content.Context;

public class MiniGridDialog extends Dialog {

  public MiniGridDialog(Context context, MiniGrid miniGrid) {
    super(context);
    MiniGridView mgv = new MiniGridView(context, miniGrid);
    this.setContentView(mgv);
  }

}
