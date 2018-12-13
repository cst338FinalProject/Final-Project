package com.jesusandresbernallopez.project2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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

    }
}
