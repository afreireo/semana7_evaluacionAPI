package com.example.semana7_evaluacionapi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.semana7_evaluacionapi.Adaptadores.AdaptadorRevistas;
import com.example.semana7_evaluacionapi.Models.Revista;

import org.json.JSONArray;
import org.json.JSONException;

import java.nio.BufferUnderflowException;
import java.util.ArrayList;

public class ListaRevistas extends AppCompatActivity {

    ListView lstRevistas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.lista_revistas);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        ListView lstRevistas = findViewById(R.id.lstRevistas);

        lstRevistas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String ID = ((Revista)parent.getItemAtPosition(position)).getJournalID();

                Toast.makeText(ListaRevistas.this, "ID : " + ID, Toast.LENGTH_SHORT).show();

                //ABRIR ACTIVIDAD 2
                Intent intent = new Intent(ListaRevistas.this, ListaVolumenes.class);
                intent.putExtra("IDJournal", ID);
                startActivity(intent);
            }
        });



        String url = "https://revistas.uteq.edu.ec/ws/journals.php";
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray JSONlistaRevista = new JSONArray(response);
                            ArrayList<Revista> revistas = Revista.JsonObjectsBuild(JSONlistaRevista);
                            AdaptadorRevistas adaptadorRevistas = new AdaptadorRevistas(getApplicationContext(), revistas);
                            lstRevistas.setAdapter(adaptadorRevistas);
                        } catch (JSONException e) {
                            Log.e("ERROR", e.toString());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("ERROR", error.toString());
            }
        });

        queue.add(stringRequest);
    }

}