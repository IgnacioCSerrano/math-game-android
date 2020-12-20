package dam.iesaugustobriga.cuevas_serrano_ignacio_practica1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    View pantalla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_inicio);
        setTitle("Matemáticapp");

        pantalla = findViewById(R.id.viewInicio);
        pantalla.setOnClickListener(v -> {
            Intent intent = new Intent(this, MenuActivity.class);
            startActivity(intent);
            this.finish();
        });
    }
}