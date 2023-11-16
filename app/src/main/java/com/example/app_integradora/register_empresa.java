package com.example.app_integradora;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class register_empresa extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_empresa);
        Button buttonOmitir=findViewById(R.id.buttonOmitir);
        Button buttonRegistrarEmpresa=findViewById(R.id.buttonRegistrarEmpresa);
        buttonOmitir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(register_empresa.this,pantalla_principal.class);
                startActivity(intent);
            }
        });

        buttonRegistrarEmpresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(register_empresa.this, pantalla_principal.class);
                startActivity(intent);
            }
        });
    }
}