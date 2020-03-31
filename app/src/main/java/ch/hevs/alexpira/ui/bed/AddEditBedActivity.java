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

public class AddEditBedActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static final String ID = "ID";
    public static final String PATIENTID = "PATIENTID";
    public static final String BEDNUMBER = "BEDNUMBER";
    public static final String BEDSIZE = "BEDSIZE";
    public static final String BEDADJUSTABLE = "BEDADJUSTABLE";

    private RadioGroup radioGroup_one;
    private RadioGroup radioGroup_two;
    private EditText editTextBedNumber;
    private EditText editTextBedAdjustable;
    private EditText editTextBedSize;
    private RadioButton radioButton_one;
    private RadioButton radioButton_two;
    private Spinner bedSizeList;

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bed);

        //adding an up button to the AppBar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editTextBedNumber = findViewById(R.id.edit_text_bedNumber);
        editTextBedSize = findViewById(R.id.edit_text_bedSize);
        editTextBedAdjustable = findViewById(R.id.edit_text_bedAdjustable);
        radioGroup_one = findViewById(R.id.radioGroup);
        radioGroup_two = findViewById(R.id.radioGroup2);

        editTextBedSize.setEnabled(false);
        editTextBedAdjustable.setEnabled(false);

        Button buttonApply = findViewById(R.id.button_apply);
        buttonApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioId = radioGroup_one.getCheckedRadioButtonId();
                radioButton_one = findViewById(radioId);

                int radioId2 = radioGroup_two.getCheckedRadioButtonId();
                radioButton_two = findViewById(radioId2);

                editTextBedAdjustable.setText(radioButton_one.getText());
                editTextBedSize.setText(radioButton_two.getText());

            }
        });



        /*
        //Populating spinner with the array "bedsize_array"
        bedSizeList = findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.bedsizes_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bedSizeList.setAdapter(adapter);
        bedSizeList.setOnItemSelectedListener(this);


         */


        Intent intent = getIntent();
        if (intent.hasExtra(ID)) {
            setTitle("Edit Bed");
            /*int radioId = radioGroup.getCheckedRadioButtonId();

            radioButton = findViewById(radioId);
            if(radioButton.toString().equals(intent.getStringExtra(BEDADJUSTABLE))) {
                radioButton.setChecked(true);
            }
            else{
                radioButton.setChecked(false);
            }*/
            editTextBedAdjustable.setText(intent.getStringExtra(BEDADJUSTABLE));
            editTextBedSize.setText(intent.getStringExtra(BEDSIZE));
            editTextBedNumber.setText(intent.getStringExtra(BEDNUMBER));
        } else {
            setTitle("Add Bed");
        }

    }

    private void saveBed(){
        int bedNumber = Integer.valueOf(editTextBedNumber.getText().toString());
        String bedSize = editTextBedSize.getText().toString();
        String bedAdjustable = editTextBedAdjustable.getText().toString();

        //To keep it simple we are going to send this info back to the List activity and it will insert it.
        Intent data = new Intent();
        data.putExtra(BEDNUMBER, bedNumber);
        data.putExtra(BEDSIZE, bedSize);
        data.putExtra(BEDADJUSTABLE, bedAdjustable);

        int id = getIntent().getIntExtra(ID,  -1);
        if(id!=-1){
            data.putExtra(ID,id);
        }

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



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        editTextBedSize.setText(text);
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }



    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void checkButton(View v) {
        int radioId = radioGroup_one.getCheckedRadioButtonId();

        radioButton_one = findViewById(radioId);

        Toast.makeText(this, "Selected Radio Button: " + radioButton_one.getText(),
                Toast.LENGTH_SHORT).show();
    }

    public void checkButton2(View v) {
        int radioId = radioGroup_two.getCheckedRadioButtonId();

        radioButton_two = findViewById(radioId);

        Toast.makeText(this, "Selected Radio Button: " + radioButton_two.getText(),
                Toast.LENGTH_SHORT).show();
    }
}
