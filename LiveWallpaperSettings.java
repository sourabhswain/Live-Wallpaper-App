
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;


public class LiveWallpaperSettings extends PreferenceActivity
implements
SharedPreferences.OnSharedPreferenceChangeListener {


@SuppressWarnings("deprecation")
@Override
protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);

getPreferenceManager().setSharedPreferencesName(LiveWallpaper.SHARED_PREFS_NAME);
addPreferencesFromResource(R.xml.livewallpapersettings);

 getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this); 
}

@Override
 protected void onResume() {
  super.onResume();
}


@SuppressWarnings("deprecation")
@Override
protected void onDestroy() {
getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
  super.onDestroy();
}

public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
    String key) {
// TODO Auto-generated method stub

}

}
