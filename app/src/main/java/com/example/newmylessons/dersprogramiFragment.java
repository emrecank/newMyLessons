package com.example.newmylessons;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import static android.content.Context.ALARM_SERVICE;
import static android.content.Context.MODE_PRIVATE;

public class dersprogramiFragment extends Fragment {

    TextView nametext,textuyari;
    ListView tablelist;
    Button shared;


    public View onCreateView(@NonNull final LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dersprogrami, container, false);
        nametext=root.findViewById(R.id.nameText);
        textuyari=root.findViewById(R.id.dersprogrami_uyari);
        shared=root.findViewById(R.id.shared);
        tablelist=root.findViewById(R.id.tablelist);
        Intent intent=getActivity().getIntent();
        final String name=intent.getStringExtra("name");
        nametext.setText(name);

        shared.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),Main2Activity.class);
                intent.putExtra("namer",name);
                startActivity(intent);

            }
        });

        ArrayList<HashMap<String,String>> dersler=new ArrayList<HashMap<String,String>>();
        SQLiteDatabase database= getActivity().openOrCreateDatabase("Ders", MODE_PRIVATE, null);
        database.execSQL("CREATE TABLE IF NOT EXISTS dersler (id INTEGER PRIMARY KEY,dersadi VARCHAR,gun VARCHAR,saat VARCHAR,dakika VARCHAR,devamsizlik INTEGER,maxdevamsizlik INTEGER)");
        Cursor cursor=database.rawQuery("SELECT * FROM dersler ORDER BY gun,saat",null);

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
                    hashMap.put("dersadi", cursor.getString(cursor.getColumnIndex("dersadi")));
                    hashMap.put("gun", cursor.getString(cursor.getColumnIndex("gun")));
                    hashMap.put("saat", cursor.getString(cursor.getColumnIndex("saat")));
                    hashMap.put("dakika", cursor.getString(cursor.getColumnIndex("dakika")));
                    hashMap.put("devamsizlik", String.valueOf(cursor.getInt(cursor.getColumnIndex("devamsizlik"))));
                    hashMap.put("maxdevamsizlik", String.valueOf(cursor.getInt(cursor.getColumnIndex("maxdevamsizlik"))));
                    dersler.add(hashMap);

            }
        }




        cursor.close();
        dersprogramiAdapter adapter=new dersprogramiAdapter(getActivity(),R.layout.dersprogrami,dersler);
        tablelist.setAdapter(adapter);

        return root;
    }

}