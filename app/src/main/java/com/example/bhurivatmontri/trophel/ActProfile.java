package com.example.bhurivatmontri.trophel;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class ActProfile extends AppCompatActivity implements View.OnClickListener {

    protected DatabaseReference mDatabase;
    protected FirebaseStorage storage = FirebaseStorage.getInstance();

    protected ImageView profile;
    protected ImageView background;
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
    protected Button buttonNorth;
    protected Button buttonCentral;
    protected Button buttonNortheastern;
    protected Button buttonWestern;
    protected Button buttonSouthern;
    protected Button buttonEastern;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_profile);

        Toolbar toolbarActProfile = (Toolbar) findViewById(R.id.toolbar_act_profile);
        setSupportActionBar(toolbarActProfile);
        getSupportActionBar().setTitle("My Profile");

        String [] img_type = {"profile.jpg","background.jpg"};
        profile = (ImageView) findViewById(R.id.img_Profile);
        background = (ImageView) findViewById(R.id.cover_background);
        idUser = (TextView) findViewById(R.id.id_Profile);
        nameUser = (TextView) findViewById(R.id.name_Profile);
        captionUser = (TextView) findViewById(R.id.caption_Profile);
        northernUser = (TextView) findViewById(R.id.trophel_northern_count);
        northeasternUser = (TextView) findViewById(R.id.trophel_northeastern_count);
        centralUser = (TextView) findViewById(R.id.trophel_central_count);
        southernUser = (TextView) findViewById(R.id.trophel_southern_count);
        easternUser = (TextView) findViewById(R.id.trophel_eastern_count);
        westernUser = (TextView) findViewById(R.id.trophel_western_count);
        starUser = (TextView) findViewById(R.id.star_count);
        buttonNorth = (Button) findViewById(R.id.button_act_northern);
        buttonCentral = (Button) findViewById(R.id.button_act_central);
        buttonNortheastern = (Button) findViewById(R.id.button_act_northeastern);
        buttonWestern = (Button) findViewById(R.id.button_act_western);
        buttonSouthern = (Button) findViewById(R.id.button_act_southern);
        buttonEastern = (Button) findViewById(R.id.button_act_eastern);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        final StorageReference mStorage = storage.getReference();

        mDatabase.child("users").child("uID").child("drive").addValueEventListener(new ValueEventListener() {
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

        mStorage.child("img_profile/uImg/drive/profile.jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Log.d("onDataChange","onSuccess");
                Picasso.with(getApplicationContext())
                        .load(uri)
                        .placeholder(R.mipmap.ic_launcher)
                        .fit()
                        .centerCrop()
                        .into(profile);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });

        mStorage.child("img_profile/uImg/drive/background.jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Log.d("onDataChange","onSuccess");
                Picasso.with(getApplicationContext())
                        .load(uri)
                        .placeholder(R.mipmap.ic_launcher)
                        .fit()
                        .centerCrop()
                        .into(background);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });

        buttonNorth.setOnClickListener(this);
        buttonCentral.setOnClickListener(this);
        buttonNortheastern.setOnClickListener(this);
        buttonWestern.setOnClickListener(this);
        buttonSouthern.setOnClickListener(this);
        buttonEastern.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        Intent intent = new Intent(ActProfile.this,ActListTrophy.class);
        switch (v.getId()){
            case R.id.button_act_northern:
                intent.putExtra("Region","1");
                startActivity(intent);
                break;
            case R.id.button_act_central:
                intent.putExtra("Region","2");
                startActivity(intent);
                break;
            case R.id.button_act_northeastern:
                intent.putExtra("Region","3");
                startActivity(intent);
                break;
            case R.id.button_act_western:
                intent.putExtra("Region","4");
                startActivity(intent);
                break;
            case R.id.button_act_southern:
                intent.putExtra("Region","5");
                startActivity(intent);
                break;
            case R.id.button_act_eastern:
                intent.putExtra("Region","6");
                startActivity(intent);
                break;
        }
    }
}
