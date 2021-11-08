package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import model.Person;
import model.TextViewAdapter;

public class Main4Activity extends AppCompatActivity {
    GridView gridView;
    double bmi;
    ArrayList<Person> p = new ArrayList<Person>();
    List<String> items = new ArrayList<String>();
    List<String> links = new ArrayList<String>();
    ListView listView;
    Button btn;
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        Intent intent= getIntent();
        listView=findViewById(R.string.listId);
        listView.setClickable(true);

        Gson gson = new Gson();
        Person person = gson.fromJson(intent.getStringExtra("Person"),Person.class);
        p.add(0,person);
        gridView=findViewById(R.string.gridId);
        CalculateBMIThread bmiThread = new CalculateBMIThread(Double.parseDouble(person.getWeight()),Double.parseDouble(person.getHeight()));
        bmiThread.run();

        items.add("Name: "+person.getName());
        items.add("Weight: "+person.getWeight());
        DecimalFormat df = new DecimalFormat("0.00");
        items.add("BMI: "+df.format(p.get(0).getBmi()));
        Toast.makeText(this,p.get(0).getBmi()+"",Toast.LENGTH_SHORT).show();

        TextViewAdapter adapter = new TextViewAdapter(this,items);
        gridView.setAdapter(adapter);
        String link1 = "https://www.youtube.com/watch?v=cZnsLVArIt8&ab_channel=FitnessBlender";
        String link2 = "https://www.youtube.com/watch?v=QvbpeETBoGg&ab_channel=Tomzo";

        links.add(link1);
        links.add(link2);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,links);
        listView.setAdapter(adapter1);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                String link =adapter1.getItem(i);
                intent.setData(Uri.parse(link));
                startActivity(intent);
            }
        });
        btn=findViewById(R.string.fitnessId);
        btn.setOnClickListener(this::btnPhotos_onClick);
    }
    public  double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
    public void btnPhotos_onClick(View view){
        Intent intent = new Intent(this,MainActivity2.class);
        startActivity(intent);
    }
    class CalculateBMIThread extends Thread{
        double weight,height;
        CalculateBMIThread(double weight,double height){
            this.weight=weight;
            this.height=height;
        }
        public void run(){


            p.get(0).setBmi(this.weight/(this.height*this.height));

        }


    }

}