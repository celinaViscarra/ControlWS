package com.caav.controlws;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MaximoAlumnoActivity extends AppCompatActivity {
    EditText carnetTxt;
    TextView notaTxt;
    //URL.
    private final String urlLocal ="http://192.168.1.8/av12013/ws_max.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maximo_alumno);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        carnetTxt = (EditText) findViewById(R.id.editText_alumnoCarnet);
        notaTxt = (TextView) findViewById(R.id.textView_alumnoNota);
    }

    public void consultarMaximo(View v){
        String carnet = carnetTxt.getText().toString();
        String url = urlLocal + "?carnet=" + carnet;
        String maximo = ControladorServicio.obtenerRespuestaPeticion(url, this);
        Log.v("Respuesta", maximo);
        notaTxt.setText("Maximo: " + ControladorServicio.obtenerMinJSON(maximo, this));
    }
}