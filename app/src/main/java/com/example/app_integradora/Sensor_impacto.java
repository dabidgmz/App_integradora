package com.example.app_integradora;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.navigation.NavigationView;

public class Sensor_impacto extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_impacto);

        //menu
        // Inicializar drawerLayout aquí
        drawerLayout = findViewById(R.id.Sensor_impacto);

        findViewById(R.id.imagMenu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ahora drawerLayout no será nulo
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        //menu
    }

    //Agregado para el menu
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}