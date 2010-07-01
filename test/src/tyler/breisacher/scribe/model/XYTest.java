package tyler.breisacher.scribe.model;

import junit.framework.Assert;

import org.junit.Assume;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import tyler.breisacher.scribe.model.XY;

@RunWith(Theories.class)
public class XYTest {

  @DataPoints public static final XY[] allXYs = XY.allXYs().toArray(new XY[] {});
  
  @Theory
  public void cornerNeighbors(XY xy) {
    Assume.assumeTrue((xy.x != 1) && (xy.y != 1));
    Assert.assertEquals(2, xy.neighbors().size());
  }
  
  @Theory
  public void edgeNeighbors(XY xy) {
    Assume.assumeTrue((xy.x == 1) ^ (xy.y == 1));
    Assert.assertEquals(3, xy.neighbors().size());
  }
  
  @Theory
  public void centerNeighbors(XY xy) {
    Assume.assumeTrue((xy.x == 1) && (xy.y == 1));
    Assert.assertEquals(4, xy.neighbors().size());
  }
}
