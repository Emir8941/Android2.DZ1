package com.example.lesson21.onboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.lesson21.R;
import com.example.lesson21.databinding.FragmentOnBoardBinding;
import com.example.lesson21.utils.PrefHelper;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

public class OnBoardFragment extends Fragment {
    FragmentOnBoardBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentOnBoardBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewAdd();
    }

    private void viewAdd() {
        ViewAdapter viewAdapter = new ViewAdapter(getActivity().getSupportFragmentManager());
        binding.viewPager.setAdapter(viewAdapter);

    }
}