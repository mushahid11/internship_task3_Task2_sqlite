package com.example.task2_sqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.task2_sqlite.adapter.RecycleViewAdapter;
import com.example.task2_sqlite.data.DatabaseHandler;
import com.example.task2_sqlite.model.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ViewTodo extends AppCompatActivity {

    private  RecyclerView recyclerView;
    private RecycleViewAdapter recycleViewAdapter;
    private List<Data> list;
    private DatabaseHandler db = new DatabaseHandler(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_todo);

        recyclerView = findViewById(R.id.rvTodo);
        db = new DatabaseHandler(this);
        list = new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        list = db.getAllContacts();
        recycleViewAdapter = new RecycleViewAdapter(list,this);
        recyclerView.setAdapter(recycleViewAdapter);
    }
}