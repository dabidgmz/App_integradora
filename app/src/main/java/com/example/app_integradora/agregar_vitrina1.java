package com.example.app_integradora;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;
import android.widget.ArrayAdapter;

import com.example.app_integradora.Retroft.GetEmpresa;

import com.example.app_integradora.Retroft.ApiRequest;
import com.google.android.material.navigation.NavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.List;
import java.util.ArrayList;

public class agregar_vitrina1 extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;
    SharedPreferences sharedPreferences;
    String token;

    Spinner spinnerEmpresas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_vitrina1);
        sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        token = sharedPreferences.getString("token", "");

        spinnerEmpresas = findViewById(R.id.spinnerEmpresas);

        obtenerEmpresas();

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.abrir, R.string.cerrar);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.menuPorfile) {
                    Intent intent = new Intent(agregar_vitrina1.this, profile.class);
                    startActivity(intent);
                    drawerLayout.closeDrawer(GravityCompat.START);
                    return true;
                } else if (item.getItemId() == R.id.menuAgregarEmpresa) {
                    Intent intent = new Intent(agregar_vitrina1.this, agregar_empresa1.class);
                    startActivity(intent);
                    drawerLayout.closeDrawer(GravityCompat.START);
                    return true;
                } else if (item.getItemId() == R.id.menuAgregarVitrina) {

                    Intent intent = new Intent(agregar_vitrina1.this, agregar_vitrina1.class);
                    startActivity(intent);
                    drawerLayout.closeDrawer(GravityCompat.START);
                    return true;
                } else if (item.getItemId() == R.id.menuAgregarSensor) {
                    Intent intent = new Intent(agregar_vitrina1.this, agregar_sensor1.class);
                    startActivity(intent);
                    drawerLayout.closeDrawer(GravityCompat.START);
                    return true;
                } else if (item.getItemId() == R.id.movimiento) {
                    Intent intent = new Intent(agregar_vitrina1.this, Sensor_mov.class);
                    startActivity(intent);
                    drawerLayout.closeDrawer(GravityCompat.START);
                    return true;
                } else if (item.getItemId() == R.id.gas1) {
                    Intent intent = new Intent(agregar_vitrina1.this, sensor_gas.class);
                    startActivity(intent);
                    drawerLayout.closeDrawer(GravityCompat.START);
                    return true;
                } else if (item.getItemId() == R.id.luz1) {
                    Intent intent = new Intent(agregar_vitrina1.this, sensor_luz.class);
                    startActivity(intent);
                    drawerLayout.closeDrawer(GravityCompat.START);
                    return true;
                } else if (item.getItemId() == R.id.humedad1) {
                    Intent intent = new Intent(agregar_vitrina1.this, Sensor_humedad.class);
                    startActivity(intent);
                    drawerLayout.closeDrawer(GravityCompat.START);
                    return true;
                } else if (item.getItemId() == R.id.temperatura1) {
                    Intent intent = new Intent(agregar_vitrina1.this, Sensor_temperatura.class);
                    startActivity(intent);
                    drawerLayout.closeDrawer(GravityCompat.START);
                    return true;
                } else if (item.getItemId() == R.id.impacto1) {
                    Intent intent = new Intent(agregar_vitrina1.this, Sensor_impacto.class);
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
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    private void obtenerEmpresas() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://3.138.171.241")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiRequest apiRequest = retrofit.create(ApiRequest.class);
        String userId = sharedPreferences.getString("userId", "");

        Call<GetEmpresa> call = apiRequest.obtenerEmpresas("Bearer " + token);


        call.enqueue(new Callback<GetEmpresa>() {
            @Override
            public void onResponse(Call<GetEmpresa> call, Response<GetEmpresa> response) {
                if (response.isSuccessful() && response.body() != null) {
                    GetEmpresa getEmpresa = response.body();
                    List<String> nombresEmpresas = new ArrayList<>();
                    for (GetEmpresa.Empresa empresa : getEmpresa.getEmpresas()) {
                        nombresEmpresas.add(empresa.getNombre());
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(agregar_vitrina1.this,
                            android.R.layout.simple_spinner_item, nombresEmpresas);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerEmpresas.setAdapter(adapter);
                } else {
                    Log.e("API Error", "Response not successful: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<GetEmpresa> call, Throwable t) {
                Log.e("API Error", "Call failed: " + t.getMessage());
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
