package com.example.matt.whatsfordinner;

/*
Matthew Noffsinger
CSCI 490
Final project "Whats for dinner" app
*/

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MealActivity extends AppCompatActivity {

    MyDBDish dbDish;
    MyDBIngredients dbIngredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal);

        configButtons();
        dbDish = new MyDBDish(this, null, null, 1);
        dbIngredients = new MyDBIngredients(this, null, null, 1);
    }

    private void configButtons() {
        FloatingActionButton homeButton = (FloatingActionButton) findViewById(R.id.homeFABfromMeals);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //this finishes the activity and goes back to the root activity on the back stack
                finish();
            }
        });

        FloatingActionButton familyButton = (FloatingActionButton) findViewById(R.id.familyFABfromMeals);
        familyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MealActivity.this, MyFamilyActivity.class));
            }
        });

        Button browseButton = (Button) findViewById(R.id.buttonBrowse);
        browseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MealActivity.this, MealChoicesActivity.class));
            }
        });

        Button createButton = (Button) findViewById(R.id.buttonCreate);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createDish();
            }
        });
    }

    public void createDish() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("New Dish Info");

        final EditText dname = new EditText(this);
        dname.setGravity(Gravity.CENTER_HORIZONTAL);
        dname.setHint("Dish name");
        final RadioGroup buttons = new RadioGroup(this);
        buttons.setOrientation(LinearLayout.HORIZONTAL);
        buttons.setGravity(Gravity.CENTER_HORIZONTAL);

        final RadioButton main = new RadioButton(this);
        main.setText("Main");
        final RadioButton side = new RadioButton(this);
        side.setText("Side");
        buttons.addView(main);
        buttons.addView(side);

        LinearLayout lay = new LinearLayout(this);
        lay.setOrientation(LinearLayout.VERTICAL);
        lay.addView(dname);
        lay.addView(buttons);
        builder.setView(lay);

        // Set up the buttons
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String dishName = dname.getText().toString();
                int index = buttons.getCheckedRadioButtonId();
                System.out.println(index + "HEHERJEKRJSKFN:LSKDJF:SLKDJHFSKDJHFLSKDJHF" + dishName);
                if (index % 2 == 0) {
                    System.out.println("SIDE SELECTED");
                    dbDish.addDish(dishName, "Side");
                } else if (index == -1) {
                    //nothing selected
                    System.out.println("NOTHING SELECTED");
                } else {
                    dbDish.addDish(dishName, "Main");
                }
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.cancel();
            }
        });

        builder.show();
    }

}
