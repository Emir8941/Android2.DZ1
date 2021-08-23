package com.example.lesson21.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lesson21.Model.NoteModel;
import com.example.lesson21.databinding.NoteItemBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.MyViewHolder> {
    NoteItemBinding binding;
     ArrayList<NoteModel> list = new ArrayList<>();

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

    @NonNull
    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        binding = NoteItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MyViewHolder(binding.getRoot());
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
        public MyViewHolder(@NonNull @NotNull View binding) {
            super(binding);
        }

        private void onBind(NoteModel s) {
            binding.itemTitle.setText(s.getTitle());
            binding.itemTime.setText(s.getDate());
        }
    }
}
