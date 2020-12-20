package dam.iesaugustobriga.cuevas_serrano_ignacio_practica1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    private Button btnJugar, btnMarcadores, btnSalir;
    private Intent intent;

    private AlertDialog confirmarSalida()
    {
        return new AlertDialog.Builder(this)
                .setTitle("Salir")
                .setMessage("¿Está seguro de que desea salir?")
                .setIcon(R.drawable.ic_baseline_exit_to_app_24)

                .setPositiveButton("Sí", (dialog, whichButton) -> finishAndRemoveTask())

                .setNegativeButton("No", (dialog, which) -> dialog.dismiss())
                .create();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_menu);
        setTitle("Matemáticapp");

        btnJugar = findViewById(R.id.btnJugar);
        btnJugar.setOnClickListener(v -> {
            Intent intent = new Intent(this, GameActivity.class);
            startActivity(intent);
        });

        btnMarcadores = findViewById(R.id.btnMarcador);
        btnMarcadores.setOnClickListener(v -> {
            Intent intent = new Intent(this, ScoreboardActivity.class);
            startActivity(intent);
        });

        btnSalir = findViewById(R.id.btnSalir);
        btnSalir.setOnClickListener(v -> {
            AlertDialog diaBox = confirmarSalida();
            diaBox.show();
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.game_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.conf:
                mostrarPantalla(ConfigActivity.class);
                return true;
            case R.id.ayuda:
                mostrarPantalla(AyudaActivity.class);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void mostrarPantalla(Class clase) {
        intent = new Intent(this, clase);
        startActivity(intent);
    }

}