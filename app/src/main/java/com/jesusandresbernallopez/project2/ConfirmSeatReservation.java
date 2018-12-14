/*
 *  Title: ConfirmSeatReservation.java

 *  Abstract: The customer enters username and password to confirm the cancellation of the reservation

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
import android.widget.TextView;
import java.util.StringTokenizer;

public class ConfirmSeatReservation extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_seat_reservation);

        String a = getIntent().getExtras().getString("Flight Number");

        TextView title = findViewById(R.id.label);
        title.setText("Flight:\n" + a);

        Button checkButton = findViewById(R.id.checkButton);
        checkButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        if(v.getId() == R.id.checkButton){
            Reservation reservation = new Reservation();
            Database db = new Database(this);

            EditText user = findViewById(R.id.usernameEditText);
            String username = user.getText().toString();

            EditText pass = findViewById(R.id.passwordEditText);
            String password = pass.getText().toString();

            String flightInfo = getIntent().getExtras().getString("Flight Info");
            String delim = ",";
            StringTokenizer st = new StringTokenizer(flightInfo, delim);
            String flightNum = st.nextToken();
            String departure = st.nextToken();
            String arrival = st.nextToken();
            String departTime = st.nextToken();
            st.nextToken();
            st.nextToken();
            String price = st.nextToken();
            String numOfTickets = getIntent().getExtras().getString("Tickets");
            int claimed = Integer.valueOf(st.nextToken());
            double totalPrice = Double.valueOf(price) * Integer.valueOf(numOfTickets);

            boolean reserveSucccesful = reservation.newReservation(db, username, password, Integer.valueOf(numOfTickets), flightNum);

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            final Intent intent = new Intent(this, MainActivity.class);

            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    startActivity(intent);
                }
            });

            int reservationNum = -1;
            if(reserveSucccesful){
                reservationNum = reservation.getLastReservation(db, username, password);
            }

            String message = "Username: " + username +"\n"+
                    "Flight Number: " + flightNum + "\n"+
                    "Departure: " + departure + ", " + departTime + "\n"+
                    "Arrival: " + arrival + "\n"+
                    "Number Of Tickets: " + numOfTickets + "\n"+
                    "Reservation Number: " + Integer.toString(reservationNum) + "\n"+
                    "Total amount: " + Double.toString(totalPrice);


            if (reserveSucccesful){
                builder.setMessage(message);
                String str = "UPDATE flights set claimedSeats = " + claimed + Integer.valueOf(numOfTickets) + " where name = '" + flightNum + "';";
                db.update(str);
            }else{
                builder.setMessage("You tried but failed");
            }


            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

}