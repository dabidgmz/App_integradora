package com.example.app_integradora;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.app_integradora.Retroft.ApiRequest;
import com.google.android.material.navigation.NavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Sensor_humedad extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_humedad);

        //configuracion del menu

        drawerLayout = findViewById(R.id.Sensor_humedad);

        findViewById(R.id.imagMenu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ahora drawerLayout no será nulo
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        //TERMINA

        // TextViews
        TextView humedadTextView = findViewById(R.id.humedar);
        TextView temperaturaTextView = findViewById(R.id.temp);

        CardView alertaCardView = findViewById(R.id.alerta);
        TextView alertaTextView = new TextView(this);

        alertaCardView.addView(alertaTextView);

        ApiRequest apiRequest = new Retrofit.Builder()
                .baseUrl("http://3.138.171.241")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiRequest.class);

        apiRequest.getHumedad().enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()){
                    String humedad = response.body();
                    humedadTextView.setText(humedad);
                    if(Integer.parseInt(humedad.replace("%", ""))> 100){
                        alertaTextView.setText("¡Alerta! La humedad ha superado el 100%");
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });

        apiRequest.getTemperatura().enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()){
                    String temperatura = response.body();
                    temperaturaTextView.setText(temperatura);
                    if (Integer.parseInt(temperatura.replace("C°", "")) > 30){
                        alertaTextView.setText("¡Alerta! La temperatura ha superado los 30 grados");
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }
    //Tambien agregado
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}