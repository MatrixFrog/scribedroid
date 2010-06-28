package tyler.breisacher.scribe.model;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public abstract class Glyphs {
  public static final Map<String, Set<XY>> ALL_GLYPHS = new LinkedHashMap<String, Set<XY>>();
  
  static {
    addSingle();
    addDouble();
    addLine();
    addPipe();
    addSquatT();
    addFourBlock();
    addT();
    addCross();
    addSixBlock();
    addBomber();
    addChair();
    addJ();
    addEarring();
    addHouse();
    addH();
    addU();
    addOttoman();
    addO();
    addNineBlock();
  }
  
  private static void addSingle() {
    Set<XY> single = new HashSet<XY>();
    single.add(new XY(0,0));
    ALL_GLYPHS.put("Single", single);
  }
  private static void addDouble() {
    Set<XY> doubleGlyph = new HashSet<XY>();
    doubleGlyph.add(new XY(0,0));
    doubleGlyph.add(new XY(1,0));
    ALL_GLYPHS.put("Double", doubleGlyph);
  }
  private static void addLine() {
    Set<XY> line = new HashSet<XY>();
    line.add(new XY(0,0));
    line.add(new XY(1,0));
    line.add(new XY(2,0));
    ALL_GLYPHS.put("Line", line);
  }
  private static void addPipe() { 
    Set<XY> pipe = new HashSet<XY>();
    pipe.add(new XY(0,1));
    pipe.add(new XY(1,1));
    pipe.add(new XY(2,1));
    pipe.add(new XY(2,0));
    ALL_GLYPHS.put("Pipe", pipe);
  }
  private static void addSquatT() {
    Set<XY> squatT = new HashSet<XY>();
    squatT.add(new XY(0,0));
    squatT.add(new XY(1,0));
    squatT.add(new XY(2,0));
    squatT.add(new XY(1,1));
    ALL_GLYPHS.put("Squat T", squatT);
  }
  private static void addFourBlock() {
    Set<XY> fourBlock = new HashSet<XY>();
    fourBlock.add(new XY(0,0));
    fourBlock.add(new XY(1,0));
    fourBlock.add(new XY(0,1));
    fourBlock.add(new XY(1,1));
    ALL_GLYPHS.put("Four Block", fourBlock);
  }
  private static void addT() {
    Set<XY> t = new HashSet<XY>();
    t.add(new XY(0,0));
    t.add(new XY(1,0));
    t.add(new XY(1,1));
    t.add(new XY(1,2));
    t.add(new XY(2,0));
    ALL_GLYPHS.put("T", t);
  }
  private static void addCross() {
    Set<XY> cross = new HashSet<XY>();
    cross.add(new XY(1,0));
    cross.add(new XY(1,1));
    cross.add(new XY(1,2));
    cross.add(new XY(0,1));
    cross.add(new XY(2,1));
    ALL_GLYPHS.put("Cross", cross);
  }
  private static void addSixBlock() {
    Set<XY> sixBlock = new HashSet<XY>();
    sixBlock.addAll(XY.allXYs());
    sixBlock.remove(new XY(0,2));
    sixBlock.remove(new XY(1,2));
    sixBlock.remove(new XY(2,2));
    ALL_GLYPHS.put("Six Block", sixBlock);
  }
  private static void addBomber() {
    Set<XY> bomber = new HashSet<XY>();
    bomber.addAll(XY.allXYs());
    bomber.remove(new XY(0,1));
    bomber.remove(new XY(0,2));
    bomber.remove(new XY(1,2));
    ALL_GLYPHS.put("Bomber", bomber);
  }
  private static void addChair() {
    Set<XY> chair = new HashSet<XY>();
    chair.addAll(XY.allXYs());
    chair.remove(new XY(0,0));
    chair.remove(new XY(1,0));
    chair.remove(new XY(1,2));
    ALL_GLYPHS.put("Chair", chair);
  }
  private static void addJ() {
    Set<XY> j = new HashSet<XY>();
    j.addAll(XY.allXYs());
    j.remove(new XY(0,0));
    j.remove(new XY(1,0));
    j.remove(new XY(1,1));
    ALL_GLYPHS.put("J", j);
  }
  private static void addEarring() {
    Set<XY> earring = new HashSet<XY>();
    earring.addAll(XY.allXYs());
    earring.remove(new XY(0,0));
    earring.remove(new XY(1,1));
    ALL_GLYPHS.put("Earring", earring);
  }
  private static void addHouse() {
    Set<XY> house = new HashSet<XY>();
    house.addAll(XY.allXYs());
    house.remove(new XY(0,0));
    house.remove(new XY(2,0));
    ALL_GLYPHS.put("House", house);
  }
  private static void addH() {
    Set<XY> h = new HashSet<XY>();
    h.addAll(XY.allXYs());
    h.remove(new XY(1,0));
    h.remove(new XY(1,2));
    ALL_GLYPHS.put("H", h);
  }
  private static void addU() {
    Set<XY> u = new HashSet<XY>();
    u.addAll(XY.allXYs());
    u.remove(new XY(1,0));
    u.remove(new XY(1,1));
    ALL_GLYPHS.put("U", u);
  }
  private static void addOttoman() {
    Set<XY> ottoman = new HashSet<XY>();
    ottoman.addAll(XY.allXYs());
    ottoman.remove(new XY(1,2));
    ALL_GLYPHS.put("Ottoman", ottoman);
  }
  private static void addO() {
    Set<XY> o = new HashSet<XY>();
    o.addAll(XY.allXYs());
    o.remove(new XY(1,1));
    ALL_GLYPHS.put("O", o);
  }
  private static void addNineBlock() {
    Set<XY> nineBlock = new HashSet<XY>();
    nineBlock.addAll(XY.allXYs());
    ALL_GLYPHS.put("Nine Block", nineBlock);
  }
}
