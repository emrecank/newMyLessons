package com.example.newmylessons;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import static android.content.Context.MODE_PRIVATE;

public class derseklecikarAdapter extends ArrayAdapter<HashMap<String,String>> {
    private ArrayList<HashMap<String, String>> derseklecikar;
    private Context context;
    private int resource;
    private View view;
    Calendar calendar;
    String namee;
    private HashMap<String, String> hashMap;

    public derseklecikarAdapter(Context context, int resource, ArrayList<HashMap<String, String>> objects,String name) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.derseklecikar = objects;
        this.namee=name;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(resource, parent, false);
        Button dersadi = (Button) view.findViewById(R.id.dersadi);
        final Button dersdelete = (Button) view.findViewById(R.id.dersdelete);
        final Button dersedit = (Button) view.findViewById(R.id.dersedit);
        hashMap = derseklecikar.get(position);
        dersadi.setText(hashMap.get("dersadi"));
        dersdelete.setText(hashMap.get("id"));
        dersedit.setText(hashMap.get("id"));
        dersedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(context,dersguncelle.class);
                intent1.putExtra("lessonid",dersedit.getText().toString());
                intent1.putExtra("name",namee);
                context.startActivity(intent1);
            }
        });
        dersdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Silme");
                builder.setMessage("Dersi Silmek İstiyor Musunuz?");
                builder.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        try {
                            SQLiteDatabase database = context.openOrCreateDatabase("Ders", MODE_PRIVATE, null);
                            database.execSQL("CREATE TABLE IF NOT EXISTS dersler (id INTEGER PRIMARY KEY,dersadi VARCHAR,gun VARCHAR,saat VARCHAR,dakika VARCHAR,devamsizlik INTEGER,maxdevamsizlik INTEGER)");
                            database.execSQL("CREATE TABLE IF NOT EXISTS devamsizliktarih (id INTEGER PRIMARY KEY,dersid INTEGER,tarih VARCHAR)");
                            database.execSQL("DELETE FROM dersler WHERE id=" + dersdelete.getText().toString() + "");
                            database.execSQL("DELETE FROM devamsizliktarih WHERE dersid=" + Integer.parseInt(dersdelete.getText().toString()) + "");
                            Toast.makeText(context, "Dersi Başarıyla Sildiniz!", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(context,MainActivity.class);
                            intent.putExtra("name",namee);
                            context.startActivity(intent);

                        } catch (Exception e) {
                            Toast.makeText(context, "Silme Başarısız!", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                builder.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dialogInterface.dismiss();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();


            }
        });
        return view;
    }

}
