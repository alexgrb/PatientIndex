package ch.hevs.alexpira;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EnterInfoActivity extends Activity {
    private Button submit;
    private EditText firstName;
    private EditText lastName;
    private EditText address;
    private EditText birthdate;
    private EditText city;
    private EditText npa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_adduser);

        submit = (Button) findViewById(R.id.btn_submit);
        firstName = (EditText) findViewById(R.id.tv_firstName);
        lastName = (EditText) findViewById(R.id.et_lastname);
        address = (EditText) findViewById(R.id.et_address);
        birthdate = (EditText) findViewById(R.id.et_birthdate);
        city = (EditText) findViewById(R.id.et_city);
        npa = (EditText) findViewById(R.id.et_NPA);

        //ClickListenener on submit button + creation d'un new intent
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EnterInfoActivity.this, ShowPatientActivity.class);
                intent.putExtra("firstname", firstName.getText().toString());
                intent.putExtra("lastname", lastName.getText().toString());
                intent.putExtra("address", address.getText().toString());
                intent.putExtra("city", city.getText().toString());
                intent.putExtra("country", npa.getText().toString());
                intent.putExtra("birthdate", birthdate.getText().toString());

                EnterInfoActivity.this.startActivity(intent);
            }
        });


    }
}
