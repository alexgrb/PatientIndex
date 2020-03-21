package ch.hevs.alexpira;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import ch.hevs.alexpira.db.AppDatabase;
import ch.hevs.alexpira.db.Patient;


public class DisplayUser extends AppCompatActivity {

    private TextView firstName;
    private TextView lastName;
    private TextView address;
    private TextView birthdate;
    private TextView city;
    private TextView npa;
    public static AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_user);

        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class,"patient-database").build();
        List<Patient> patients = appDatabase.patientDao().getAll();
        String info = "";
    if(patients!=null) {
        for (Patient patient : patients) {
            String firstName = patient.getPatientFirstName();
            String lastName = patient.getPatientLastName();

            info = info + "\n\n" + "Firstname : " + firstName + "\n Lastname :" + lastName;
        }

        Intent intent = getIntent();

        TextView textview1 = (TextView) findViewById(R.id.tv_firstName);
        textview1.setText(info);
    }
    else{

            info="Y a r dedans";
        Intent intent = getIntent();

        TextView textview1 = (TextView) findViewById(R.id.tv_firstName);
        textview1.setText(info);

    }
        /*
        Intent intent = getIntent();

        firstName = (TextView) findViewById(R.id.tv_firstName);
        lastName = (TextView) findViewById(R.id.et_lastname);
        address = (TextView) findViewById(R.id.et_address);
        city = (TextView) findViewById(R.id.et_city);
        npa = (TextView) findViewById(R.id.et_NPA);
        birthdate = (TextView) findViewById(R.id.et_birthdate);

        firstName.setText(intent.getStringExtra("firstName"));
        lastName.setText(intent.getStringExtra("lastName"));
        address.setText(intent.getStringExtra("address"));
        city.setText(intent.getStringExtra("city"));
        npa.setText(intent.getStringExtra("NPA"));
        birthdate.setText(intent.getStringExtra("birthdate"));

         */
    }
}
