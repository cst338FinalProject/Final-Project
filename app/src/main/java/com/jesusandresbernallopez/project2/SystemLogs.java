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
import android.widget.TextView;

import java.util.ArrayList;

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
        String s = "SELECT * FROM log;";
        try {
            if (db.lookup(s) == null) {
                throw new Exception("Found no flights");
            }
            Cursor c = db.lookup(s);
            ArrayList<String> list = new ArrayList<>();

            StringBuilder sb = new StringBuilder();
            int col = c.getColumnCount();
            int row = c.getCount();

            for (int i = 0; i < row; i++) {
                c.moveToNext();
                for (int j = 0; j < col; j++) {
                    try {
                        sb.append(c.getString(j) + ",");
                    } catch (Exception e) {
                        sb.append(Integer.toString(c.getInt(j)) + ",");
                    }
                }
                list.add(sb.toString());
                sb = new StringBuilder();
            }

            c.close();
            db.close();
            for(int i = 0; i < list.size(); i++){
                TextView t = new TextView(this);
                t.setText(list.get(i));
                layout.addView(t);
            }

        } catch (Exception e) {
            System.exit(2);
        }

        layout.setOnClickListener(this);

        setContentView(layout);
    }
}
