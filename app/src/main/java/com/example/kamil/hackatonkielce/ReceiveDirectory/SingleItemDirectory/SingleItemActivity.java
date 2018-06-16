package com.example.kamil.hackatonkielce.ReceiveDirectory.SingleItemDirectory;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kamil.hackatonkielce.MapsActivity;
import com.example.kamil.hackatonkielce.R;

public class SingleItemActivity extends AppCompatActivity {

    TextView txt;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_item);

        Bundle extras = getIntent().getExtras();

        String addPhoneNumber = extras.getString("addPhoneNumber");
        String title = extras.getString("title");
        String sallary  = extras.getString("sallary");
        String idOfPaidOffer = extras.getString("PaidOffer");
        String description = extras.getString("desc");



        txt = (TextView) findViewById(R.id.textViewTitle);
        Typeface fontOfButtons = Typeface.createFromAsset(getAssets(),"fonts/Bold.ttf");
        txt.setTypeface(fontOfButtons);
        txt.setText(title);
        btn = (Button) findViewById(R.id.buttonLocalization);
        btn.setTypeface(fontOfButtons);
        txt = (TextView) findViewById(R.id.textViewSalary);
        if(sallary ==null) {
            txt.setText(" ");
        } else {
            txt.setText("Kwota: " + sallary + "zł");
        }
        txt = (TextView) findViewById(R.id.textContent);
        txt.setText(description);
        txt = (TextView) findViewById(R.id.textViewPhone);
        txt.setText("Kontakt: " + addPhoneNumber);



        Log.d("sth","jestem w single item "+ addPhoneNumber);

    }

}
