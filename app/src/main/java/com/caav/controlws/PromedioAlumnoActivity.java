package com.caav.controlws;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

@SuppressLint("NewApi")
public class PromedioAlumnoActivity extends AppCompatActivity {
    EditText carnetTxt;
    TextView notaPromedioTxt;
    //URL.
    private final String urlLocal ="http://192.168.1.8/av12013/ws_promedio.php";

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promedio_alumno);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        carnetTxt = (EditText) findViewById(R.id.editText_alumnoCarnet);
        notaPromedioTxt = (TextView) findViewById(R.id.textView_alumnoNota);
    }

    public void consultarPromedio(View v){
        String carnet = carnetTxt.getText().toString();
        String url = urlLocal + "?carnet=" + carnet;
        String promedio = ControladorServicio.obtenerRespuestaPeticion(url, this);
        Log.v("Respuesta", promedio);
        notaPromedioTxt.setText("Nota Promedio: " + ControladorServicio.obtenerPromedioJSON(promedio, this));
    }
}
