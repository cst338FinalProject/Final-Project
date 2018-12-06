package com.jesusandresbernallopez.project2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class ReserveSeat extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_seat);

        Button checkButton = findViewById(R.id.checkButton);
        checkButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v){
        /*
            TODO: All the checking to make sure that customer is reserving seat
            1. Pop up to show the customer possible flights
            2. Customer picks one and then another popup/view shows up
                for customer to input username and password
            3. After system confirms it show a pop up with flight information
            4. Customer confirms and goes back to home page
        */
        if(v.getId() == R.id.checkButton){
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }
    }
}
