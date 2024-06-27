package com.example.semana7_evaluacionapi;

import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.semana7_evaluacionapi.Adaptadores.AdaptadorArticulos;
import com.example.semana7_evaluacionapi.Adaptadores.AdaptadorVolumenes;
import com.example.semana7_evaluacionapi.Models.Articulo;


import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class ListaArticulos extends AppCompatActivity {

    private RecyclerView rvListaArticulos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.lista_articulos);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        rvListaArticulos = (RecyclerView) findViewById(R.id.rvListaArticulos);
        rvListaArticulos.setHasFixedSize(true);
        rvListaArticulos.setLayoutManager(new LinearLayoutManager(this));
        rvListaArticulos.setItemAnimator(new DefaultItemAnimator());


        String url = "https://revistas.uteq.edu.ec/ws/pubs.php?i_id=" + getIntent().getStringExtra("IssueID");
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        ArrayList<Articulo> lstArticulos = new ArrayList<Articulo> ();
                        try {
                            JSONArray JSONlistaArticulo= new JSONArray(response);
                            lstArticulos = Articulo.JsonObjectsBuild(JSONlistaArticulo);
                            int resId = R.anim.layout_animation_down_to_up;
                            LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getApplicationContext(),
                                    resId);
                            rvListaArticulos.setLayoutAnimation(animation);
                            AdaptadorArticulos adapatorArticulo = new AdaptadorArticulos(getApplicationContext(), lstArticulos);
                            rvListaArticulos.setAdapter(adapatorArticulo);
                        }
                        catch (JSONException e)
                        {
                            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(stringRequest);
    }
}