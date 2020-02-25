package com.example.newmylessons;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class dersekle extends AppCompatActivity {
    TextView dersgunu;
    EditText dersadi,derssaat,dersdakika,devamsizlik;
    Button ekle,derssaatplus,derssaatminus,dersdakikaplus,dersdakikaminus,devamsizlikplus,devamsizlikminus,gunminus,gunplus;
    int cderssaat=0;
    int cdersdakika=0;
    int cdevamsizlik=0;
    int cgun=0;
    String[] days={"Pazartesi","Salı","Çarşamba","Perşembe","Cuma"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dersekle);
        Intent intent=getIntent();
        final String name=intent.getStringExtra("name");



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
        ekle=findViewById(R.id.ekle);





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
                if(cgun>=0 && cgun<4)
                {
                    cgun++;
                    dersgunu.setText(days[cgun]);

                }



            }
        });
        gunminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cgun-1>=0 && cgun<=4)
                {
                    cgun--;
                    dersgunu.setText(days[cgun]);

                }

            }
        });

        ekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(dersadi.getText().toString().equals("") || derssaat.getText().toString().equals("") || dersdakika.getText().toString().equals("") || devamsizlik.getText().toString().equals(""))
                    {
                        Toast.makeText(dersekle.this, "Boş Alan Bırakmayınız!", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        SQLiteDatabase database= dersekle.this.openOrCreateDatabase("Ders", MODE_PRIVATE, null);
                        database.execSQL("CREATE TABLE IF NOT EXISTS dersler (id INTEGER PRIMARY KEY,dersadi VARCHAR,gun VARCHAR,saat VARCHAR,dakika VARCHAR,devamsizlik INTEGER,maxdevamsizlik INTEGER)");
                        if(dersgunu.getText().toString().equals("Pazartesi"))
                        {
                            database.execSQL("INSERT INTO dersler (dersadi,gun,saat,dakika,devamsizlik,maxdevamsizlik) VALUES ('"+dersadi.getText().toString()+"','1','"+derssaat.getText().toString()+"','"+dersdakika.getText().toString()+"',0,"+devamsizlik.getText()+")");

                        }
                        if(dersgunu.getText().toString().equals("Salı"))
                        {
                            database.execSQL("INSERT INTO dersler (dersadi,gun,saat,dakika,devamsizlik,maxdevamsizlik) VALUES ('"+dersadi.getText().toString()+"','2','"+derssaat.getText().toString()+"','"+dersdakika.getText().toString()+"',0,"+devamsizlik.getText()+")");

                        }
                        if(dersgunu.getText().toString().equals("Çarşamba"))
                        {
                            database.execSQL("INSERT INTO dersler (dersadi,gun,saat,dakika,devamsizlik,maxdevamsizlik) VALUES ('"+dersadi.getText().toString()+"','3','"+derssaat.getText().toString()+"','"+dersdakika.getText().toString()+"',0,"+devamsizlik.getText()+")");

                        }
                        if(dersgunu.getText().toString().equals("Perşembe"))
                        {
                            database.execSQL("INSERT INTO dersler (dersadi,gun,saat,dakika,devamsizlik,maxdevamsizlik) VALUES ('"+dersadi.getText().toString()+"','4','"+derssaat.getText().toString()+"','"+dersdakika.getText().toString()+"',0,"+devamsizlik.getText()+")");

                        }
                        if(dersgunu.getText().toString().equals("Cuma"))
                        {
                            database.execSQL("INSERT INTO dersler (dersadi,gun,saat,dakika,devamsizlik,maxdevamsizlik) VALUES ('"+dersadi.getText().toString()+"','5','"+derssaat.getText().toString()+"','"+dersdakika.getText().toString()+"',0,"+devamsizlik.getText()+")");

                        }
                        Toast.makeText(dersekle.this, "Ders Başarıyla Eklendi.", Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(dersekle.this, MainActivity.class);
                        intent.putExtra("name",name);
                        startActivity(intent);
                    }

                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    Toast.makeText(dersekle.this, "Ders Eklenemedi.", Toast.LENGTH_LONG).show();

                }

            }
        });
    }

}
