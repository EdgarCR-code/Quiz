package com.example.quiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

// MainActivity.java
public class MainActivity extends AppCompatActivity {

    private TextView ultimaPuntuacionTextView;
    private Button inciarQuizButton;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ultimaPuntuacionTextView = findViewById(R.id.tv_ultima_puntuacion);
        inciarQuizButton = findViewById(R.id.btn_iniciarQuiz);

        sharedPreferences = getSharedPreferences("quiz_prefs", MODE_PRIVATE);
        int ultimaPuntuacion = sharedPreferences.getInt("ultima_puntuacion", 0);
        ultimaPuntuacionTextView.setText("Última puntuación: " + ultimaPuntuacion);

        inciarQuizButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, QuizActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}
