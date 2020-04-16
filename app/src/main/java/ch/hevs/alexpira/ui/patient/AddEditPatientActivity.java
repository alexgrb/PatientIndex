package ch.hevs.alexpira.ui.patient;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import ch.hevs.alexpira.R;
import ch.hevs.alexpira.adapter.ListAdapter;
import ch.hevs.alexpira.database.entity.BedEntity;
import ch.hevs.alexpira.database.pojo.PatientWithBed;
import ch.hevs.alexpira.viewmodel.AddEditPatientListViewModel;
import ch.hevs.alexpira.viewmodel.PatientListViewModel;

public class AddEditPatientActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    public static final String ID = "ID";
    public static final String FIRSTNAME = "FIRSTNAME";
    public static final String LASTNAME = "LASTNAME";
    public static final String ADDRESS = "ADDRESS";
    public static final String BIRTHDATE = "BIRTHDATE";
    public static final String CITY = "CITY";
    public static final String NPA = "NPA";
    public static final String BEDID = "BEDNUMBER";
    private AddEditPatientListViewModel viewModel;
    private Spinner spinnerBedNumber;
    private ListAdapter<String> adapterBedNumber;

    private Button pickdate;
    private EditText firstname;
    private EditText lastname;
    private EditText adress;
    private EditText birthdate;
    private EditText city;
    private EditText npa;
    private EditText bedid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //adding an up button to the AppBar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        setupBedNumberSpinner();

        viewModel = new ViewModelProvider(this).get(AddEditPatientListViewModel.class);
        viewModel.getPatientsWithBed().observe(this, new Observer<List<PatientWithBed>>() {
            @Override
            public void onChanged(@Nullable List<PatientWithBed> patientEntities) {
                if (patientEntities != null) {
                    List<String> emptyBedsOnly = new ArrayList<>();
                    for (PatientWithBed bed : patientEntities){
                        if(bed.bedEntity != null){
                            emptyBedsOnly.add(bed.bedEntity.getId());
                        }
                    }
                    updateFromAccSpinner(emptyBedsOnly);
                    Toast.makeText(AddEditPatientActivity.this, "Patient loaded", Toast.LENGTH_SHORT).show();
                }
            }
        });
        pickdate = findViewById(R.id.btn_pickdate);
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
        adress = findViewById(R.id.et_address);
        birthdate = findViewById(R.id.et_birthdate);
        city = findViewById(R.id.et_city);
        npa = findViewById(R.id.et_NPA);
        //setting the edit text uneditable
        birthdate.setEnabled(false);

        //getting the data back from the list
        Intent intent = getIntent();
        if (intent.hasExtra(FIRSTNAME)) {
            setTitle("Edit Patient");
            firstname.setText(intent.getStringExtra(FIRSTNAME));
            lastname.setText(intent.getStringExtra(LASTNAME));
            adress.setText(intent.getStringExtra(ADDRESS));
            birthdate.setText(intent.getStringExtra(BIRTHDATE));
            city.setText(intent.getStringExtra(CITY));
            npa.setText(intent.getStringExtra(NPA));

        } else {
            setTitle("Add Patient");
        }
    }

    private void setupBedNumberSpinner() {
        spinnerBedNumber = findViewById(R.id.spinner_from);
        adapterBedNumber = new ListAdapter<>(this, R.layout.row_client, new ArrayList<>());
        spinnerBedNumber.setAdapter(adapterBedNumber);
    }

    private void updateFromAccSpinner(List<String> beds) {
        adapterBedNumber.updateData(new ArrayList<>(beds));
    }

    public void savePatient() {
        //getting the input from the edittextfields
        String s_firstname = firstname.getText().toString();
        String s_lastname = lastname.getText().toString();
        String s_adress = adress.getText().toString();
        String s_birthdate = birthdate.getText().toString();
        String s_city = city.getText().toString();
        String s_npa = npa.getText().toString();
        String s_bedId = (String) spinnerBedNumber.getSelectedItem();

        //Just in case the user left an empty textfield (not our case since we have the TextWatcher right?)
        if (s_firstname.trim().isEmpty() || s_lastname.trim().isEmpty() || s_adress.trim().isEmpty()
                || s_birthdate.trim().isEmpty() || s_city.trim().isEmpty() || s_npa.trim().isEmpty()) {
            Toast.makeText(this, "Please fill all the values", Toast.LENGTH_SHORT).show();
            return;
        }

        //passing the data in the activity that started it
        Intent data = new Intent();
        data.putExtra(FIRSTNAME, s_firstname);
        data.putExtra(LASTNAME, s_lastname);
        data.putExtra(ADDRESS, s_adress);
        data.putExtra(BIRTHDATE, s_birthdate);
        data.putExtra(CITY, s_city);
        data.putExtra(NPA, s_npa);
        data.putExtra(BEDID, s_bedId);

        //get the id of the selected patient so the DB knows which patient needs to be edited
        int id = getIntent().getIntExtra(BEDID, -1);
        if (id != -1) {
            //put id inside the intent
            data.putExtra(ID, id);
        }
        //actually sending the data back to the DisplayPatientsActivity and closing the activity
        setResult(RESULT_OK, data);
        finish();
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
        birthdate = findViewById(R.id.et_birthdate);
        birthdate.setText(currentDateString);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_patient_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_patient:
                savePatient();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}