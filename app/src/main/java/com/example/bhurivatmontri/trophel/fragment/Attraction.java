package com.example.bhurivatmontri.trophel.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bhurivatmontri.trophel.Home;
import com.example.bhurivatmontri.trophel.R;
import com.example.bhurivatmontri.trophel.adapter.CustomAdapter;
import com.example.bhurivatmontri.trophel.adapter.EndangeredItem;
import com.example.bhurivatmontri.trophel.adapter.Friend;
import com.example.bhurivatmontri.trophel.adapter.GridAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Attraction extends Fragment {

    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;

    protected DatabaseReference mDatabase;
    protected FirebaseStorage storage = FirebaseStorage.getInstance();
    ArrayList<String> name = new ArrayList<>();
    ArrayList<String> uriImg = new ArrayList<>();
    ArrayList<String> keyName = new ArrayList<>();
    ArrayList<EndangeredItem> listAttractions = new ArrayList<>();

    public Attraction() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDatabase();
        initDataset();
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_attraction, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_Attraction);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager  = new GridLayoutManager(getActivity(),1);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new GridAdapter(getActivity(),listAttractions);
        mRecyclerView.setAdapter(mAdapter);

        return view ;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_attraction, menu);
        return;
    }

    private void initDataset() {
        int i = 0;
        Log.d("onDataChange","555555555555555555555555555555555555555555");
        for (String addName : name) {
            listAttractions.add(new EndangeredItem(addName,uriImg.get(i),keyName.get(i))) ;
            i++;
        }
    }

    private void initDatabase(){
        mDatabase = FirebaseDatabase.getInstance().getReference();
        final StorageReference mStorage = storage.getReference();

        mDatabase.child("attractions").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("onDataChange",""+dataSnapshot.getChildrenCount());
                for(DataSnapshot item_attrs : dataSnapshot.getChildren()){
                    Log.d("onDataChange","555"+item_attrs.getValue().toString());
                    Log.d("onDataChange","666"+item_attrs.child("region_Eng").getValue());
                    //friendID.add(item_friend.getKey().toString());
                    if(item_attrs.child("region_Eng").getValue().toString().equals("Northern")){
                        name.add(item_attrs.child("name_Eng").getValue().toString());
                        uriImg.add(item_attrs.child("uri_img").getValue().toString());
                        keyName.add(item_attrs.getKey().toString());
                    }
                }
                initDataset();
                //Log.d("onDataChange","zzzz "+listFriend.size());
                mAdapter = new GridAdapter(getActivity(),listAttractions);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
