package com.example.app_integradora;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class Menu_despegable extends AppCompatActivity {

    private Spinner spinner;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> opcionesList;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner);

        // Inicializa la lista de opciones
        opcionesList = new ArrayList<>();

        // Crea un adaptador vacío y asígnalo al Spinner
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opcionesList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        // Establece un listener para manejar los eventos de selección
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Acciones a realizar cuando se selecciona una opción
                Toast.makeText(getApplicationContext(), "Seleccionaste: " + opcionesList.get(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Acciones a realizar cuando no se selecciona ninguna opción
            }
        });

        // Agrega opciones a la lista (puedes hacer esto en cualquier parte de tu código)
        agregarOpcion("Opción 1");
        agregarOpcion("Opción 2");
        agregarOpcion("Opción 3");
    }

    // Método para agregar una opción a la lista y actualizar el adaptador
    private void agregarOpcion(String opcion) {
        opcionesList.add(opcion);
        adapter.notifyDataSetChanged();
    }
}
