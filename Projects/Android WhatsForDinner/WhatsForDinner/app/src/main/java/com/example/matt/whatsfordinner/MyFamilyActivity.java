package com.example.matt.whatsfordinner;

/*
Matthew Noffsinger
CSCI 490
Final project "Whats for dinner" app
*/

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MyFamilyActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinner;

    //all the databases we use
    MyDBHandler dbHandler;
    MyDBMembers dbMembers;
    MyDBMemberPreferences dbMembPreferences;


    View mainLayout;

    //from dialog box for a new family
    private String newFamName = "";

    List<String> theseFamiliesStrings = new ArrayList<>();
    List<Family> theseFamilies = new ArrayList<Family>();

    List<TextView> theMemberViewsAdded = new ArrayList<>();

    String currentFamily = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_family);

        configButtons();
        dbHandler = new MyDBHandler(this, null, null, 1);
        dbMembers = new MyDBMembers(this, null, null, 1);
        dbMembPreferences = new MyDBMemberPreferences(this, null, null, 1);


        spinner = (Spinner) findViewById(R.id.spinnerFamilyNames);
        spinner.setOnItemSelectedListener(this);


        mainLayout = (LinearLayout) findViewById(R.id.infoFamilyLinearLayout);


        printDatabase();
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        String theseNames[] = new String[theseFamiliesStrings.size()];
        theseNames = theseFamiliesStrings.toArray(theseNames);
        currentFamily = theseNames[i];
        Toast.makeText(getApplicationContext(), theseNames[i], Toast.LENGTH_SHORT).show();

        //List<String> theseMembers = dbMembers.getMembersString(dbHandler.getId(currentFamily));
        updateView();



    }

    private void updateView() {

        List<String> theseMembers = dbMembers.getMembersString(dbHandler.getId(currentFamily));
        for (int x = 0; x < theMemberViewsAdded.size(); x++) {

            theMemberViewsAdded.get(x).setVisibility(View.GONE);

        }

        for (int x = 0; x < theseMembers.size(); x++) {

            final TextView valueTV = new TextView(this);
            valueTV.setText(theseMembers.get(x));
            valueTV.setGravity(Gravity.CENTER_HORIZONTAL);

            //THIS CHECKS TO SEE IF THE NAME IS CLICKED THEN GOES TO THE MEMBER DETAIL SECTION
            valueTV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(getBaseContext(), MemberProfileActivity.class);
                    intent.putExtra("name", valueTV.getText().toString());
                    startActivity(intent);

                }
            });
            valueTV.setClickable(true);
            valueTV.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            theMemberViewsAdded.add(valueTV);
            ((LinearLayout) mainLayout).addView(valueTV);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        Toast.makeText(getApplicationContext(), "Please choose a family", Toast.LENGTH_SHORT).show();
    }

    private void configButtons() {

        FloatingActionButton homeButton = (FloatingActionButton) findViewById(R.id.homeFABfromMyFamily);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        FloatingActionButton newFamButton = (FloatingActionButton) findViewById(R.id.newFamFAB);
        newFamButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addNewFamily(view);
            }
        });


        FloatingActionButton removeFamButton = (FloatingActionButton) findViewById(R.id.removeFamFAB);
        removeFamButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                removeButtonClicked(view);
            }
        });


        Button addMembButton = (Button) findViewById(R.id.newMemberButton);
        addMembButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewMember();
            }
        });

    }


    private void addNewMember() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("What is their name?");

        // Set up the input
        final EditText membName = new EditText(this);
        membName.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(membName);

        System.out.println(currentFamily);
        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String thisMemb = membName.getText().toString();
                int familyId = dbHandler.getId(currentFamily);
                dbMembers.addMember(thisMemb, familyId);
                updateView();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    //Print the database
    public void printDatabase() {
        //THIS IS FOR THE SPINNER
        theseFamiliesStrings = dbHandler.getFamiliesString();
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, theseFamiliesStrings.toArray());
        spinner.setAdapter(aa);

    }

    public void addNewFamily(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("New family name");

        // Set up the input
        final EditText famName = new EditText(this);
        famName.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(famName);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                newFamName = famName.getText().toString();
                Family family = new Family(newFamName);
                theseFamilies.add(family);
                dbHandler.addFamily(family);
                printDatabase();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();

    }

    //Delete items
    public void removeButtonClicked(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Family to remove");

        // Set up the input
        final EditText famName = new EditText(this);
        famName.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(famName);
        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                newFamName = famName.getText().toString();
                dbHandler.deleteFamily(newFamName);

                printDatabase();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();


    }

}