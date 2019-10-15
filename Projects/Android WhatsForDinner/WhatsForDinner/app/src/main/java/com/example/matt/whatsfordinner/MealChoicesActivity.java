package com.example.matt.whatsfordinner;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.style.LineHeightSpan;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MealChoicesActivity extends AppCompatActivity {

    MyDBDish dbDish;
    MyDBIngredients dbIngredients;

    View mainLayout;

    List<Button> theDishButtonsAdded = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_choices);

        mainLayout = (LinearLayout)findViewById(R.id.dishButtonsLinearLayout);

        configButtons();
        dbDish = new MyDBDish(this, null, null, 1);
        dbIngredients = new MyDBIngredients(this, null, null, 1);
        updateView();
    }

    private void configButtons() {
        FloatingActionButton homeButton = (FloatingActionButton) findViewById(R.id.homeFABfromMealChoices);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //this finishes the activity and goes back to the root activity on the back stack
                finish();
            }
        });

        FloatingActionButton familyButton = (FloatingActionButton) findViewById(R.id.familyFABfromMealChoices);
        familyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MealChoicesActivity.this, MyFamilyActivity.class));
            }
        });

    }
    public void onRadioButtonClicked(View view) {
        //finish this later
    }

    public void updateView(){
        List<String> theseDishes = dbDish.getDishString();
        for(int x = 0; x < theDishButtonsAdded.size(); x++){
            theDishButtonsAdded.get(x).setVisibility(View.GONE);
        }

        //System.out.println(dbDish.getSize() + "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        for(int x = 0; x < dbDish.getSize(); x++){
            final Button newbutton = new Button(this);
            newbutton.setText(theseDishes.get(x));
            //newbutton.setGravity(Gravity.CENTER_HORIZONTAL);
            newbutton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    //go to that dish page, do later
                    Intent intent = new Intent(getBaseContext(), DishActivity.class);
                    intent.putExtra("dishname", newbutton.getText().toString());
                    startActivity(intent);
                }
            });
            newbutton.setClickable(true);
            newbutton.setPadding(2,2,2,2);
            newbutton.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 80));
            theDishButtonsAdded.add(newbutton);
            ((LinearLayout) mainLayout).addView(newbutton);

        }
    }
}
