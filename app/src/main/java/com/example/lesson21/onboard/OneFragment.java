package com.example.lesson21.onboard;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lesson21.R;
import com.example.lesson21.databinding.FragmentOneBinding;
import com.example.lesson21.utils.PrefHelper;

public class OneFragment extends Fragment {
    FragmentOneBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOneBinding.inflate(inflater,container,false);
        initView();
        return binding.getRoot();
    }

    private void initView() {
        binding.btnSkip.setOnClickListener(v -> {
            PrefHelper.init(requireContext());
            PrefHelper.setOnBoardIsShow();
            close();
        });
    }
    private void close() {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
        navController.navigate(R.id.authFragment);
    }

}