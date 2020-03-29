package ch.hevs.alexpira.ui.patient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.DateFormat;

import ch.hevs.alexpira.R;
import ch.hevs.alexpira.database.AppDatabase;

public class AddPatientActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private Button submit;
    private Button pickdate;
    private EditText firstname;
    private EditText lastname;
    private EditText adress;
    private EditText birthdate;
    private EditText city;
    private EditText npa;

    public static final String ID = "ID";
    public static final String FIRSTNAME = "FIRSTNAME";
    public static final String LASTNAME = "LASTNAME";
    public static final String ADDRESS = "ADDRESS";
    public static final String BIRTHDATE = "BIRTHDATE";
    public static final String CITY = "CITY";
    public static final String NPA = "NPA";
    public static AppDatabase appDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //adding an up button to the AppBar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
      //  appDatabase = AppDatabase.getAppDatabase(this);
        submit = findViewById(R.id.btn_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDisplayPatient();
            }
        });

        pickdate= findViewById(R.id.btn_pickdate);
        pickdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerDialogFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });

        //assigning every EditText attribute to its layout it
        firstname = findViewById(R.id.et_firstName);
        lastname = findViewById(R.id.et_lastname);
        adress= findViewById(R.id.et_address);
        birthdate =  findViewById(R.id.et_birthdate);
        city= findViewById(R.id.et_city);
        npa= findViewById(R.id.et_NPA);


        //adding a TextChangedListener to every EditText attributes
        firstname.addTextChangedListener(addPatientTextWatcher);
        lastname.addTextChangedListener(addPatientTextWatcher);
        adress.addTextChangedListener(addPatientTextWatcher);
        birthdate.addTextChangedListener(addPatientTextWatcher);
        city.addTextChangedListener(addPatientTextWatcher);
        npa.addTextChangedListener(addPatientTextWatcher);


        //setting the edit text uneditable
        birthdate.setEnabled(false);



        Intent intent = getIntent();
        if(intent.hasExtra(ID)){
            setTitle("Edit Patient");
            lastname.setText(intent.getStringExtra(LASTNAME));
            firstname.setText(intent.getStringExtra(FIRSTNAME));

        }
        else {
            setTitle("Add Patient");
        }
    }

    public void openDisplayPatient() {

        EditText firstName = (EditText) findViewById(R.id.et_firstName);
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



        PatientEntity patient = new PatientEntity(textfirstname, textlastname);
        appDatabase.patientDao().insertAll(patient);
*/
        Intent intent = new Intent(this, DisplayPatientActivity.class);

        intent.putExtra(FIRSTNAME, textfirstname);
        intent.putExtra(LASTNAME, textlastname);
        intent.putExtra(ADDRESS, textadress);
        intent.putExtra(BIRTHDATE, textbirthdate);
        intent.putExtra(CITY, textcity);
        //intent.putExtra(NPA, textnpa);


        startActivity(intent);
    }

    // we have set AddPatientActivity to the OnDateSetListener. This is why this method is overriden.
    // in this method we are setting our textview to the date we picked.
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        //setting the calendar variable to the date we picked in our DatePicker fragment
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        //we are using this date picker to create our text
        String currentDateString = DateFormat.getDateInstance(DateFormat.SHORT).format(c.getTime());
        birthdate =  findViewById(R.id.et_birthdate);
        birthdate.setText(currentDateString);

    }

    private TextWatcher addPatientTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String firstnameInput = firstname.getText().toString().trim();
            String lastnameInput = lastname.getText().toString().trim();
            String adressInput = adress.getText().toString().trim();
            String birthdateInput = birthdate.getText().toString().trim();
            String cityInput = city.getText().toString().trim();
            String npaInput = npa.getText().toString().trim();

            Button buton = findViewById(R.id.btn_submit);
            submit.setEnabled(!firstnameInput.isEmpty() && !lastnameInput.isEmpty() && !adressInput.isEmpty()
                    && !birthdateInput.isEmpty() && !cityInput.isEmpty() && !npaInput.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

}
