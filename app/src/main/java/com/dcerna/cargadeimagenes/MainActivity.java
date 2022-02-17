package com.dcerna.cargadeimagenes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] datos = new String[]{"aaa","bbb","ccc","ddd","jejeje","juasjuasjuas","asdasd"};

        RecyclerView recycler = findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        AdaptadorImagenes adaptadorImagenes = new AdaptadorImagenes(datos);
        recycler.setAdapter(adaptadorImagenes);
    }
}