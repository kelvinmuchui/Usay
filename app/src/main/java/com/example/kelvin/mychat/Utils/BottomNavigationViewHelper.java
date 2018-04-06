package com.example.kelvin.mychat.Utils;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

import com.example.kelvin.mychat.Friends.FriendsActivity;
import com.example.kelvin.mychat.Home.HomeActivity;
import com.example.kelvin.mychat.R;
import com.example.kelvin.mychat.Service.ServicesActivity;
import com.example.kelvin.mychat.Usay.MainActivity;

/**
 * Created by kelvin on 3/29/18.
 */

public class BottomNavigationViewHelper {

    private static final String TAG = "BottomNavigationViewHel";



    public static void enableNavigation(final Context context, BottomNavigationView view){

         view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.ic_message:
                        Intent intent1 = new Intent(context, MainActivity.class);
                        context.startActivity(intent1);
                        break;
                    case R.id.ic_friends:
                        Intent intent2 = new Intent(context, FriendsActivity.class);
                        context.startActivity(intent2);

                        break;
                    case R.id.ic_services:
                        Intent intent3 = new Intent(context, ServicesActivity.class);
                        context.startActivity(intent3);
                        break;

                    case R.id.ic_home:
                        Intent intent4 = new Intent(context, HomeActivity.class);
                        context.startActivity(intent4);
                        break;
                }


                return false;
            }
        });
    }
}
