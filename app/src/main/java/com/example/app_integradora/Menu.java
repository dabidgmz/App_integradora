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

}





