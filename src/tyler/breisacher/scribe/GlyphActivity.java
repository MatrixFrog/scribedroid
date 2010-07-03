package tyler.breisacher.scribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import tyler.breisacher.scribe.model.Glyphs;
import tyler.breisacher.scribe.model.XY;
import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.SimpleAdapter;

public class GlyphActivity extends ListActivity implements OnClickListener {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    Log.i(Constants.LOG_TAG, "---");
    super.onCreate(savedInstanceState);

    //View backButton = getLayoutInflater().inflate(R.layout.back_button, this.getListView());
    //this.getListView().addFooterView(backButton);

    List<Map<String, Object>> glyphsList = new ArrayList<Map<String, Object>>();
    for (Entry<String, Set<XY>> e : Glyphs.ALL_GLYPHS.entrySet()) {
      Map<String, Object> map = new HashMap<String, Object>();
      map.put("name", e.getKey());
      map.put("glyph", e.getValue());
      glyphsList.add(map);
    }

    SimpleAdapter a = new SimpleAdapter(this,
                                        glyphsList,
                                        R.layout.glyph_item,
                                        new String[] {"name", "glyph"},
                                        new int[] {R.id.glyph_name, R.id.glyph});
    SimpleAdapter.ViewBinder viewBinder = new SimpleAdapter.ViewBinder() {
      @Override
      public boolean setViewValue(View view, Object data, String textRepresentation) {
        if (view instanceof GlyphView && data instanceof Set<?>) {
          ((GlyphView) view).setGlyph((Set<XY>) data);
          return true;
        }
        return false;
      }
    };
    a.setViewBinder(viewBinder);
    setListAdapter(a);
  }

  @Override
  public void onClick(View v) {
    this.finish();
  }
}
