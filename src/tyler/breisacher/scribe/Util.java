package tyler.breisacher.scribe;

import java.util.Random;

import tyler.breisacher.scribe.model.ScribeMark;

public abstract class Util {
  private static Random random = new Random();
  
  public static ScribeMark getRandomMark() {
    return random.nextBoolean() ? ScribeMark.RED : ScribeMark.BLUE;
  }
}
