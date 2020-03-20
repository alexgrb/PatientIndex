package ch.hevs.alexpira;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddPatient extends AppCompatActivity {
    private Button submit;
    public static final String FIRSTNAME = "FIRSTNAME";
    public static final String LASTNAME= "LASTNAME";
    public static final String ADDRESS = "ADDRESS";
    public static final String BIRTHDATE = "BIRTHDATE";
    public static final String CITY = "CITY";
    public static final String NPA = "NPA";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        submit = (Button) findViewById(R.id.btn_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openDisplayPatient();
            }
        });

    }

    public void openDisplayPatient(){

        EditText firstName = (EditText) findViewById(R.id.tv_firstName);
        String textfirstname = firstName.getText().toString();

        EditText lastname = (EditText) findViewById(R.id.et_lastname);
        String textlastname = lastname.getText().toString();

        EditText adress = (EditText) findViewById(R.id.et_address);
        String textadress = adress.getText().toString();

        EditText birthdate = (EditText) findViewById(R.id.et_birthdate);
        String textbirthdate = birthdate.getText().toString();

        EditText city = (EditText) findViewById(R.id.et_city);
        String textcity = city.getText().toString();

        /*
        EditText npa = (EditText) findViewById(R.id.et_enpea);
        String textnpa = npa.getText().toString();

         */



        Intent intent = new Intent(this, DisplayUser.class);

        intent.putExtra(FIRSTNAME, textfirstname);
        intent.putExtra(LASTNAME, textlastname);
        intent.putExtra(ADDRESS, textadress);
        intent.putExtra(BIRTHDATE, textbirthdate);
        intent.putExtra(CITY, textcity);
        //intent.putExtra(NPA, textnpa);




        startActivity(intent);
    }
}