package com.nbegin.projectandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private Basedrum basedrum;
    private Cymbale1 cymbale;
    private Hihat hihat;
    private Snare snare;
    private Tom1 tom1;
    private Tom2 tom2;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);
        firebaseAuth = FirebaseAuth.getInstance();
        initialiseClass();
    }

    private void initialiseClass() {
        basedrum = findViewById(R.id.bassedrum1);
        cymbale = findViewById(R.id.cymbale1);
        hihat = findViewById(R.id.hihat);
        snare = findViewById(R.id.snare);
        tom1 = findViewById(R.id.tom1);
        tom2 = findViewById(R.id.tom2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        super.onOptionsItemSelected(item);
        if(item.getItemId()== R.id.menu_logout){
            callDisconnectedMessage();
            return true;
        }
        if(item.getItemId()== R.id.menu_exit){
            firebaseAuth.signOut();
            finishAffinity();
        }
        return false;
    }
    private void callDisconnectedMessage(){
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.msg_disconnected);
        Button dialogBtnOk = dialog.findViewById(R.id.btn_ok);

        dialogBtnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                returnToSingUp();
            }
        });
        dialog.show();
    }

    private void sendUserToSingUp(){
        Intent sendUserToSingUpOrLigInIntet = new Intent(this, Login.class);
        startActivity(sendUserToSingUpOrLigInIntet);
    }

    private void returnToSingUp() {
        firebaseAuth.signOut();
        sendUserToSingUp();
    }
}
