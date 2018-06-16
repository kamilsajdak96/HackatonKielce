package com.example.kamil.hackatonkielce;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.kamil.hackatonkielce.giveDirectory.PaidOffers;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

public class GiveActivityForVoluntary extends AppCompatActivity {

    EditText addTitle;
    EditText textContent;
    EditText addPhoneNumber;

    private Button btnChoose;
    private ImageView imageView;

    private Uri filepath;

    private final int PICK_IMAGE_REQUEST = 71;

    FirebaseStorage storage;
    StorageReference storageReference;

    Button button;
    Button buttonAddReport;

    DatabaseReference raportDatabase;

    public GiveActivityForVoluntary() {
    }


    //test
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_give_voluntary);

        raportDatabase = FirebaseDatabase.getInstance().getReference("AddingVolunterOfferts");

        button = (Button) findViewById(R.id.buttonPhoto);
        Typeface fontOfButtons = Typeface.createFromAsset(getAssets(),"fonts/Bold.ttf");
        button.setTypeface(fontOfButtons);
        button = (Button) findViewById(R.id.buttonLocalization);
        Typeface fontOfButtons2 = Typeface.createFromAsset(getAssets(),"fonts/Bold.ttf");
        button.setTypeface(fontOfButtons);
        buttonAddReport = (Button) findViewById(R.id.buttonAccept);
        Typeface fontOfButtons3 = Typeface.createFromAsset(getAssets(),"fonts/Bold.ttf");
        buttonAddReport.setTypeface(fontOfButtons);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        buttonAddReport = (Button) findViewById(R.id.buttonAccept);
        addTitle = (EditText) findViewById(R.id.addTitle);
        textContent = (EditText) findViewById(R.id.textContent);
        addPhoneNumber = (EditText) findViewById(R.id.addPhoneNumber);


        imageView = (ImageView)findViewById(R.id.imgView);

        // button = (Button) findViewById(R.id.buttonPhoto);
        button = (Button) findViewById(R.id.buttonPhoto);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();

            }
        });

        buttonAddReport = (Button) findViewById(R.id.buttonAccept);
        buttonAddReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // uploadImage();
//                text = editText.getText().toString();
//                Intent toy = new Intent(edmt.dev.androidgridlayout.ActivitySecondSettings.this, ActivitySecondWorkers.class);
//                startActivity(toy);
                uploadImage();
                addReport();

//                try {
//
//                    Thread.sleep(2000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }

            }
        });


//        button = (Button) findViewById(R.id.buttonPhoto);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //tutaj bedzie otwarcie do wybrania zdjecia albo do zrobienia zdjecia
//                Toast.makeText(GiveActivity.this, "Zrob zdjecie/wybierz z galerii", Toast.LENGTH_SHORT).show();
//
//            }
//        });
        button = (Button) findViewById(R.id.buttonLocalization);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent toy = new Intent(GiveActivityForVoluntary.this, FromGiveActivityToLocation.class);
                startActivity(toy);
            }
        });


    }

    private void uploadImage() {

        if(filepath != null){
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Ładuje...");
            progressDialog.show();

            StorageReference ref = storageReference.child("images/"+ UUID.randomUUID().toString());
            ref.putFile(filepath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Log.d("sth", ""+ taskSnapshot.toString());
                            progressDialog.dismiss();
                            Toast.makeText(GiveActivityForVoluntary.this, "Załadowano obrazek!", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(GiveActivityForVoluntary.this, "Nie załadowano obrazka :(!", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                            progressDialog.setMessage("Uploaded" + (int)progress+"%");
                        }
                    });

        }

    }

    private void chooseImage() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Picture"),PICK_IMAGE_REQUEST);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null){

            filepath = data.getData();
            try{
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),filepath);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e){
                e.printStackTrace();
            }

        }

    }

    private void addReport(){
        String title = addTitle.getText().toString().trim();
        String text = textContent.getText().toString();
        String number = addPhoneNumber.getText().toString();

        if(!TextUtils.isEmpty(title)){

            String id = raportDatabase.push().getKey();
            PaidOffers reports = new PaidOffers(id,title,text,number);
            raportDatabase.child(id).setValue(reports);
            Toast.makeText(this,"Dodano raport, dziękujemy.",Toast.LENGTH_LONG).show();

        }

        else{
            Toast.makeText(this,"Wprowadź dane!", Toast.LENGTH_LONG).show();
        }
    }
}
