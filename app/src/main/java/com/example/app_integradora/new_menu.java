package com.example.app_integradora;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.drawerlayout.widget.DrawerLayout;



import com.google.android.material.navigation.NavigationView;

public class new_menu extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
    if (drawerToggle.onOptionsItemSelected(item)){
        return true;
    }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_menu);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.abrir,R.string.cerrar);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.menuPorfile) {
                    // Inicia la Activity Profile
                    Intent intent = new Intent(new_menu.this, profile.class);
                    startActivity(intent);
                    drawerLayout.closeDrawer(GravityCompat.START);

                    return true;
                } else if (item.getItemId()==R.id.menuAgregarEmpresa) {
                    Intent intent = new Intent(new_menu.this, agregar_empresa1.class);
                    startActivity(intent);
                    drawerLayout.closeDrawer(GravityCompat.START);

                    return true;
                }
                else if (item.getItemId()==R.id.menuAgregarVitrina) {
                    Intent intent = new Intent(new_menu.this, agregar_vitrina1.class);
                    startActivity(intent);
                    drawerLayout.closeDrawer(GravityCompat.START);

                    return true;
                }
                else if (item.getItemId()==R.id.menuAgregarSensor) {
                    Intent intent = new Intent(new_menu.this, agregar_sensor1.class);
                    startActivity(intent);
                    drawerLayout.closeDrawer(GravityCompat.START);

                    return true;
                }
                else if (item.getItemId()==R.id.movimiento) {
                    Intent intent = new Intent(new_menu.this, Sensor_mov.class);
                    startActivity(intent);
                    drawerLayout.closeDrawer(GravityCompat.START);

                    return true;
                }
                else if (item.getItemId()==R.id.gas1) {
                    Intent intent = new Intent(new_menu.this, sensor_gas.class);
                    startActivity(intent);
                    drawerLayout.closeDrawer(GravityCompat.START);

                    return true;
                }
                else if (item.getItemId()==R.id.luz1) {
                    Intent intent = new Intent(new_menu.this, sensor_luz.class);
                    startActivity(intent);
                    drawerLayout.closeDrawer(GravityCompat.START);

                    return true;
                }
                else if (item.getItemId()==R.id.humedad1) {
                    Intent intent = new Intent(new_menu.this, Sensor_humedad.class);
                    startActivity(intent);
                    drawerLayout.closeDrawer(GravityCompat.START);

                    return true;
                }
                else if (item.getItemId()==R.id.temperatura1) {
                    Intent intent = new Intent(new_menu.this, Sensor_temperatura.class);
                    startActivity(intent);
                    drawerLayout.closeDrawer(GravityCompat.START);

                    return true;
                }
                else if (item.getItemId()==R.id.impacto1) {
                    Intent intent = new Intent(new_menu.this, Sensor_impacto.class);
                    startActivity(intent);
                    drawerLayout.closeDrawer(GravityCompat.START);

                    return true;
                }

                return false; // Si no es el ítem deseado, no hacer nada
            }

        });
        Button buttonTriggers = findViewById(R.id.trigger);
        Button buttonSensors = findViewById(R.id.Sensor);
        LinearLayout ultrasonic = findViewById(R.id.ultrasonic);
        LinearLayout temperatura = findViewById(R.id.temperatura);
        LinearLayout humedad = findViewById(R.id.humedad);
        LinearLayout impact = findViewById(R.id.Impact);
        LinearLayout gas = findViewById(R.id.gas);
        LinearLayout luz = findViewById(R.id.luz);

        buttonTriggers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(new_menu.this, triggers.class);
                startActivity(intent);
            }
        });

        buttonSensors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(new_menu.this, new_menu.class);
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

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}