package com.caav.controlws;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ActualizarMateriaActivity extends AppCompatActivity {
    ControlBDAlumno db;
    static List<Materia> listaMaterias; //
    static List<String> nombreMaterias;
    EditText fechaTxt;
    ListView listaViewMaterias;

    //URL.
    private final String urlLocal ="http://192.168.1.8/AV12013//ws_db_materia_fecha.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_materia);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        db = new ControlBDAlumno(this);
        listaMaterias = new ArrayList<Materia>();
        nombreMaterias = new ArrayList<String>();
        fechaTxt = (EditText) findViewById(R.id.editText_Fecha);
        listaViewMaterias = (ListView) findViewById(R.id.listView1);
    }

    public void servicioPHP(View v){
        try {
            //Formato dd-mm-yyyy
            String[] fecha = fechaTxt.getText().toString().split("/");
            String url = String.format(urlLocal, fecha[0], fecha[1], fecha[2]);
            Log.v("Url enviada", url);
            String respuesta = ControladorServicio.obtenerRespuestaPeticion(url, this);
            Log.v("Respuesta", respuesta);
            listaMaterias = (ArrayList<Materia>) ControladorServicio.obtenerMaterias(respuesta, this);
            ArrayList<String> nombreMaterias = new ArrayList<>();
            for (Materia pivote : listaMaterias)
                nombreMaterias.add(String.format("%s - %s", pivote.getCodmateria(), pivote.getNommateria()));
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, nombreMaterias);
            listaViewMaterias.setAdapter(adapter);
        }catch (ArrayIndexOutOfBoundsException e){
            Toast.makeText(this, "No ha ingresado una fecha valida", Toast.LENGTH_LONG).show();
        }
    }

    public void guardar(View v){
        db.abrir();
        for(int i=0; i<listaMaterias.size(); i++){
            Log.v("Guardar", db.insertar(listaMaterias.get(i)));
        }
        db.cerrar();
        Toast.makeText(this, "Guardado con exito", Toast.LENGTH_SHORT).show();
        listaMaterias.removeAll(listaMaterias);
        actualizarListView();
    }

    private void actualizarListView(){
        String dato="";
        nombreMaterias.clear();
        for(int i=0; i<listaMaterias.size(); i++){
            dato=listaMaterias.get(i).getCodmateria()+""+listaMaterias.get(i).getNommateria();
            nombreMaterias.add(dato);
        }
        eliminarDuplicados();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, nombreMaterias);
        listaViewMaterias.setAdapter(adapter);
    }


    private void eliminarDuplicados(){
        HashSet<Materia> conjuntoMateria = new HashSet<Materia>();
        conjuntoMateria.addAll(listaMaterias);
        listaMaterias.clear();
        listaMaterias.addAll(conjuntoMateria);

        HashSet<String> conjuntoNombre = new HashSet<String>();
        conjuntoNombre.addAll(nombreMaterias);
        nombreMaterias.clear();
        nombreMaterias.addAll(conjuntoNombre);
    }
}
