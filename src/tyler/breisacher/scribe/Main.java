package tyler.breisacher.scribe;

import java.util.Random;

import tyler.breisacher.scribe.model.MiniGrid;
import tyler.breisacher.scribe.model.ScribeMark;
import tyler.breisacher.scribe.model.XY;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Main extends Activity implements OnClickListener {

  private MiniGrid miniGrid = new MiniGrid();

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.main);

    MiniGridView miniGridView = (MiniGridView) findViewById(R.id.miniGrid);
    Button button = (Button) findViewById(R.id.button);
    
    miniGridView.setMiniGrid(miniGrid);
    button.setOnClickListener(this);
  }

  @Override
  public void onClick(View v) {
    for (XY xy : XY.allXYs()) {
      if (miniGrid.get(xy) == ScribeMark.EMPTY) {
        ScribeMark newMark = new Random().nextBoolean() ? ScribeMark.BLUE : ScribeMark.RED;
        miniGrid.set(xy, newMark);
        Toast.makeText(this, "Set " + xy + " to " + newMark, Toast.LENGTH_SHORT).show();
        return;
      }
    }
  }
}