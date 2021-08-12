package com.example.lesson21.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.lesson21.Model.TaskModel;
import com.example.lesson21.R;
import com.example.lesson21.adapter.NoteAdapter;
import com.example.lesson21.databinding.FragmentHomeBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    NoteAdapter adapter;
    List<TaskModel> list = new ArrayList<>();

    private FragmentHomeBinding binding;
    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
    binding = FragmentHomeBinding.inflate(inflater, container, false);
       binding.recyclerView.setAdapter(adapter);
    getData();
    return binding.getRoot();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    private void getData(){
        getActivity().getSupportFragmentManager().setFragmentResultListener("key", getViewLifecycleOwner(), new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull @NotNull String requestKey, @NonNull @NotNull Bundle result) {
                TaskModel model = (TaskModel) result.getSerializable("key");
                adapter.addTask(model);
                list.add(model);
            }
        });
    }
}