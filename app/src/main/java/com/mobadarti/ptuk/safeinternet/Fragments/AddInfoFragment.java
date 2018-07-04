package com.mobadarti.ptuk.safeinternet.Fragments;

import android.content.Context;
import android.net.Uri;
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


public class AddInfoFragment extends Fragment {
    EditText info_etxt;
    Button add_btn;
    DatabaseReference bookref;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null)
            view = inflater.inflate(R.layout.fragment_add_info, container, false);
        info_etxt=view.findViewById(R.id.info_etxt);
        add_btn=view.findViewById(R.id.add_btn);
        bookref= FirebaseDatabase.getInstance().getReference().child("info");
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String info=info_etxt.getText().toString();
                if(info.length()>0){
                    String key=bookref.push().getKey();
                    bookref.updateChildren(new HashMap<String, Object>());
                    DatabaseReference ref=bookref.child(key);
                    Map<String,Object> map=new HashMap<>();
                    map.put("content",info);
                    ref.updateChildren(map);
                    Toast.makeText(getActivity(), getResources().getString(R.string.info_added), Toast.LENGTH_SHORT).show();
                    info_etxt.setText("");
                }
            }
        });
        return view;
    }


}
