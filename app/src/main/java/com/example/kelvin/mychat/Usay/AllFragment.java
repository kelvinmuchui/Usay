package com.example.kelvin.mychat.Usay;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.Volley;
import com.example.kelvin.mychat.Model.AllUsers;
import com.example.kelvin.mychat.Model.User;
import com.example.kelvin.mychat.R;
import com.example.kelvin.mychat.data.ListUser;
import com.example.kelvin.mychat.data.StaticConfig;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.yarolegovich.lovelydialog.LovelyProgressDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by kelvin on 3/29/18.
 */

public class AllFragment extends Fragment {
    private static final String TAG = "AllFragment";

    private RecyclerView allUsersList;
    private DatabaseReference myRef;

    


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragement_all, container, false);

        allUsersList = (RecyclerView) view.findViewById(R.id.all_users_list);
        allUsersList.setHasFixedSize(true);
        allUsersList.setLayoutManager(new LinearLayoutManager(getActivity()));
        myRef = FirebaseDatabase.getInstance().getReference().child("user");
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<AllUsers, AllusersViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<AllUsers, AllusersViewHolder>
                        (
                                AllUsers.class,
                                R.layout.rc_item_user,
                                AllusersViewHolder.class,
                                myRef

                        )
                {
                    @Override
                    protected void populateViewHolder(AllusersViewHolder viewHolder, AllUsers model, int position)
                    {

                        viewHolder.setName(model.getName());



                    }
                };
        allUsersList.setAdapter(firebaseRecyclerAdapter);
    }

    public static class AllusersViewHolder extends RecyclerView.ViewHolder{

        View mView;
        public AllusersViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setName(String name){
            TextView user_name = (TextView) mView.findViewById(R.id.txtName);
            user_name.setText(name);

        }
        public void setAvata( Context ctx, String avata , int position){
            CircleImageView user_image = (CircleImageView) mView.findViewById(R.id.icon_avata);


        }

    }
}
