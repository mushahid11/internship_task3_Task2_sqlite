package com.example.task2_sqlite.adapter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.task2_sqlite.R;
import com.example.task2_sqlite.data.DatabaseHandler;
import com.example.task2_sqlite.model.Data;

import java.util.List;

import static android.media.CamcorderProfile.get;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {

    private final List<Data> todolist;
    private final Context context;
    private  DatabaseHandler handler;
    private  RecycleViewAdapter2 recycleViewAdapter2;


    public RecycleViewAdapter(List<Data> todolist, Context context) {
        this.todolist = todolist;
        this.context = context;
        handler=new DatabaseHandler(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.row_todo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final Data question = todolist.get(position);
        holder.data.setText(todolist.get(position).getData());
        holder.todo_date.setText(todolist.get(position).getDate());
        holder.todo_time.setText(todolist.get(position).getTime());
        holder.setIsRecyclable(false);
        Boolean checkData = handler.checkDatabaseForFavAndUnFav(todolist.get(position).getDate(),todolist.get(position).getTime());

        if(checkData){
            holder.img_heart.setImageResource(R.drawable.heart);
        }else{
            holder.img_heart.setImageResource(R.drawable.uncheckedheart);
        }

        holder.img_heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
       Boolean checkData = handler.checkDatabaseForFavAndUnFav(todolist.get(position).getDate(),todolist.get(position).getTime());

    if(checkData){
        holder.img_heart.setImageResource(R.drawable.uncheckedheart);
        handler.deleteFavData(question);

    }else{
        holder.img_heart.setImageResource(R.drawable.heart);
        handler.addDataToFav(question);
    }
            }
        });

        holder.img_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.deleteData(question);
                    notifyItemRemoved(position);
                    notifyDataSetChanged();
                todolist.remove(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return todolist.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView data,todo_date,todo_time;
        ImageView img_heart, img_delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            data = itemView.findViewById(R.id.row_textview);
            img_heart = itemView.findViewById(R.id.row_heart_image);
            img_delete = itemView.findViewById(R.id.row_delete_image);
            todo_date = itemView.findViewById(R.id.row_todo_date);
            todo_time = itemView.findViewById(R.id.row_todo_time);
        }
    }
}
