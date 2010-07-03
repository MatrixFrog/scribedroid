package tyler.breisacher.scribe;

import tyler.breisacher.scribe.model.ScribeMark;
import tyler.breisacher.scribe.model.XY;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

public class CellView extends View {

  private ScribeMark mark = ScribeMark.EMPTY;
  private int margin = 3;
  private int size = Constants.MiniGridViewSize.SMALL;
  private XY xy;
  private boolean lastMove;

  {
    this.setBackgroundColor(Color.BLACK);
  }

  public CellView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public CellView(Context context, int size) {
    super(context);
    this.size = size;
  }

  public void setMark(ScribeMark mark) {
    this.mark = mark;
    this.postInvalidate();
  }

  public void setLastMove(boolean lastMove) {
    if (this.lastMove == lastMove) return;

    this.lastMove = lastMove;
    this.postInvalidate();
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    Log.v(Constants.LOG_TAG, "Measuring " + this);
    WindowManager windowManager = (WindowManager) this.getContext().getSystemService(Context.WINDOW_SERVICE);
    int displayWidth = windowManager.getDefaultDisplay().getWidth();
    int displayHeight = windowManager.getDefaultDisplay().getHeight();
    switch (size) {
    case Constants.MiniGridViewSize.LARGE:
      int largeDim = (Math.min(displayWidth, displayHeight)-30) / 3;
      setMeasuredDimension(largeDim, largeDim);
      break;
    case Constants.MiniGridViewSize.SMALL:
    default:
      int smallDim = (Math.min(displayWidth, displayHeight)-10) / 9;
      setMeasuredDimension(smallDim, smallDim);
    }
  }

  @Override
  protected void onDraw(Canvas canvas) {
    Log.v(Constants.LOG_TAG, "Drawing " + this);
    super.onDraw(canvas);
    Paint paint = new Paint();
    paint.setColor(Settings.getColorForMark(this.mark, this.isEnabled()));
    canvas.drawRoundRect(new RectF(margin, margin, this.getWidth() - margin, this.getHeight() - margin), 10, 10, paint);

    if (this.lastMove) {
      paint.setColor(Settings.getLastMoveColorForMark(this.mark, this.isEnabled()));
      canvas.drawCircle(this.getWidth()/2, this.getHeight()/2, this.getWidth()/8, paint);
    }
  }

  public void setXY(XY xy) {
    this.xy = xy;
  }

  public XY getXY() {
    return xy;
  }
}
