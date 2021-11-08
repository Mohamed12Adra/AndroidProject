package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import model.Person;

public class MainActivity extends AppCompatActivity {
    TextView name,weight,gender;
    EditText nameText,weightText,heightText;
    Spinner genderSpinner;
    Button btnStart,btnSave;
    ArrayList<Person> person = new ArrayList<Person>();

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameText=findViewById(R.string.nameId);
        weightText=findViewById(R.string.weightId);
        btnStart=findViewById(R.string.buttonId);
        btnSave=findViewById(R.string.saveId);
        genderSpinner=findViewById(R.string.spinnerId);
        heightText=findViewById(R.string.heightId);
        ArrayList<String> spinnerItems = new ArrayList<String>();
        spinnerItems.add("Female");
        spinnerItems.add("Male");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,spinnerItems);

        genderSpinner.setAdapter(adapter);
       btnStart.setOnClickListener(this::btnStart_onClick);
       btnSave.setOnClickListener(this::btnSave_onClick);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(this);
        String personJson = sh.getString("Person","");
        Gson gson = new Gson();
        Person person1 = gson.fromJson(personJson,Person.class);
        person.add(0,person1);
        nameText.setText(person1.getName());
        weightText.setText(person1.getWeight());
        heightText.setText(person1.getHeight());
        int index=0;
        for(int i=0; i<genderSpinner.getAdapter().getCount(); i++){
            if(person1.getGender().contains(genderSpinner.getItemAtPosition(i).toString())){
                index=i;
                break;
            }
        }
        genderSpinner.setSelection(index);

    }

    public void btnStart_onClick(View view){

        Gson gson = new Gson();
        Intent intent = new Intent(this,Main4Activity.class);
        String personString = gson.toJson(person.get(0));
       intent.putExtra("Person",personString);
        startActivity(intent);
    }
    public void btnSave_onClick(View view){

        String name = nameText.getText().toString();
        String weight = weightText.getText().toString();
        String height = heightText.getText().toString();
        String gender = genderSpinner.getSelectedItem().toString();
        double w = Double.parseDouble(weight);
        double h = Double.parseDouble(height);
        if(w>0 && h>0) {
            CreatePersonThread thread = new CreatePersonThread(name, weight, height, gender);
            thread.run();
            Gson gson = new Gson();
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = prefs.edit();
            String personJson = gson.toJson(person.get(0));
            editor.putString("Person", personJson);
            editor.commit();
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Check the height and the weight", Toast.LENGTH_LONG).show();
        }

    }
    class CreatePersonThread extends Thread{
        String name1 ;
        String weight1 ;
        String height1 ;
        String gender1;
        CreatePersonThread(String name1,String weight1,String height1, String gender1){
            this.name1=name1;
            this.weight1=weight1;
            this.height1=height1;
            this.gender1=gender1;
        }
        public void run(){
            Person person1 = new Person(name1,weight1,height1,gender1);
            person.add(0,person1);
        }
    }
}