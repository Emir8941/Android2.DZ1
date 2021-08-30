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
import com.example.lesson21.model.NoteModel;
import com.example.lesson21.utils.App;
import com.example.lesson21.utils.Constants;
import java.text.SimpleDateFormat;
import java.util.Date;


public class NoteFragment extends Fragment {
    FragmentNoteBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentNoteBinding.inflate(inflater, container, false);
        initView();
        return binding.getRoot();
    }

    public String getDateConverter() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM HH:mm");
        Date date = new Date();
        return formatter.format(date);
    }

    private void initView() {
        binding.time.setText(getDateConverter());
        binding.btnBack.setOnClickListener(v -> {
            close();
        });
        binding.btnSave.setOnClickListener(v -> {
            String text = binding.etText.getText().toString().trim();
            if (TextUtils.isEmpty(text)) {
                binding.etText.setError("Введите текст");
                return;
            }
            NoteModel model = new NoteModel(text, getDateConverter());
            App.getDatabase().getDao().insertNote(model);
            Bundle bundle = new Bundle();
            bundle.putSerializable(Constants.BUNDLE_KEY, model);
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