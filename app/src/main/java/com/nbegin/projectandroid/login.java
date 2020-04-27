package com.nbegin.projectandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class login extends AppCompatActivity {

    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.btn_login);
        setListener();

    }
    private void setListener(){
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActivity(MainActivity.class);
            }
        });

    }
    private void goToActivity(Class activityToGoTo){
        Intent sendUserToSingUpOrLigInIntet = new Intent(this, activityToGoTo);
        startActivity(sendUserToSingUpOrLigInIntet);
    }
    //TODO sprint 2 haha
}
