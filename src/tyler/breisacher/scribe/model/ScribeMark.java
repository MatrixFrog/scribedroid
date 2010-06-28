package tyler.breisacher.scribe.model;


public enum ScribeMark {
	BLUE('+'), RED('O'), EMPTY('-');

	ScribeMark(char ch) {
		this.ch = ch;
	}
	
	private final char ch;
	
	public char toChar() {
		return ch;
	}

  public static ScribeMark fromChar(char ch) {
    if (ch == BLUE.ch) return BLUE;
    if (ch == RED.ch) return RED;
    return EMPTY;
  }

  /**
   * The "other" player
   */
  public ScribeMark other() {
    switch (this) {
    case RED:
      return BLUE;
    case BLUE:
      return RED;
    case EMPTY:
    default:
      return EMPTY;
    }
  }
}
