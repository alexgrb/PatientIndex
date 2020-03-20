package ch.hevs.alexpira;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayUser extends AppCompatActivity {

    private TextView tv_firstName;
    private TextView tv_lastName;
    private TextView tv_address;
    private TextView tv_birthdate;
    private TextView tv_city;
    private TextView tv_npa;

    private String firstname;
    private String lastname;
    private String address;
    private String birthday;
    private String city;
    private String NPA;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_user);

        Intent intent = getIntent();

        firstname = intent.getStringExtra(AddPatient.FIRSTNAME);
        lastname = intent.getStringExtra(AddPatient.LASTNAME);
        address = intent.getStringExtra(AddPatient.ADDRESS);
        birthday = intent.getStringExtra(AddPatient.BIRTHDATE);
        city = intent.getStringExtra(AddPatient.CITY);
       // NPA = intent.getStringExtra(AddPatient.NPA);



        tv_firstName = (TextView) findViewById(R.id.tv_firstName);
        tv_lastName = (TextView) findViewById(R.id.et_lastname);
        tv_address = (TextView) findViewById(R.id.et_address);
        tv_birthdate = (TextView) findViewById(R.id.et_birthdate);
        tv_city = (TextView) findViewById(R.id.et_city);
       // tv_npa = (TextView) findViewById(R.id.et_address);



        tv_firstName.setText(firstname);
        tv_lastName.setText(lastname);
        tv_address.setText(address);
        tv_birthdate.setText(birthday);
        tv_city.setText(city);
        //tv_npa.setText(NPA);


        /*
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

         */
    }
}
