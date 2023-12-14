package com.example.app_integradora;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

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
            public void onClick(View v) {
                String nombreStr = nombre.getText().toString().trim();
                String apellidoStr = apellido.getText().toString().trim();
                String telefonoStr = telefono.getText().toString().trim();
                String correoStr = correo.getText().toString().trim();
                String contrasenaStr = contrasena.getText().toString().trim();
                String confirmarContrasenaStr = confirmarContrasena.getText().toString().trim();
                String matriculaStr = matricula.getText().toString().trim();
                String departamentoStr = departamento.getText().toString().trim();

                // Validaciones básicas
                if (TextUtils.isEmpty(nombreStr) || nombreStr.length() < 3) {
                    showToast("Nombre inválido, debe tener al menos 3 caracteres");
                    return;
                }

                if (!TextUtils.isDigitsOnly(matriculaStr)) {
                    showToast("Matrícula inválida, debe contener solo números");
                    return;
                }

                if (TextUtils.isEmpty(telefonoStr) || telefonoStr.length() != 10) {
                    showToast("Teléfono inválido, debe contener 10 dígitos");
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(correoStr).matches()) {
                    showToast("Correo electrónico inválido");
                    return;
                }

                if (TextUtils.isEmpty(contrasenaStr) || contrasenaStr.length() < 6) {
                    showToast("Contraseña inválida, debe tener al menos 6 caracteres");
                    return;
                }

                if (!contrasenaStr.equals(confirmarContrasenaStr)) {
                    showToast("Las contraseñas no coinciden");
                    return;
                }

                PostUserRegister user = new PostUserRegister(nombreStr, apellidoStr, correoStr, departamentoStr, telefonoStr, matriculaStr, contrasenaStr, confirmarContrasenaStr);
                viewModel.registerUser(user);
                viewModel.getRegisterResult().observe(User_Register.this, new Observer<ResponsePostUserRegister>() {
                    @Override
                    public void onChanged(ResponsePostUserRegister response) {
                        if (response != null && response.getMsg().equals("Registro correcto")) {
                            showToast("Registro exitoso. Por favor, revise su correo electrónico.");
                            Intent intent = new Intent(User_Register.this, login.class);
                            startActivity(intent);
                            finish();
                        } else {
                            showToast("Error en el registro. Por favor, inténtelo de nuevo.");
                        }
                    }
                });
                viewModel.getError().observe(User_Register.this, new Observer<String>() {
                    @Override
                    public void onChanged(String errorMessage) {
                        showToast("Error: " + errorMessage);
                    }
                });
            }
        });
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}