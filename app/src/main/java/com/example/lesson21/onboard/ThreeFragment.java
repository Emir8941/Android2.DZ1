package com.example.lesson21.onboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.lesson21.R;
import com.example.lesson21.databinding.FragmentThreeBinding;
import com.example.lesson21.utils.PrefHelper;
import com.google.firebase.auth.FirebaseAuth;


public class ThreeFragment extends Fragment {
    FragmentThreeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentThreeBinding.inflate(inflater,container,false);
        initView();
        return binding.getRoot();
    }
    private void initView() {
        binding.btnStart.setOnClickListener(v -> {
            PrefHelper.init(requireContext());
            PrefHelper.setOnBoardIsShow();
            auth();
        });
    }
   private void auth() {
        if (FirebaseAuth.getInstance().getCurrentUser()== null){
            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
            navController.navigate(R.id.authFragment);
            return;
        }


    }

}