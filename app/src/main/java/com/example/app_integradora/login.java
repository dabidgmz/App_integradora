package com.example.app_integradora;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.app_integradora.Interactor.LoginInteractor;
import com.example.app_integradora.Retroft.PostUserLogin;
import com.example.app_integradora.Retroft.ResponsePostUserLogin;
import com.example.app_integradora.viewmodel.viewmodelogin;

public class login extends AppCompatActivity {

    private viewmodelogin viewModel;  // Declare viewmodelogin instance

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button signInButton = findViewById(R.id.buttonSignIn);
        Button signUpButton = findViewById(R.id.buttonSignUp);
        EditText correo = findViewById(R.id.editTextEmail);
        EditText contrasena = findViewById(R.id.editTextPassword);

        viewModel = new ViewModelProvider(this).get(viewmodelogin.class);  // Initialize viewmodelogin instance

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = correo.getText().toString();
                String password = contrasena.getText().toString();

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(login.this, "Por favor llene todos los campos", Toast.LENGTH_SHORT).show();
                } else {
                    PostUserLogin user = new PostUserLogin(correo.getText().toString(), contrasena.getText().toString());

                    // Pass the viewmodelogin instance to LoginInteractor
                    LoginInteractor log = new LoginInteractor(login.this, viewModel);
                    log.loginUser(user);
                }
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this, User_Register.class));
            }
        });

        viewModel.getLoginresult().observe(this, new Observer<ResponsePostUserLogin>() {
            @Override
            public void onChanged(ResponsePostUserLogin response) {
                if (response != null) {
                    Toast.makeText(login.this, "¡Bienvenido!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(login.this, principal.class));
                    finish();
                } else {
                    Toast.makeText(login.this, "Login denegado", Toast.LENGTH_SHORT).show();
                }
            }
        });

        viewModel.getError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String error) {
                Toast.makeText(login.this, "Error de conexión: " + error, Toast.LENGTH_SHORT).show();
            }
        });
    }
}