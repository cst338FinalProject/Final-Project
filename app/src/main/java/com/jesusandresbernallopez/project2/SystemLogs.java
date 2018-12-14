/*
 *  Title: SystemLogs.java

 *  Abstract: Displays activity to the admin.

 *  Authors: Jesus A. Bernal Lopez
 *           Mike Menendez

 *  Date: 12-14-2018
 */

package com.jesusandresbernallopez.project2;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class SystemLogs extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_logs);

        Button yesButton = findViewById(R.id.selectedYes);
        yesButton.setOnClickListener(this);

        Button noButton = findViewById(R.id.selectedNo);
        noButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        if(v.getId() == R.id.selectedYes){
            Intent i = new Intent(this, NewFlight.class);
            startActivity(i);
        }else if(v.getId() == R.id.selectedNo){
            showLogs();
        }
    }

    private void showLogs(){
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layout.setLayoutParams(params);

        Database db = new Database(getBaseContext());
        String s = "SELECT * FROM log";
        Cursor c = db.lookup(s);
        Button b = new Button(this);
        b.setText("Hi");
        b.setOnClickListener(this);
        layout.addView(b);

        int column = c.getColumnCount();
        int row = c.getCount();

        for(int i = 0; i < row; i++){
            for(int j = 0; j < column; j++){
                try{

                }catch(Exception e){

                }
            }
        }

        layout.setOnClickListener(this);

        setContentView(layout);
    }
}
