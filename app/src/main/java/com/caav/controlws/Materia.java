package com.caav.controlws;

public class Materia {
    public String codmateria, nommateria, unidadesval;

    public Materia(){}

    public Materia(String cod, String nom, String unidades){
        this.codmateria=cod;
        this.nommateria=nom;
        this.unidadesval=unidades;
    }

    public String getCodmateria() {
        return codmateria;
    }

    public void setCodmateria(String codmateria) {
        this.codmateria = codmateria;
    }

    public String getNommateria() {
        return nommateria;
    }

    public void setNommateria(String nommateria) {
        this.nommateria = nommateria;
    }

    public String getUnidadesval() {
        return unidadesval;
    }

    public void setUnidadesval(String unidadesval) {
        this.unidadesval = unidadesval;
    }
}
