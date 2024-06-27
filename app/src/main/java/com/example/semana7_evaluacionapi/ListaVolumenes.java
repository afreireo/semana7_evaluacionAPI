package com.example.semana7_evaluacionapi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
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
import com.example.semana7_evaluacionapi.Adaptadores.AdaptadorRevistas;
import com.example.semana7_evaluacionapi.Adaptadores.AdaptadorVolumenes;
import com.example.semana7_evaluacionapi.Models.Revista;
import com.example.semana7_evaluacionapi.Models.Volumen;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class ListaVolumenes extends AppCompatActivity {
    private RecyclerView rvListaVolumenes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.lista_volumenes);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        rvListaVolumenes= (RecyclerView) findViewById(R.id.rvListaVolumenes);
        rvListaVolumenes.setHasFixedSize(true);
        rvListaVolumenes.setLayoutManager(new LinearLayoutManager(this));
        rvListaVolumenes.setItemAnimator(new DefaultItemAnimator());

        Bundle b = this.getIntent().getExtras();

        String url = "https://revistas.uteq.edu.ec/ws/issues.php?j_id=" + getIntent().getStringExtra("IDJournal");
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        ArrayList<Volumen> lstVolumen = new ArrayList<Volumen> ();
                        try {
                            JSONArray JSONlistaVolumen= new JSONArray(response);
                            lstVolumen = Volumen.JsonObjectsBuild(JSONlistaVolumen);
                            int resId = R.anim.layout_animation_down_to_up;
                            LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getApplicationContext(),
                                    resId);
                            rvListaVolumenes.setLayoutAnimation(animation);
                            AdaptadorVolumenes adapatorVolumen = new AdaptadorVolumenes(getApplicationContext(), lstVolumen);
                            rvListaVolumenes.setAdapter(adapatorVolumen);
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