package com.example.lesson21.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.lesson21.adapter.NoteAdapter;
import com.example.lesson21.databinding.FragmentHomeBinding;
import com.example.lesson21.utils.Constants;

public class HomeFragment extends Fragment {
    private NoteAdapter adapter;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        binding.recyclerView.setAdapter(adapter);
        unitView();
        getData();
        return binding.getRoot();
    }
    private void getData() {
        getParentFragmentManager().setFragmentResultListener(Constants.REQUEST_KEY, getViewLifecycleOwner(), (requestKey, result) -> {
            String text = result.getString(Constants.BUNDLE_KEY);
            adapter.addTask(text);
        });
    }
    private void unitView() {
        binding.recyclerView.setAdapter(adapter);
    }

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new NoteAdapter();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
