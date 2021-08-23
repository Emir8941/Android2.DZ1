package com.example.lesson21.onboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.lesson21.R;
import com.example.lesson21.databinding.FragmentHomeBinding;
import com.example.lesson21.databinding.FragmentOnBoardBinding;
import com.example.lesson21.utils.PrefHelper;

import org.jetbrains.annotations.NotNull;

public class OnBoardFragment extends Fragment {
    FragmentOnBoardBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       PrefHelper.init(requireContext());
       PrefHelper.setOnBoardIsShow();
        return inflater.inflate(R.layout.fragment_on_board, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewAdd();
    }

    private void viewAdd() {
        ViewAdapter viewPagerAdapter = new ViewAdapter(getActivity().getSupportFragmentManager());
        binding.viewPager.setAdapter(viewPagerAdapter);

    }
}