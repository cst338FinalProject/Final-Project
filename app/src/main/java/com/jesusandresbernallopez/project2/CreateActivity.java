package com.jesusandresbernallopez.project2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class CreateActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        Button checkButton = findViewById(R.id.checkButton);
        checkButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v){
        /* TODO: Before sending user back to the home page
        1. Verify the account(If any fail go to 2.)
            A. Check account does not exist
            B. Check the account username is formatted correctly
            a. Inform customer account was created successfully
            b. Send to home page
        2. Display an error message
            A. Customer confirms error message
            B. Send to home page
        */
        if(v.getId() == R.id.checkButton){
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }
    }

}
