package ch.hevs.alexpira.ui.bed;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import ch.hevs.alexpira.R;
import ch.hevs.alexpira.adapter.BedAdapter;
import ch.hevs.alexpira.adapter.RecyclerAdapter;
import ch.hevs.alexpira.database.entity.BedEntity;
import ch.hevs.alexpira.database.entity.PatientEntity;
import ch.hevs.alexpira.viewmodel.BedListViewModel;

public class ListBedActivity extends AppCompatActivity { //BaseActivity {

        public static final int ADD_BED_REQUEST = 1;
        public static final int EDIT_BED_REQUEST = 2;

        private static final String TAG = "DisplayPatientsActivity";

        private List<BedEntity> patients;
        private RecyclerAdapter<PatientEntity> adapter;
        private BedListViewModel viewModel;



        @Override
        protected void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);

            //getLayoutInflater().inflate(R.layout.activity_display_patients, frameLayout);

            setContentView(R.layout.activity_list_bed);

            FloatingActionButton buttonAddPatient = findViewById(R.id.btn_add_patient);
           /* buttonAddPatient.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ch.hevs.alexpira.ui.bed.ListBedActivity.this, AddBedActivity.class);
                    //instead of the usual startActivity, this way we can get our input back
                    startActivityForResult(intent, ADD_BED_REQUEST);
                }
            });
*/
            RecyclerView recyclerView = findViewById(R.id.recycler_view);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setHasFixedSize(true);

            final BedAdapter adapter = new BedAdapter();
            recyclerView.setAdapter(adapter);

            //PatientListViewModel.Factory factory = new PatientListViewModel.Factory(getApplication());

            viewModel = new ViewModelProvider(this).get(BedListViewModel.class);
            //viewModel = new ViewModelProvider(this, factory).get(PatientListViewModel.class);
            viewModel.getBeds().observe(this, new Observer<List<BedEntity>>() {
                @Override
                public void onChanged(@Nullable List<BedEntity> bedEntities) {
                    adapter.setBeds(bedEntities);
                    //adapter.onCreateViewHolder(patientEntities)
                    Toast.makeText(ch.hevs.alexpira.ui.bed.ListBedActivity.this, "onChanged", Toast.LENGTH_SHORT).show();
                }
            });




            adapter.setOnItemClickListener(new BedAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BedEntity bed) {
                    Intent intent = new Intent(ch.hevs.alexpira.ui.bed.ListBedActivity.this, AddBedActivity.class);
                    intent.putExtra(AddBedActivity.ID, bed.getId());
                    intent.putExtra(AddBedActivity.PATIENTID, bed.getPatientId());
                    intent.putExtra(AddBedActivity.BEDNUMBER, bed.getBedNumber());
                    startActivityForResult(intent, EDIT_BED_REQUEST);
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
            if (requestCode == ADD_BED_REQUEST && resultCode == RESULT_OK){
                String bedNumber = data.getStringExtra(AddBedActivity.BEDNUMBER);
                String patientID = data.getStringExtra(AddBedActivity.PATIENTID);
                //int priority = data.getIntExtra(AddNoteActivity.EXTRA_PRIORITY, 1);

                BedEntity bed = new BedEntity(Integer.getInteger(bedNumber), Integer.getInteger(patientID));
                viewModel.insert(bed);
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

                Toast.makeText(this, "Note saved", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Note not saved", Toast.LENGTH_SHORT).show();
            }        }
    }

