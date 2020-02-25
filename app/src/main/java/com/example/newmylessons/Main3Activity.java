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

public class Main3Activity extends AppCompatActivity {
    EditText sharedtext;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Button sharedsave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        sharedtext=findViewById(R.id.sharedtext);
        sharedsave=findViewById(R.id.sharedsave);
        sharedPreferences=this.getPreferences(Main2Activity.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        final String sharedname=sharedPreferences.getString("name","yok");
        sharedtext.setText(sharedname);

        sharedsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("name",sharedtext.getText().toString());
                editor.commit();
                Intent intent=new Intent(Main3Activity.this,MainActivity.class);
                intent.putExtra("name",sharedname);
                startActivity(intent);
                Toast.makeText(Main3Activity.this, "İsminiz Başarıyla Değiştirildi!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
