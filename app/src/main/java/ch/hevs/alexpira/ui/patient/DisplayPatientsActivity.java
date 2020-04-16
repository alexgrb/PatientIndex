package ch.hevs.alexpira.ui.patient;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;
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
import java.util.ArrayList;
import java.util.List;
import ch.hevs.alexpira.R;
import ch.hevs.alexpira.adapter.ListAdapter;
import ch.hevs.alexpira.adapter.PatientAdapter;
import ch.hevs.alexpira.database.entity.BedEntity;
import ch.hevs.alexpira.database.entity.PatientEntity;
import ch.hevs.alexpira.database.pojo.PatientWithBed;
import ch.hevs.alexpira.util.OnAsyncEventListener;
import ch.hevs.alexpira.viewmodel.PatientListViewModel;

public class DisplayPatientsActivity extends AppCompatActivity {

    public static final int ADD_PATIENT_REQUEST = 1;
    public static final int EDIT_PATIENT_REQUEST = 2;
    private PatientListViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

        viewModel = new ViewModelProvider(this).get(PatientListViewModel.class);
        viewModel.getPatientsWithBed().observe(this, new Observer<List<PatientWithBed>>() {
            @Override
            public void onChanged(@Nullable List<PatientWithBed> patientEntities) {
                if (patientEntities != null) {
                    //Eliminate useless row in the list. We only want patients.
                    List<PatientWithBed> patientsOnly = new ArrayList<>();
                    for (PatientWithBed patient : patientEntities) {
                        if (patient.patientEntity != null) {
                            patientsOnly.add(patient);
                        }
                    }
                    adapter.setPatients(patientsOnly);

                    Toast.makeText(DisplayPatientsActivity.this, "Patient list loaded", Toast.LENGTH_SHORT).show();
                }
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
                viewModel.delete(adapter.getPatientAt(viewHolder.getAdapterPosition()), new OnAsyncEventListener() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onFailure(Exception e) {

                    }
                });
                Toast.makeText(DisplayPatientsActivity.this, "Note deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);
        //implement the interface
        adapter.setOnItemClickListener(new PatientAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(PatientWithBed patient) {
                Intent intent = new Intent(DisplayPatientsActivity.this, AddEditPatientActivity.class);
                intent.putExtra(AddEditPatientActivity.LASTNAME, patient.patientEntity.getPatientLastName());
                intent.putExtra(AddEditPatientActivity.FIRSTNAME, patient.patientEntity.getPatientFirstName());
                intent.putExtra(AddEditPatientActivity.ADDRESS, patient.patientEntity.getPatientAdress());
                intent.putExtra(AddEditPatientActivity.BIRTHDATE, patient.patientEntity.getPatientDate());
                intent.putExtra(AddEditPatientActivity.CITY, patient.patientEntity.getPatientcity());
                intent.putExtra(AddEditPatientActivity.NPA, patient.patientEntity.getPatientNPA());

                startActivityForResult(intent, EDIT_PATIENT_REQUEST);
            }
        });

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
            String bedId = data.getStringExtra(AddEditPatientActivity.BEDID);

            PatientEntity patient = new PatientEntity(firstname, lastname, adress, birthdate, city, npa, bedId);
            viewModel.insert(patient, new OnAsyncEventListener() {
                @Override
                public void onSuccess() {

                }

                @Override
                public void onFailure(Exception e) {

                }
            });
            Toast.makeText(this, "Patient saved", Toast.LENGTH_SHORT).show();
        } else if (requestCode == EDIT_PATIENT_REQUEST && resultCode == RESULT_OK) {
            String id = data.getStringExtra(AddEditPatientActivity.BEDID);

            //something went wrong if we get here
            if (id.equals("-1")) {
                Toast.makeText(this, "Patient can't be updated", Toast.LENGTH_SHORT).show();
                return;
            }

            String firstname = data.getStringExtra(AddEditPatientActivity.FIRSTNAME);
            String lastname = data.getStringExtra(AddEditPatientActivity.LASTNAME);
            String adress = data.getStringExtra(AddEditPatientActivity.ADDRESS);
            String birthdate = data.getStringExtra(AddEditPatientActivity.BIRTHDATE);
            String city = data.getStringExtra(AddEditPatientActivity.CITY);
            String npa = data.getStringExtra(AddEditPatientActivity.NPA);
            String bedId = data.getStringExtra((AddEditPatientActivity.BEDID));

            PatientEntity patient = new PatientEntity(firstname, lastname, adress, birthdate, city, npa, bedId);
            viewModel.update(patient, new OnAsyncEventListener() {
                @Override
                public void onSuccess() {

                }

                @Override
                public void onFailure(Exception e) {

                }
            });

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
                //viewModel.deleteAllPatients();
                Toast.makeText(this, "All patients deleted", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

