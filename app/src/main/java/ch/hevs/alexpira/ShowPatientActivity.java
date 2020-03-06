package ch.hevs.alexpira;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class ShowPatientActivity extends Activity {
    private TextView firstName;
    private TextView lastName;
    private TextView address;
    private TextView birthdate;
    private TextView city;
    private TextView npa;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_user);

        Intent intent = getIntent();

        firstName = (TextView) findViewById(R.id.tv_firstName);
        lastName = (TextView) findViewById(R.id.et_lastname);
        address = (TextView) findViewById(R.id.et_address);
        city = (TextView) findViewById(R.id.et_city);
        npa = (TextView) findViewById(R.id.et_NPA);
        birthdate = (TextView) findViewById(R.id.et_birthdate);

        firstName.setText(intent.getStringExtra("firstName"));
        lastName.setText(intent.getStringExtra("lastName"));
        address.setText(intent.getStringExtra("address"));
        city.setText(intent.getStringExtra("city"));
        npa.setText(intent.getStringExtra("NPA"));
        birthdate.setText(intent.getStringExtra("birthdate"));

    }


}
