package tyler.breisacher.scribe;

import tyler.breisacher.scribe.model.ScribeMark;
import android.graphics.Color;

public abstract class Settings {

  public static int getEnabledColorForMark(ScribeMark mark) {
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
  
  public static int getDisabledColorForMark(ScribeMark mark) {
    switch (mark) {
    case BLUE:
      return Color.rgb(0, 0, 127);
    case RED:
      return Color.rgb(127, 0, 0);
    case EMPTY:
    default:
      return Color.rgb(127, 127, 127);
    }
  }

  public static int getColorForMark(ScribeMark mark, boolean enabled) {
    return enabled ? getEnabledColorForMark(mark) : getDisabledColorForMark(mark);
  }

  public static int getLastMoveColorForMark(ScribeMark mark, boolean enabled) {
    return Color.BLACK;
  }
  
}
