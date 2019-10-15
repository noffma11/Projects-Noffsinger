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

public class MemberProfileActivity extends AppCompatActivity {

    MyDBDish dbDish;
    MyDBIngredients dbIngredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_profile);

        String memberName = getIntent().getStringExtra("name");

        TextView theMembersNameTextView = (TextView) findViewById(R.id.tempNameMemberTextView);
        theMembersNameTextView.setText(memberName);

        configButtons();
        dbDish = new MyDBDish(this, null, null, 1);
        dbIngredients = new MyDBIngredients(this, null, null, 1);
        updateView();

    }

    private void updateView() {
        //not implemented but will be for drawing from the database to see what
        // favorites and allergies this member has
    }

    private void configButtons() {
        Button addFavoriteButton = (Button) findViewById(R.id.addLikeButton);
        addFavoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //might not have time to finish this
            }
        });

        Button removeFavoriteButton = (Button) findViewById(R.id.removeLikeButton);
        removeFavoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //might not have time to finish this
            }
        });

        Button addAllergieButton = (Button) findViewById(R.id.addAllergieButton);
        addFavoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //might not have time to finish this
            }
        });

        Button removeAllergieButton = (Button) findViewById(R.id.removeAllergieButton);
        removeFavoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //might not have time to finish this
            }
        });


        FloatingActionButton homeButton = (FloatingActionButton) findViewById(R.id.homeFABfromMemberProfile);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //this finishes the activity and goes back to the root activity on the back stack
                finish();
            }
        });

        FloatingActionButton familyButton = (FloatingActionButton) findViewById(R.id.familyFABfromMemberProfile);
        familyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MemberProfileActivity.this, MyFamilyActivity.class));
            }
        });

    }
}
