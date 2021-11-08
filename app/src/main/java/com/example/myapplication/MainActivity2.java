package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.GridView;

import java.util.ArrayList;

import model.Image;
import model.ImageAdapter;

public class MainActivity2 extends AppCompatActivity {
    GridView gridView;
    int[] images ={R.drawable.images,R.drawable.gym,R.drawable.file};
    ArrayList<Image> arrImages = new ArrayList<Image>();
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        gridView = findViewById(R.string.PhotoGridId);
        for(int i=0; i<images.length; i++){
            Image im = new Image(images[i]);
            arrImages.add(im);
        }
        ImageAdapter adapter= new ImageAdapter(getApplicationContext(), arrImages);
        gridView.setAdapter(adapter);
    }
}