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

public class AddVideoFragment extends Fragment {
    private View view;
    private DatabaseReference root;
    private EditText name_etxt,uri_etxt;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add_video, container, false);
        root = FirebaseDatabase.getInstance().getReference().child("videos");
        name_etxt = (EditText) view.findViewById(R.id.name_etxt);
        uri_etxt = (EditText) view.findViewById(R.id.uri_etxt);
        ((Button) view.findViewById(R.id.add_btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=name_etxt.getText().toString();
                String uri=uri_etxt.getText().toString();
                String id=uri.substring(uri.indexOf('=')+1,uri.length());
                String key=root.push().getKey();
                root.updateChildren(new HashMap<String, Object>());
                DatabaseReference inner=root.child(key);
                Map<String,Object>map=new HashMap<>();
                map.put("title",name);
                map.put("uri",id);
                inner.updateChildren(map);
                Toast.makeText(getActivity(), getResources().getString(R.string.video_added), Toast.LENGTH_SHORT).show();
                name_etxt.setText("");
                uri_etxt.setText("");
            }
        });
        return view;
    }




}
