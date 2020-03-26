package ch.hevs.alexpira.database;

import android.os.AsyncTask;
import android.util.Log;

import ch.hevs.alexpira.database.entity.PatientEntity;

public class DatabaseInitializer {

    //This tag will be used in our logs.
    public static final String TAG = "DatabaseInitialiser";

    public static void populateDatabase(final AppDatabase db){
        //We are going to log our action.
        Log.i(TAG, "Inserting demo data.");
        PopulateDbAsync task = new PopulateDbAsync(db);
        task.execute();
    }
    //This method will call patientDao and insert one patient.
    private static void addPatient(final AppDatabase db, final String id, final String firstname, final String lastname) {
        PatientEntity patient = new PatientEntity(id, firstname, lastname);
        db.patientDao().insert(patient);
    }

    //This method is deleting everything in the database and then populate it.
    private static void populateWithTestData(AppDatabase db) {
        db.patientDao().deleteAll();

        addPatient(db,"1", "Yvan", "Neuilly");

        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //This will launch the previous methods in background.
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void>{

        private final AppDatabase database;

        PopulateDbAsync(AppDatabase db){
            database = db;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            populateWithTestData(database);
            return null;
        }
    }
}
