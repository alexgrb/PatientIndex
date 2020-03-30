package ch.hevs.alexpira.ui.bed;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import ch.hevs.alexpira.R;
import ch.hevs.alexpira.database.entity.BedEntity;
import ch.hevs.alexpira.ui.HomeActivity;
import ch.hevs.alexpira.viewmodel.BedViewModel;

public class DisplayBedActivity extends AppCompatActivity{

    private TextView tv_bed_number;
    private TextView tv_bed_size;
    private TextView tv_bed_adjustable;

    private BedViewModel viewModel;

    private BedEntity bed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //adding an up button to the AppBar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_bed);

        BedViewModel.Factory factory = new BedViewModel.Factory(getApplication(), 2);

        viewModel = new ViewModelProvider(this, factory).get(BedViewModel.class);
        viewModel.getBed().observe(this, bedEntitiy -> {
            if(bedEntitiy != null){
                bed = bedEntitiy;
                updateContent();
            }
        });

    }
    private void updateContent(){
        if(bed!=null){
            tv_bed_number.setText(bed.getBedNumber());
            tv_bed_size.setText(bed.getBedSize());
            tv_bed_adjustable.setText(bed.getBedAdjustablee());
        }
    }


}
