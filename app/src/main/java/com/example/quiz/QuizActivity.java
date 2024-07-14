package com.example.quiz;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
// QuizActivity.java
public class QuizActivity extends AppCompatActivity {

    private TextView preguntasText;
    private RadioGroup gruposDeRespuestas;
    private Button botonSiguiente;
    private int indicePregunta = 0;
    private int puntuacion = 0;

    private String[] preguntas = {
            "¿Cuál es el número que sigue en la serie: 2, 4, 6, 8, ...?",
            "Si un tren viaja a 60 km/h, ¿cuánto tiempo tardará en recorrer 120 km?",
            "¿Qué número es el siguiente en la serie: 1, 1, 2, 3, 5, 8, ...?",
            "Si Ana es mayor que Beto y Beto es mayor que Carla, ¿quién es el menor?",
            "¿Cuál de los siguientes números es impar?",
            "Si hoy es miércoles, ¿qué día será en 3 días?",
            "¿Cuántos lados tiene un hexágono?",
            "En un reloj analógico, ¿cuántos grados hay en el ángulo entre las manecillas cuando marca las 3 en punto?",
            "Si un auto viaja a 80 km/h, ¿cuántos kilómetros recorrerá en 2.5 horas?",
            "Si tienes tres manzanas y le das una a tu amigo, ¿cuántas te quedan?"
    };

    private String[][] respuestas = {
            {"10", "11", "12", "13"},
            {"1 hora", "1.5 horas", "2 horas", "2.5 horas"},
            {"10", "11", "12", "13"},
            {"Ana", "Beto", "Carla", "No se puede determinar"},
            {"4", "8", "15", "22"},
            {"Jueves", "Viernes", "Sábado", "Domingo"},
            {"4", "5", "6", "7"},
            {"45 grados", "90 grados", "120 grados", "180 grados"},
            {"150 km", "180 km", "200 km", "220 km"},
            {"Ninguna", "Una", "Dos", "Tres"}

    };

    private int[] respuestasCorrectas = {0, 2, 3, 2, 2, 2, 2, 1, 2, 2};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        preguntasText = findViewById(R.id.tv_preguntas);
        gruposDeRespuestas = findViewById(R.id.respuestas);
        botonSiguiente = findViewById(R.id.btn_siguiente);

        generarRespuestas();

        gruposDeRespuestas.setOnCheckedChangeListener((group, checkedId) -> botonSiguiente.setEnabled(true));

        botonSiguiente.setOnClickListener(v -> {
            int preguntaSeleccionada = gruposDeRespuestas.indexOfChild(findViewById(gruposDeRespuestas.getCheckedRadioButtonId()));
            if (preguntaSeleccionada == respuestasCorrectas[indicePregunta]) {
                puntuacion++;
            }
            indicePregunta++;
            if (indicePregunta < preguntas.length) {
                generarRespuestas();
            } else {
                Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
                intent.putExtra("puntuacion", puntuacion);
                startActivity(intent);
                finish();
            }
        });
    }

    private void generarRespuestas() {
        preguntasText.setText(preguntas[indicePregunta]);
        gruposDeRespuestas.removeAllViews();
        for (String respuesta : respuestas[indicePregunta]) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(respuesta);
            radioButton.setTextColor(Color.WHITE);
            radioButton.setTextSize(25);
            radioButton.setPadding(0, 50, 0, 15);
            gruposDeRespuestas.addView(radioButton);
        }
        botonSiguiente.setEnabled(false);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}
