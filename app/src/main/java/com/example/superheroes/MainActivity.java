package com.example.superheroes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RequestQueue requestQueue;
    static ArrayList<Heroe> list;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestQueue = Volley.newRequestQueue(this);
        makePetition();
        inflateView();
    }

    void inflateView(){

    }

    void makePetition(){
        String url = "https://simplifiedcoding.net/demos/view-flipper/heroes.php";
        JsonRequest jsonRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                response -> {
                    try {
                        JSONArray jsonArray = response.getJSONArray("heroes");
                        Type listType = new TypeToken<ArrayList<Heroe>>(){}.getType();
                        Gson gson = new Gson();
                        ArrayList<Heroe> list = gson.fromJson(jsonArray.toString(), listType);
                        RecyclerView recyclerView = findViewById(R.id.rvSuperHeroes);
                        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),2);
                        recyclerView.setLayoutManager(layoutManager);

                        for(Heroe heroe: list) {
                            String heroeS = heroe.getName();
                            heroeS += "\n" + heroe.getImageurl();
                            Log.d("HOLI3",heroeS);
                        }

                        AdaptadorHeroes adaptadorHeroes = new AdaptadorHeroes(getApplicationContext(),list);
                        recyclerView.setAdapter(adaptadorHeroes);
                    }catch (JSONException exception){

                    }
                    Log.d("HOLI",response.toString());
                },
                error -> {
                    Log.d("HOLI","error");
                }
        );
        requestQueue.add(jsonRequest);
    }
}