package com.example.newmylessons;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class dersguncelle extends AppCompatActivity {

    TextView dersgunu;
    EditText dersadi,derssaat,dersdakika,devamsizlik;
    Button guncelle,derssaatplus,derssaatminus,dersdakikaplus,dersdakikaminus,devamsizlikplus,devamsizlikminus,gunminus,gunplus;
    int cderssaat=0;
    int cdersdakika=0;
    int cdevamsizlik=0;
    int cgun=0;
    String[] days={"Pazartesi","Salı","Çarşamba","Perşembe","Cuma"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dersguncelle);
        Intent intent=getIntent();
        final String name=intent.getStringExtra("name");
        final String lessonid=intent.getStringExtra("lessonid");


        derssaatplus=findViewById(R.id.derssaatplus);
        derssaatminus=findViewById(R.id.derssaatminus);
        dersdakikaplus=findViewById(R.id.dersdakikaplus);
        dersdakikaminus=findViewById(R.id.dersdakikaminus);
        devamsizlikplus=findViewById(R.id.devamsizlikplus);
        devamsizlikminus=findViewById(R.id.devamsizlikminus);
        dersadi=findViewById(R.id.dersadi);
        dersgunu=findViewById(R.id.dersgunu);
        derssaat=findViewById(R.id.derssaat);
        dersdakika=findViewById(R.id.dersdakika);
        devamsizlik=findViewById(R.id.devamsizlik);
        gunminus=findViewById(R.id.gunminus);
        gunplus=findViewById(R.id.gunplus);
        guncelle=findViewById(R.id.guncelle);

        SQLiteDatabase database= dersguncelle.this.openOrCreateDatabase("Ders", MODE_PRIVATE, null);
        database.execSQL("CREATE TABLE IF NOT EXISTS dersler (id INTEGER PRIMARY KEY,dersadi VARCHAR,gun VARCHAR,saat VARCHAR,dakika VARCHAR,devamsizlik INTEGER,maxdevamsizlik INTEGER)");
        final Cursor cursor=database.rawQuery("SELECT * FROM dersler WHERE id="+lessonid+"",null);

        while(cursor.moveToNext())
        {

            dersadi.setText(cursor.getString(cursor.getColumnIndex("dersadi")));
            derssaat.setText(cursor.getString(cursor.getColumnIndex("saat")));
            dersdakika.setText(cursor.getString(cursor.getColumnIndex("dakika")));
            if(cursor.getString(cursor.getColumnIndex("gun")).equals("1"))
            {
                dersgunu.setText("Pazartesi");

            }
            if(cursor.getString(cursor.getColumnIndex("gun")).equals("2"))
            {
                dersgunu.setText("Salı");

            }
            if(cursor.getString(cursor.getColumnIndex("gun")).equals("3"))
            {
                dersgunu.setText("Çarşamba");

            }
            if(cursor.getString(cursor.getColumnIndex("gun")).equals("4"))
            {
                dersgunu.setText("Perşembe");

            }
            if(cursor.getString(cursor.getColumnIndex("gun")).equals("5"))
            {
                dersgunu.setText("Cuma");

            }
            devamsizlik.setText(cursor.getString(cursor.getColumnIndex("maxdevamsizlik")));
        }


        derssaatplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(derssaat.getText().toString().equals(""))
                {
                    derssaat.setText("00");

                }
                else if(Integer.parseInt(derssaat.getText().toString()) >= 0 && Integer.parseInt(derssaat.getText().toString()) < 23)
                {
                    cderssaat=Integer.parseInt(derssaat.getText().toString());
                    cderssaat++;
                    if(cderssaat<10)
                        derssaat.setText("0"+Integer.toString(cderssaat));
                    else
                        derssaat.setText(Integer.toString(cderssaat));

                }


            }
        });
        derssaatminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(derssaat.getText().toString().equals(""))
                {
                    derssaat.setText("00");

                }
                else if(Integer.parseInt(derssaat.getText().toString())-1 >= 0 && Integer.parseInt(derssaat.getText().toString()) < 24 )
                {
                    cderssaat=Integer.parseInt(derssaat.getText().toString());
                    cderssaat--;
                    if(cderssaat<10)
                        derssaat.setText("0"+Integer.toString(cderssaat));
                    else
                        derssaat.setText(Integer.toString(cderssaat));
                }


            }
        });
        dersdakikaplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dersdakika.getText().toString().equals(""))
                {
                    dersdakika.setText("00");

                }
                else if(Integer.parseInt(dersdakika.getText().toString()) >= 0 && Integer.parseInt(dersdakika.getText().toString()) < 59)
                {
                    cdersdakika=Integer.parseInt(dersdakika.getText().toString());
                    if(cdersdakika==55)
                        cdersdakika=cdersdakika+4;
                    else
                        cdersdakika=cdersdakika+5;
                    if(cdersdakika<10)
                        dersdakika.setText("0"+Integer.toString(cdersdakika));
                    else
                        dersdakika.setText(Integer.toString(cdersdakika));
                }

            }
        });
        dersdakikaminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dersdakika.getText().toString().equals(""))
                {
                    dersdakika.setText("00");

                }
                else if(Integer.parseInt(dersdakika.getText().toString())-1 >= 0 && Integer.parseInt(dersdakika.getText().toString()) <= 60)
                {
                    cdersdakika=Integer.parseInt(dersdakika.getText().toString());
                    if(cdersdakika==59)
                        cdersdakika=cdersdakika-4;
                    else
                        cdersdakika=cdersdakika-5;
                    if(cdersdakika<10)
                        dersdakika.setText("0"+Integer.toString(cdersdakika));
                    else
                        dersdakika.setText(Integer.toString(cdersdakika));
                }


            }
        });
        devamsizlikplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(devamsizlik.getText().toString().equals(""))
                {
                    devamsizlik.setText("0");

                }
                else if(Integer.parseInt(devamsizlik.getText().toString()) >= 0 && Integer.parseInt(devamsizlik.getText().toString()) <13)
                {
                    cdevamsizlik=Integer.parseInt(devamsizlik.getText().toString());
                    cdevamsizlik++;
                    devamsizlik.setText(Integer.toString(cdevamsizlik));

                }


            }
        });
        devamsizlikminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(devamsizlik.getText().toString().equals(""))
                {
                    devamsizlik.setText("0");

                }
                else if(Integer.parseInt(devamsizlik.getText().toString())-1 >= 0 && Integer.parseInt(devamsizlik.getText().toString()) <14)
                {
                    cdevamsizlik=Integer.parseInt(devamsizlik.getText().toString());
                    cdevamsizlik--;
                    devamsizlik.setText(Integer.toString(cdevamsizlik));

                }

            }
        });
        gunplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (dersgunu.getText().toString())
                {
                    case "Pazartesi":
                    {
                        dersgunu.setText(days[1]);
                        break;
                    }
                    case "Salı":
                    {
                        dersgunu.setText(days[2]);
                        break;
                    }
                    case "Çarşamba":
                    {
                        dersgunu.setText(days[3]);
                        break;
                    }
                    case "Perşembe":
                    {
                        dersgunu.setText(days[4]);
                        break;
                    }
                    case "Cuma":
                    {
                        dersgunu.setText(days[4]);
                        break;
                    }
                    default:dersgunu.getText().toString();
                }



            }
        });
        gunminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (dersgunu.getText().toString())
                {
                    case "Pazartesi":
                    {
                        dersgunu.setText(days[0]);
                        break;
                    }
                    case "Salı":
                    {
                        dersgunu.setText(days[0]);
                        break;
                    }
                    case "Çarşamba":
                    {
                        dersgunu.setText(days[1]);
                        break;
                    }
                    case "Perşembe":
                    {
                        dersgunu.setText(days[2]);
                        break;
                    }
                    case "Cuma":
                    {
                        dersgunu.setText(days[3]);
                        break;
                    }
                    default:dersgunu.getText().toString();
                }
            }
        });

        guncelle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(dersadi.getText().toString().equals("") || derssaat.getText().toString().equals("") || dersdakika.getText().toString().equals("") || devamsizlik.getText().toString().equals(""))
                    {
                        Toast.makeText(dersguncelle.this, "Boş Alan Bırakmayınız!", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        SQLiteDatabase database= dersguncelle.this.openOrCreateDatabase("Ders", MODE_PRIVATE, null);
                        database.execSQL("CREATE TABLE IF NOT EXISTS dersler (id INTEGER PRIMARY KEY,dersadi VARCHAR,gun VARCHAR,saat VARCHAR,devamsizlik INTEGER,maxdevamsizlik INTEGER)");
                        if(dersgunu.getText().toString().equals("Pazartesi"))
                        {
                            database.execSQL("UPDATE dersler SET dersadi='"+dersadi.getText().toString()+"',gun='1',saat='"+derssaat.getText().toString()+"',dakika='"+dersdakika.getText().toString()+"',maxdevamsizlik="+devamsizlik.getText()+" WHERE id="+lessonid+"");
                        }
                        if(dersgunu.getText().toString().equals("Salı"))
                        {
                            database.execSQL("UPDATE dersler SET dersadi='"+dersadi.getText().toString()+"',gun='2',saat='"+derssaat.getText().toString()+"',dakika='"+dersdakika.getText().toString()+"',maxdevamsizlik="+devamsizlik.getText()+" WHERE id="+lessonid+"");
                        }
                        if(dersgunu.getText().toString().equals("Çarşamba"))
                        {
                            database.execSQL("UPDATE dersler SET dersadi='"+dersadi.getText().toString()+"',gun='3',saat='"+derssaat.getText().toString()+"',dakika='"+dersdakika.getText().toString()+"',maxdevamsizlik="+devamsizlik.getText()+" WHERE id="+lessonid+"");

                        }
                        if(dersgunu.getText().toString().equals("Perşembe"))
                        {
                            database.execSQL("UPDATE dersler SET dersadi='"+dersadi.getText().toString()+"',gun='4',saat='"+derssaat.getText().toString()+"',dakika='"+dersdakika.getText().toString()+"',maxdevamsizlik="+devamsizlik.getText()+" WHERE id="+lessonid+"");

                        }
                        if(dersgunu.getText().toString().equals("Cuma"))
                        {
                            database.execSQL("UPDATE dersler SET dersadi='"+dersadi.getText().toString()+"',gun='5',saat='"+derssaat.getText().toString()+"',dakika='"+dersdakika.getText().toString()+"',maxdevamsizlik="+devamsizlik.getText()+" WHERE id="+lessonid+"");

                        }
                        Toast.makeText(dersguncelle.this, "Ders Başarıyla Güncellendi.", Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(dersguncelle.this, MainActivity.class);
                        intent.putExtra("name",name);
                        startActivity(intent);
                    }

                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    Toast.makeText(dersguncelle.this, "Ders Güncellenemedi.", Toast.LENGTH_LONG).show();

                }

            }
        });
    }

}
