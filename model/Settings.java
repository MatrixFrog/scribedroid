package tyler.breisacher.scribe.model;

public class Settings {
  
  public enum GameMode {Majority, SuperGlyph};
  public GameMode mode = GameMode.Majority;
  
  private static Settings settings;
  
  public static Settings getInstance() {
    if (settings == null) {
      settings = new Settings();
    }
    return settings;
  }
  
}
