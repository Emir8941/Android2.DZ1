package com.example.lesson21.ui.note;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.lesson21.R;
import com.example.lesson21.databinding.FragmentNoteBinding;
import com.example.lesson21.utils.Constants;


public class NoteFragment extends Fragment {
    FragmentNoteBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentNoteBinding.inflate(inflater, container, false);
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
        initView(navController);
        return binding.getRoot();
    }
    private void initView(NavController navController) {
        binding.btnBack.setOnClickListener(v -> {
            close();
        });
        binding.btnSave.setOnClickListener(v -> {
            String text = binding.etText.getText().toString().trim();
            if (TextUtils.isEmpty(text)) {
                binding.etText.setError("Введите текст");
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putSerializable(Constants.BUNDLE_KEY, text);
            getParentFragmentManager().setFragmentResult(Constants.REQUEST_KEY, bundle);
            close();
        });
    }

    private void close() {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
        navController.navigateUp();
    }
    @Override
    public void onResume() {
        super.onResume();
        ActionBar actionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }
    @Override
    public void onStop() {
        super.onStop();
        ActionBar actionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.show();
        }
    }
}