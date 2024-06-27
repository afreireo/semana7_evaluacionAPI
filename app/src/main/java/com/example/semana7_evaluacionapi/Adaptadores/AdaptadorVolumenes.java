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
import com.example.semana7_evaluacionapi.Models.Volumen;
import com.example.semana7_evaluacionapi.R;

import java.util.List;

public class AdaptadorVolumenes extends RecyclerView.Adapter<AdaptadorVolumenes.VolumenViewHolder> {
    private Context context;
    private List<Volumen> lstVolumenes;
    public AdaptadorVolumenes(Context mCtx, List<Volumen> volumenes) {
        this.lstVolumenes = volumenes;
        context = (Context) mCtx;
    }
    @Override
    public VolumenViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.ly_itemvolumen, null);
        return new VolumenViewHolder(view);
    }

    public void onBindViewHolder(VolumenViewHolder holder, int position) {
        Volumen volumen = lstVolumenes.get(position);

        holder.textViewTitulo.setText(volumen.getTitulo());
        holder.textViewVol.setText(volumen.getVolumen());
        holder.textViewFecha.setText(volumen.getFecha());
        Glide.with(context)
                .load(volumen.getUrlPortada())
                .into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ListaArticulos.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("IssueID", volumen.getIssueID());
                Toast.makeText(context, "ID : " + volumen.getIssueID(), Toast.LENGTH_SHORT).show();
                context.startActivity(intent);
            }
        });
    }

    public int getItemCount() {return lstVolumenes.size();}

    class VolumenViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitulo, textViewVol, textViewFecha;
        ImageView imageView;
        public VolumenViewHolder(View itemView) {
            super(itemView);
            textViewTitulo= itemView.findViewById(R.id.txtTituloArticulo);
            textViewVol = itemView.findViewById(R.id.txtAutoresArticulo);
            textViewFecha = itemView.findViewById(R.id.txtFecha);
            imageView = itemView.findViewById(R.id.imgPortada);

        }
    }
}
