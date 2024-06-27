package com.example.semana7_evaluacionapi.Models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Articulo {
    private String tituloArticulo;
    private String autorArticulo;

    public Articulo(JSONObject a) throws JSONException {
        tituloArticulo = a.getString("title").toString();
        autorArticulo = a.getString("date_published").toString();

        JSONArray autoresArray = a.getJSONArray("authors");
        StringBuilder nombresBuilder = new StringBuilder();
        for (int i = 0; i < autoresArray.length(); i++) {
            JSONObject autor = autoresArray.getJSONObject(i);
            String nombres = autor.getString("nombres");
            nombresBuilder.append(nombres);
            if (i < autoresArray.length() - 1) {
                nombresBuilder.append(", ");
            }
        }
        autorArticulo = nombresBuilder.toString();
    }

    public static ArrayList<Articulo> JsonObjectsBuild(JSONArray datos) throws JSONException {
        ArrayList<Articulo> articulos = new ArrayList<>();
        for (int i = 0; i < datos.length(); i++) {
            articulos.add(new Articulo(datos.getJSONObject(i)));
        }
        return articulos;
    }




    public String getTituloArticulo() {
        return tituloArticulo;
    }

    public void setTituloArticulo(String tituloArticulo) {
        this.tituloArticulo = tituloArticulo;
    }

    public String getAutorArticulo() {
        return autorArticulo;
    }

    public void setAutorArticulo(String autorArticulo) {
        this.autorArticulo = autorArticulo;
    }
}
