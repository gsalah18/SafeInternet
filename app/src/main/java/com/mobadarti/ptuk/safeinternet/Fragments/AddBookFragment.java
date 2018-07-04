package com.mobadarti.ptuk.safeinternet.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mobadarti.ptuk.safeinternet.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddBookFragment extends Fragment {
    EditText title_etxt;
    EditText link_etxt;
    Button add_btn;
    DatabaseReference bookref;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (view == null)
            view = inflater.inflate(R.layout.fragment_add_book, container, false);
        title_etxt=view.findViewById(R.id.title_etxt);
        link_etxt=view.findViewById(R.id.link_etxt);
        add_btn=view.findViewById(R.id.add_btn);
        bookref= FirebaseDatabase.getInstance().getReference().child("books");
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title=title_etxt.getText().toString();
                String link=link_etxt.getText().toString();
                if(title.length()>0&&link.length()>0){
                    String key=bookref.push().getKey();
                    bookref.updateChildren(new HashMap<String, Object>());
                    DatabaseReference ref=bookref.child(key);
                    Map<String,Object>map=new HashMap<>();
                    map.put("title",title);
                    map.put("link",link);
                    ref.updateChildren(map);
                    Toast.makeText(getActivity(), getResources().getString(R.string.book_added), Toast.LENGTH_SHORT).show();
                    title_etxt.setText("");
                    link_etxt.setText("");
                }
            }
        });

        return view;
    }

}
