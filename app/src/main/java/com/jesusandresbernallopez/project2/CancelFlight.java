package com.jesusandresbernallopez.project2;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class CancelFlight extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout());
    }

    @Override
    public void onClick(View v){
        ArrayList<String> list = getIntent().getExtras().getStringArrayList("list");
        for(int i = 0; i < list.size(); i++){
            if(v.getId() == i){
                StringTokenizer st = new StringTokenizer(list.get(i), ",");
                Reservation reservation = new Reservation();
                Database db = new Database(getBaseContext());
                boolean didDelete = reservation.deleteReservation(db, getIntent().getExtras().getString("username"),
                        getIntent().getExtras().getString("password"), Integer.valueOf(st.nextToken()));

                final Intent intent = new Intent(this, MainActivity.class);
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(intent);
                    }
                });
                if(didDelete){
                    builder.setTitle("Success");
                    builder.setMessage("Your reservation was deleted");
                }else{
                    builder.setTitle("Fail");
                    builder.setMessage("Sorry, there was a problem please try again later.");
                }

                AlertDialog dialog = builder.create();
                dialog.show();;

            }
        }
    }

    private LinearLayout layout(){
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layout.setLayoutParams(params);

        ArrayList<String> list = getIntent().getExtras().getStringArrayList("list");
        for(int i = 0; i < list.size(); i++){
            Button b = new Button(this);

            StringTokenizer st = new StringTokenizer(list.get(i), ",");
            st.nextToken();
            b.setText(st.nextToken());
            b.setId(i);
            b.setOnClickListener(this);
            layout.addView(b);
        }


        layout.setOnClickListener(this);

        return layout;
    }
}
