package com.example.app_integradora;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.app_integradora.Modelos.Modelprincipal;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class sensor_gas extends AppCompatActivity {
    DrawerLayout drawerLayout;
    private Handler mHandler;
    private static final int INTERVALO_DE_ACTUALIZACION = 10000; // 10 segundos
    ActionBarDrawerToggle drawerToggle;
    TextView textViewSensorGas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_gas);

        drawerLayout = findViewById(R.id.sensor_gas);
        textViewSensorGas = findViewById(R.id.GasValue);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AdafruitApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AdafruitApi adafruitApi = retrofit.create(AdafruitApi.class);
        mHandler = new Handler();
        startRepeatingTask();


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

                        if ("gas".equals(feedKey)) {
                            textViewSensorGas.setText(value);
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