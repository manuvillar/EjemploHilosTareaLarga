package es.oretania.ejemplohilostarealarga;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import es.oretania.ejemplohilostarealarga.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.botonTareaLarga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Thread.sleep(15000);
                }catch (InterruptedException e){

                }
            }
        });

        binding.botonTareaLargaHilo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(15000);
                        }catch (InterruptedException e){

                        }
                        //Para modificar valores de la interfaz se tiene que hacer
                        // en hilo principal de esta manera.
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                binding.textView.setText("Texto modificado por el hilo");
                            }
                        });
                        Toast.makeText(v.getContext(), "Hilo acabado", Toast.LENGTH_SHORT).show();
                    }
                }).start();
            }
        });
    }
}