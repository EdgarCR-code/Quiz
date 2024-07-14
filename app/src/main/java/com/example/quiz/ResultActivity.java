package com.example.quiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

// ResultActivity.java
public class ResultActivity extends AppCompatActivity {

    private TextView puntuacionTextView;
    private TextView mensajeTextView;
    private Button regresarButton;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        puntuacionTextView = findViewById(R.id.tv_puntuacionFinal);
        mensajeTextView = findViewById(R.id.mensaje);
        regresarButton = findViewById(R.id.btn_inicio);

        int puntuacion = getIntent().getIntExtra("puntuacion", 0);
        puntuacionTextView.setText("Tu puntuación es: " + puntuacion);

        String mensaje;
        if (puntuacion >= 8) {
            mensaje = "ChatGPT detected";
        } else if (puntuacion >= 5) {
            mensaje = "Felicidades, tienes cerebro :D";
        } else if (puntuacion == 0) {
            mensaje = "Eres tonto";
        } else {
            mensaje = "Cerebro.exe ha dejado de funcionar";
        }
        mensajeTextView.setText(mensaje);

        sharedPreferences = getSharedPreferences("quiz_prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("ultima_puntuacion", puntuacion);
        editor.apply();

        regresarButton.setOnClickListener(v -> {
            Intent intent = new Intent(ResultActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}

