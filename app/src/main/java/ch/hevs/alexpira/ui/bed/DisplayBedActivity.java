package ch.hevs.alexpira.ui.bed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import ch.hevs.alexpira.R;
import ch.hevs.alexpira.ui.HomeActivity;

public class DisplayBedActivity extends AppCompatActivity implements DeleteBedDialog.BedDialogListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //adding an up button to the AppBar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_bed);

        //Button edit to go back on the AddBedActivity
        Button buttonEdit = findViewById(R.id.button_edit);
        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DisplayBedActivity.this, AddBedActivity.class);
                startActivity(intent);
            }
        });

        //Button delete to show dialog
        Button buttonDelete = findViewById(R.id.button_deleteBed);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
    }

    public void openDialog(){
        DeleteBedDialog deleteBedDialog= new DeleteBedDialog();
        deleteBedDialog.show(getSupportFragmentManager(), "delete bed dialog");
    }

    //method overriden from the DeleteBedDialog
    @Override
    public void onYesClicked() {
        Toast.makeText(DisplayBedActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(DisplayBedActivity.this, HomeActivity.class);
        startActivity(intent);
    }

    //method overriden from the DeleteBedDialog
    @Override
    public void onNoClicked() {
        Toast.makeText(DisplayBedActivity.this, "Canceled", Toast.LENGTH_SHORT).show();

    }
}
