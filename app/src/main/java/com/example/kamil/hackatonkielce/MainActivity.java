package com.example.kamil.hackatonkielce;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.kamil.hackatonkielce.ReceiveDirectory.ReceiveActivity;
import com.example.kamil.hackatonkielce.giveDirectory.GiveActivity;

public class MainActivity extends AppCompatActivity {

    TextView t;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        t = (TextView) findViewById(R.id.textView1);
        Typeface myCustomFont = Typeface.createFromAsset(getAssets(),"fonts/Pacifico.otf");
        t.setTypeface(myCustomFont);
        button = (Button) findViewById(R.id.buttonGiveJob);
        Typeface fontOfButtons = Typeface.createFromAsset(getAssets(),"fonts/Bold.ttf");
        button.setTypeface(fontOfButtons);
        button = (Button) findViewById(R.id.buttonReceiveJob);
        Typeface fontOfButtons2 = Typeface.createFromAsset(getAssets(),"fonts/Bold.ttf");
        button.setTypeface(fontOfButtons);
        button = (Button) findViewById(R.id.buttonVoluntary);
        Typeface fontOfButtons3 = Typeface.createFromAsset(getAssets(),"fonts/Bold.ttf");
        button.setTypeface(fontOfButtons);

        button = (Button) findViewById(R.id.buttonGiveJob);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //hehe = 0;
                Intent toy = new Intent(MainActivity.this, GiveActivity.class);
                startActivity(toy);
            }
        });
        button = (Button) findViewById(R.id.buttonReceiveJob);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent toy = new Intent(MainActivity.this, ReceiveActivity.class);
                startActivity(toy);
            }
        });

        button = (Button) findViewById(R.id.buttonVoluntary);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Intent toy = new Intent(MainActivity.this, VoluntaryActivity.class);
                Intent toy = new Intent(MainActivity.this, VoluntaryActivity.class);
                startActivity(toy);
            }
        });


    }

}
