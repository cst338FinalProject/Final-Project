package com.jesusandresbernallopez.project2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;

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
            popUp();
//            Intent i = new Intent(this, MainActivity.class);
//            startActivity(i);
        }

        for(int i = 0; i < 5; i++){
            if(v.getId() == i){
                Log.d("tag: \n", i + " ajksdnfamlenf\n\n\n\n\n");
            }
        }
    }

    private void popUp(){
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layout.setLayoutParams(params);

        Database db = new Database(getBaseContext());
        Flight flight = new Flight();

        EditText departure = findViewById(R.id.departureEditText);
        EditText arrival = findViewById(R.id.arrivalEditText);
        EditText tickets = findViewById(R.id.numberOfTickets);
        String t = tickets.getText().toString();

        Log.d("Tickets:\n", tickets.getText().toString());
        Log.d("Departure:\n", departure.getText().toString());
        Log.d("Arrival:\n", arrival.getText().toString());

        ArrayList<String> a = flight.flightSearch(Integer.valueOf(t),
                departure.getText().toString(),
                arrival.getText().toString() , db);

        for(int i = 0; i < a.size(); i++){
            Button b = new Button(this);
            b.setText("Available Flight: " + "ajsdfhaus"/*a.get(i)*/);
            b.setId(i);
            b.setOnClickListener(this);
            layout.addView(b);
        }

        layout.setOnClickListener(this);

        setContentView(layout);
    }
}
