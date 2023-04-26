package com.fit2081.week2lab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class FragmentFruit extends Fragment {
    public FragmentFruit(){
        setHasOptionsMenu(false);
    }
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_fruit, container, false);
        return view;
    }
//    @Override
//    public void onCreateOptionsMenu(@NonNull Menu menu, MenuInflater inflater){
//        inflater.inflate(R.menu.fruit_appbar, menu);
//        super.onCreateOptionsMenu(menu, inflater);
//    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item){
//        final int drawerOptionWHAT = R.id.drawerWHAT;
//        switch (item.getItemId()){
//            case drawerOptionWHAT:
//                ;
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
}
