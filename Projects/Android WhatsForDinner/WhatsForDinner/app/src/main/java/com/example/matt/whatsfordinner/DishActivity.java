package com.example.matt.whatsfordinner;

/*
Matthew Noffsinger
CSCI 490
Final project "Whats for dinner" app
*/

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DishActivity extends AppCompatActivity {

    MyDBDish dbDish;
    MyDBIngredients dbIngredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish);

        String dishName = getIntent().getStringExtra("dishname");

        TextView theMealNameTextView = (TextView) findViewById(R.id.mealNameTitle);
        theMealNameTextView.setText(dishName);
        TextView theMealTypeTextView = (TextView) findViewById(R.id.mealTypeTitle);
            //this section does not work yet, query isn't working
        //theMealTypeTextView.setText(dbDish.getMainOrSide(dishName));

        configButtons();
        dbDish = new MyDBDish(this, null, null, 1);
        dbIngredients = new MyDBIngredients(this, null, null, 1);
        updateView();
    }

    private void updateView() {
        //not implemented but will be for drawing from the database
        // to see what ingredients this dish has
        //most of the code is written but no time to add it now
    }

    private void configButtons() {


        FloatingActionButton homeButton = (FloatingActionButton) findViewById(R.id.homeFABfromDish);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        FloatingActionButton familyButton = (FloatingActionButton) findViewById(R.id.familyFABfromDish);
        familyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(DishActivity.this, MyFamilyActivity.class));
            }
        });
    }
}
