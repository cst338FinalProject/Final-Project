package com.jesusandresbernallopez.project2;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
        EditText user = findViewById(R.id.usernameEditText);
        EditText pass = findViewById(R.id.passwordEditText);
        String username = user.getText().toString();
        String password = pass.getText().toString();

        if(v.getId() == R.id.checkButton){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            final Intent intent = new Intent(this, MainActivity.class);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    startActivity(intent);
                }
            });

            if(username.equals("!admiM2")/* || check that username and password are valid entries || username is not available*/){
                builder.setTitle("Fail");
                builder.setMessage("You suck.");
            }else{ // new account can be created and you are good to go
                builder.setTitle("Success");
                builder.setMessage("Your account was created.");
            }

            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }



}
