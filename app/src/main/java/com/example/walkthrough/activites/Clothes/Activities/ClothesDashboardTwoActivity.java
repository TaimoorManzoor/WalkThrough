package com.example.walkthrough.activites.Clothes.Activities;

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
import com.example.walkthrough.activites.Clothes.bottomNavigationFragments.ClothesUpdateProfileFragment;
import com.example.walkthrough.activites.Clothes.bottomNavigationFragments.NewClothesHomeFragment;
import com.example.walkthrough.activites.Clothes.bottomNavigationFragments.TotalEarningsFragment;
import com.example.walkthrough.activites.Clothes.fragments.ClothesProductFragment;
import com.example.walkthrough.activites.MainActivity;
import com.example.walkthrough.activites.Tailor.Fragment.NewTailorUpdateProfileFragment;
import com.example.walkthrough.activites.Tailor.Fragment.UploadPortfolioFragment;
import com.example.walkthrough.activites.Tailor.TailerDashboardActivity;
import com.example.walkthrough.databinding.ActivityClothesDashboard2Binding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class ClothesDashboardTwoActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private ActivityClothesDashboard2Binding binding;
    DrawerLayout drawerLayout;
    BottomNavigationView bottomNavigationView;
    Toolbar toolbar;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityClothesDashboard2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        toolbar = findViewById(R.id.toolbar_C);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Ez Silai Clothes Panel");

        drawerLayout = findViewById(R.id.clothesDrawerLayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        openFragment(new NewClothesHomeFragment());

        navigationView = findViewById(R.id.clothesNavigationDrawer);
        navigationView.setNavigationItemSelectedListener(this);

        bottomNavigationView = findViewById(R.id.clothesBottomNavigationView);
        bottomNavigationView.setBackground(null);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.cloth_bottom_home) {
                    openFragment(new NewClothesHomeFragment());
                    return true;
                } else if (itemId == R.id.cloth_bottom_addProduct) {
                    openFragment(new ClothesProductFragment());
                    return true;
                } else if (itemId == R.id.cloth_bottom_totalEarning) {
                    openFragment(new TotalEarningsFragment());
                    return true;
                } else if (itemId == R.id.cloth_bottom_updateProfile) {
                    openFragment(new ClothesUpdateProfileFragment());
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

        if (itemId == R.id.clothesSignout) {
            FirebaseAuth.getInstance().signOut();
            ProgressDialog progressDialog=new ProgressDialog(ClothesDashboardTwoActivity.this);
            progressDialog.setMessage("Signout");
            progressDialog.show();
            PrefManager prefManager=new PrefManager(ClothesDashboardTwoActivity.this);
            prefManager.setCurrentstatus("");
            progressDialog.dismiss();
            Toast.makeText(ClothesDashboardTwoActivity.this, "Signout", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));

        } else if (itemId == R.id.cltohesOrderReceived) {
            startActivity(new Intent(ClothesDashboardTwoActivity.this, RequestedClothesOrderActivity.class));
//            openFragment(new UploadPortfolioFragment());
        }
        drawerLayout.closeDrawer(GravityCompat.START);

        return false;
    }
}