package com.example.matt.whatsfordinner;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button buttonWhatsForDinner;
    Button buttonMeals;
    Button buttonRandomMeal;
    FloatingActionButton familyFAB;
    FloatingActionButton homeFAB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configButtons();
    }

    private void configButtons(){
        buttonWhatsForDinner = (Button) findViewById(R.id.buttonWhatsForDinner);
        buttonWhatsForDinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, WhatsForDinnerActivity.class));
            }
        });

        buttonMeals = (Button) findViewById(R.id.buttonMeals);
        buttonMeals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MealActivity.class));
            }
        });

        buttonRandomMeal = (Button) findViewById(R.id.buttonRandomMeal);
        buttonRandomMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //change this for correct activity
                //startActivity(new Intent(MainActivity.this, MyFamilyActivity.class));
            }
        });

        familyFAB = (FloatingActionButton) findViewById(R.id.familyFAB);
        familyFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MyFamilyActivity.class));
            }
        });

    }
}
