package ch.hevs.alexpira;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //adding an up button to the AppBar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);

        Button button = findViewById(R.id.button);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);
        Button button6 = findViewById(R.id.button6);

        button.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                Intent intent = new Intent(Home.this, AddPatient.class);
                startActivity(intent);
                break;
            case R.id.button2:
                Intent intent2 = new Intent(Home.this, SearchPatientActivity.class);
                startActivity(intent2);
                break;
            case R.id.button3:
                Intent intent3 = new Intent(Home.this, SearchBedActivity.class);
                startActivity(intent3);
                break;
            case R.id.button4:
                Intent intent4 = new Intent(Home.this, StatisticsActivity.class);
                startActivity(intent4);
                break;
            case R.id.button5:
                Intent intent5 = new Intent(Home.this, AddBedActivity.class);
                startActivity(intent5);
                break;
            case R.id.button6:
                Intent intent6 = new Intent(Home.this, SettingsActivity.class);
                startActivity(intent6);
                break;
        }
    }
}
