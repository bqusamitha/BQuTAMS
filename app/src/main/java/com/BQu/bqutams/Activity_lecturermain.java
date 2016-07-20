package com.BQu.bqutams;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Activity_lecturermain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecturermain);
    }

    //click on HR Button
    public void onclickdeviceregistration(View view){

        Intent intent = new Intent(Activity_lecturermain.this, Activity_searchstudents.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
