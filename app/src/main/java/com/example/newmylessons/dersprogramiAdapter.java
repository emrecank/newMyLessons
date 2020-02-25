package com.example.newmylessons;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class dersprogramiAdapter extends ArrayAdapter<HashMap<String,String>> {
    private ArrayList<HashMap<String,String>> dersprogrami;
    private Context context;
    private int resource;
    private View view;
    Calendar calendar;
    String[] days={"Pazartesi","Salı","Çarşamba","Perşembe","Cuma"};

    private HashMap<String,String> hashMap;
    public dersprogramiAdapter(Context context, int resource, ArrayList<HashMap<String, String>> objects)
    {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.dersprogrami=objects;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {

        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=inflater.inflate(resource, parent,false);

        TextView dersprog=(TextView)view.findViewById(R.id.tt);
        ImageView dersproguyari=(ImageView)view.findViewById(R.id.dersproguyari);
        dersproguyari.setVisibility(View.INVISIBLE);


        hashMap=dersprogrami.get(position);

        calendar= Calendar.getInstance();
        int day=calendar.get(Calendar.DAY_OF_WEEK);
        /*if(Integer.parseInt(hashMap.get("devamsizlik"))==Integer.parseInt(hashMap.get("maxdevamsizlik")))
        {
            Toast.makeText(context, days[Integer.parseInt(hashMap.get("gun"))-1]+" günü "+hashMap.get("dersadi")+" dersine gitmelisiniz,devamsızlık sınırına ulaştınız!", Toast.LENGTH_LONG).show();
            dersproguyari.setVisibility(View.VISIBLE);
        }
        else if(Integer.parseInt(hashMap.get("devamsizlik"))>Integer.parseInt(hashMap.get("maxdevamsizlik"))){
            dersprog.setBackgroundResource(android.R.color.holo_red_light);
            dersproguyari.setVisibility(View.VISIBLE);
        }
        else {
            dersprog.setBackgroundColor(Color.WHITE);
            dersproguyari.setVisibility(View.INVISIBLE);
        }*/

        if(hashMap.get("gun").equals("1"))
        {
            dersprog.setText("Pazartesi : "+hashMap.get("dersadi")+" - "+hashMap.get("saat")+"."+hashMap.get("dakika"));
            if(day==Calendar.MONDAY)
            {
                dersprog.setBackgroundResource(android.R.color.holo_green_light);
                dersprog.setTextColor(Color.WHITE);

            }
            else
            {
                dersprog.setBackgroundResource(android.R.color.white);
                dersprog.setTextColor(Color.BLACK);

            }
        }
        if(hashMap.get("gun").equals("2"))
        {
            dersprog.setText("Salı : "+hashMap.get("dersadi")+" - "+hashMap.get("saat")+"."+hashMap.get("dakika"));
            if(day==Calendar.TUESDAY)
            {
                dersprog.setBackgroundResource(android.R.color.holo_green_light);
                dersprog.setTextColor(Color.WHITE);
            }
            else
            {
                dersprog.setBackgroundResource(android.R.color.white);
                dersprog.setTextColor(Color.BLACK);

            }
        }
        if(hashMap.get("gun").equals("3"))
        {
            dersprog.setText("Çarşamba : "+hashMap.get("dersadi")+" - "+hashMap.get("saat")+"."+hashMap.get("dakika"));
            if(day==Calendar.WEDNESDAY)
            {
                dersprog.setBackgroundResource(android.R.color.holo_green_light);
                dersprog.setTextColor(Color.WHITE);
            }
            else
            {
                dersprog.setBackgroundResource(android.R.color.white);
                dersprog.setTextColor(Color.BLACK);

            }
        }
        if(hashMap.get("gun").equals("4"))
        {
            dersprog.setText("Perşembe : "+hashMap.get("dersadi")+" - "+hashMap.get("saat")+"."+hashMap.get("dakika"));
            if(day==Calendar.THURSDAY)
            {
                dersprog.setBackgroundResource(android.R.color.holo_green_light);
                dersprog.setTextColor(Color.WHITE);
            }
            else
            {
                dersprog.setBackgroundResource(android.R.color.white);
                dersprog.setTextColor(Color.BLACK);


            }
        }
        if(hashMap.get("gun").equals("5"))
        {
            dersprog.setText("Cuma : "+hashMap.get("dersadi")+" - "+hashMap.get("saat")+"."+hashMap.get("dakika"));
            if(day==Calendar.FRIDAY)
            {
                dersprog.setBackgroundResource(android.R.color.holo_green_light);
                dersprog.setTextColor(Color.WHITE);
            }
            else
            {
                dersprog.setBackgroundResource(android.R.color.white);
                dersprog.setTextColor(Color.BLACK);

            }
        }

        return view;
    }
}
