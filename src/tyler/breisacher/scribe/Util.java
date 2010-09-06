package tyler.breisacher.scribe;

import java.util.Random;

import tyler.breisacher.scribe.model.ScribeMark;
import android.content.Context;

public abstract class Util {
  private static Random random = new Random();

  public static ScribeMark getRandomMark() {
    return random.nextBoolean() ? ScribeMark.RED : ScribeMark.BLUE;
  }

  public static String scribeMarkName(Context context, ScribeMark mark) {
  	switch (mark) {
  	case RED:
  		return context.getString(R.string.red);
  	case BLUE:
  		return context.getString(R.string.blue);
    default:
    	return context.getString(R.string.empty);
  	}

  }
}
