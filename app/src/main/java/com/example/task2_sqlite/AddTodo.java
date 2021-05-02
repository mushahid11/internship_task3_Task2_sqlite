package com.example.task2_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.task2_sqlite.data.DatabaseHandler;
import com.example.task2_sqlite.model.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddTodo extends AppCompatActivity {

    private EditText et_todoData;
    private Button btn_save;
    private DatabaseHandler db;
    private String current_time = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);

        et_todoData = findViewById(R.id.et_todoData);
        btn_save = findViewById(R.id.btn_Save);
        db = new DatabaseHandler(this);

         // getting current_date
        final Date date = new Date();
        SimpleDateFormat postFormater = new SimpleDateFormat("dd-MM-yyyy");
        final String current_date = postFormater.format(date);

        final Handler someHandler = new Handler(getMainLooper());
        someHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // getting current_time
                java.util.Date time = new java.util.Date();
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                 current_time = sdf.format(time);
                someHandler.postDelayed(this, 1000);
            }
        }, 10);


        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.addData(new Data( et_todoData.getText().toString().trim(),current_date,current_time));
                Toast.makeText(AddTodo.this, "Data Add", Toast.LENGTH_SHORT).show();
                et_todoData.setText("");
            }
        });
    }
}