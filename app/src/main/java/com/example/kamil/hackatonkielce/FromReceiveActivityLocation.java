package com.example.kamil.hackatonkielce;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

public class FromReceiveActivityLocation extends AppCompatActivity {

    TextView t;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_from_receive_activity_location);

        Intent toy = new Intent(FromReceiveActivityLocation.this, MapsActivity.class);
        startActivity(toy);

    }
}
