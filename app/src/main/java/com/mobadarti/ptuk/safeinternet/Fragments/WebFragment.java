package com.mobadarti.ptuk.safeinternet.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.mobadarti.ptuk.safeinternet.Activites.WebActivity;
import com.mobadarti.ptuk.safeinternet.Models.Website;
import com.mobadarti.ptuk.safeinternet.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Iterator;

public class WebFragment extends Fragment implements ListView.OnItemClickListener {

    private View view;
    private ArrayList<Website> Websties=new ArrayList<>();
    private ListView listView;
    private ArrayAdapter<Website> adapter;
    private DatabaseReference databaseReference;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_web, container, false);
        try {

            databaseReference= FirebaseDatabase.getInstance().getReference().child("websites");
            Websties.clear();
            listView = (ListView) view.findViewById(R.id.listview);
            listView.setOnItemClickListener(this);
            databaseReference.addChildEventListener(new ChildEventListener() {
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
        } catch (Exception e) {
            Toast.makeText(getActivity(), e + "", Toast.LENGTH_SHORT).show();
        }
        return view;
    }

    private void Refresh(DataSnapshot dataSnapshot) {
        Iterator i=dataSnapshot.getChildren().iterator();
        while (i.hasNext()){
            String name=((DataSnapshot)i.next()).getValue().toString();
            String url=((DataSnapshot)i.next()).getValue().toString();
            Websties.add(new Website(name,url));
        }
        adapter = new ArrayAdapter<Website>(getActivity(), R.layout.list_text, Websties);
        listView.setAdapter(adapter);
    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(getActivity(), WebActivity.class);
        intent.putExtra("link",Websties.get(i).getUrl());
        intent.putExtra("title",Websties.get(i).getName());
        startActivity(intent);
    }
}
