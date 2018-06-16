package com.example.kamil.hackatonkielce;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.kamil.hackatonkielce.ReceiveDirectory.ReceiveActivity;

public class VoluntaryActivity extends AppCompatActivity {

    TextView t;
    Button button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voluntary);

        t = (TextView) findViewById(R.id.textView1);
        Typeface myCustomFont = Typeface.createFromAsset(getAssets(),"fonts/Pacifico.otf");
        t.setTypeface(myCustomFont);
        button = (Button) findViewById(R.id.buttonGiveJob);
        Typeface fontOfButtons = Typeface.createFromAsset(getAssets(),"fonts/Bold.ttf");
        button.setTypeface(fontOfButtons);
        button = (Button) findViewById(R.id.buttonReceiveJob);
        Typeface fontOfButtons2 = Typeface.createFromAsset(getAssets(),"fonts/Bold.ttf");
        button.setTypeface(fontOfButtons);


        button = (Button) findViewById(R.id.buttonGiveJob);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent toy = new Intent(VoluntaryActivity.this, GiveActivityForVoluntary.class);
                startActivity(toy);
            }
        });
        button = (Button) findViewById(R.id.buttonReceiveJob);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent toy = new Intent(VoluntaryActivity.this, ReceiveActivityForVoluntary.class);
                startActivity(toy);
            }
        });


    }
}
