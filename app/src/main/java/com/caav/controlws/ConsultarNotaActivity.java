package com.caav.controlws;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ConsultarNotaActivity extends AppCompatActivity {
    EditText carnetTxt, codMateriaTxt, cicloTxt;
    TextView notaTxt;

    private final String urlLocal ="http://192.168.1.8/av12013/ws_nota_query.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_nota);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        carnetTxt = (EditText) findViewById(R.id.editText_alumnoCarnet);
        codMateriaTxt = (EditText) findViewById(R.id.editText_notaMateria);
        cicloTxt = (EditText) findViewById(R.id.editText_notaCiclo);
        notaTxt = (TextView) findViewById(R.id.textView_alumnoNota);
    }

    public void consultarNota(View v){
        String carnet = carnetTxt.getText().toString();
        String codMateria = codMateriaTxt.getText().toString();
        String ciclo = cicloTxt.getText().toString();
        String url = "";
        url = urlLocal + "?carnet=" + carnet + "&codmateria=" + codMateria + "&ciclo=" + ciclo;
        String nota = ControladorServicio.obtenerRespuestaPeticion(url, this);
        Log.v("Respuesta", nota);
        notaTxt.setText("Nota: " + ControladorServicio.consultarNota(nota, this));
    }
}
