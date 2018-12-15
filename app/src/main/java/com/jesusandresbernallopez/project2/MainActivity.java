/*
 *  Title: MainActivity.java

 *  Abstract: Home screen of the app

 *  Authors: Jesus A. Bernal Lopez
 *           Mike Menendez

 *  Date: 12-14-2018
 */

package com.jesusandresbernallopez.project2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button createAccountButton = findViewById(R.id.createAccount);
        createAccountButton.setOnClickListener(this);

        Button reserveSeat = findViewById(R.id.reserveSeat);
        reserveSeat.setOnClickListener(this);

        Button cancelReservation = findViewById(R.id.cancelReservation);
        cancelReservation.setOnClickListener(this);

        Button manageSystem = findViewById(R.id.manageSystem);
        manageSystem.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){

        if(v.getId() == R.id.createAccount){
            Intent i = new Intent(this, CreateActivity.class);
            startActivity(i);
        }else if(v.getId() == R.id.reserveSeat){
            Intent i = new Intent(this, ReserveSeat.class);
            startActivity(i);
        }else if(v.getId() == R.id.cancelReservation){
            Intent i = new Intent(this, CancelReservation.class);
            startActivity(i);
        }else if(v.getId() == R.id.manageSystem){
            Intent i = new Intent(this, ManageSystem.class);
            startActivity(i);
        }
    }
}
