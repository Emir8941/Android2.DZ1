package com.example.lesson21.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lesson21.Model.TaskModel;
import com.example.lesson21.R;
import com.example.lesson21.databinding.NoteItemBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.MyViewHolder> {
    NoteItemBinding binding;
    ArrayList<TaskModel> list = new ArrayList<>();

    @NonNull
    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false);
        return new MyViewHolder(view);
    }
    public void addTask(TaskModel taskModel) {
        this.list.add(taskModel);
        notifyDataSetChanged();
    }
    @Override
    public void onBindViewHolder(@NonNull @NotNull MyViewHolder holder, int position) {
        holder.onBind(list.get(position));
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle;
        public MyViewHolder(@NonNull @NotNull View binding) {
            super(binding);
            txtTitle = binding.findViewById(R.id.item_title);
        }
        private void onBind(TaskModel s) {
        }
    }
}
