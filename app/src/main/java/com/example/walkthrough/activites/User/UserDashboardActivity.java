package com.example.walkthrough.activites.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.walkthrough.R;
import com.example.walkthrough.SharedPrefPkg.PrefManager;
import com.example.walkthrough.activites.MainActivity;
import com.example.walkthrough.activites.User.Activtiy.RequestedClothesUserActivity;
import com.example.walkthrough.activites.User.fragments.BottomNavigation.HomeFragment;
import com.example.walkthrough.activites.User.fragments.BottomNavigation.UpdateUserProfileFragment;
import com.example.walkthrough.activites.User.fragments.BottomNavigation.TailorsFragment;
import com.example.walkthrough.activites.User.fragments.BottomNavigation.TrackFragment;
import com.example.walkthrough.activites.User.fragments.BottomNavigation.UserConfrimedBookedTailorFragment;
import com.example.walkthrough.activites.User.fragments.DrawerNavigation.ListofClothesToUserFragment;
import com.example.walkthrough.activites.User.fragments.DrawerNavigation.UsersSettingsFragment;
import com.example.walkthrough.activites.User.fragments.RequestUserFragment;
import com.example.walkthrough.databinding.ActivityUserDashboardBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class UserDashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private ActivityUserDashboardBinding binding;
    DrawerLayout drawerLayout;
    BottomNavigationView bottomNavigationView;
    Toolbar toolbar;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Ez Silai Customer Panel");
        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        openFragment(new HomeFragment());

        navigationView = findViewById(R.id.navigation_drawer);
        navigationView.setNavigationItemSelectedListener(this);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setBackground(null);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.home_bottom) {
                    openFragment(new HomeFragment());
                    return true;
                } else if (itemId == R.id.track_bottom) {
                    openFragment(new RequestUserFragment());
                    return true;
                } else if (itemId == R.id.tailors_bottom) {
                    openFragment(new TailorsFragment());
                    return true;
                } else if (itemId == R.id.profile_bottom) {
                    openFragment(new UpdateUserProfileFragment());
                }
                return false;
            }
        });
    }

    private void openFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,fragment).commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.sign_out) {
            FirebaseAuth.getInstance().signOut();
            ProgressDialog progressDialog=new ProgressDialog(UserDashboardActivity.this);
            progressDialog.setMessage("Signout");
            progressDialog.show();
            PrefManager prefManager=new PrefManager(UserDashboardActivity.this);
            prefManager.setCurrentstatus("");
            progressDialog.dismiss();
            Toast.makeText(UserDashboardActivity.this, "Signout", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));

        } else if (itemId == R.id.settings) {
            openFragment(new UserConfrimedBookedTailorFragment());
        }
        else if (itemId == R.id.Clotheslist) {
            openFragment(new ListofClothesToUserFragment());
        }
        else if (itemId == R.id.RequestedClotheslist) {
            startActivity(new Intent(UserDashboardActivity.this, RequestedClothesUserActivity.class));
        }
        drawerLayout.closeDrawer(GravityCompat.START);

        return false;
    }
}