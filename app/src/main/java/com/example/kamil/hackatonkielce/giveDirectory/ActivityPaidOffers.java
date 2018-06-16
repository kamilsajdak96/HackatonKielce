package com.example.kamil.hackatonkielce.giveDirectory;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.kamil.hackatonkielce.R;
import com.firebase.client.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

//import edmt.dev.androidgridlayout.ActivitySecondWorkers;
//import edmt.dev.androidgridlayout.R;

public class ActivityPaidOffers extends AppCompatActivity {
    Button buttonAddReport;
    EditText editTextName;
    Spinner spinner;

    DatabaseReference raportDatabase;

    public static int hehe;

    public static String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paid_offers);

        raportDatabase = FirebaseDatabase.getInstance().getReference("PaidOffer");

        editTextName = (EditText) findViewById(R.id.editTextName);
        buttonAddReport = (Button) findViewById(R.id.buttonAddReport);
        spinner = (Spinner) findViewById(R.id.spinner);
        buttonAddReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hehe = 0;
//                text = editText.getText().toString();
//                Intent toy = new Intent(edmt.dev.androidgridlayout.ActivitySecondSettings.this, ActivitySecondWorkers.class);
//                startActivity(toy);
                addReport();
            }
        });

    }
    private void addReport(){
        String name = editTextName.getText().toString().trim();
        String genre = spinner.getSelectedItem().toString();

        if(!TextUtils.isEmpty(name)){

            String id = raportDatabase.push().getKey();
            //PaidOffers reports = new PaidOffers(id,name,genre);
           // raportDatabase.child(id).setValue(reports);
            Toast.makeText(this,"Dodano raport, dziękujemy.",Toast.LENGTH_LONG).show();

        }
        else{
            Toast.makeText(this,"Wprowadź dane!", Toast.LENGTH_LONG).show();
        }
    }
}