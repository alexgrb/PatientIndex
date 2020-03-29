package ch.hevs.alexpira.ui.patient;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ch.hevs.alexpira.R;
import ch.hevs.alexpira.adapter.PatientAdapter;
import ch.hevs.alexpira.adapter.RecyclerAdapter;
import ch.hevs.alexpira.database.entity.PatientEntity;
import ch.hevs.alexpira.viewmodel.PatientListViewModel;

public class DisplayPatientsActivity extends AppCompatActivity { //BaseActivity {

    private static final String TAG = "DisplayPatientsActivity";

    private List<PatientEntity> patients;
    private RecyclerAdapter<PatientEntity> adapter;
    private PatientListViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //getLayoutInflater().inflate(R.layout.activity_display_patients, frameLayout);

        setContentView(R.layout.activity_display_patients);


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
                Toast.makeText(DisplayPatientsActivity.this, "onChanged", Toast.LENGTH_SHORT).show();
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

}
