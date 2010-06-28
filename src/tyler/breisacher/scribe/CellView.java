package tyler.breisacher.scribe;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

public class CellView extends TextView {
  public CellView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    // Keep the view squared. From the TicTacToe sample
    int w = MeasureSpec.getSize(widthMeasureSpec);
    int h = MeasureSpec.getSize(heightMeasureSpec);
    int d = w == 0 ? h : h == 0 ? w : w < h ? w : h;
    setMeasuredDimension(d, d);
  }
  
}
