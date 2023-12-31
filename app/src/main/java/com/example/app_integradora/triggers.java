package com.example.app_integradora;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ToggleButton;

import com.example.app_integradora.Modelos.Modeltriggers;
import com.example.app_integradora.viewmodel.viewmodeltriggers;
import com.google.android.material.snackbar.Snackbar;

public class triggers extends AppCompatActivity {
    private viewmodeltriggers viewModel;

    MutableLiveData<Boolean> resultado = new MutableLiveData<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_triggers);
        final DrawerLayout drawerLayout = findViewById(R.id.mennu);
        Button buttontriggers = findViewById(R.id.trigger);
        Button buttonsensors = findViewById(R.id.Sensor);
        viewModel = new ViewModelProvider(this).get(viewmodeltriggers.class);

        ToggleButton toggleButtonVentilador = findViewById(R.id.toggleButtonVentilador);
        ToggleButton toggleButtonCerradura = findViewById(R.id.toggleButtonCerradura);

        viewModel.obtenerEstadoInicialVentilador().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean estadoInicial) {
                if(estadoInicial != null)
                { Snackbar.make(findViewById(android.R.id.content), "llegaron los datos"+estadoInicial, Snackbar.LENGTH_LONG).show();

                    actualizarEstadoToggleButton(toggleButtonVentilador, estadoInicial ? "ON" : "OFF");
                }
                else
                {
                    toggleButtonVentilador.setChecked(false);
                }
                toggleButtonVentilador.setChecked(estadoInicial);
            }
        });


        viewModel.obtenerDatosDelVentilador().observe(this, new Observer<Modeltriggers>() {
            @Override
            public void onChanged(Modeltriggers model) {
                if (model != null) {
                    actualizarEstadoToggleButton(toggleButtonVentilador, model.getValue());
                }
            }
        });

        // Obtener el valor actual de la cerradura al iniciar la actividad
        viewModel.obtenerDatosDeCerradura().observe(this, new Observer<Modeltriggers>() {
            @Override
            public void onChanged(Modeltriggers model) {
                if (model != null) {
                    actualizarEstadoToggleButton(toggleButtonCerradura, model.getValue());
                }
            }
        });



        toggleButtonVentilador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isChecked = toggleButtonVentilador.isChecked();

                if (isChecked) {
                    viewModel.enviarComandoVentilador("ON");
                } else {
                    viewModel.enviarComandoVentilador("OFF");
                }
            }
        });
        toggleButtonCerradura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isChecked = toggleButtonCerradura.isChecked();

                if (isChecked) {
                    viewModel.enviarComandoCerradura("ON");
                } else {
                    viewModel.enviarComandoCerradura("OFF");
                }
            }
        });

        buttontriggers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(triggers.this, triggers.class);
                startActivity(intent);
            }
        });

        buttonsensors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(triggers.this,principal.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.imagMenu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        viewModel.resultado.observe(this, new Observer<Boolean>() {
        @Override
        public void onChanged(Boolean success) {
            if (success == null) {
                mostrarMensajeError();
            } else {
                // Operación exitosa
            }
        }
    });
}
    private void actualizarEstadoToggleButton(ToggleButton toggleButton, String valor) {
        toggleButton.setChecked(valor.equals("ON"));
    }

    private void mostrarMensajeError() {
        Snackbar.make(findViewById(android.R.id.content), "Hubo un error al enviar el comando", Snackbar.LENGTH_LONG).show();

    }
}