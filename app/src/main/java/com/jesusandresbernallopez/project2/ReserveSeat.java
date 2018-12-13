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
import java.util.StringTokenizer;

import java.util.ArrayList;

public class ReserveSeat extends AppCompatActivity implements View.OnClickListener{

    ArrayList<String> a = new ArrayList<String>();
    String delim = ",";
    String numOfTickets = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_seat);

        Button checkButton = findViewById(R.id.checkButton);
        checkButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v){
        Log.d("ID", Integer.toString(v.getId()));

        if(v.getId() == R.id.checkButton){
            popUp();
        }

        for(int i = 0; i < a.size(); i++){
            if(v.getId() == i){
                Intent intent = new Intent(this, ConfirmSeatReservation.class);
                Bundle bundle = new Bundle();

                StringTokenizer st = new StringTokenizer(a.get(i), delim);

                bundle.putString("Flight Number", st.nextToken());
                bundle.putString("Flight Info", a.get(i));
                bundle.putString("Tickets", numOfTickets);

                intent.putExtras(bundle);
                startActivity(intent);
                Log.d("Why", a.get(i));
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
        numOfTickets = t;

        a = flight.flightSearch(Integer.valueOf(t),
                departure.getText().toString(),
                arrival.getText().toString() , db);

        for(int i = 0; i < a.size(); i++){
            Button b = new Button(this);

            StringTokenizer st = new StringTokenizer(a.get(i), delim);
            String display = st.nextToken();
            b.setText(display);
            b.setId(i);
            b.setOnClickListener(this);
            layout.addView(b);
        }

        layout.setOnClickListener(this);

        setContentView(layout);
        Log.d("Pop", "at least it got here");
    }
}