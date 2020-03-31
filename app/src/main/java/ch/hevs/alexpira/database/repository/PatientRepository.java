package ch.hevs.alexpira.database.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import ch.hevs.alexpira.BaseApp;
import java.util.List;

import ch.hevs.alexpira.database.AppDatabase;
import ch.hevs.alexpira.database.async.patient.CreatePatient;
import ch.hevs.alexpira.database.dao.PatientDao;
import ch.hevs.alexpira.database.entity.PatientEntity;
import ch.hevs.alexpira.database.pojo.PatientWithBed;
import ch.hevs.alexpira.util.OnAsyncEventListener;

public class PatientRepository {

    private static PatientRepository instance;

    ////////////////////////////////////////
    //TEST CODIN FLOW
    private PatientDao patientDao;
    private LiveData<List<PatientWithBed>> allPatientsWithBeds;
    private LiveData<List<PatientEntity>> allPatients;
    public PatientRepository(Application application){
        AppDatabase database = AppDatabase.getInstance(application);
       patientDao = database.patientDao();
        allPatientsWithBeds = patientDao.getAllWithBed();
        allPatients = patientDao.getAll();

    }

    public LiveData<List<PatientWithBed>> getAllPatientsWithBeds() {
        return allPatientsWithBeds;
    }
    public LiveData<List<PatientEntity>> getAllPatients() {
        return allPatients;
    }

    /////////////////////////////////////////
    private PatientRepository(){

    }
    public static PatientRepository getInstance(){
        if(instance == null){
            synchronized (BedRepository.class){
                if(instance == null){
                    instance = new PatientRepository();
                }
            }
        }
        return instance;
    }

    public LiveData<PatientEntity> getPatient(final int patientId, Application application){
        return ((BaseApp) application).getDatabase().patientDao().getById(patientId);
    }

    public LiveData<List<PatientEntity>> getPatients(Application application){
        return ((BaseApp) application).getDatabase().patientDao().getAll();
    }

   /* public void insert(final PatientEntity client, OnAsyncEventListener callback,
                       Application application) {
        new CreatePatient(application, callback).execute(client);
    }*/

   public LiveData<List<PatientWithBed>> getAllById(
                                                                     Application application) {
       return ((BaseApp) application).getDatabase().patientDao().getAllWithBed();
   }
    public void insert(PatientEntity patient) {
        new InsertPatientAsyncTask(patientDao).execute(patient);
    }
    public void delete(PatientEntity patient) {
        new DeleteNoteAsyncTask(patientDao).execute(patient);
    }
    public void deleteAllNotes() {
        new DeleteAllNotesAsyncTask(patientDao).execute();
    }
    public void update(PatientEntity patient) {
        new UpdateNoteAsyncTask(patientDao).execute(patient);
    }

    private static class UpdateNoteAsyncTask extends AsyncTask<PatientEntity, Void, Void> {
        private PatientDao patientDao;

        private UpdateNoteAsyncTask(PatientDao patientDao) {
            this.patientDao= patientDao;
        }

        @Override
        protected Void doInBackground(PatientEntity... patients) {
            patientDao.update(patients[0]);
            return null;
        }
    }

    private static class InsertPatientAsyncTask extends AsyncTask<PatientEntity, Void, Void> {
        private PatientDao patientDao;

        private InsertPatientAsyncTask(PatientDao patientDao) {
            this.patientDao= patientDao;
        }

        @Override
        protected Void doInBackground(PatientEntity... patients) {
            patientDao.insert(patients[0]);
            return null;
        }
    }

    private static class DeleteAllNotesAsyncTask extends AsyncTask<PatientEntity, Void, Void> {
        private PatientDao patientDao;

        private DeleteAllNotesAsyncTask(PatientDao patientDao) {
            this.patientDao= patientDao;
        }

        @Override
        protected Void doInBackground(PatientEntity... patients) {
            patientDao.deleteAll();
            return null;
        }
    }

    private static class DeleteNoteAsyncTask extends AsyncTask<PatientEntity, Void, Void> {
        private PatientDao patientDao;

        private DeleteNoteAsyncTask(PatientDao patientDao) {
            this.patientDao = patientDao;
        }

        @Override
        protected Void doInBackground(PatientEntity... patients) {
            patientDao.delete(patients[0]);
            return null;
        }
    }
}
