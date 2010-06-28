package tyler.breisacher.scribe;

import tyler.breisacher.scribe.model.ScribeBoard;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Main extends Activity implements OnClickListener {

  private ScribeBoard scribeBoard = ScribeBoard.generateRandomBoard();
  
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.main);

    ScribeBoardView scribeBoardView = (ScribeBoardView) findViewById(R.id.scribeBoard);
    scribeBoardView.setScribeBoard(scribeBoard);
    
    Button button = (Button) findViewById(R.id.button);
    
    button.setOnClickListener(this);
  }

  @Override
  public void onClick(View v) {
    
  }
}