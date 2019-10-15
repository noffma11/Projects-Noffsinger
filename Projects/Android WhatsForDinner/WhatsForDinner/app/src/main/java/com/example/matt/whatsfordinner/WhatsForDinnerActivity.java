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

public class WhatsForDinnerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whats_for_dinner);

        configButtons();
    }

    private void configButtons() {
        FloatingActionButton homeButton = (FloatingActionButton) findViewById(R.id.homeFABfromWFD);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        FloatingActionButton familyButton = (FloatingActionButton) findViewById(R.id.familyFABfromWFD);
        familyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WhatsForDinnerActivity.this, MyFamilyActivity.class));
            }
        });
    }


    //would have added the functionality to change how what the view
    //shows when one of the "main" or "side" buttons is press
    //(time restricted)
    public void onRadioButtonClicked(View view) {
        //finish later
    }
}
