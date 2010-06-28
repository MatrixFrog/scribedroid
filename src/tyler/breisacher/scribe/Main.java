package tyler.breisacher.scribe;

import tyler.breisacher.scribe.model.GridPosition;
import tyler.breisacher.scribe.model.ScribeBoard;
import tyler.breisacher.scribe.model.ScribeMark;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Main extends Activity implements OnClickListener {

  //private ScribeBoard scribeBoard = ScribeBoard.generateRandomBoard();
  private ScribeBoard scribeBoard = new ScribeBoard();
  
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.main);

    ScribeBoardView scribeBoardView = (ScribeBoardView) findViewById(R.id.scribeBoard);
    Button button = (Button) findViewById(R.id.button);
    
    scribeBoardView.setScribeBoard(scribeBoard);
    
    button.setOnClickListener(this);
  }

  @Override
  public void onClick(View v) {
    for (GridPosition gp : GridPosition.allPositionsOn(scribeBoard)) {
      if (gp.miniGrid.get(gp.xy) == ScribeMark.EMPTY) {
        ScribeMark mark = ScribeMark.RED;
        scribeBoard.set(gp, mark);
        return;
      }
    }
  }
}