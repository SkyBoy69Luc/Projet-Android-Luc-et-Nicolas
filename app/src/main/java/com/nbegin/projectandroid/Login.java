package com.nbegin.projectandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private EditText emailEdit;
    private EditText passwordEdit;
    private Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailEdit = findViewById(R.id.txt_email);
        passwordEdit = findViewById(R.id.txt_password);
        btnLogin = findViewById(R.id.btn_login);
        firebaseAuth = FirebaseAuth.getInstance();
        setListener();
    }
    private void setListener(){
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();
            }
        });
    }
    private void Login(){
        String email = emailEdit.getText().toString();
        String password = passwordEdit.getText().toString();
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    showMessage(R.string.welcome);
                    goToActivity(MainActivity.class);
                }else{
                    showMessage(R.string.incorrect_information);
                }
            }
        });
    }
    private void showMessage(int message){
        Toast.makeText(this, message,Toast.LENGTH_SHORT).show();
    }
    private void goToActivity(Class activityToGoTo){
        Intent sendUserToSingUpOrLigInIntet = new Intent(this, activityToGoTo);
        startActivity(sendUserToSingUpOrLigInIntet);
    }
}
