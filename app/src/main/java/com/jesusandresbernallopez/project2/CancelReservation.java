package com.jesusandresbernallopez.project2;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class CancelReservation extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_reservation);

        Button checkButton = findViewById(R.id.checkButton);
        checkButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v){
        /* TODO: DO all the checking before sending customer to a new window
            1. If no reservations inform customer and go to home page after customer verifies
            2. If customer has reservations
                A. Display them in a popup/new view
                B. Customer selects reservation he/she wishes to cancel
                C. Customer confirms it
                D. App goes back to home page

         */
        if(v.getId() == R.id.checkButton){
            // if customer has reservation
            // send to another view with customer's reservations
            // else
            final Intent intent = new Intent(this, MainActivity.class);
            AlertDialog.Builder builder = new  AlertDialog.Builder(this);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    startActivity(intent);
                }
            });
            Boolean a = true;
            if(a/*If customer has no reservations*/){
                builder.setTitle("Fail");
                builder.setMessage("Sorry, no reservation with the entered credentials.");
            }else{ // display reservations that the customer has

            }

            AlertDialog dialog = builder.create();
            dialog.show();

        }
    }

}
