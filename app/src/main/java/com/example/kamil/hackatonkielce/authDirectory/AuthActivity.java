package com.example.kamil.hackatonkielce.authDirectory;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kamil.hackatonkielce.MainActivity;
import com.example.kamil.hackatonkielce.R;
import com.example.kamil.hackatonkielce.registerDirectory.RegisterActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class AuthActivity extends AppCompatActivity {


    //    @BindView(R.id.passEditText)
    EditText passEditText;
    //    @BindView(R.id.emailEditText)
    EditText emailEditText;
    //    @BindView(R.id.loginButton)
    Button loginButton;
    //    @BindView(R.id.registerButton)
    Button registerButton;
    //TextView textView;

    private String email;
    private String password;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        loginButton = findViewById(R.id.loginButton);
        registerButton = findViewById(R.id.registerButton);
        emailEditText = findViewById(R.id.emailEditText);
        passEditText = findViewById(R.id.passEditText);


        mAuth = FirebaseAuth.getInstance();


        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent toy = new Intent(AuthActivity.this, RegisterActivity.class);
                startActivity(toy);
                finish();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onUserLogin();

            }

        });


    }


    @Override
    protected void onStart() {
        super.onStart();


    }


    protected void onUserLogin() {

        email = emailEditText.getText().toString();
        password = passEditText.getText().toString();


        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("sth", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                             Intent toy = new Intent(AuthActivity.this, MainActivity.class);
                                            startActivity(toy);
                             //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.d("sth", "signInWithEmail:failure", task.getException());
                            Toast.makeText(AuthActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            // updateUI(null);
                        }

                    }
                });
    }


}



