package tyler.breisacher.scribe;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class GlyphsViewer extends Activity implements OnClickListener {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    Log.i(Constants.LOG_TAG, "GlyphsViewer.onCreate() called");
    super.onCreate(savedInstanceState);

    setContentView(R.layout.glyphs);
    Button backButton = (Button) findViewById(R.id.button_back_to_game);
    backButton.setOnClickListener(this);
  }

  @Override
  public void onClick(View v) {
    this.finish();
  }
}
