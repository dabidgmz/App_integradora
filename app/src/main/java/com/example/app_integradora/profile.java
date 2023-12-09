package com.example.app_integradora;

import androidx.appcompat.app.AppCompatActivity;

public class profile extends AppCompatActivity {
/*
    private ViewModelPerfil viewModelPerfil;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile1);
        viewModelPerfil = new ViewModelProvider(this).get(ViewModelPerfil.class);
        viewModelPerfil.getUserProfile().observe(this, userProfile -> {
            if (userProfile != null) {
                updateUI(userProfile);
            }
        });

        viewModelPerfil.getErrorLiveData().observe(this, errorMessage -> {
            Toast.makeText(profile.this, errorMessage, Toast.LENGTH_SHORT).show();
        });

        String token = viewModelToken.getinstance().getToken(); // Corregido
        viewModelPerfil.getUserProfile();
    }

    private void updateUI(ResponsePostUserMe userProfile) {
        ImageView imageViewUsuario = findViewById(R.id.imageViewUsuario);
        TextView textViewNombre = findViewById(R.id.textViewNombre);
        TextView textViewApellido = findViewById(R.id.textViewApellido);
        TextView textViewMatricula = findViewById(R.id.textViewMatricula);
        TextView textViewEmail = findViewById(R.id.textViewEmail);
        TextView textViewDepartamento = findViewById(R.id.textViewdepartamento);
        textViewNombre.setText("Nombre: " + userProfile.getName());
        textViewApellido.setText("Apellido: " + userProfile.getLastname());
        textViewMatricula.setText("Matrícula: " + userProfile.getId());
        textViewEmail.setText("Email: " + userProfile.getEmail());
        textViewDepartamento.setText("Departamento: " + userProfile.getDepartament());
    }*/
}
