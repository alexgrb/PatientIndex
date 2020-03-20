package ch.hevs.alexpira;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddPatient extends AppCompatActivity {
    private Button submit;
    private EditText firstName;
    private EditText lastName;
    private EditText address;
    private EditText birthdate;
    private EditText city;
    private EditText npa;
    public static final String FIRSTNAME = "alexpira.EXTRA_TEXT";
    public static final String LASTNAME= "alexpira.EXTRA_TEXT";
    public static final String ADRESS = "alexpira.EXTRA_TEXT";
    public static final String BIRTHDATE = "alexpira.EXTRA_TEXT";
    public static final String CITY = "alexpira.EXTRA_TEXT";
    public static final String NPA = "alexpira.EXTRA_TEXT";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        submit = (Button) findViewById(R.id.btn_submit);
        firstName = (EditText) findViewById(R.id.tv_firstName);
        lastName = (EditText) findViewById(R.id.et_lastname);
        address = (EditText) findViewById(R.id.et_address);
        birthdate = (EditText) findViewById(R.id.et_birthdate);
        city = (EditText) findViewById(R.id.et_city);
        npa = (EditText) findViewById(R.id.et_NPA);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                intent.putExtra("firstname", firstName.getText().toString());
                intent.putExtra("lastname", lastName.getText().toString());
                intent.putExtra("address", address.getText().toString());
                intent.putExtra("city", city.getText().toString());
                intent.putExtra("country", npa.getText().toString());
                intent.putExtra("birthdate", birthdate.getText().toString());
                 */
                openActivity2();
            }
        });

    }

    public void openActivity2(){

        EditText firstName = (EditText) findViewById(R.id.tv_firstName);
        String text = firstName.getText().toString();


        Intent intent = new Intent(this, DisplayUser.class);
        intent.putExtra(FIRSTNAME, text);
        startActivity(intent);
    }
}
