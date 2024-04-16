package com.example.walkthrough.activites.Tailor;

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
import android.view.Gravity;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.walkthrough.R;
import com.example.walkthrough.SharedPrefPkg.PrefManager;
import com.example.walkthrough.activites.MainActivity;
import com.example.walkthrough.activites.Tailor.Fragment.NewOrderTFragment;
import com.example.walkthrough.activites.Tailor.Fragment.NewTTHomeFragment;
import com.example.walkthrough.activites.Tailor.Fragment.NewTailorUpdateProfileFragment;
import com.example.walkthrough.activites.Tailor.Fragment.OrderFragmentTailor;
import com.example.walkthrough.activites.Tailor.Fragment.TailorTotalEarningFragment;
import com.example.walkthrough.activites.Tailor.Fragment.UploadPortfolioFragment;
import com.example.walkthrough.activites.User.fragments.DrawerNavigation.UsersSettingsFragment;
import com.example.walkthrough.databinding.ActivityTailerDashboardBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class TailerDashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private ActivityTailerDashboardBinding binding;
    DrawerLayout drawerLayout;
    BottomNavigationView bottomNavigationView;
    Toolbar toolbar;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTailerDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Ez Silai Tailor Panel");
/*
        TextView titleTextView = new TextView(this);
        titleTextView.setText("Your Text Here");
        titleTextView.setTextSize(18); // Set the text size as needed
        titleTextView.setTextColor(getResources().getColor(android.R.color.white)); // Set text color

        // Set layout parameters for the TextView
        Toolbar.LayoutParams layoutParams = new Toolbar.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.gravity = Gravity.CENTER; // Set text gravity to center
        titleTextView.setLayoutParams(layoutParams);

        // Add the TextView to the Toolbar
        toolbar.addView(titleTextView);*/

        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        openFragment(new NewTTHomeFragment());

        navigationView = findViewById(R.id.navigation_drawer);
        navigationView.setNavigationItemSelectedListener(this);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setBackground(null);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.home) {
                    openFragment(new NewTTHomeFragment());
                    return true;
                } else if (itemId == R.id.order) {
                    openFragment(new NewOrderTFragment());
                    return true;
                } else if (itemId == R.id.profile) {
                    openFragment(new NewTailorUpdateProfileFragment());
                    return true;
                } else if (itemId == R.id.RequestOrder) {
//                    openFragment(new OrderFragmentTailor());
                    startActivity(new Intent(TailerDashboardActivity.this,PendingreqOrderCheckerActivity.class));
                }
                else if (itemId == R.id.bookedusermenuID) {
                    openFragment(new TailorTotalEarningFragment());
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

        if (itemId == R.id.tailorSignout) {
            FirebaseAuth.getInstance().signOut();
            ProgressDialog progressDialog=new ProgressDialog(TailerDashboardActivity.this);
            progressDialog.setMessage("Signout");
            progressDialog.show();
            PrefManager prefManager=new PrefManager(TailerDashboardActivity.this);
            prefManager.setCurrentstatus("");
            progressDialog.dismiss();
            Toast.makeText(TailerDashboardActivity.this, "Signout", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));

        } else if (itemId == R.id.OrderReceived) {
            openFragment(new UploadPortfolioFragment());
        }
        drawerLayout.closeDrawer(GravityCompat.START);

        return false;
    }
}