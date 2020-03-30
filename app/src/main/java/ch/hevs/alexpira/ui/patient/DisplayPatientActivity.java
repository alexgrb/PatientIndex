package ch.hevs.alexpira.ui.patient;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import ch.hevs.alexpira.R;
import ch.hevs.alexpira.database.AppDatabase;
import ch.hevs.alexpira.database.entity.PatientEntity;
import ch.hevs.alexpira.ui.BaseActivity;
import ch.hevs.alexpira.viewmodel.PatientViewModel;


public class DisplayPatientActivity extends BaseActivity {

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
    private static AppDatabase appDatabase;

    private PatientViewModel viewModel;

    private PatientEntity patient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_user);


        //INITIATE VIEW
        SharedPreferences settings = getSharedPreferences(BaseActivity.PREFS_NAME, 0);
        String user = settings.getString(PREFS_USER, null);


        PatientViewModel.Factory factory = new PatientViewModel.Factory(getApplication(), 2);

        Intent intent = getIntent();

        firstname = intent.getStringExtra(AddEditPatientActivity.FIRSTNAME);
        lastname = intent.getStringExtra(AddEditPatientActivity.LASTNAME);
        address = intent.getStringExtra(AddEditPatientActivity.ADDRESS);
        birthday = intent.getStringExtra(AddEditPatientActivity.BIRTHDATE);
        city = intent.getStringExtra(AddEditPatientActivity.CITY);
        //NPA = intent.getStringExtra(AddPatient.NPA);



        tv_firstName = (TextView) findViewById(R.id.et_firstName);
        tv_lastName = (TextView) findViewById(R.id.et_lastname);
        tv_address = (TextView) findViewById(R.id.et_address);
        tv_birthdate = (TextView) findViewById(R.id.et_birthdate);
        tv_city = (TextView) findViewById(R.id.et_city);

        viewModel = new ViewModelProvider(this, factory).get(PatientViewModel.class);
        viewModel.getPatient().observe(this, bedEntitiy -> {
            if(bedEntitiy != null){
                patient = bedEntitiy;
                updateContent();
            }
        });
    }

    private void updateContent(){
        if(patient!=null){
            tv_firstName.setText(patient.getPatientFirstName());
            tv_lastName.setText(patient.getPatientLastName());
        }
    }
}
