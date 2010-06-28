package tyler.breisacher.scribe;

import tyler.breisacher.scribe.model.MiniGrid;
import android.app.Activity;
import android.os.Bundle;

public class Main extends Activity {

  private MiniGrid miniGrid = MiniGrid.fromString("+O+\nOO+\n++O");

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.main);

    MiniGridView miniGridView = (MiniGridView) findViewById(R.id.miniGrid);
    miniGridView.setMiniGrid(miniGrid);        
  }
}