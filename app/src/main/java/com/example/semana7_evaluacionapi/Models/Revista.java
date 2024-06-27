package com.example.semana7_evaluacionapi.Models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Revista {
    private String Titulo;
    private String Abrev;
    private String Descripcion;
    private String urlLogo;
    private String JournalID;

    public Revista(JSONObject a) throws JSONException {
        JournalID = a.getString("journal_id").toString();
        Titulo = a.getString("name").toString();
        Abrev = a.getString("abbreviation").toString() ;
        Descripcion = a.getString("description").toString() ;
        urlLogo = a.getString("portada").toString() ;
    }

    public static ArrayList<Revista> JsonObjectsBuild(JSONArray datos) throws JSONException {
        ArrayList<Revista> revistas = new ArrayList<>();
        for (int i = 0; i < datos.length(); i++) {
            revistas.add(new Revista(datos.getJSONObject(i)));
        }
        return revistas;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }
    public String getAbrev() {
        return Abrev;
    }

    public void setAbrev(String abrev) {
        Abrev = abrev;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getUrlLogo() {
        return urlLogo;
    }

    public void setUrlLogo(String urlLogo) {
        this.urlLogo = urlLogo;
    }

    public String getJournalID() {
        return JournalID;
    }

    public void setJournalID(String journalID) {
        JournalID = journalID;
    }
}
