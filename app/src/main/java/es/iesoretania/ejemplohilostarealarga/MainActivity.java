package es.iesoretania.ejemplohilostarealarga;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import es.iesoretania.ejemplohilostarealarga.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inicializar ViewBinding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Configurar listeners para los botones
        binding.btnHiloPrincipal.setOnClickListener(v -> {
            ejecutarTareaLargaEnHiloPrincipal();
        });

        binding.btnHiloSecundario.setOnClickListener(v -> {
            ejecutarTareaLargaEnHiloSecundario();
        });
    }

    // Simula una tarea larga en el hilo principal
    private void ejecutarTareaLargaEnHiloPrincipal() {
        binding.txtResultado.setText("Ejecutando en el hilo principal...");
        try {
            // Simula una tarea de 5 segundos
            Thread.sleep(5000);
            binding.txtResultado.setText("Tarea completada en hilo principal");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Simula una tarea larga en un hilo secundario
    private void ejecutarTareaLargaEnHiloSecundario() {
        binding.txtResultado.setText("Ejecutando en un hilo secundario...");
        new Thread(() -> {
            try {
                // Simula una tarea de 5 segundos
                Thread.sleep(5000);
                // Actualiza el resultado en el hilo principal
                runOnUiThread(() -> binding.txtResultado.setText("Tarea completada en hilo secundario"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}