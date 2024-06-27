package com.example.semana7_evaluacionapi.Adaptadores;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.semana7_evaluacionapi.Models.Revista;
import com.example.semana7_evaluacionapi.R;

import java.util.ArrayList;

public class AdaptadorRevistas extends ArrayAdapter<Revista> {
    public AdaptadorRevistas(Context context, ArrayList<Revista> datos) {
        super(context, R.layout.ly_revista, datos);
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.ly_revista, null);

        TextView lblTitulo = (TextView)item.findViewById(R.id.lblTitulo);
        lblTitulo.setText(getItem(position).getTitulo());

        TextView lblAbrev = (TextView)item.findViewById(R.id.lblDoi);
        lblAbrev.setText(getItem(position).getAbrev());

        TextView lblDescripcion = (TextView)item.findViewById(R.id.lblPublicado);
        lblDescripcion.setText(Html.fromHtml(getItem(position).getDescripcion()));

        ImageView imageView = (ImageView)item.findViewById(R.id.imgCover);
        Glide.with(this.getContext()).load(getItem(position).getUrlLogo()).into(imageView);

        return(item);
    }
}
