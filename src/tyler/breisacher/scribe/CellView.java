package tyler.breisacher.scribe;

import tyler.breisacher.scribe.model.ScribeMark;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.TextView;

public class CellView extends TextView {
  
  private ScribeMark mark;

  public CellView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public void setMark(ScribeMark mark) {
    this.mark = mark;
    setText(mark.toChar() + "");
  }
  
  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    setMeasuredDimension(25, 25);
  }
  
  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    this.setBackgroundColor(Settings.getColorForMark(this.mark));
  }
}
