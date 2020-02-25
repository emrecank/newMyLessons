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

import java.util.ArrayList;
import java.util.HashMap;

import static android.content.Context.MODE_PRIVATE;

public class devamsizlikFragment extends Fragment {
    TextView nametext,textuyari;
    ListView devamsizlik_list;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_devamsizlik, container, false);
        nametext=root.findViewById(R.id.nametext);
        Intent intent=getActivity().getIntent();
        final String name=intent.getStringExtra("name");
        nametext.setText(name);
        textuyari=root.findViewById(R.id.devamsizlik_uyari);
        devamsizlik_list=root.findViewById(R.id.devamsizlik_list);
        ArrayList<HashMap<String,String>> dersler=new ArrayList<HashMap<String,String>>();
        SQLiteDatabase database= getActivity().openOrCreateDatabase("Ders", MODE_PRIVATE, null);
        database.execSQL("CREATE TABLE IF NOT EXISTS dersler (id INTEGER PRIMARY KEY,dersadi VARCHAR,gun VARCHAR,saat VARCHAR,dakika VARCHAR,devamsizlik INTEGER,maxdevamsizlik INTEGER)");
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
                    hashMap.put("id", cursor.getString(cursor.getColumnIndex("id")));
                    hashMap.put("dersadi", cursor.getString(cursor.getColumnIndex("dersadi")));
                    hashMap.put("devamsizlik", cursor.getString(cursor.getColumnIndex("devamsizlik")));
                    hashMap.put("maxdevamsizlik", cursor.getString(cursor.getColumnIndex("maxdevamsizlik")));
                    dersler.add(hashMap);
            }
        }





        cursor.close();
        devamsizlikAdapter adapter=new devamsizlikAdapter(getActivity(),R.layout.devamsizlik,dersler);
        devamsizlik_list.setAdapter(adapter);




        return root;
    }
}