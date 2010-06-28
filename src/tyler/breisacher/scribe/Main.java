package tyler.breisacher.scribe;

import tyler.breisacher.scribe.model.MiniGrid;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Main extends Activity implements OnClickListener {

  private MiniGrid miniGrid = MiniGrid.fromString("+O+\nOO+\n++O");

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

  }
}