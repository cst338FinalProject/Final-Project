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
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

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

        // i < theNumberOf AvailableFlights
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

        // i < theNumberOf AvailableFlights
        for(int i = 0; i < 2; i++){
            Button b = new Button(this);
            // the text will be the available flights
            b.setText("Available Flight: " + Integer.toString(i + 1));
            b.setId(i);
            b.setOnClickListener(this);
            layout.addView(b);
        }

        layout.setOnClickListener(this);

        setContentView(layout);
    }
}
