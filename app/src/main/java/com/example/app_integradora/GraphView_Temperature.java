package com.example.app_integradora;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.app_integradora.Modelos.Modelprincipal;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GraphView_Temperature extends AppCompatActivity {
    private Handler handler;
    private Runnable runnable;
    private static final int INTERVALO_DE_ACTUALIZACION = 10000;

    private GraphView graphTemperatura;
    private GraphView graphHumedad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_view_temperature);

        graphTemperatura = findViewById(R.id.graphTemperatura);
        graphHumedad = findViewById(R.id.graphHumedad);

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                realizarPeticion();
                handler.postDelayed(this, INTERVALO_DE_ACTUALIZACION);
            }
        };
        handler.post(runnable);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
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
                                LineGraphSeries<DataPoint> seriesTemp = new LineGraphSeries<>(new DataPoint[] {
                                        new DataPoint(new Date(), Double.parseDouble(value))
                                });
                                graphTemperatura.addSeries(seriesTemp);
                                break;
                            case "humedad":
                                LineGraphSeries<DataPoint> seriesHum = new LineGraphSeries<>(new DataPoint[] {
                                        new DataPoint(new Date(), Double.parseDouble(value))
                                });
                                graphHumedad.addSeries(seriesHum);
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
