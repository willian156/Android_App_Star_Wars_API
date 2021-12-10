package com.example.trab_android;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private static Retrofit retrofit;


    ListView peopleList;
    ArrayAdapter<String> adapter;
    List<String> opcoes;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        peopleList = findViewById(R.id.PeopleList);
        opcoes = new ArrayList<>();
        connect();


        //definir formato de apresentação
        adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, opcoes);
        peopleList.setAdapter(adapter);

        //Identificar item selecionado
        peopleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String posicao = String.valueOf(i);
                Toast.makeText(MainActivity.this, posicao, Toast.LENGTH_SHORT).show();
            }
        });

    }



    public void connect(){
        // Chamada HTTP para a api de teste
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://swapi.dev/api/people";
        //Configuraçaõ para uma chamada HTTP GET, resultado armazenado em formato string
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Exibe o resultado recebido já formatado em JSON
                        try {
                            JSONObject people = new JSONObject(response);
                            opcoes.add(people.get("name")+","+
                                            people.get("height")+","+
                                            people.get("mass")+","+
                                            people.get("hair_color")+","+
                                            people.get("skin_color")+","+
                                            people.get("eye_color")+","+
                                            people.get("birth_year")+","+
                                            people.get("gender")+","+
                                            people.get("homeworld")+","+
                                            people.get("films")+","+
                                            people.get("species")+","+
                                            people.get("vehicles")+","+
                                            people.get("starships")+","+
                                            people.get("created")+","+
                                            people.get("edited")+","+
                                            people.get("url"));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Falha na requisição", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(stringRequest);
    }
}