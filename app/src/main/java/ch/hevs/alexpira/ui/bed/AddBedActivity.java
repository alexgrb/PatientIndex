package ch.hevs.alexpira.ui.bed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import ch.hevs.alexpira.R;

public class AddBedActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static final String ID = "ID";
    public static final String PATIENTID = "PATIENTID";
    public static final String BEDNUMBER = "BEDNUMBER";
    public static final String BEDSIZE = "BEDSIZE";
    public static final String BEDADJUSTABLE = "BEDADJUSTABLE";

    private RadioGroup radioGroup;
    private EditText editTextBedNumber;
    private RadioButton radioButton;
    private Spinner bedSizeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bed);

        //adding an up button to the AppBar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editTextBedNumber = findViewById(R.id.edit_text_bedNumber);

        //Populating spinner with the array "bedsize_array"
        bedSizeList = findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.bedsizes_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bedSizeList.setAdapter(adapter);
        bedSizeList.setOnItemSelectedListener(this);

        //Radio buttons
        radioGroup = findViewById(R.id.radioGroup);


        setTitle("Add Bed");

    }

    private void saveBed(){
        String bedNumber = editTextBedNumber.getText().toString();
        String bedSize = bedSizeList.toString();
        String bedAdjustable = String.valueOf(radioGroup.getCheckedRadioButtonId());

        //Trim to avoid blank space
        if(bedNumber.trim().isEmpty()){
            Toast.makeText(this, "Please insert a bed number", Toast.LENGTH_SHORT);
            return;
        }

        //To keep it simple we are going to send this info back to the List activity and it will insert it.
        Intent data = new Intent();
        data.putExtra(BEDNUMBER, bedNumber);
        data.putExtra(BEDSIZE, bedSize);
        data.putExtra(BEDADJUSTABLE, bedAdjustable);

        setResult(RESULT_OK, data);
        //Finish the activity
        finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_bed_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){
            switch(item.getItemId()){
                case R.id.save_bed:
                    saveBed();
                    return true;
                default:
                    return super.onOptionsItemSelected(item);
            }
    }

    public void checkButton(View v) {

        //a toast message showing us the value of what we selected
        int radioId = radioGroup.getCheckedRadioButtonId();

        radioButton = findViewById(radioId);

        Toast.makeText(this, "Selected Radio Button: " + radioButton.getText(),
                Toast.LENGTH_SHORT).show();
    }

    //method to
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
