package com.example.app_integradora;

import static com.example.app_integradora.R.id.btnverifique;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class verificar_token extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verificar_token);
        Button btnverificar = findViewById(R.id.btnverifique);
        btnverificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(verificar_token.this, Menu.class);
                startActivity(intent);
            }
        });
    }
}