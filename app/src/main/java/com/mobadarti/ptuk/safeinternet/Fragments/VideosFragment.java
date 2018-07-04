package com.mobadarti.ptuk.safeinternet.Fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.mobadarti.ptuk.safeinternet.Activites.YoutubeActivity;
import com.mobadarti.ptuk.safeinternet.Adapters.VideoAdapter;
import com.mobadarti.ptuk.safeinternet.Models.Video;
import com.mobadarti.ptuk.safeinternet.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideosFragment extends Fragment implements ListView.OnItemClickListener{

    private View view;
    private ListView listView;
    private VideoAdapter adapter;
    public static ArrayList<Video> Videos;
    private FragmentActivity myContext;

    @Override
    public void onAttach(Context context) {
        if (context instanceof FragmentActivity) {
            myContext = (FragmentActivity) context;
        }
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_videos, container, false);
        listView = (ListView) view.findViewById(R.id.listview);
        listView.setOnItemClickListener(this);
        DatabaseReference ref= FirebaseDatabase.getInstance().getReference().child("videos");
        Videos=new ArrayList<>();

        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Refresh(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        return view;
    }

    private void Refresh(DataSnapshot dataSnapshot) {
        Iterator i=dataSnapshot.getChildren().iterator();
        String Name,Uri;
        while (i.hasNext()){
            Name=((DataSnapshot)i.next()).getValue().toString();
            Uri=((DataSnapshot)i.next()).getValue().toString();
            Videos.add(new Video(Name,Uri));
        }
        adapter=new VideoAdapter(getActivity(),Videos,myContext);
        listView.setAdapter(adapter);
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent=new Intent(getActivity(), YoutubeActivity.class);
        intent.putExtra("uri",Videos.get(i).getUrl());
        startActivity(intent);
    }
}
