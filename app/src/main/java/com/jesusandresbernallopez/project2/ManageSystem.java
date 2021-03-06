/*
 *  Title: ManageSystem.java

 *  Abstract: Prompts user for admin username and password if denied sends to homescreen and if
 *            approves send admin to the next screen.

 *  Authors: Jesus A. Bernal Lopez
 *           Mike Menendez

 *  Date: 12-14-2018
 */

package com.jesusandresbernallopez.project2;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ManageSystem extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_system);

        Button checkButton = findViewById(R.id.checkButton);
        checkButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        if(v.getId() == R.id.checkButton){
            EditText username = findViewById(R.id.usernameEditText);
            EditText password = findViewById(R.id.passwordEditText);
            String user = username.getText().toString();
            String pass = password.getText().toString();

            Account a = new Account();
            if(a.adminVerify(user, pass)){
                Intent i = new Intent(this, SystemLogs.class);
                startActivity(i);
            }else{
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                final Intent intent = new Intent(this, MainActivity.class);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(intent);
                    }
                });

                builder.setTitle("Wrong Credentials");
                builder.setMessage("Check username and password and try again.");

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        }
    }
}
