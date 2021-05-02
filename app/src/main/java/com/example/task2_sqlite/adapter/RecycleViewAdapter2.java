package com.example.task2_sqlite.adapter;

import android.content.Context;
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

public class RecycleViewAdapter2 extends RecyclerView.Adapter<RecycleViewAdapter2.ViewHolder> {

private List<Data> todolist;
private Context context;
private DatabaseHandler handler;


    public RecycleViewAdapter2(List<Data> todolist, Context context) {
        this.todolist = todolist;
        this.context = context;
        handler = new DatabaseHandler(context);
    }

    @NonNull
@Override
public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
        .inflate(R.layout.row_fav, parent, false);
        return new ViewHolder(view);
        }

@Override
public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
final Data question = todolist.get(position);
        holder.data.setText(todolist.get(position).getData());
        holder.fav_date.setText(todolist.get(position).getDate());
        holder.fav_time.setText(todolist.get(position).getTime());


        holder.row_fav_img.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
        handler.deleteFavData(question);
        todolist.remove(position);
        notifyItemRemoved(position);
        }
        });
        }

@Override
public int getItemCount() {
        return todolist.size();
        }

public static class ViewHolder extends RecyclerView.ViewHolder {

    private TextView data,fav_date,fav_time;
    private ImageView row_fav_img;


    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        data = itemView.findViewById(R.id.row_fav_textview);
        row_fav_img = itemView.findViewById(R.id.row_fav_image);
        fav_date = itemView.findViewById(R.id.row_fav_date);
        fav_time = itemView.findViewById(R.id.row_fav_time);
    }
}
}
