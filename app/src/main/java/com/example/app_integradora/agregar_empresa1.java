package com.example.app_integradora;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.app_integradora.Retroft.PostEmpresa;
import com.example.app_integradora.Retroft.ResponsePostEmpresa;
import com.example.app_integradora.viewmodel.viewmodelempresa;

public class agregar_empresa1 extends AppCompatActivity {
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_empresa1);

        // Inicializar drawerLayout aquí
        drawerLayout = findViewById(R.id.drawer_layout);

        findViewById(R.id.imagMenu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ahora drawerLayout no será nulo
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        EditText nombreEditText = findViewById(R.id.editTextNombre);
        Button agregarempresa = findViewById(R.id.buttonAgregarEmpresa);

        viewmodelempresa viewModel = new ViewModelProvider(this).get(viewmodelempresa.class);



        agregarempresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = nombreEditText.getText().toString();

                if (!nombre.isEmpty()) {
                    viewModel.createEmpresa(new PostEmpresa(nombre));
                } else {
                    Toast.makeText(agregar_empresa1.this, "Please enter a valid name", Toast.LENGTH_SHORT).show();
                }
            }
        });

        viewModel.getEmpresaResult().observe(this, new Observer<ResponsePostEmpresa>() {
            @Override
            public void onChanged(ResponsePostEmpresa response) {
                if (response != null) {
                    Toast.makeText(agregar_empresa1.this, "Empresa registrada exitosamente", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(agregar_empresa1.this, new_menu.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(agregar_empresa1.this, "Error registering empresa", Toast.LENGTH_SHORT).show();
                }
            }
        });

        viewModel.getError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String error) {
                Toast.makeText(agregar_empresa1.this, "Error de conexión: " + error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}