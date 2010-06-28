package tyler.breisacher.scribe;

import tyler.breisacher.scribe.model.ScribeMark;
import android.graphics.Color;

public abstract class Settings {

  public static int getColorForMark(ScribeMark mark) {
    switch (mark) {
    case BLUE:
      return Color.BLUE;
    case RED:
      return Color.RED;
    case EMPTY:
    default:
      return Color.WHITE;
    }
  }
  
}
