package com.example.app_integradora;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class User_Register extends AppCompatActivity {


    private Button buttonRegistrarUsuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);
        Button buttonRegistrarUsuario=findViewById(R.id.buttonRegistrarUsuario);
        buttonRegistrarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(User_Register.this,register_empresa.class);
                startActivity(intent);
            }
        });
    }
}