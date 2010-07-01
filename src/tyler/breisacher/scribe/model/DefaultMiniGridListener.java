package tyler.breisacher.scribe.model;

import java.util.Collection;

public abstract class DefaultMiniGridListener implements MiniGridListener {

  @Override
  public void miniGridEnabled(MiniGrid miniGrid, boolean enabled) {
  }

  @Override
  public void miniGridLastMovesChanged(MiniGrid miniGrid, Collection<XY> lastMoves) {
  }

  @Override
  public void miniGridMarked(MiniGrid miniGrid, XY xy, ScribeMark mark) {
  }
}
