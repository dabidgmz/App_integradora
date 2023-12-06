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

import com.example.app_integradora.Retroft.PostEmpresa;
import com.example.app_integradora.Retroft.ResponsePostEmpresa;
import com.example.app_integradora.viewmodel.viewmodelempresa;

public class register_empresa extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_empresa);

        EditText nombreEditText = findViewById(R.id.editTextNombreEmpresa);
        Button buttonOmitir = findViewById(R.id.buttonOmitir);
        Button buttonRegistrarEmpresa = findViewById(R.id.buttonRegistrarEmpresa);

        viewmodelempresa viewModel = new ViewModelProvider(this).get(viewmodelempresa.class);

        buttonOmitir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(register_empresa.this, login.class);
                startActivity(intent);
            }
        });

        buttonRegistrarEmpresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = nombreEditText.getText().toString();

                if (!nombre.isEmpty()) {
                    viewModel.createEmpresa(new PostEmpresa(nombre));
                } else {
                    Toast.makeText(register_empresa.this, "Please enter a valid name", Toast.LENGTH_SHORT).show();
                }
            }
        });

        viewModel.getEmpresaResult().observe(this, new Observer<ResponsePostEmpresa>() {
            @Override
            public void onChanged(ResponsePostEmpresa response) {
                if (response != null) {
                    Toast.makeText(register_empresa.this, "Empresa registrada exitosamente", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(register_empresa.this, login.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(register_empresa.this, "Error registering empresa", Toast.LENGTH_SHORT).show();
                }
            }
        });

        viewModel.getError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String error) {
                Toast.makeText(register_empresa.this, "Error de conexión: " + error, Toast.LENGTH_SHORT).show();
            }
        });
    }
}

