package tyler.breisacher.scribe;

import tyler.breisacher.scribe.model.MiniGrid;
import android.app.Activity;
import android.os.Bundle;

public class Main extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.main);
        
        MiniGrid miniGrid = MiniGrid.fromString("+O+\nOO+\n++O");
        
        MiniGridView miniGridView = (MiniGridView) findViewById(R.id.miniGrid);
        miniGridView.setMiniGrid(miniGrid);        
    }
}