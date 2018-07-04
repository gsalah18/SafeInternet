package com.mobadarti.ptuk.safeinternet.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.mobadarti.ptuk.safeinternet.Models.Book;
import com.mobadarti.ptuk.safeinternet.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Iterator;


public class BookFragment extends Fragment implements ListView.OnItemClickListener{

    private DatabaseReference bookref= FirebaseDatabase.getInstance().getReference().child("books");
    private View view;
    private ListView listView;
    private ArrayList<Book>data=new ArrayList<>();
    private ArrayAdapter<Book>adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(view==null)
            view=inflater.inflate(R.layout.fragment_book, container, false);
        listView=view.findViewById(R.id.listview);
        listView.setOnItemClickListener(this);
        data.clear();
        bookref.addChildEventListener(new ChildEventListener() {
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
        Iterator i= dataSnapshot.getChildren().iterator();
        while (i.hasNext()){
            String link=((DataSnapshot)i.next()).getValue().toString();
            String title=((DataSnapshot)i.next()).getValue().toString();
            data.add(new Book(title,link));
        }
        adapter=new ArrayAdapter<Book>(getActivity(),R.layout.list_text,data);
        listView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(data.get(i).getLink()));
        startActivity(intent);
    }
}
