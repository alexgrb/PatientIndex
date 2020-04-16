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
    private static void addPatient(final AppDatabase db, final String firstname, final String lastname, int bedId) {
        PatientEntity patient = new PatientEntity(firstname, lastname, bedId);
      //  db.patientDao().insert(patient);
    }

    private static void addBed(final AppDatabase db, final int bedNumber, String bedSize, String bedAdjustable) {
        BedEntity bed = new BedEntity(bedNumber, bedSize, bedAdjustable);
        //db.bedDao().insert(bed);
    }

    //This method is deleting everything in the database and then populate it.
    private static void populateWithTestData(AppDatabase db) {
       // db.bedDao().deleteAllBeds();
        //db.patientDao().deleteAll();

        addBed(db,100, "Baby size", "Adjustable");
        addBed(db,101, "King size", "Adjustable");
        addBed(db,102, "King size", "Non-Adjustable");
        addBed(db,103, "Baby size", "Adjustable");
        addBed(db,104, "King size", "Non-Adjustable");
        addBed(db,200, "Baby size", "Adjustable");
        addBed(db,201, "King size", "Non-Adjustable");
        addBed(db,202, "Baby size", "Adjustable");
        addBed(db,203, "King size", "Non-Adjustable");
        addBed(db,204, "Baby size", "Adjustable");
        addBed(db,300, "Baby size", "Non-Adjustable");
        addBed(db,301, "King size", "Non-Adjustable");
        addBed(db,302, "King size", "Adjustable");
        addBed(db,303, "Baby size", "Adjustable");
        addBed(db,304, "King size", "Non-Adjustable");

        addPatient(db, "Alex", "Gharbi", 1);
        addPatient(db, "Pira", "Thambirajah", 2);
        addPatient(db, "Goerge", "Vitte", 3);
        addPatient(db, "Pierre", "Rapite", 4);
        addPatient(db, "Christian", "Weber", 5);

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
