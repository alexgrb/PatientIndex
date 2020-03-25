package ch.hevs.alexpira;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayPatientActivity extends AppCompatActivity implements DeletePatientDialog.PatientDialogListener {

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
        //NPA = intent.getStringExtra(AddPatient.NPA);



        tv_firstName = (TextView) findViewById(R.id.tv_firstName);
        tv_lastName = (TextView) findViewById(R.id.et_lastname);
        tv_address = (TextView) findViewById(R.id.et_address);
        tv_birthdate = (TextView) findViewById(R.id.et_birthdate);
        tv_city = (TextView) findViewById(R.id.et_city);
        //tv_npa = (TextView) findViewById(R.id.et_enpea);



        tv_firstName.setText(firstname);
        tv_lastName.setText(lastname);
        tv_address.setText(address);
        tv_birthdate.setText(birthday);
        tv_city.setText(city);
        //tv_npa.setText(NPA);

        //Button to go back on the AddPatient Activity
        Button buttonEdit = findViewById(R.id.button_editPatient);
        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DisplayPatientActivity.this, AddPatient.class);
                startActivity(intent);
            }
        });

        //OnClick Listener to oppen a dialog message
        Button buttonDelete = findViewById(R.id.button_deletePatient);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
    }

    public void openDialog(){
        DeletePatientDialog deletePatientDialog = new DeletePatientDialog();
        deletePatientDialog.show(getSupportFragmentManager(), "delete patient dialog");
    }


    @Override
    public void onYesClicked() {
        Toast.makeText(DisplayPatientActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(DisplayPatientActivity.this, Home.class);
        startActivity(intent);
    }

    @Override
    public void onNoClicked() {
        Toast.makeText(DisplayPatientActivity.this, "Canceled", Toast.LENGTH_SHORT).show();
    }
}