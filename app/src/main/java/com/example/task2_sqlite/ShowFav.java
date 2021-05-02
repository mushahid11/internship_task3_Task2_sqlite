package com.example.task2_sqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.task2_sqlite.adapter.RecycleViewAdapter;
import com.example.task2_sqlite.adapter.RecycleViewAdapter2;
import com.example.task2_sqlite.data.DatabaseHandler;
import com.example.task2_sqlite.model.Data;

import java.util.ArrayList;
import java.util.List;

public class ShowFav extends AppCompatActivity {


   private RecyclerView rvTodoFav;
   private RecycleViewAdapter2 recycleViewAdapter2;
   private List<Data> list;
   private   DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_fav);

        rvTodoFav = findViewById(R.id.rvTodoFav);
        db = new DatabaseHandler(this);
        list = new ArrayList<>();
        rvTodoFav.setHasFixedSize(true);
        list = db.getAllDetailFave();
        recycleViewAdapter2 = new RecycleViewAdapter2(list,this);
        rvTodoFav.setAdapter(recycleViewAdapter2);
    }
}
