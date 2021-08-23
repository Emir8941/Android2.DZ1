package com.example.lesson21.onboard;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import org.jetbrains.annotations.NotNull;

public class ViewAdapter extends FragmentPagerAdapter {
    public ViewAdapter(@NonNull @NotNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @NotNull
    @Override
    public Fragment getItem(int position) {
       switch (position){
           case 0:
               return new OneFragment();
           case 1:
               return new TwoFragment();
           case 2:
               return new ThreeFragment();
       }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
