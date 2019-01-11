package com.example.nicho.scrolltotop;

import android.os.Bundle;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.rvscroller.RVScroller;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FloatingActionButton floatingActionButton;
    private MyRecyclerViewAdapter adapter;
    private RequestQueue requestQueue;
    private ArrayList<ItemModel> itemModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.main_recyclerview);
        floatingActionButton = findViewById(R.id.main_fab);
        adapter = new MyRecyclerViewAdapter(itemModels, this);
        requestQueue = Volley.newRequestQueue(this);

        setupRecyclerView();

        JsonParser.parseJsonToAdapter(getString(R.string.json_data_url), itemModels, adapter, requestQueue);


    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(adapter);

        RVScroller rvScroller = new RVScroller.Custom(floatingActionButton, true).withDelay(RVScroller.NO_DELAY).build();

        recyclerView.addOnScrollListener(rvScroller);
    }
}
