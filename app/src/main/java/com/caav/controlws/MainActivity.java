package com.caav.controlws;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void lanzarActividad(View v){
        Intent i = null;
        switch(v.getId()){
            case R.id.button_materia:
                i = new Intent(this,ActualizarMateriaActivity.class);
                break;
            case R.id.button_alumno:
                    i = new Intent(this,PromedioAlumnoActivity.class);
                break;
            case R.id.button_ingresarNota:
                    i = new Intent(this,IngresarNotaActivity.class);
                    break;
            case R.id.button_actualizarNota:
                i = new Intent(this, ActualizarNotaActivity.class);
                break;
            case R.id.button_consultarNota:
                i = new Intent(this, ConsultarNotaActivity.class);
                break;
            case R.id.button_eliminarNota:
                i = new Intent(this, EliminarNotaActivity.class);
                break;
        } if(i!=null)
            startActivity(i);
    }
}
