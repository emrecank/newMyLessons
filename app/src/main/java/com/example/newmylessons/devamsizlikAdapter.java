package com.example.newmylessons;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import static android.content.Context.MODE_PRIVATE;

public class devamsizlikAdapter extends ArrayAdapter<HashMap<String,String>> {
    private ArrayList<HashMap<String, String>> dersprogrami;
    private Context context;
    private int resource;
    private View view;
    Calendar calendar;
    int cdevamsizlik = 0;

    private HashMap<String, String> hashMap;

    public devamsizlikAdapter(Context context, int resource, ArrayList<HashMap<String, String>> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.dersprogrami = objects;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(resource, parent, false);
        final Button dersadi = (Button) view.findViewById(R.id.bt_devamsizlik);
        final TextView devamsizlik = (TextView) view.findViewById(R.id.tv_devamsizlik);
        Button devamsizlik_azalt = (Button) view.findViewById(R.id.devamsizlik_azalt);
        Button devamsizlik_arttir = (Button) view.findViewById(R.id.devamsizlik_arttir);
        Button devamsizlik_info = (Button) view.findViewById(R.id.devamsizlikinfo);
        final TextView devamsizlikid = (TextView) view.findViewById(R.id.devamsizlikid);
        final TextView maxdevamsizlik = (TextView) view.findViewById(R.id.maxdevamsizlik);
        hashMap = dersprogrami.get(position);
        dersadi.setText(hashMap.get("dersadi"));
        devamsizlikid.setText(hashMap.get("id"));
        devamsizlik_info.setText(hashMap.get("id"));
        maxdevamsizlik.setText(hashMap.get("maxdevamsizlik"));
        devamsizlik.setText(String.valueOf(hashMap.get("devamsizlik")));
        if (Integer.parseInt(devamsizlik.getText().toString()) > Integer.parseInt(maxdevamsizlik.getText().toString())) {
            dersadi.setBackgroundResource(R.drawable.redbutton);
            dersadi.setTextColor(Color.WHITE);
            Toast.makeText(context, dersadi.getText().toString() + " dersinde devamsızlık sınırını aştınız!", Toast.LENGTH_SHORT).show();
        } else if (Integer.parseInt(devamsizlik.getText().toString()) == Integer.parseInt(maxdevamsizlik.getText().toString())) {
            dersadi.setBackgroundResource(R.drawable.orangebutton);
            dersadi.setTextColor(Color.WHITE);
            Toast.makeText(context, dersadi.getText().toString() + " dersinde devamsızlık sınırına ulaştınız!", Toast.LENGTH_SHORT).show();
        } else {
            dersadi.setBackgroundResource(R.drawable.button);
            dersadi.setTextColor(Color.WHITE);
        }

        devamsizlik_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                {
                    ArrayList<String> devamsizliktarihi=new ArrayList<String>();
                    SQLiteDatabase database = context.openOrCreateDatabase("Ders", MODE_PRIVATE, null);
                    database.execSQL("CREATE TABLE IF NOT EXISTS devamsizliktarih (id INTEGER PRIMARY KEY,dersid INTEGER,tarih VARCHAR)");
                    Cursor cursor=database.rawQuery("SELECT tarih FROM devamsizliktarih WHERE dersid="+Integer.parseInt(devamsizlikid.getText().toString())+"",null);
                    if(cursor.moveToNext()==false)
                    {
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setTitle("Devamsızlık Tarihleri");
                        builder.setMessage("Devamsızlığınız bulunmamaktadır!");
                        builder.setNegativeButton("Tamam", null);
                        builder.show();
                    }
                    else
                    {
                        cursor.moveToPrevious();
                        while(cursor.moveToNext())
                        {
                            devamsizliktarihi.add(cursor.getString(cursor.getColumnIndex("tarih")));

                        }
                        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1,devamsizliktarihi);
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setTitle("Devamsızlık Tarihleri");
                        builder.setAdapter(arrayAdapter,null);
                        builder.setNegativeButton("Tamam", null);
                        builder.show();




                    }
                }
                catch (Exception e)
                {
                    Toast.makeText(context, "Çekmyior devamsızlık", Toast.LENGTH_SHORT).show();
                }

            }
        });

        devamsizlik_azalt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Integer.parseInt(devamsizlik.getText().toString()) <= 0) {

                } else {
                    try {
                        final ArrayList<String> devamsizliktarihi=new ArrayList<String>();
                        final ArrayList<Integer> devamsizliktarihiid=new ArrayList<Integer>();
                        SQLiteDatabase database = context.openOrCreateDatabase("Ders", MODE_PRIVATE, null);
                        database.execSQL("CREATE TABLE IF NOT EXISTS dersler (id INTEGER PRIMARY KEY,dersadi VARCHAR,gun VARCHAR,saat VARCHAR,dakika VARCHAR,devamsizlik INTEGER,maxdevamsizlik INTEGER)");
                        database.execSQL("CREATE TABLE IF NOT EXISTS devamsizliktarih (id INTEGER PRIMARY KEY,dersid INTEGER,tarih VARCHAR)");
                        Cursor cursor=database.rawQuery("SELECT id,tarih FROM devamsizliktarih WHERE dersid="+Integer.parseInt(devamsizlikid.getText().toString())+"",null);
                        while(cursor.moveToNext())
                        {
                            devamsizliktarihi.add(cursor.getString(cursor.getColumnIndex("tarih")));
                            devamsizliktarihiid.add(cursor.getInt(cursor.getColumnIndex("id")));

                        }
                        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1,devamsizliktarihi);
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setTitle("Silmek istediğiniz tarihi seçiniz:");
                        builder.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, final int which) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                builder.setTitle("Silme");
                                builder.setMessage("Seçilen tarihi silmek istiyor musunuz?");
                                builder.setNegativeButton("Hayır", null);
                                builder.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        SQLiteDatabase database = context.openOrCreateDatabase("Ders", MODE_PRIVATE, null);
                                        database.execSQL("CREATE TABLE IF NOT EXISTS dersler (id INTEGER PRIMARY KEY,dersadi VARCHAR,gun VARCHAR,saat VARCHAR,dakika VARCHAR,devamsizlik INTEGER,maxdevamsizlik INTEGER)");
                                        database.execSQL("CREATE TABLE IF NOT EXISTS devamsizliktarih (id INTEGER PRIMARY KEY,dersid INTEGER,tarih VARCHAR)");
                                        database.execSQL("DELETE FROM devamsizliktarih WHERE tarih='"+devamsizliktarihi.get(which)+"' AND dersid="+Integer.parseInt(devamsizlikid.getText().toString())+" AND id="+devamsizliktarihiid.get(which)+"");
                                        cdevamsizlik = Integer.parseInt(devamsizlik.getText().toString());
                                        cdevamsizlik--;
                                        database.execSQL("UPDATE dersler SET devamsizlik=" + cdevamsizlik + " WHERE id=" + Integer.parseInt(devamsizlikid.getText().toString()) + "");
                                        devamsizlik.setText(String.valueOf(cdevamsizlik));
                                        if (Integer.parseInt(devamsizlik.getText().toString()) > Integer.parseInt(maxdevamsizlik.getText().toString())) {
                                            dersadi.setBackgroundResource(R.drawable.redbutton);
                                            dersadi.setTextColor(Color.WHITE);
                                            Toast.makeText(context, dersadi.getText().toString() + " dersinde devamsızlık sınırını aştınız!", Toast.LENGTH_SHORT).show();

                                        } else if (Integer.parseInt(devamsizlik.getText().toString()) == Integer.parseInt(maxdevamsizlik.getText().toString())) {
                                            dersadi.setBackgroundResource(R.drawable.orangebutton);
                                            dersadi.setTextColor(Color.WHITE);
                                            Toast.makeText(context, dersadi.getText().toString()+ " dersinde devamsızlık sınırına ulaştınız!", Toast.LENGTH_SHORT).show();
                                        } else {
                                            dersadi.setBackgroundResource(R.drawable.button);
                                            dersadi.setTextColor(Color.WHITE);
                                        }

                                    }
                                });
                                builder.show();
                            }
                        });
                        builder.setNegativeButton("Kapat", null);
                        builder.show();

                    } catch (Exception e) {
                        Toast.makeText(context, "Devamsızlık Azaltılamadı", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });
        devamsizlik_arttir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar takvim = Calendar.getInstance();
                int yil = takvim.get(Calendar.YEAR);
                int ay = takvim.get(Calendar.MONTH);
                int gun = takvim.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dpd = new DatePickerDialog(context,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                month += 1;
                                try
                                {
                                    SQLiteDatabase database = context.openOrCreateDatabase("Ders", MODE_PRIVATE, null);
                                    database.execSQL("CREATE TABLE IF NOT EXISTS dersler (id INTEGER PRIMARY KEY,dersadi VARCHAR,gun VARCHAR,saat VARCHAR,dakika VARCHAR,devamsizlik INTEGER,maxdevamsizlik INTEGER)");
                                    database.execSQL("CREATE TABLE IF NOT EXISTS devamsizliktarih (id INTEGER PRIMARY KEY,dersid INTEGER,tarih VARCHAR)");
                                    database.execSQL("INSERT INTO devamsizliktarih (dersid,tarih) VALUES ("+Integer.parseInt(devamsizlikid.getText().toString())+",'"+dayOfMonth+"/"+month+"/"+year+"')");
                                    cdevamsizlik = Integer.parseInt(devamsizlik.getText().toString());
                                    cdevamsizlik++;
                                    database.execSQL("UPDATE dersler SET devamsizlik=" + cdevamsizlik + " WHERE id=" + Integer.parseInt(devamsizlikid.getText().toString()) + "");
                                    devamsizlik.setText(String.valueOf(cdevamsizlik));
                                    if (Integer.parseInt(devamsizlik.getText().toString()) > Integer.parseInt(maxdevamsizlik.getText().toString())) {
                                        dersadi.setBackgroundResource(R.drawable.redbutton);
                                        dersadi.setTextColor(Color.WHITE);
                                        Toast.makeText(context, dersadi.getText().toString() + " dersinde devamsızlık sınırını aştınız!", Toast.LENGTH_SHORT).show();

                                    } else if (Integer.parseInt(devamsizlik.getText().toString()) == Integer.parseInt(maxdevamsizlik.getText().toString())) {
                                        dersadi.setBackgroundResource(R.drawable.orangebutton);
                                        dersadi.setTextColor(Color.WHITE);
                                        Toast.makeText(context, dersadi.getText().toString() + " dersinde devamsızlık sınırına ulaştınız!", Toast.LENGTH_SHORT).show();
                                    } else {
                                        dersadi.setBackgroundResource(R.drawable.button);
                                        dersadi.setTextColor(Color.WHITE);
                                    }
                                }
                                catch (Exception e)
                                {
                                    Toast.makeText(context, "Devamsızlık Arttırılamadı", Toast.LENGTH_SHORT).show();
                                }

                            }
                        }, yil, ay, gun);
                dpd.setButton(DatePickerDialog.BUTTON_POSITIVE, "Seç", dpd);
                dpd.setButton(DatePickerDialog.BUTTON_NEGATIVE, "İptal", dpd);
                dpd.show();


            }
        });
        return view;
    }
}
