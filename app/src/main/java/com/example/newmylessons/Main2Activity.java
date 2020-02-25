package com.example.newmylessons;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    EditText name;
    Button go;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        sharedPreferences=this.getPreferences(Main2Activity.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        Intent into=getIntent();
        if(into.getStringExtra("namer")!=null)
        {
            setContentView(R.layout.activity_main2);
            name=findViewById(R.id.name);
            name.setText(into.getStringExtra("namer"));
            go=findViewById(R.id.go);
            go.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String isim=name.getText().toString();
                    if(isim.equals(""))
                    {
                        Toast.makeText(Main2Activity.this, "Bu Alan Zorunludur!", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        editor.putString("name",isim);
                        editor.commit();
                        Intent intent=new Intent(Main2Activity.this, MainActivity.class);
                        intent.putExtra("name",isim);
                        startActivity(intent);
                        Toast.makeText(Main2Activity.this, "Adınız Başarıyla Değiştirildi!", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
        else if(sharedPreferences.getString("name","ad yok").equals("ad yok"))
        {
            setContentView(R.layout.activity_main2);
            name=findViewById(R.id.name);
            go=findViewById(R.id.go);
            go.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String isim=name.getText().toString();
                    if(isim.equals(""))
                    {
                        Toast.makeText(Main2Activity.this, "Bu Alan Zorunludur!", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        editor.putString("name",isim);
                        editor.commit();
                        Intent intent=new Intent(Main2Activity.this, MainActivity.class);
                        intent.putExtra("name",isim);
                        startActivity(intent);
                    }

                }
            });
        }
        else
        {
            finish();
            Intent intent=new Intent(Main2Activity.this,MainActivity.class);
            intent.putExtra("name",sharedPreferences.getString("name","abcd"));
            startActivity(intent);
        }

    }
}
