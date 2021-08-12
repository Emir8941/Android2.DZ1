package com.example.lesson21.ui.note;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.lesson21.Model.TaskModel;
import com.example.lesson21.R;
import com.example.lesson21.databinding.FragmentNoteBinding;


public class NoteFragment extends Fragment {
    FragmentNoteBinding binding;
    TaskModel taskModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentNoteBinding.inflate(inflater, container, false);
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
        getTitle(navController);
        return binding.getRoot();
    }
    private void getTitle(NavController navController) {
        binding.btnSave.setOnClickListener(v -> {
            Bundle args = new Bundle();
            String title = binding.etTitle.getText().toString().trim();
            taskModel = new TaskModel(title);
            args.putSerializable("key", taskModel);
            navController.navigateUp();
        });
    }
}