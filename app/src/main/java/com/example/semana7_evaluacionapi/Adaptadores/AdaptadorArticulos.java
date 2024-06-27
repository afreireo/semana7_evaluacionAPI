package com.example.semana7_evaluacionapi.Adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.semana7_evaluacionapi.ListaArticulos;
import com.example.semana7_evaluacionapi.Models.Articulo;
import com.example.semana7_evaluacionapi.Models.Volumen;
import com.example.semana7_evaluacionapi.R;

import java.util.List;

public class AdaptadorArticulos extends RecyclerView.Adapter<AdaptadorArticulos.ArticuloViewHolder> {
    private Context context;
    private List<Articulo> lstArticulos;
    public AdaptadorArticulos(Context mCtx, List<Articulo> articulos) {
        this.lstArticulos = articulos;
        context = (Context) mCtx;
    }
    public AdaptadorArticulos.ArticuloViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.ly__itemarticulo, null);
        return new AdaptadorArticulos.ArticuloViewHolder(view);
    }

    public void onBindViewHolder(AdaptadorArticulos.ArticuloViewHolder holder, int position) {
        Articulo articulo = lstArticulos.get(position);

        holder.textViewTituloArticulo.setText(articulo.getTituloArticulo());
        holder.textViewAAutores.setText(articulo.getAutorArticulo());

    }

    public int getItemCount() {return lstArticulos.size();}

    class ArticuloViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTituloArticulo, textViewAAutores;
        public ArticuloViewHolder(View itemView) {
            super(itemView);
            textViewTituloArticulo = itemView.findViewById(R.id.txtTituloArticulo);
            textViewAAutores = itemView.findViewById(R.id.txtAutoresArticulo);

        }
    }
}
