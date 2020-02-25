package com.example.newmylessons;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.newmylessons.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;

import static android.content.Context.MODE_PRIVATE;

public class derseklecikarFragment extends Fragment {
    TextView nametext,textuyari;
    ListView derscikarlist;
    FloatingActionButton derseklebutton;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_derseklecikar, container, false);
        nametext=root.findViewById(R.id.nametext);
        Intent intent=getActivity().getIntent();
        final String name=intent.getStringExtra("name");
        nametext.setText(name);
        textuyari=root.findViewById(R.id.derscikar_uyari);
        derscikarlist=root.findViewById(R.id.derscikar_list);
        derseklebutton=root.findViewById(R.id.derseklebutton);
        derseklebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),dersekle.class);
                intent.putExtra("name",name);
                startActivity(intent);
            }
        });



        ArrayList<HashMap<String,String>> dersler=new ArrayList<HashMap<String,String>>();
        SQLiteDatabase database= getActivity().openOrCreateDatabase("Ders", MODE_PRIVATE, null);
        database.execSQL("CREATE TABLE IF NOT EXISTS dersler (id INTEGER PRIMARY KEY,dersadi VARCHAR,gun VARCHAR,saat VARCHAR,dakika VARHCHAR,devamsizlik INTEGER,maxdevamsizlik INTEGER)");
        Cursor cursor=database.rawQuery("SELECT * FROM dersler",null);

        if(cursor.moveToNext()==false)
        {
            textuyari.setVisibility(View.VISIBLE);
        }
        else
        {
            textuyari.setVisibility(View.INVISIBLE);
            cursor.moveToPrevious();
            while(cursor.moveToNext())
            {
                    HashMap<String,String> hashMap=new HashMap<String, String>();
                    hashMap.put("id", String.valueOf(cursor.getInt(cursor.getColumnIndex("id"))));
                    hashMap.put("dersadi", cursor.getString(cursor.getColumnIndex("dersadi")));
                    dersler.add(hashMap);
            }
        }



        cursor.close();
        derseklecikarAdapter adapter=new derseklecikarAdapter(getActivity(),R.layout.derseklecikar,dersler,name);
        derscikarlist.setAdapter(adapter);
        return root;
    }
}