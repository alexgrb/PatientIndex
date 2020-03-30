package ch.hevs.alexpira.ui.bed;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
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

        private BedListViewModel viewModel;



        @Override
        protected void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);

            setContentView(R.layout.activity_list_bed);

            FloatingActionButton buttonAddBed = findViewById(R.id.button_add_bed);
            buttonAddBed.setOnClickListener(new View.OnClickListener(){
                @Override
                        public void onClick(View v){
                    Intent intent = new Intent(ListBedActivity.this, AddBedActivity.class);
                    startActivityForResult(intent,ADD_BED_REQUEST);
                }
            });
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


            new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
                @Override
                public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                    return false;
                }

                @Override
                public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                    viewModel.delete(adapter.getBedAt(viewHolder.getAdapterPosition()));
                    Toast.makeText(ListBedActivity.this, "Bed deleted", Toast.LENGTH_SHORT).show();
                }
            }).attachToRecyclerView(recyclerView);


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
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            //check if this is the correct request and if everything went ok!
            if (requestCode == ADD_BED_REQUEST && resultCode == RESULT_OK){
                String bedNumber = data.getStringExtra(AddBedActivity.BEDNUMBER);
                String bedSize = data.getStringExtra(AddBedActivity.BEDSIZE);
                String bedAdjustable = data.getStringExtra(AddBedActivity.BEDADJUSTABLE);
                //int priority = data.getIntExtra(AddNoteActivity.EXTRA_PRIORITY, 1);

                BedEntity bed = new BedEntity(258/*Integer.getInteger(bedNumber)*/, 1);
                viewModel.insert(bed);
                Toast.makeText(this, "Bed saved", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Bed not saved", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
    public boolean onCreateOptionsMenu(Menu menu){
            MenuInflater menuInflater = getMenuInflater();
            menuInflater.inflate(R.menu.list_bed_activity_menu, menu);
            return true;

        }

        @Override
    public boolean onOptionsItemSelected(MenuItem item){
            switch (item.getItemId()){
                case R.id.delete_all_beds:
                    viewModel.deleteAllBeds();
                    Toast.makeText(this, "All beds deleted", Toast.LENGTH_SHORT).show();
                    return true;

                default:
                    return super.onOptionsItemSelected(item);
            }
        }
    }

