package tyler.breisacher.scribe.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import tyler.breisacher.scribe.Constants;
import android.util.Log;

public class MiniGrid {
  private final ScribeMark[][] data;
  List<Region> regions = new ArrayList<Region>();
  private final List<MiniGridListener> listeners = new ArrayList<MiniGridListener>();
  private boolean enabled = true;

  private Collection<XY> lastMoves = new HashSet<XY>();
  private ScribeBoard parent;

  private MiniGrid() {
    data = new ScribeMark[3][3];
    for (XY xy : XY.allXYs()) {
      this.set(xy, ScribeMark.EMPTY);
    }
  }

  public MiniGrid(ScribeBoard scribeBoard) {
    this();
    this.parent = scribeBoard;
    this.addMiniGridListener(scribeBoard);
  }

  public void set(int x, int y, ScribeMark mark) {
    set(new XY(x,y), mark);
  }

  public void set(XY xy, ScribeMark mark) {
    if (!this.enabled) throw new ScribeException("This MiniGrid is disabled");
    if (mark != ScribeMark.EMPTY && this.get(xy) != ScribeMark.EMPTY) {
      throw new ScribeException("You cannot over-write a square that has already been claimed.");
    }
    data[xy.y][xy.x] = mark;
    if (mark != ScribeMark.EMPTY) {
      updateRegions(xy, mark);
      notifyListenersOfMark(xy, mark);
    }
  }

  private void updateRegions(XY xy, ScribeMark mark) {
    List<Region> regionsContainingNewSquare = new ArrayList<Region>();

    for (Region region : this.regions) {
      for (XY neighbor : xy.neighbors()) {
        if (this.get(neighbor)==mark && region.contains(neighbor)) {
          region.add(xy);
          regionsContainingNewSquare.add(region);
          break;
        }
      }
    }
    switch (regionsContainingNewSquare.size()) {
    case 0:
      Region newRegion = new Region(xy, mark);
      this.regions.add(newRegion);
      break;
    case 1:
      break;
    default: // merge all the regions into one
      Region mergedRegion = regionsContainingNewSquare.remove(0);
      for (Region region : regionsContainingNewSquare) {
        this.regions.remove(region);
        mergedRegion.addAll(region);
      }
    }
  }

  public ScribeMark get(int x, int y) {
    return get(new XY(x,y));
  }

  public ScribeMark get(XY xy) {
    return data[xy.y][xy.x];
  }

  public boolean isEmpty() {
    for (XY xy : XY.allXYs()) {
      if (this.get(xy) != ScribeMark.EMPTY) return false;
    }
    return true;
  }

  public boolean isFull() {
    for (XY xy : XY.allXYs()) {
      if (this.get(xy) == ScribeMark.EMPTY) return false;
    }
    return true;
  }

  public ScribeMark winner() {
    if (!this.isFull()) return ScribeMark.EMPTY;

    Map<ScribeMark, Integer> points = new EnumMap<ScribeMark, Integer>(ScribeMark.class);
    points.put(ScribeMark.BLUE, 0);
    points.put(ScribeMark.RED, 0);

    for (Region region : this.regions) {
      if (region.isGlyph()) {
        points.put(region.mark, points.get(region.mark)+region.size());
      }
    }
    if (points.get(ScribeMark.BLUE) == points.get(ScribeMark.RED))
      throw new ScribeException("There should never be a tie for a single 3x3 grid.");

    if (points.get(ScribeMark.BLUE) > points.get(ScribeMark.RED))
      return ScribeMark.BLUE;
    else
      return ScribeMark.RED;
  }

  List<Region> getRegions() {
    return Collections.unmodifiableList(regions);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (int y=0; y<3; y++) {
      for (int x=0; x<3; x++) {
        sb.append(data[y][x].toChar());
      }
      if (y < 2) sb.append('\n'); // hacky!
    }
    return sb.toString();
  }

  /**
   * The input to fromString is the same as the output of toString
   */
  static MiniGrid fromString(String string) {
    string = string.replace("\n", "");
    MiniGrid miniGrid = new MiniGrid();
    int i=0;
    for (XY xy : XY.allXYs()) {
      miniGrid.set(xy, ScribeMark.fromChar(string.charAt(i++)));
    }
    return miniGrid;
  }

  public void addMiniGridListener(MiniGridListener listener) {
    listeners.add(listener);
  }

  private void notifyListenersOfMark(XY xy, ScribeMark mark) {
    for (MiniGridListener listener : listeners) {
      listener.miniGridMarked(this, xy, mark);
    }
  }

  private void notifyListenersOfEnabledState() {
    for (MiniGridListener listener : listeners) {
      listener.miniGridEnabled(this, enabled);
    }
  }

  private void notifyListenersOfLastMovesChange() {
    for (MiniGridListener listener : listeners) {
      listener.miniGridLastMovesChanged(this, getLastMoves());
    }
  }

  void setEnabled(boolean enabled) {
    this.enabled = enabled;
    notifyListenersOfEnabledState();
  }

  public boolean isEnabled() {
    return enabled;
  }

  void clearLastMoves() {
    if (!this.lastMoves.isEmpty()) {
      this.lastMoves.clear();
      notifyListenersOfLastMovesChange();
    }
  }

  void addLastMove(XY xy) {
    this.lastMoves.add(xy);
    notifyListenersOfLastMovesChange();
  }

  public Collection<XY> getLastMoves() {
    return Collections.unmodifiableCollection(lastMoves);
  }

  public void tryMove(XY xy) {
    try {
      this.set(xy, parent.whoseTurn());
    }
    catch (ScribeException e) {
      Log.i(Constants.LOG_TAG, "Player attempted an illegal move:");
      Log.i(Constants.LOG_TAG, "    " + e.getMessage());
    }
  }
}
