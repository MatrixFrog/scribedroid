package tyler.breisacher.scribe.model;

public interface ScribeListener {

  /**
   * Called automatically whenever the scribeBoard being listened to is filled,
   * and thus one player or the other has won the game.
   * 
   * @param scribeBoard
   * @param winner
   */
  void scribeBoardWon(ScribeBoard scribeBoard, ScribeMark winner);

}
