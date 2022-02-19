package com.dcerna.cargadeimagenes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recycler = findViewById(R.id.recycler);
        queue = Volley.newRequestQueue(this);



        JsonRequest jsonRequest = new JsonObjectRequest(
                Request.Method.GET,
                "https://simplifiedcoding.net/demos/view-flipper/heroes.php",
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();
                        Log.d("img", response.toString());
                        Gson gson = new Gson();
                        Type lsAlbumType = new TypeToken<List<HeroImg>>(){}.getType();

                        List<HeroImg> lsAlbum = null;
                        try {
                            lsAlbum =  gson.fromJson(response.get("heroes").toString(), lsAlbumType);
                            for(HeroImg it : lsAlbum){
                                Log.d("heroes", "name:" + it.getName() + "\nurl: " + it.getImageurl());
                                //requestImageMethod(it.getImageurl());
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.d("sas", "hola ini");
                        recycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        AdaptadorImagenes adapter = new AdaptadorImagenes(lsAlbum, getApplicationContext());
                        recycler.setAdapter(adapter);

                    }
                },
                error -> {
                    Log.d("Error", error.getMessage());
                    error.printStackTrace();
                }
        );

        queue.add(jsonRequest);
    }
}