package dam.iesaugustobriga.cuevas_serrano_ignacio_practica1;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ConfigActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText etUsername;
    private Spinner spDifficulty;
    private CheckBox cbSound;

    private Settings settings;
    private String dificultad;
    private boolean sonido;

    private void setSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.dificultad, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spDifficulty.setAdapter(adapter);
        spDifficulty.setSelection(adapter.getPosition(settings.getDifficulty()));
        spDifficulty.setOnItemSelectedListener(this);
    }

    private void setCheckbox() {
        AudioManager am = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);
        cbSound.setChecked(settings.hasSound());
        cbSound.setOnClickListener(v -> {
            sonido = ((CheckBox) v).isChecked();
            am.adjustStreamVolume(AudioManager.STREAM_MUSIC, sonido
                    ? AudioManager.ADJUST_UNMUTE
                    : AudioManager.ADJUST_MUTE, AudioManager.FLAG_SHOW_UI);
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        dificultad = parent.getItemAtPosition(pos).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // pass
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_config);
        setTitle("Matemáticapp");
        settings = new Settings(this);

        etUsername = findViewById(R.id.etUsername);
        etUsername.setText(settings.getUsername());
        spDifficulty = findViewById(R.id.spDifficulty);
        setSpinner();
        cbSound = findViewById(R.id.cbSound);
        setCheckbox();

        Button btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(v -> {

//            SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
//            SharedPreferences.Editor editor = sharedPref.edit();
//            editor.putString("Nombre", etUsername.getText().toString());
//            editor.commit();

            settings.setUsername(etUsername.getText().toString().isEmpty()
                    ? settings.getUsername()
                    : etUsername.getText().toString());
            settings.setDifficulty(dificultad);
            settings.setSound(sonido);
            Toast.makeText(this,"Cambios guardados", Toast.LENGTH_SHORT).show();
        });

        Button btnVolver = findViewById(R.id.btnVolver);
        btnVolver.setOnClickListener(v -> this.finish());
    }

}