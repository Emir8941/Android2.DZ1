package com.example.lesson21.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lesson21.model.NoteModel;
import com.example.lesson21.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.MyViewHolder> {
    List<NoteModel> list = new ArrayList<>();

    public void addTask(NoteModel text) {
        list.add(text);
        notifyDataSetChanged();
    }
    public NoteModel getNoteAt(int position) {
        return list.get(position);
    }

    public void setList(List<NoteModel> models) {
        list.clear();
        list.addAll(models);
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyViewHolder holder, int position) {
        holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void filterList(ArrayList<NoteModel> filteredList){
        list = filteredList;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView itemTitle, itemTime;
        public MyViewHolder(@NonNull @NotNull View binding) {
            super(binding);
            itemTitle = binding.findViewById(R.id.item_title);
            itemTime = binding.findViewById(R.id.item_time);
        }

        private void onBind(NoteModel s) {
           itemTitle.setText(s.getTitle());
           itemTime.setText(s.getDate());
        }
    }
}
