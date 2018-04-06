package com.example.kelvin.mychat.Usay;

import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.kelvin.mychat.R;
import com.example.kelvin.mychat.Utils.BottomNavigationViewHelper;
import com.example.kelvin.mychat.Utils.ServiceUtil;


public class MainActivity extends AppCompatActivity {

    private static  final  int ACTIVITY_NUM = 0;

    private FloatingActionButton floatButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        if(toolbar != null){
            setSupportActionBar(toolbar);
           // getActionBar().setTitle("Usay");
        }



        floatButton = findViewById(R.id.fab);
        setupViewPager();
        setUpBottomNavigationView();

    }

    private  void setupViewPager(){

        //Using section pager to add fragments
        final SectionPagerAdapter adpter = new SectionPagerAdapter(getSupportFragmentManager());
        adpter.addFragment(new FriendsFragment());// fragment index 0
        adpter.addFragment(new AllFragment());// fragment index 1
        adpter.addFragment(new UserProfileFragment());
        floatButton.setOnClickListener(((FriendsFragment) adpter.getItem(0)).onClickFloatButton.getInstance(this));

        ViewPager viewPager = (ViewPager) findViewById(R.id.container);
        viewPager.setAdapter(adpter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                ServiceUtil.stopServiceChat(MainActivity.this.getApplicationContext(), false);
                if (adpter.getItem(position) instanceof FriendsFragment){
                    floatButton.setVisibility(View.VISIBLE);
                    floatButton.setOnClickListener(((FriendsFragment) adpter.getItem(position)).onClickFloatButton.getInstance(MainActivity.this));
                    floatButton.setImageResource(R.drawable.plus);
                }
                else{
                    floatButton.setVisibility(View.GONE);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }

        });

        //Creating the Tab of the appllicaation
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setText("Favorites");//Icon for index 0
        tabLayout.getTabAt(1).setText("All");//Icon for index 1
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_info);


    }



    /**
     * set up bottom navigation
     */

    private void  setUpBottomNavigationView(){

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.enableNavigation(MainActivity.this, bottomNavigationView );

        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }



    /**
     * addintg the tabs
     */


}
