package es.oretania.ejemplohilostarealarga;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
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

        binding.textView.setTextSize(24);
        binding.textView.setGravity(Gravity.CENTER);

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
                        // en hilo principal que es el que ha creado esa vista
                        // Esto se hace de esta manera.
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                binding.textView.setText("Texto modificado por el hilo");
                            }
                        });
                        //Más correcto es usar la clase AsyncTask
                    }
                }).start();
            }
        });
    }
}