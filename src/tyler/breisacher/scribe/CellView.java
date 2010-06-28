package tyler.breisacher.scribe;

import tyler.breisacher.scribe.model.ScribeMark;
import android.content.Context;
import android.graphics.Canvas;
import android.widget.TextView;

public class CellView extends TextView {
  
  private ScribeMark mark;

  public CellView(Context context) {
    super(context);
  }

  public void setMark(ScribeMark mark) {
    this.mark = mark;
    setText(mark.toChar() + "");
  }
  
  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    setMeasuredDimension(50, 50);
  }
  
  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    this.setBackgroundColor(Settings.getColorForMark(this.mark));
  }
}
