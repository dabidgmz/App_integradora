package com.example.app_integradora;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                Toast.makeText(MainActivity.this, "Cargando: " + millisUntilFinished / 1000, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(MainActivity.this,inicio.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this, "iniciando", Toast.LENGTH_SHORT).show();
            }
        }.start();
    }
}