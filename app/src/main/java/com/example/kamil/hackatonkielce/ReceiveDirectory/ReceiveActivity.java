package com.example.kamil.hackatonkielce.ReceiveDirectory;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kamil.hackatonkielce.FromReceiveActivityLocation;
import com.example.kamil.hackatonkielce.R;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class ReceiveActivity extends AppCompatActivity {

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

        recyclerView  =findViewById(R.id.offersRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        offertsList = new ArrayList<>() ;


        buttonLocation = (Button) findViewById(R.id.buttonLocation);
        buttonLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // = 0;
                Intent toy = new Intent(ReceiveActivity.this, FromReceiveActivityLocation.class);
                startActivity(toy);
            }
        });
        buttonFilter = (Button) findViewById(R.id.buttonFilter);
        buttonFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(ReceiveActivity.this, "Tu nam sie sortuje baza", Toast.LENGTH_SHORT).show();

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

        Query query = FirebaseDatabase.getInstance().getReference("AddingOfferts")
                .orderByChild("streetName")
                .equalTo(street);

    }

    protected void selectAll() {
        Log.d("sth","zatancze na twoim grobie");
        Query query = FirebaseDatabase.getInstance().getReference("AddingOfferts");
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
                    Log.d("sth",""+artist.title);
                }
                offertsAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };



//        Firebase.setAndroidContext(this);
//
//        setUrl("https://androidhack-e4f9d.firebaseio.com/AddingOfferts");
//
//
//        mref1 = new Firebase(getUrl());

//        mListView = (ListView) findViewById(R.id.listVieww);
//        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, mWydzialy);
//        mListView.setAdapter(arrayAdapter);
//
//        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                if (position == 0) {
//                    Toast.makeText(ReceiveActivity.this, "Zrob zdjecie/wybierz z galerii", Toast.LENGTH_SHORT).show();
//                    zmiennaDoPrzekazaniaWFourthOffer = 0;
//                }
//                if (position == 1) {
//                    Toast.makeText(ReceiveActivity.this, "Zrob zdjecie/wybierz z galerii", Toast.LENGTH_SHORT).show();
//                    zmiennaDoPrzekazaniaWFourthOffer = 1;
//                }
//                if (position == 2) {
//                    Toast.makeText(ReceiveActivity.this, "Zrob zdjecie/wybierz z galerii", Toast.LENGTH_SHORT).show();
//                    zmiennaDoPrzekazaniaWFourthOffer = 2;
//                }
//                if (position == 3) {
//                    Toast.makeText(ReceiveActivity.this, "Zrob zdjecie/wybierz z galerii", Toast.LENGTH_SHORT).show();
//                    zmiennaDoPrzekazaniaWFourthOffer = 3;
//
//                }
//                if (position == 4) {
//                    Toast.makeText(ReceiveActivity.this, "Zrob zdjecie/wybierz z galerii", Toast.LENGTH_SHORT).show();
//                    zmiennaDoPrzekazaniaWFourthOffer = 4;
//                }
//
//                if (position == 5) {
//                    Toast.makeText(ReceiveActivity.this, "Zrob zdjecie/wybierz z galerii", Toast.LENGTH_SHORT).show();
//
//                    zmiennaDoPrzekazaniaWFourthOffer = 5;
//                }
//                if (position == 6) {
//                    Toast.makeText(ReceiveActivity.this, "Zrob zdjecie/wybierz z galerii", Toast.LENGTH_SHORT).show();
//
//                    zmiennaDoPrzekazaniaWFourthOffer = 6;
//
//                }
//                if (position == 7) {
//                    Toast.makeText(ReceiveActivity.this, "Zrob zdjecie/wybierz z galerii", Toast.LENGTH_SHORT).show();
//
//                    zmiennaDoPrzekazaniaWFourthOffer = 7;
//
//
//                }
//
//            }
//        });
//
//
//        mref1.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                //String value = dataSnapshot.getValue(String.class);
//                String value = String.valueOf(dataSnapshot.getValue());
//                mWydzialy.add(value);
//                arrayAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onCancelled(FirebaseError firebaseError) {
//
//            }
//        });


//        abuttonDodaj = (Button) findViewById(R.id.buttonDodaj);
//        abuttonDodaj.setOnClickListener(new View.OnClickListener(){
//
//            @Override
//            public void onClick(View view){
//
//                Firebase mRefChild = mref.child("Name");
//                mRefChild.setValue("Goeff");
//            }
//
//        });

    }

//    public String getUrl() {
//    return url;
//}
//
//    public void setUrl(String url) {
//        this.url = url;
//    }
