package com.example.kamil.hackatonkielce;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kamil.hackatonkielce.FromReceiveActivityLocation;
import com.example.kamil.hackatonkielce.R;
import com.example.kamil.hackatonkielce.ReceiveDirectory.Offerts;
import com.example.kamil.hackatonkielce.ReceiveDirectory.OffertsAdapter;
import com.example.kamil.hackatonkielce.ReceiveDirectory.SingleItemDirectory.SingleItemActivity;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class ReceiveActivityForVoluntary extends AppCompatActivity {

    //private Firebase mref1;
    // private String url;
    //private ListView mListView;
    //private ArrayList<String> mWydzialy = new ArrayList<>();
    //public static int zmiennaDoPrzekazaniaWFourthOffer;

    RecyclerView recyclerView;
    OffertsAdapter offertsAdapter;
    private List<Offerts> offertsList;

    DatabaseReference dbArtists;


    TextView textView;
    Button buttonLocation;
    Button buttonFilter;

    public static int hehe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive);

        recyclerView = findViewById(R.id.offersRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        offertsList = new ArrayList<>();


        buttonLocation = (Button) findViewById(R.id.buttonLocation);

        Typeface fontOfButtons = Typeface.createFromAsset(getAssets(),"fonts/Bold.ttf");
        buttonLocation.setTypeface(fontOfButtons);
        buttonLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // = 0;
                Intent toy = new Intent(ReceiveActivityForVoluntary.this, FromReceiveActivityLocation.class);
                startActivity(toy);
            }
        });
        buttonFilter = (Button) findViewById(R.id.buttonFilter);
        Typeface fontOfButtons1 = Typeface.createFromAsset(getAssets(),"fonts/Bold.ttf");
        buttonFilter.setTypeface(fontOfButtons1);
        buttonFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(ReceiveActivityForVoluntary.this, "Tu nam sie sortuje baza", Toast.LENGTH_SHORT).show();

            }
        });

        selectAll();


        textView = (TextView) findViewById(R.id.textView1);
        Typeface myCustomFont = Typeface.createFromAsset(getAssets(), "fonts/Pacifico.ttf");
        textView.setTypeface(myCustomFont);


        offertsAdapter = new OffertsAdapter(this, offertsList);
        recyclerView.setAdapter(offertsAdapter);
    }


    protected void selectStreet(String street) {

        Query query = FirebaseDatabase.getInstance().getReference("AddingVolunterOfferts")
                .orderByChild("streetName")
                .equalTo(street);

    }

    protected void selectAll() {
        Log.d("sth", "zatancze na twoim grobie");
        Query query = FirebaseDatabase.getInstance().getReference("AddingVolunterOfferts");
        query.addListenerForSingleValueEvent(valueEventListener);
    }


    ValueEventListener valueEventListener = new ValueEventListener() {


        @Override
        public void onDataChange(@NonNull com.google.firebase.database.DataSnapshot dataSnapshot) {
            offertsList.clear();
            if (dataSnapshot.exists()) {
                for (com.google.firebase.database.DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Offerts artist = snapshot.getValue(Offerts.class);
                    offertsList.add(artist);
                    Log.d("sth", "" + artist.title);
                }
                offertsAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };


}