package tyler.breisacher.scribe;

import tyler.breisacher.scribe.model.ScribeMark;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.widget.TextView;

public class CellView extends TextView {
  
  private ScribeMark mark;
  private int margin = 3;

  public CellView(Context context) {
    super(context);
  }

  public void setMark(ScribeMark mark) {
    this.mark = mark;
    this.setBackgroundColor(Color.BLACK);
  }
  
  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    setMeasuredDimension(50, 50);
  }
  
  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    Paint paint = new Paint();
    paint.setColor(Settings.getColorForMark(this.mark, this.isEnabled()));
    canvas.drawRoundRect(new RectF(margin, margin, this.getWidth() - margin, this.getHeight() - margin), 10, 10, paint);
    
  }
}
