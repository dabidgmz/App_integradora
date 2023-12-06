package com.example.app_integradora;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.app_integradora.Retroft.PostUserRegister;
import com.example.app_integradora.Retroft.ResponsePostUserRegister;
import com.example.app_integradora.viewmodel.viewmodelregistro;

public class User_Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        Button buttonRegistrarUsuario = findViewById(R.id.buttonRegistrarUsuario);
        EditText nombre = findViewById(R.id.editTextNombre);
        EditText apellido = findViewById(R.id.editTextApellido);
        EditText telefono = findViewById(R.id.editTexttelefono);
        EditText correo = findViewById(R.id.editTextCorreo);
        EditText contrasena = findViewById(R.id.editTextContrasena);
        EditText confirmarContrasena = findViewById(R.id.editTextConfirmpassword);
        EditText matricula = findViewById(R.id.editTextmatricula);
        EditText departamento = findViewById(R.id.editTextdepartament);

        viewmodelregistro viewModel = new ViewModelProvider(this).get(viewmodelregistro.class);

        buttonRegistrarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nombre.getText().toString();
                String lastName = apellido.getText().toString();
                String phone = telefono.getText().toString();
                String email = correo.getText().toString();
                String password = contrasena.getText().toString();
                String passwordConfirmation = confirmarContrasena.getText().toString();
                String mat = matricula.getText().toString();
                String depart = departamento.getText().toString();

                if (password.equals(passwordConfirmation)) {
                    PostUserRegister post = new PostUserRegister(name, lastName, phone, email, password, depart, passwordConfirmation);

                    viewModel.registerUser(post);
                } else {
                    Toast.makeText(User_Register.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                }
            }
        });

        viewModel.getRegisterResult().observe(this, new Observer<ResponsePostUserRegister>() {
            @Override
            public void onChanged(ResponsePostUserRegister response) {
                if (response != null ) {
                    Toast.makeText(User_Register.this, response.getMsg(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(User_Register.this, login.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(User_Register.this, "Registro denegado", Toast.LENGTH_SHORT).show();
                }
            }
        });

        viewModel.getError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String error) {
                Toast.makeText(User_Register.this, "Error de conexión: " + error, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
