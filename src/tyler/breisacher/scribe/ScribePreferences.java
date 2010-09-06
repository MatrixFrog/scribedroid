package tyler.breisacher.scribe;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class ScribePreferences extends PreferenceActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.addPreferencesFromResource(R.xml.prefs);
  }
}
