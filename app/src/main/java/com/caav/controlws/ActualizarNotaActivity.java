package com.caav.controlws;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;

public class ActualizarNotaActivity extends AppCompatActivity {
    EditText carnetTxt, codMateriaTxt, cicloTxt, notaTxt;
    //URL.
    private final String urlLocal ="http://192.168.1.8/av12013/ws_nota_update.php/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_nota);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        carnetTxt = (EditText) findViewById(R.id.editText_notaCarnet);
        codMateriaTxt = (EditText) findViewById(R.id.editText_notaMateria);
        cicloTxt = (EditText) findViewById(R.id.editText_notaCiclo);
        notaTxt = (EditText) findViewById(R.id.editText_notaFinal);
    }

    public void actualizarNota(View v){
        String carnet = carnetTxt.getText().toString();
        String codMateria = codMateriaTxt.getText().toString();
        String ciclo = cicloTxt.getText().toString();
        String notaFinal = notaTxt.getText().toString();
        String url = "";
        url = urlLocal + "?carnet=" + carnet + "&codmateria=" + codMateria + "&ciclo="
                + ciclo + "&notafinal="+ notaFinal;
        ControladorServicio.actualizarNota(url, this);
    }
}
