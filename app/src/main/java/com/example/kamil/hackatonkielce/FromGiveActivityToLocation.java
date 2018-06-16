package com.example.kamil.hackatonkielce;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

public class FromGiveActivityToLocation extends AppCompatActivity {

    TextView t;
    Button button;
    //test
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_from_give_activity_to_location);

        Intent toy = new Intent(FromGiveActivityToLocation.this, MapsActivity.class);
        startActivity(toy);

    }
}
