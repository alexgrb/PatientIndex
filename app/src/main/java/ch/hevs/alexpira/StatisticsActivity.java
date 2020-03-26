package ch.hevs.alexpira;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import ch.hevs.alexpira.R;

public class StatisticsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        //adding an up button to the AppBar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
