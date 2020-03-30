package ch.hevs.alexpira.ui.patient;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import ch.hevs.alexpira.R;
import ch.hevs.alexpira.adapter.PatientAdapter;
import ch.hevs.alexpira.adapter.RecyclerAdapter;
import ch.hevs.alexpira.database.entity.PatientEntity;
import ch.hevs.alexpira.viewmodel.PatientListViewModel;

public class DisplayPatientsActivity extends AppCompatActivity { //BaseActivity {

    public static final int ADD_PATIENT_REQUEST = 1;
    public static final int EDIT_PATIENT_REQUEST = 2;

    private static final String TAG = "DisplayPatientsActivity";

    private List<PatientEntity> patients;
    private RecyclerAdapter<PatientEntity> adapter;
    private PatientListViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //getLayoutInflater().inflate(R.layout.activity_display_patients, frameLayout);

        setContentView(R.layout.activity_display_patients);

        FloatingActionButton buttonAddPatient = findViewById(R.id.btn_add_patient);
        buttonAddPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DisplayPatientsActivity.this, AddEditPatientActivity.class);
                //instead of the usual startActivity, this way we can get our input back
                startActivityForResult(intent, ADD_PATIENT_REQUEST);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final PatientAdapter adapter = new PatientAdapter();
        recyclerView.setAdapter(adapter);

        //PatientListViewModel.Factory factory = new PatientListViewModel.Factory(getApplication());

        viewModel = new ViewModelProvider(this).get(PatientListViewModel.class);
        //viewModel = new ViewModelProvider(this, factory).get(PatientListViewModel.class);
        viewModel.getPatients().observe(this, new Observer<List<PatientEntity>>() {
            @Override
            public void onChanged(@Nullable List<PatientEntity> patientEntities) {
                adapter.setPatients(patientEntities);
                //adapter.onCreateViewHolder(patientEntities)
                //Toast.makeText(DisplayPatientsActivity.this, "onChanged", Toast.LENGTH_SHORT).show();
            }
        });

        //delete an item by swipping
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                viewModel.delete(adapter.getPatientAt(viewHolder.getAdapterPosition()));
                Toast.makeText(DisplayPatientsActivity.this, "Note deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);
        //implement the interface
        adapter.setOnItemClickListener(new PatientAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(PatientEntity patient) {
                Intent intent = new Intent(DisplayPatientsActivity.this, AddEditPatientActivity.class);
                intent.putExtra(AddEditPatientActivity.ID, patient.getRowid());
                intent.putExtra(AddEditPatientActivity.LASTNAME, patient.getPatientLastName());
                intent.putExtra(AddEditPatientActivity.FIRSTNAME, patient.getPatientFirstName());
                intent.putExtra(AddEditPatientActivity.ADDRESS, patient.getPatientAdress());
                intent.putExtra(AddEditPatientActivity.BIRTHDATE, patient.getPatientDate());
                intent.putExtra(AddEditPatientActivity.CITY, patient.getPatientcity());
                intent.putExtra(AddEditPatientActivity.NPA, patient.getPatientNPA());


                startActivityForResult(intent, EDIT_PATIENT_REQUEST);
            }
        });


/*
        adapter = new RecyclerAdapter<>(new RecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {

            }

            @Override
            public void onItemLongClick(View v, int position) {

            }
        });
        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(view -> {
                    Intent intent = new Intent(DisplayPatientsActivity.this, SearchPatientActivity.class);
                    intent.setFlags(
                            Intent.FLAG_ACTIVITY_NO_ANIMATION |
                                    Intent.FLAG_ACTIVITY_NO_HISTORY
                    );
                    startActivity(intent);
                }
        );

*/


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //check if this is the correct request and if everything went ok!
        if (requestCode == ADD_PATIENT_REQUEST && resultCode == RESULT_OK) {
            String firstname = data.getStringExtra(AddEditPatientActivity.FIRSTNAME);
            String lastname = data.getStringExtra(AddEditPatientActivity.LASTNAME);
            String adress = data.getStringExtra(AddEditPatientActivity.ADDRESS);
            String birthdate = data.getStringExtra(AddEditPatientActivity.BIRTHDATE);
            String city = data.getStringExtra(AddEditPatientActivity.CITY);
            String npa = data.getStringExtra(AddEditPatientActivity.NPA);

            PatientEntity patient = new PatientEntity(firstname, lastname, adress, birthdate, city, npa);
            viewModel.insert(patient);
           /* viewModel.insert(patient, new OnAsyncEventListener() {
                @Override
                public void onSuccess() {
                    Log.d(TAG, "createAccount: success");
                }

                @Override
                public void onFailure(Exception e) {
                    Log.d(TAG, "createAccount: failure");
                }
            });

            */

            Toast.makeText(this, "Patient saved", Toast.LENGTH_SHORT).show();
        } else if (requestCode == EDIT_PATIENT_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(AddEditPatientActivity.ID, -1);

            //something went wrong
            if (id == -1) {
                Toast.makeText(this, "Patient can't be updated", Toast.LENGTH_SHORT).show();
                return;
            }

            String firstname = data.getStringExtra(AddEditPatientActivity.FIRSTNAME);
            String lastname = data.getStringExtra(AddEditPatientActivity.LASTNAME);
            String adress = data.getStringExtra(AddEditPatientActivity.ADDRESS);
            String birthdate = data.getStringExtra(AddEditPatientActivity.BIRTHDATE);
            String city = data.getStringExtra(AddEditPatientActivity.CITY);
            String npa = data.getStringExtra(AddEditPatientActivity.NPA);

            //int priority = data.getIntExtra(AddEditNoteActivity.EXTRA_PRIORITY, 1);

            PatientEntity patient = new PatientEntity(firstname, lastname, adress, birthdate,city, npa);
            patient.setRowid(id);
            viewModel.update(patient);

            Toast.makeText(this, "Patient updated", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "Patient not saved", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.patient_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_all_patients:
                viewModel.deleteAllPatients();
                Toast.makeText(this, "All patients deleted", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

