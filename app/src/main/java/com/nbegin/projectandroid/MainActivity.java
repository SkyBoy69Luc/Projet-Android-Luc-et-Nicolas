package com.nbegin.projectandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Basedrum basedrum;
    Cymbale1 cymbale;
    Hihat hihat;
    Snare snare;
    Tom1 tom1;
    Tom2 tom2;

    //FirebaseAuth firebaseAuth;
    EditText emailEdit;
    EditText passwordEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitialiseClass();

    }

    private void InitialiseClass() {
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
            //Toast.makeText(this, "Log out",Toast.LENGTH_SHORT).show();
            //firebaseAuth.signOut();
            sendUserToSingUpOrLigInActivity();
            return true;
        }
        if(item.getItemId()== R.id.menu_exit){
            this.finish();
            System.exit(0);

        }
        return false;
    }
    private void sendUserToSingUpOrLigInActivity(){
        Intent sendUserToSingUpOrLigInIntet = new Intent(this, login.class);
        startActivity(sendUserToSingUpOrLigInIntet);
    }
}
