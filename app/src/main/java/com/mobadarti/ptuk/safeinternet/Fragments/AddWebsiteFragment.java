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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddWebsiteFragment extends Fragment {

    private View view;
    private EditText name_etxt,uri_etxt;
    private DatabaseReference databaseReference;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_add_website, container, false);
        name_etxt=(EditText)view.findViewById(R.id.name_etxt);
        uri_etxt=(EditText)view.findViewById(R.id.uri_etxt);
        databaseReference= FirebaseDatabase.getInstance().getReference().child("websites");
        ((Button)view.findViewById(R.id.add_btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name_etxt.getText().toString().length()>0&&uri_etxt.getText().toString().length()>0){
                    String name=name_etxt.getText().toString();
                    String url=uri_etxt.getText().toString();
                    String temp=databaseReference.push().getKey();
                    databaseReference.updateChildren(new HashMap<String, Object>());
                    DatabaseReference inner=databaseReference.child(temp);
                    HashMap<String,Object>map=new HashMap<>();
                    map.put("name",name);
                    map.put("url",url);
                    inner.updateChildren(map);
                    Toast.makeText(getActivity(), getResources().getString(R.string.website_added), Toast.LENGTH_SHORT).show();
                    name_etxt.setText("");
                    uri_etxt.setText("");
                }else Toast.makeText(getActivity(), getResources().getString(R.string.must_fill_url), Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }


}
