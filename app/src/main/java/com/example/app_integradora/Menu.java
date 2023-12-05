package com.example.app_integradora;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class Menu extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        final DrawerLayout drawerLayout = findViewById(R.id.menu);
        Button buttontriggers=findViewById(R.id.trigger);
        Button buttonsensors=findViewById(R.id.Sensor);
        LinearLayout ultrasonic = findViewById(R.id.ultrasonic);
        LinearLayout temperatura = findViewById(R.id.temperatura);
        LinearLayout humedad = findViewById(R.id.humedad);
        LinearLayout Impact = findViewById(R.id.Impact);
        LinearLayout gas = findViewById(R.id.gas);
        LinearLayout luz = findViewById(R.id.luz);

        buttontriggers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu.this,triggers.class);
                startActivity(intent);
            }
        });
        buttonsensors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu.this,Menu.class);
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
        Intent intent = new Intent(this,Sensor_mov.class);
        startActivity(intent);
    }
    public void onLineartemp(View view) {
        Intent intent = new Intent(this,Sensor_mov.class);
        startActivity(intent);
    }
public void onLinearhum(View view) {
        Intent intent = new Intent(this, Sensor_humedad.class);
        startActivity(intent);
    }
    public void onLineargas(View view) {
        Intent intent = new Intent(this,sensor_gas.class);
        startActivity(intent);
    }
    public void onLinearluz(View view) {
        Intent intent = new Intent(this,Sensor_mov.class);
        startActivity(intent);
    }
    public void onLinearimp(View view) {
        Intent intent = new Intent(this, Sensor_impacto.class);
        startActivity(intent);
    }

  /* public void onMenuOptionSelected(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.menuEmpresa1:
                intent = new Intent(this, InformacionEmpresaActivity.class);
                intent.putExtra("nombre_empresa", "Empresa 1");
                startActivity(intent);
                break;

            case R.id.menuAgregarEmpresa:
                intent = new Intent(this, AgregarEmpresaActivity.class);
                startActivity(intent);
                break;

            case R.id.menuVitrina1:
                intent = new Intent(this, InformacionVitrinaActivity.class);
                intent.putExtra("nombre_vitrina", "Vitrina 1");
                startActivity(intent);
                break;

            case R.id.menuAgregarVitrina:
                intent = new Intent(this, AgregarVitrinaActivity.class);
                startActivity(intent);
                break;

            case R.id.menuSensor1:
                intent = new Intent(this, InformacionSensorActivity.class);
                intent.putExtra("nombre_sensor", "Sensor 1");
                startActivity(intent);
                break;

            case R.id.menuAgregarSensor:
                intent = new Intent(this, AgregarSensorActivity.class);
                startActivity(intent);
                break;
        }
    }*/
}





