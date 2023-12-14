package com.example.app_integradora;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.app_integradora.Modelos.Modelprincipal;
import com.google.android.material.navigation.NavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Sensor_humedad extends AppCompatActivity {

    private TextView textViewValorTemp;
    private TextView textViewValorHum;
    private CardView cardViewAlerta;
    private Handler mHandler;
    private static final int INTERVALO_DE_ACTUALIZACION = 10000; // 10 segundos
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_humedad);

        textViewValorTemp = findViewById(R.id.temp);
        textViewValorHum = findViewById(R.id.humedar);
        cardViewAlerta = findViewById(R.id.alerta);
        drawerLayout = findViewById(R.id.Sensor_humedad);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AdafruitApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AdafruitApi adafruitApi = retrofit.create(AdafruitApi.class);
        mHandler = new Handler();
        startRepeatingTask();


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
                    Intent intent = new Intent(Sensor_humedad.this, profile.class);
                    startActivity(intent);
                    drawerLayout.closeDrawer(GravityCompat.START);

                    return true;
                } else if (item.getItemId()==R.id.menuAgregarEmpresa) {
                    Intent intent = new Intent(Sensor_humedad.this, agregar_empresa1.class);
                    startActivity(intent);
                    drawerLayout.closeDrawer(GravityCompat.START);

                    return true;
                }
                else if (item.getItemId()==R.id.menuAgregarVitrina) {
                    Intent intent = new Intent(Sensor_humedad.this, agregar_vitrina1.class);
                    startActivity(intent);
                    drawerLayout.closeDrawer(GravityCompat.START);

                    return true;
                }
                else if (item.getItemId()==R.id.menuAgregarSensor) {
                    Intent intent = new Intent(Sensor_humedad.this, agregar_sensor1.class);
                    startActivity(intent);
                    drawerLayout.closeDrawer(GravityCompat.START);

                    return true;
                }
                else if (item.getItemId()==R.id.movimiento) {
                    Intent intent = new Intent(Sensor_humedad.this, Sensor_mov.class);
                    startActivity(intent);
                    drawerLayout.closeDrawer(GravityCompat.START);

                    return true;
                }
                else if (item.getItemId()==R.id.gas1) {
                    Intent intent = new Intent(Sensor_humedad.this, sensor_gas.class);
                    startActivity(intent);
                    drawerLayout.closeDrawer(GravityCompat.START);

                    return true;
                }
                else if (item.getItemId()==R.id.luz1) {
                    Intent intent = new Intent(Sensor_humedad.this, sensor_luz.class);
                    startActivity(intent);
                    drawerLayout.closeDrawer(GravityCompat.START);

                    return true;
                }
                else if (item.getItemId()==R.id.humedad1) {
                    Intent intent = new Intent(Sensor_humedad.this, Sensor_humedad.class);
                    startActivity(intent);
                    drawerLayout.closeDrawer(GravityCompat.START);

                    return true;
                }
                else if (item.getItemId()==R.id.temperatura1) {
                    Intent intent = new Intent(Sensor_humedad.this, Sensor_temperatura.class);
                    startActivity(intent);
                    drawerLayout.closeDrawer(GravityCompat.START);

                    return true;
                }
                else if (item.getItemId()==R.id.impacto1) {
                    Intent intent = new Intent(Sensor_humedad.this, Sensor_impacto.class);
                    startActivity(intent);
                    drawerLayout.closeDrawer(GravityCompat.START);

                    return true;
                }

                return false;
            }

        });

        findViewById(R.id.imagMenu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ahora drawerLayout no será nulo
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        //menu
    }
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    Runnable mStatusChecker = new Runnable() {
        @Override
        public void run() {
            try {
                realizarPeticion();

            } finally {
                mHandler.postDelayed(mStatusChecker, INTERVALO_DE_ACTUALIZACION);
            }
        }
    };


    void startRepeatingTask() {
        mStatusChecker.run();
    }

    void stopRepeatingTask() {
        mHandler.removeCallbacks(mStatusChecker);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Detiene la tarea de actualización periódica al destruir la actividad
        stopRepeatingTask();
    }

    private void realizarPeticion() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AdafruitApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AdafruitApi adafruitApi = retrofit.create(AdafruitApi.class);

        Call<Modelprincipal> call = adafruitApi.obtenerDatos();
        call.enqueue(new Callback<Modelprincipal>() {
            @Override
            public void onResponse(Call<Modelprincipal> call, Response<Modelprincipal> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Modelprincipal model = response.body();

                    for (Modelprincipal.FeedData feedData : model.getData()) {
                        String feedKey = feedData.getFeedKey();
                        String value = feedData.getValue();

                        switch (feedKey) {
                            case "temperatura":
                                textViewValorTemp.setText(value);
                                if (Float.parseFloat(value) > 30) {
                                    // Mostrar mensaje de advertencia en el CardView
                                }
                                break;
                            case "humedad":
                                textViewValorHum.setText(value);
                                if (Float.parseFloat(value) > 70) {
                                    // Mostrar mensaje de advertencia en el CardView
                                }
                                break;
                            default:
                                break;
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<Modelprincipal> call, Throwable t) {
                Log.e("Error", "Error en la llamada a la API", t);
            }
        });
    }
}