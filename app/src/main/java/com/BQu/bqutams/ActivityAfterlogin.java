package com.BQu.bqutams;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class ActivityAfterlogin extends AppCompatActivity {

    ImageButton lectureimage,hrimage,eventiage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afterlogin);
    }
        //click on lecturer Button
        public void onclicklecturer(View view){

            Intent intent = new Intent(ActivityAfterlogin.this, Activity_lecturermain.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

        //click on event Button
        public void onclickevent(View view){

            Intent intent = new Intent(ActivityAfterlogin.this, Activity_eventmain.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

        //click on HR Button
        public void onclickhr(View view){

            Intent intent = new Intent(ActivityAfterlogin.this, Activity_hrmain.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
}
