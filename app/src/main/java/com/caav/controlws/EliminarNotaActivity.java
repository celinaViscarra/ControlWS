package com.caav.controlws;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;

public class EliminarNotaActivity extends AppCompatActivity {
    EditText carnetTxt, codMateriaTxt, cicloTxt;

    private final String urlLocal ="http://192.168.1.8/av12013/ws_dato_delete.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_nota);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        carnetTxt = (EditText) findViewById(R.id.editText_alumnoCarnet);
        codMateriaTxt = (EditText) findViewById(R.id.editText_notaMateria);
        cicloTxt = (EditText) findViewById(R.id.editText_notaCiclo);
    }

    public void eliminarNota(View v){
        String carnet = carnetTxt.getText().toString();
        String codMateria = codMateriaTxt.getText().toString();
        String ciclo = cicloTxt.getText().toString();
        String url = "";
        url = urlLocal + "?carnet=" + carnet + "&codmateria=" + codMateria + "&ciclo=" + ciclo;
        ControladorServicio.eliminarNota(url, this);
    }
}
