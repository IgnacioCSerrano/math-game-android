package dam.iesaugustobriga.cuevas_serrano_ignacio_practica1;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;

import androidx.appcompat.app.AppCompatActivity;

// https://drone-ah.com/2010/04/25/android-managing-global-configuratio/

public class Settings {

    private static final String USERNAME_KEY = "nombre";
    private static final String DIFFICULTY_KEY = "dificultad";
    private static final String SOUND_KEY = "sonido";

    private static final String USERNAME_DEFAULT = "Usuario";
//    private static final String DIFFICULTY_DEFAULT = "Medio";
    private static String difficulty_default;
    private static final boolean SOUND_DEFAULT = true;

    private final SharedPreferences settings;

    /**
     * @param act The context from which to pick SharedPreferences
     */
    public Settings (Context act) {
        settings = act.getSharedPreferences("file", Context.MODE_PRIVATE);
//        difficulty_default = act.getString(R.string.difficulty_default);
        difficulty_default = act.getResources().getStringArray(R.array.dificultad)[0];
    }

    /**
     * Set the username in the preferences.
     *
     * @param username the username to save into prefs
     */
    public void setUsername(String username) {
        Editor editor = settings.edit();
        editor.putString(USERNAME_KEY, username);
        editor.commit();
    }

    /**
     * @return the username from the prefs
     */
    public String getUsername() {
        return settings.getString(USERNAME_KEY, USERNAME_DEFAULT);
    }

    /**
     * Set the difficulty in the preferences.
     *
     * @param difficulty the difficulty to save into prefs
     */
    public void setDifficulty(String difficulty) {
        Editor editor = settings.edit();
        editor.putString(DIFFICULTY_KEY, difficulty);
        editor.commit();
    }

    /**
     * @return the difficulty from the prefs
     */
    public String getDifficulty() {
        return settings.getString(DIFFICULTY_KEY, difficulty_default);
    }

    /**
     * Set the sound state in the preferences.
     *
     * @param sound the sound state to save into prefs
     */
    public void setSound(boolean sound) {
        Editor editor = settings.edit();
        editor.putBoolean(SOUND_KEY, sound);
        editor.commit();
    }

    /**
     * @return the sound state from the prefs
     */
    public boolean hasSound() {
        return settings.getBoolean(SOUND_KEY, SOUND_DEFAULT);
    }

    /*
        Check if there are any stored settings. Can be used to automatically
        load the settings page where necessary
    */
    public boolean hasSettings() {
        // We just check if a username has been set
        return (!settings.getString(USERNAME_KEY, "").equals(""));
    }

}
