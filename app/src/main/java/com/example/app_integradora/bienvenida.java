package com.example.app_integradora;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.app_integradora.Menu;

public class bienvenida extends AppCompatActivity {

    private Button bienvenido;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenida);
        bienvenido = findViewById(R.id.bienvenido);
        bienvenido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Intent bienv = new Intent(this, Menu.class);

            }
        });
    }
}