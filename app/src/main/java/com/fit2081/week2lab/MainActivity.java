package com.fit2081.week2lab;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private FragmentManager fragmentManager;
    private MaterialToolbar materialToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init FragmentManager
        fragmentManager = getSupportFragmentManager();

        //init DrawerLayout
        drawerLayout = findViewById(R.id.mainDrawerLayout);

        //init NavigationView
        NavigationView navigationView = findViewById(R.id.mainNavigationView);
        navigationView.setNavigationItemSelectedListener(
                new ListenerNavigation(drawerLayout, fragmentManager));

        //init MaterialToolbar
        materialToolbar = findViewById(R.id.mainMaterialToolbar);
        setSupportActionBar(materialToolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, materialToolbar, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState == null){
            fragmentManager.beginTransaction()
                    .replace(R.id.mainFragmentContainer, new FragmentBooks())
                    .addToBackStack(null)
                    .commit();
        }
    }
}