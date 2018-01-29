package com.example.bhurivatmontri.trophel.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bhurivatmontri.trophel.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class Profile extends Fragment {

    protected DatabaseReference mDatabase;
    protected TextView idUser;
    protected TextView nameUser;
    protected TextView captionUser;
    protected TextView northernUser;
    protected TextView northeasternUser;
    protected TextView centralUser;
    protected TextView southernUser;
    protected TextView easternUser;
    protected TextView westernUser;
    protected TextView starUser;

    public Profile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        idUser = (TextView) view.findViewById(R.id.id_Profile);
        nameUser = (TextView) view.findViewById(R.id.name_Profile);
        captionUser = (TextView) view.findViewById(R.id.caption_Profile);
        northernUser = (TextView) view.findViewById(R.id.trophel_northern_count);
        northeasternUser = (TextView) view.findViewById(R.id.trophel_northeastern_count);
        centralUser = (TextView) view.findViewById(R.id.trophel_central_count);
        southernUser = (TextView) view.findViewById(R.id.trophel_southern_count);
        easternUser = (TextView) view.findViewById(R.id.trophel_eastern_count);
        westernUser = (TextView) view.findViewById(R.id.trophel_western_count);
        starUser = (TextView) view.findViewById(R.id.star_count);


        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("users").child("uID").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child("id").getValue().toString();
                String name = dataSnapshot.child("name").getValue().toString();
                String caption = dataSnapshot.child("caption").getValue().toString();
                int countNorthern = dataSnapshot.child("count_Northern").getValue(Integer.class);
                int countNortheastern = dataSnapshot.child("count_Northeastern").getValue(Integer.class);
                int countCentral = dataSnapshot.child("count_Central").getValue(Integer.class);
                int countSouthern = dataSnapshot.child("count_Southern").getValue(Integer.class);
                int countEastern = dataSnapshot.child("count_Eastern").getValue(Integer.class);
                int countWestern = dataSnapshot.child("count_Western").getValue(Integer.class);
                int countStar = dataSnapshot.child("count_Star").getValue(Integer.class);
                idUser.setText("id:"+value);
                nameUser.setText(name);
                captionUser.setText("("+caption+")");
                northernUser.setText(" : "+countNorthern);
                northeasternUser.setText(" : "+countNortheastern);
                centralUser.setText(" : " +
                        ""+countCentral);
                southernUser.setText(" : "+countSouthern);
                easternUser.setText(" : "+countEastern);
                westernUser.setText(" : "+countWestern);
                starUser.setText(" : "+countStar);
                //idUser.setText();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return view;

    }

}
