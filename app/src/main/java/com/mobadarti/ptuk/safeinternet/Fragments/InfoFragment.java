package com.mobadarti.ptuk.safeinternet.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
public class InfoFragment extends Fragment {
    private DatabaseReference inforef= FirebaseDatabase.getInstance().getReference().child("info");
    private ListView listView;
    private ArrayList<String>data=new ArrayList<>();
    private ArrayAdapter<String>adapter;

    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(view==null)
            view = inflater.inflate(R.layout.fragment_info, container, false);
        listView=view.findViewById(R.id.listview);
        inforef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Refresh(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Refresh(dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Refresh(dataSnapshot);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                Refresh(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return view;
    }
    private void Refresh(DataSnapshot dataSnapshot){
        Iterator i=dataSnapshot.getChildren().iterator();
        while (i.hasNext()){
            data.add(((DataSnapshot)i.next()).getValue().toString());
        }
        adapter=new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,data);
        listView.setAdapter(adapter);
    }

}
