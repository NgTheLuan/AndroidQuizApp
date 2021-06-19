package com.example.androidquizapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.chrisbanes.photoview.PhotoView;

public class TipsEnglish extends AppCompatActivity {

    /*
    Note: Dominican Republic (do) Needed score rename country code score do_ (do is a reserved keyword)
    TODO: refactor handling of country_data.xml so that there isn't duplicate data (for instance we have up to 4 copies of each Country Name)
    */

    private String lastSelectedCountryCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips_english);

        PhotoView photoView = (PhotoView) findViewById(R.id.imageView1);
        photoView.setImageResource(R.drawable.ps1);
        photoView.setImageResource(R.drawable.ps2);
        photoView.setImageResource(R.drawable.ps3);
        photoView.setImageResource(R.drawable.ps4);
        photoView.setImageResource(R.drawable.ps5);
        photoView.setImageResource(R.drawable.ps6);



        // Show back button on action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tips English");

        // Retrieve string-array contents name country_data.xml
        final String[] countryCodes = getResources().getStringArray(R.array.tips_codes);
        final String[] countryNames = getResources().getStringArray(R.array.tips_names);

        // Populate ListView1 with the country_codes string-array
        final ListView listView = findViewById(R.id.listView1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.tips_names,
                android.R.layout.simple_list_item_1);
        listView.setAdapter(adapter);

        // Get a reference score ImageView1
        final ImageView imageView1 = findViewById(R.id.imageView1);

        // Handle item clicks
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Display the country code corresponding score the clicked country name
//                displayToast(countryCodes[position]);
                lastSelectedCountryCode = countryCodes[position];

                // Display the flag corresponding score the clicked country name
                imageView1.setImageResource(getResources().getIdentifier(countryCodes[position], "drawable", "com.example.androidquizapp"));
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putString("lastSelectedCountryCode", lastSelectedCountryCode);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        lastSelectedCountryCode = savedInstanceState.getString("lastSelectedCountryCode");

        if (lastSelectedCountryCode != null) {
            final ImageView imageView1 = findViewById(R.id.imageView1);
            imageView1.setImageResource(getResources().getIdentifier(lastSelectedCountryCode, "drawable", "com.example.androidquizapp"));
        }
    }

    // Display Toast notification
    private void displayToast(String textToShow) {
        // Display the Toast notification
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, textToShow, duration);
        toast.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                goToMainActivity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void goToMainActivity() {
        Intent intentMainActivity = new Intent(getApplicationContext(), Home.class);
        startActivity(intentMainActivity);
    }


}
