package com.caav.controlws;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ControlBDAlumno {
    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public ControlBDAlumno(Context ctx) {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    private class DatabaseHelper extends SQLiteOpenHelper {
        private static final String BASE_DATOS = "alumno.s3bd";
        private static final int VERSION = 1;

        public DatabaseHelper(Context context) {
            super(context, BASE_DATOS, null, VERSION);
        }

        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL("CREATE TABLE alumno(carnet VARCHAR(7) NOT NULL PRIMARY KEY, nombre VARCHAR(30)," +
                        "apellido VARCHAR(30), sexo VARCHAR(1), matganada INTEGER);");
                db.execSQL("CREATE TABLE materia(codmateria VARCHAR(6) NOT NULL PRIMARY KEY," +
                        "nommateria VARCHAR(30), unidadesval VARCHAR(1));");
                db.execSQL("CREATE TABLE nota(carnet VARCHAR(7) NOT NULL, codmateria VARCHAR(6) NOT NULL, " +
                        "ciclo VARCHAR(5), notafinal REAl, PRIMARY KEY(carnet, codmateria, ciclo));");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
    }

    public void abrir() throws SQLException {
        db = DBHelper.getWritableDatabase();
        return;
    }

    public void cerrar() {
        DBHelper.close();
    }
    public String insertar(Materia materia) {
        String regInsertados = "Registro Insertado Nº= ";
        long contador = 0;
        ContentValues mat = new ContentValues();
        mat.put("codmateria", materia.getCodmateria());
        mat.put("nommateria", materia.getNommateria());
        mat.put("unidadesval", materia.getUnidadesval());
        contador = db.insert("materia", null, mat);
        if (contador == -1 || contador == 0) {
            regInsertados = "Error al Insertar el registro, Registro Duplicado. Verificar insercion";
        } else {
            regInsertados = regInsertados + contador;
        } return regInsertados;
    }
}
