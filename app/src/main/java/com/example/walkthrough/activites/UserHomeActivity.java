package com.example.walkthrough.activites;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.walkthrough.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class UserHomeActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        FrameLayout frameLayout;
        Toolbar toolbar = findViewById(R.id.toolbar);
        NavigationView navigationView = findViewById(R.id.navigation);
        drawerLayout = findViewById(R.id.drawerlayout);
        BottomNavigationView bottomNavigationView;
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(UserHomeActivity.this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.clothe: {
                        setTitle("Clothes Product");
                        DrawerLoadFragment(new ClotheFragment(), false);
                        break;
                    }
                    case R.id.tailor: {
                        setTitle("Tailor");
                        DrawerLoadFragment(new TailorFragment(), true);
                        break;
                    }

                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
        bottomNavigationView=findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.cart: {
                        setTitle("Cart");
                        LoadFragment(new CartFragment(), false);
                        break;
                    }
                    case R.id.category: {
                        setTitle("Category of Clothes and Tailor");
                        LoadFragment(new CategoryFragment(), false);
                        break;
                    }
                    case R.id.home: {
                        setTitle("Home");
                        LoadFragment(new HomeFragment(), true);
                        break;
                    }
                    case R.id.person: {
                        setTitle("Account");
                        LoadFragment(new AccountFragment(), false);
                        break;
                    }
                    case R.id.eye:
                    {
                        setTitle("Check Eye");
                        LoadFragment(new EyeFragment(),false);
                        break;
                    }

                }
                return true;
            }
        });
        bottomNavigationView.setSelectedItemId(R.id.home);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(this).inflate(R.menu.toolbar_navigation,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
  /*  public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemid=item.getItemId();
        if(item.getItemId()==R.id.profile){
            LoadFragment(new ProfileFragment(), false);

        }
        return super.onOptionsItemSelected(item);
    }*/

    public void onBackPressed() {
        // Close the navigation drawer if it's open
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    public void LoadFragment(Fragment fragment, boolean flag){
        if(flag==true){
            FragmentManager fragmentManager=getSupportFragmentManager();
            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.container,fragment).commit();

        }
        else {
            FragmentManager fragmentManager=getSupportFragmentManager();
            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container,fragment).commit();
        }
    }

    public void DrawerLoadFragment(Fragment fragment, boolean flag) {
        if (flag == true) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, fragment).commit();

        } else {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, fragment).commit();
        }
    }

  /*  public static class ViewPageTailorAdaptor extends FragmentPagerAdapter {
        public ViewPageTailorAdaptor(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return new PortfolioFragment();
        }

        @Override
        public int getCount() {
            return 1;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {

            return "Portfolio";

        }
    }

    public static class tailor_verification_codeFragment extends Fragment {


        public tailor_verification_codeFragment() {
            // Required empty public constructor
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.fragment_tailor_verification_code, container, false);
        }
    }*/
}












