package com.example.app_integradora;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.app_integradora.viewmodel.viewmodelprincipal;
import com.google.android.material.navigation.NavigationView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class principal extends AppCompatActivity {
    DrawerLayout drawerLayout;
    private viewmodelprincipal viewModelprincipal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        drawerLayout = findViewById(R.id.principal);
        Button buttonTriggers = findViewById(R.id.trigger);
        Button buttonSensors = findViewById(R.id.Sensor);
        LinearLayout ultrasonic = findViewById(R.id.ultrasonic);
        LinearLayout temperatura = findViewById(R.id.temperatura);
        LinearLayout humedad = findViewById(R.id.humedad);
        LinearLayout impact = findViewById(R.id.Impact);
        LinearLayout gas = findViewById(R.id.gas);
        LinearLayout luz = findViewById(R.id.luz);
        TextView textViewValorUltra = findViewById(R.id.valorultra);

        viewModelprincipal.getValorUltraLiveData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String valorUltra) {
                // Actualizar el TextView con el nuevo valor
                textViewValorUltra.setText(valorUltra);
            }
        });

        viewmodelprincipal viewmodelprincipal = new ViewModelProvider(this).get(com.example.app_integradora.viewmodel.viewmodelprincipal.class);
        viewmodelprincipal.getDatosUltraLiveData().observe(this, valorUltra -> {
            if (valorUltra != null) {
                textViewValorUltra.setText(valorUltra);
            }
        });
        viewmodelprincipal.getValorUltraLiveData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String valorUltra) {
                textViewValorUltra.setText(valorUltra);
            }
        });


 buttonTriggers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(principal.this, triggers.class);
                startActivity(intent);
            }
        });

        buttonSensors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(principal.this, Menu.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.imagMenu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    // botones de sensores
    public void onLinear(View view) {
        Intent intent = new Intent(this, Sensor_mov.class);
        startActivity(intent);
    }

    public void onLineartemp(View view) {
        Intent intent = new Intent(this, Sensor_temperatura.class);
        startActivity(intent);
    }

    public void onLinearhum(View view) {
        Intent intent = new Intent(this, Sensor_humedad.class);
        startActivity(intent);
    }

    public void onLineargas(View view) {
        Intent intent = new Intent(this, sensor_gas.class);
        startActivity(intent);
    }

    public void onLinearluz(View view) {
        Intent intent = new Intent(this, profile.class);
        startActivity(intent);
    }

    public void onLinearimp(View view) {
        Intent intent = new Intent(this, Sensor_impacto.class);
        startActivity(intent);
    }
}