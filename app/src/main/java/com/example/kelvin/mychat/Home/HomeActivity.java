package com.example.kelvin.mychat.Home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.kelvin.mychat.R;
import com.example.kelvin.mychat.Utils.BottomNavigationViewHelper;

/**
 * Created by kelvin on 3/29/18.
 */

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivity";
    private static  final  int ACTIVITY_NUM = 3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comingsoon);
        setUpBottomNavigationView();
    }
    private void  setUpBottomNavigationView(){
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.enableNavigation(HomeActivity.this, bottomNavigationView );

        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }
}
