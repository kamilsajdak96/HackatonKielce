package com.example.kamil.hackatonkielce.registerDirectory;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kamil.hackatonkielce.R;
import com.example.kamil.hackatonkielce.authDirectory.AuthActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {


    EditText emailId;
    EditText passID;

    Button registerButt;
    Button goBackButt;
    private FirebaseAuth mAuth;

    private String email;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        registerButt = (Button) findViewById(R.id.registerButt);
        goBackButt = (Button) findViewById(R.id.goBackButt);
        emailId = (EditText) findViewById(R.id.emailId);
        passID = (EditText) findViewById(R.id.passID);


        goBackButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("sth", "finish");
//                Intent toy = new Intent(RegisterActivity.this, AuthActivity.class);
//                startActivity(toy);
                finish();
            }
        });

        goBackButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onRegisterClick();
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        //FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);


    }

    protected void onRegisterClick() {

        registerButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = emailId.getText().toString();
                password = passID.getText().toString();

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Log.d("sth", "signInWithEmail:success");
                                    startActivity(new Intent(getApplicationContext(), AuthActivity.class));
                                    finish();
                                } else {
                                    Log.d("sth", "signInWithEmail:failure");
                                    Toast.makeText(getApplicationContext(), "E-mail or password is wrong", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

    }


}


