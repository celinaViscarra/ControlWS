package com.caav.controlws;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONObject;

@SuppressLint("NewApi")
public class IngresarNotaActivity extends AppCompatActivity {
    EditText carnetTxt, codMateriaTxt, cicloTxt, notaTxt;
    //URL.
    private final String urlLocal ="http://192.168.1.8/av12013/ws_nota_insert.php";

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar_nota);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        carnetTxt = (EditText) findViewById(R.id.editText_notaCarnet);
        codMateriaTxt = (EditText) findViewById(R.id.editText_notaMateria);
        cicloTxt = (EditText) findViewById(R.id.editText_notaCiclo);
        notaTxt = (EditText) findViewById(R.id.editText_notaFinal);
    }

    public void insertarNota(View v){
        String carnet = carnetTxt.getText().toString();
        String codMateria = codMateriaTxt.getText().toString();
        String ciclo = cicloTxt.getText().toString();
        String notaFinal = notaTxt.getText().toString();
        String url = "";
        url = urlLocal + "?carnet=" + carnet + "&codmateria=" + codMateria + "&ciclo="
                + ciclo + "&notafinal="+ notaFinal;
        ControladorServicio.insertarNota(url, this);
    }
}
