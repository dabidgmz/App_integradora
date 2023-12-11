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

        new CountDownTimer(300, 100) {
            @Override
            public void onTick(long millisUntilFinished) {
                Toast.makeText(MainActivity.this, "Cargando: " + millisUntilFinished / 1000, Toast.LENGTH_SHORT).show();
            }

            @Override             //modificado para mandarlo directo a la principal
            public void onFinish() {
                Intent intent = new Intent(MainActivity.this, login.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this, "iniciando", Toast.LENGTH_SHORT).show();
            }
        }.start();
    }
}