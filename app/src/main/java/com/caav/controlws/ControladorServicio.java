package com.caav.controlws;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ControladorServicio {
    //Se utiliza para php.
    public static String obtenerRespuestaPeticion(String url, Context context){
        String respuesta = "";

        //Estableciendo tiempo de espera del servicio.
        HttpParams parametros = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(parametros, 3000);
        HttpConnectionParams.setSoTimeout(parametros, 5000);

        //Creando objetos de conexion.
        HttpClient cliente = new DefaultHttpClient(parametros);
        HttpGet httpGet = new HttpGet(url);
        try{
            HttpResponse httpRespuesta = cliente.execute(httpGet);
            StatusLine estado = httpRespuesta.getStatusLine();
            int codigoEstado = estado.getStatusCode();
            if(codigoEstado==200){
                HttpEntity entidad = httpRespuesta.getEntity();
                respuesta = EntityUtils.toString(entidad);
            }
        } catch (Exception e) {
            Toast.makeText(context, "Error en la  conexion", Toast.LENGTH_LONG).show();
            //Desplegando el error en el LogCat
            Log.v("Error de conexion", e.toString());
        }
        return respuesta;
    }

    //Para obtener Materia de php.
    public static List<Materia> obtenerMaterias(String json,Context ctx){
        List<Materia> listaMaterias = new ArrayList<Materia>();
        try{
            JSONArray materiaJSON = new JSONArray(json);
            for(int i=0; i<materiaJSON.length(); i++){
                JSONObject object = materiaJSON.getJSONObject(i);
                Materia materia = new Materia();
                materia.setCodmateria(object.getString("codmateria"));
                materia.setNommateria(object.getString("nommateria"));
                materia.setUnidadesval(object.getString("unidadesval"));
                listaMaterias.add(materia);
            }
            return listaMaterias;
        } catch (Exception e) {
            Toast.makeText(ctx, "Error en el parseo de JSON", Toast.LENGTH_LONG).show();
            return null;
        }
    }

    //Para insertar nota en php.
    public static void insertarNota(String peticion, Context ctx){
        String json = obtenerRespuestaPeticion(peticion, ctx);
        try{
            JSONObject resultado = new JSONObject(json);
            Toast.makeText(ctx, "Registro ingresado"+
                    resultado.getJSONArray("resultado").toString(),
                    Toast.LENGTH_LONG).show();
            int respuesta = resultado.getInt("resultado");
            if(respuesta==1)
                Toast.makeText(ctx, "Registro ingresado", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(ctx, "Error registro duplicado", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Funcion especial.
    //Obtener promedio.
    public static String obtenerPromedioJSON(String json, Context ctx){
        try{
            JSONArray objs = new JSONArray(json);
            if (objs.length() != 0){
                //Nota final promedio.
                return objs.getJSONObject(0).getString("Promedio");
            }else {
                Toast.makeText(ctx, "Error carnet no existe", Toast.LENGTH_LONG).show();
                return "";
            }
        }catch (JSONException e){
            Toast.makeText(ctx, "Error con la respuesta JSON", Toast.LENGTH_LONG).show();
            return "";
        }
    }
}
