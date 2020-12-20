package dam.iesaugustobriga.cuevas_serrano_ignacio_practica1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import dam.iesaugustobriga.cuevas_serrano_ignacio_practica1.db.entity.UserEntity;
import dam.iesaugustobriga.cuevas_serrano_ignacio_practica1.viewmodel.UserViewModel;

public class GameActivity extends AppCompatActivity {

    private TextView tvOperand1, tvOperand2, tvOperator, tvCountdown, tvPuntos;
    private EditText etResult;
    private Button btnComprobar;
    private UserViewModel mViewModel;

    private Settings settings;
    private int puntuacion = 0;
    private SimpleDateFormat formFecha = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    private SimpleDateFormat formHora = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
    private Random aleatorio = new Random();
    private String operators = "+-×÷";
    private int d;
    private CountDownTimer timer;
    private long milisecondsLeft;
    private final int TIME = 30000;

//    private int GetRandomInt(int min, int max)
//    {
//        return aleatorio.nextInt(max + 1 - min) + min;
//    }

    private String getOperator()
    {
//        int random = aleatorio.nextInt(operators.length());
        int random = aleatorio.nextInt(d);
        return Character.toString(operators.charAt(random));
    }

    private void generarOperacion() {
        tvOperand1.setText(String.format(Locale.getDefault(), "%d", aleatorio.nextInt(9) + 1));
        tvOperand2.setText(String.format(Locale.getDefault(), "%d", aleatorio.nextInt(9) + 1));
        tvOperator.setText(getOperator());
    }

    private int getResultado() {
        int resultado = 0;
        int o1 = Integer.parseInt(tvOperand1.getText().toString());
        int o2 = Integer.parseInt(tvOperand2.getText().toString());

        switch (operators.indexOf(tvOperator.getText().charAt(0)))
        {
            case 0:
                resultado = o1 + o2;
                break;
            case 1:
                resultado = o1 - o2;
                break;
            case 2:
                resultado = o1 * o2;
                break;
            case 3:
                resultado = o1 / o2;
                break;
        }
        return resultado;
    }

    private void reset() {
        puntuacion = 0;
        tvPuntos.setText("0");
        timer.start();
        generarOperacion();
    }

    private void insertUserData() {
        Date date = new Date();
        mViewModel.insertUser(new UserEntity(settings.getUsername(), puntuacion, settings.getDifficulty(), formFecha.format(date), formHora.format(date)));
    }

    private void showDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.layout_dialog);

        Button btnJugar = dialog.findViewById(R.id.btnJugar);
        btnJugar.setOnClickListener(v -> {
            reset();
            dialog.dismiss();
        });

        Button btnSalir = dialog.findViewById(R.id.btnSalir);
        btnSalir.setOnClickListener(v -> this.finish());

        dialog.show();
    }

    private void startTimer(long timeLeft) {
        timer = new CountDownTimer(timeLeft, 1000) {
            public void onTick(long millisUntilFinished) {
                milisecondsLeft = millisUntilFinished;
                tvCountdown.setText(String.format(Locale.getDefault(), "%d", (millisUntilFinished + 1000) / 1000));
            }
            public void onFinish() {
                tvCountdown.setText("0");
                insertUserData();
                showDialog();
            }
        };
        timer.start();
    }

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_game);
        setTitle("Matemáticapp");

        String[] dificultades = getResources().getStringArray(R.array.dificultad);

        settings = new Settings(this);
        d = Arrays.asList(dificultades).indexOf(settings.getDifficulty()) + 2;
        mViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        tvOperand1 = findViewById(R.id.tvOperand1);
        tvOperand2 = findViewById(R.id.tvOperand2);
        tvOperator = findViewById(R.id.tvOperator);
        tvCountdown = findViewById(R.id.tvCountdown);
        tvPuntos = findViewById(R.id.tvPuntos);
        etResult = findViewById(R.id.etResult);
        etResult.setGravity(Gravity.CENTER);

        btnComprobar = findViewById(R.id.btnComprobar);
        btnComprobar.setOnClickListener(v -> {
            if (!etResult.getText().toString().isEmpty()) {
                int respuesta = Integer.parseInt(etResult.getText().toString());
                puntuacion = (respuesta == getResultado())
                        ? puntuacion + 1
                        : (puntuacion > 0)
                            ? puntuacion - 1
                            : 0;
                tvPuntos.setText(String.format("%d", puntuacion));
                generarOperacion();
                etResult.getText().clear();
            }
        });

//        Chronometer simpleChronometer = findViewById(R.id.chrono);
//        simpleChronometer.start();

        milisecondsLeft = TIME;
        startTimer(milisecondsLeft);

        generarOperacion();
    }

    @Override
    public void onBackPressed() {
        timer.cancel();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("¿Está seguro de que detener la partida?");

//        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                timer.cancel();
//                finish();
//            }
//        });
        builder.setPositiveButton("Sí", (dialog, which) -> finish());

//        builder.setNegativeButton("No",new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.cancel();
//            }
//        });
        builder.setNegativeButton("No", (dialog, which) -> {
            dialog.dismiss();
            startTimer(milisecondsLeft);
        });

        AlertDialog alert=builder.create();
        alert.show();
    }
}