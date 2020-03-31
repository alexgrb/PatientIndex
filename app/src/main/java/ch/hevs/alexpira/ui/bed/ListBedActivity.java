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
import ch.hevs.alexpira.database.entity.BedEntity;
import ch.hevs.alexpira.database.pojo.PatientWithBed;
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

            //Add button to add bed
            FloatingActionButton buttonAddBed = findViewById(R.id.button_add_bed);
            buttonAddBed.setOnClickListener(new View.OnClickListener(){
                @Override
                        public void onClick(View v){
                    Intent intent = new Intent(ListBedActivity.this, AddEditBedActivity.class);
                    startActivityForResult(intent,ADD_BED_REQUEST);
                }
            });
            //Creating and setting the view
            RecyclerView recyclerView = findViewById(R.id.recycler_view);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setHasFixedSize(true);

            final BedAdapter adapter = new BedAdapter();
            recyclerView.setAdapter(adapter);

            viewModel = new ViewModelProvider(this).get(BedListViewModel.class);
            viewModel.getPatients().observe(this, new Observer<List<PatientWithBed>>() {
                @Override
                public void onChanged(@Nullable List<PatientWithBed> bedEntities) {
                    adapter.setBeds(bedEntities);
                    //adapter.onCreateViewHolder(patientEntities)
                    Toast.makeText(ch.hevs.alexpira.ui.bed.ListBedActivity.this, "Bed list loaded", Toast.LENGTH_SHORT).show();
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
                public void onItemClick(PatientWithBed bed) {
                    Intent intent = new Intent(ch.hevs.alexpira.ui.bed.ListBedActivity.this, AddEditBedActivity.class);
                    intent.putExtra(AddEditBedActivity.ID, bed.bedEntity.getId());
                    intent.putExtra(AddEditBedActivity.BEDSIZE, bed.bedEntity.getBedSize());
                    intent.putExtra(AddEditBedActivity.BEDNUMBER, String.valueOf(bed.bedEntity.getBedNumber()));
                    intent.putExtra(AddEditBedActivity.BEDADJUSTABLE, bed.bedEntity.getBedAdjustablee());
                    startActivityForResult(intent, EDIT_BED_REQUEST);
                }
            });
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            //check if this is the correct request and if everything went ok!
            if (requestCode == ADD_BED_REQUEST && resultCode == RESULT_OK){
                int bedNumber = data.getIntExtra(AddEditBedActivity.BEDNUMBER, 299);
                String bedSize = data.getStringExtra(AddEditBedActivity.BEDSIZE);
                String bedAdjustable = data.getStringExtra(AddEditBedActivity.BEDADJUSTABLE);
                //int priority = data.getIntExtra(AddNoteActivity.EXTRA_PRIORITY, 1);

                BedEntity bed = new BedEntity(bedNumber, bedSize, bedAdjustable);
                viewModel.insert(bed);
                Toast.makeText(this, "Bed saved", Toast.LENGTH_SHORT).show();
            } else if (requestCode == EDIT_BED_REQUEST && resultCode == RESULT_OK){

                int id = data.getIntExtra(AddEditBedActivity.ID, -1);
                if(id==-1){
                    Toast.makeText(this,"not can't be updated", Toast.LENGTH_SHORT).show();
                    return;
                }
                int bedNumber = data.getIntExtra(AddEditBedActivity.BEDNUMBER, 299);
                String bedSize = data.getStringExtra(AddEditBedActivity.BEDSIZE);
                String bedAdjustable = data.getStringExtra(AddEditBedActivity.BEDADJUSTABLE);

                BedEntity bed = new BedEntity(bedNumber,bedSize, bedAdjustable);
                bed.setId(id);
                viewModel.update(bed );
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

