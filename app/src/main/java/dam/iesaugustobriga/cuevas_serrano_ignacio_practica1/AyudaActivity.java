package dam.iesaugustobriga.cuevas_serrano_ignacio_practica1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class AyudaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_ayuda);
        setTitle("Matemáticapp");

        Button btnVolver = findViewById(R.id.btnVolver);
        btnVolver.setOnClickListener(v -> this.finish());
    }
}