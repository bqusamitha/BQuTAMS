package com.BQu.bqutams;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText usernameEt,passwordEt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usernameEt = (EditText)findViewById(R.id.user_name);
        passwordEt = (EditText)findViewById(R.id.password);
    }

    public void onLogin(View view){

        String username = usernameEt.getText().toString();
        String password = passwordEt.getText().toString();
        String type = "login";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type,username,password);

    }
}
