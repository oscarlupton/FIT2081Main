package com.fit2081.week2lab;

import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.navigation.NavigationView;

public class ListenerNavigation implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout drawerLayout;
    private FragmentManager fragmentManager;
    public ListenerNavigation(DrawerLayout drawerLayout, FragmentManager fragmentManager){
        this.drawerLayout = drawerLayout;
        this.fragmentManager = fragmentManager;
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        final int drawerOptionHome = R.id.drawerBooks;
        final int drawerOptionFruit = R.id.drawerFruit;
        switch (item.getItemId()){
            case drawerOptionHome:
                fragmentManager.popBackStack(); //remove previous fragment before replacing
                fragmentManager.beginTransaction()
                        .replace(R.id.mainFragmentContainer, new FragmentBooks())
                        .addToBackStack(null)
                        .commit();
                break;
            case drawerOptionFruit:
                fragmentManager.popBackStack();
                fragmentManager.beginTransaction()
                        .replace(R.id.mainFragmentContainer, new FragmentFruit())
                        .addToBackStack(null)
                        .commit();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}