package com.jesusandresbernallopez.project2;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewFlight extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_flight);

        Button addFlight = findViewById(R.id.addFlightButton);
        addFlight.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){ //TODO: Add an addFlight function
        if(v.getId() == R.id.addFlightButton){
            EditText fn = findViewById(R.id.flightNumber);
            String flightNumber = fn.getText().toString();

            EditText d = findViewById(R.id.departureEditText);
            String departure = d.getText().toString();

            EditText a = findViewById(R.id.arrivalEditText);
            String arrival = a.getText().toString();

            EditText dt = findViewById(R.id.departureTimeEditText);
            String departureTime = dt.getText().toString();

            EditText fc = findViewById(R.id.flightCapacityEditText);
            String flightCap = fc.getText().toString();

            EditText p = findViewById(R.id.priceEditText);
            Float price = Float.valueOf(p.getText().toString());


            Flight flight = new Flight();
            Database db = new Database(getBaseContext());

            final boolean flightAdded = flight.addFlight(flightNumber, departure, arrival, Integer.valueOf(departureTime),
                    Integer.valueOf(flightCap), price, db);


            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            final Intent intent = new Intent(this, MainActivity.class);

            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    if(flightAdded){
                        startActivity(intent);
                    }
                }
            });

           if(flightAdded){
               builder.setTitle("Success");
               builder.setMessage("Flight has been added");
           }else{
               builder.setTitle("Failure");
               builder.setMessage("Check information and try again.");
           }

           AlertDialog dialog = builder.create();
           dialog.show();


        }
    }
}
