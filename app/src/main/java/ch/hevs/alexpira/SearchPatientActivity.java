package ch.hevs.alexpira;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import ch.hevs.alexpira.R;

public class SearchPatientActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //adding an up button to the AppBar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_patient);
    }
}
