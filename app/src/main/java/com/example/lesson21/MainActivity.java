package com.example.lesson21;

import android.os.Bundle;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.lesson21.databinding.ActivityMainBinding;
import com.example.lesson21.utils.PrefHelper;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    PrefHelper prefHelper = new PrefHelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        prefHelper.init(this);
       if (!PrefHelper.getOnBoardIsShow()){
           navController.navigate(R.id.onBoardFragment);
       }
        binding.appBarMain.fab.setOnClickListener(v -> {
            navController.navigate(R.id.action_nav_home_to_noteFragment);
        });
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        navigationChangedListener(navController);
    }

    private void navigationChangedListener(NavController navController){
        navController.addOnDestinationChangedListener(((controller, destination, arguments) -> {
            if (controller.getGraph().getStartDestination() == destination.getId()){
                binding.appBarMain.fab.show();
            }else {
                binding.appBarMain.fab.hide();
            }
        }));
   }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}