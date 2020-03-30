package ch.hevs.alexpira.database;

import android.os.AsyncTask;
import android.util.Log;

import ch.hevs.alexpira.database.entity.BedEntity;
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
    private static void addPatient(final AppDatabase db, final int id, final String firstname, final String lastname) {
        PatientEntity patient = new PatientEntity(id, firstname, lastname);
        db.patientDao().insert(patient);
    }

    private static void addBed(final AppDatabase db, final int bedNumber, final int patientId, String bedSize, String bedAdjustable) {
        BedEntity bed = new BedEntity(bedNumber, patientId, bedSize, bedAdjustable);
        db.bedDao().insert(bed);
    }

    //This method is deleting everything in the database and then populate it.
    private static void populateWithTestData(AppDatabase db) {
        db.patientDao().deleteAll();

        addPatient(db,1, "Yvan", "Neuilly");
        addPatient(db,2, "Yvick", "Exact");

        db.bedDao().deleteAllNotes();

        addBed(db,100, 1, "Baby size", "Adjustable");
        addBed(db,200, 2, "King size", "Non-Adjustable");

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
