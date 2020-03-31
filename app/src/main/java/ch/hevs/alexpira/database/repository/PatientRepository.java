package ch.hevs.alexpira.database.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import ch.hevs.alexpira.BaseApp;
import java.util.List;

import ch.hevs.alexpira.database.AppDatabase;
import ch.hevs.alexpira.database.dao.BedDao;
import ch.hevs.alexpira.database.dao.PatientDao;
import ch.hevs.alexpira.database.entity.PatientEntity;
import ch.hevs.alexpira.database.pojo.PatientWithBed;

public class PatientRepository {

    private static PatientRepository instance;
    private PatientDao patientDao;
    private BedDao bedDao;

    private LiveData<List<PatientEntity>> allPatients;
    private LiveData<List<PatientWithBed>> allPatientsWithBed;
    public PatientRepository(Application application){
        AppDatabase database = AppDatabase.getInstance(application);
       patientDao = database.patientDao();
       bedDao = database.bedDao();

        allPatients = patientDao.getAll();
        allPatientsWithBed = bedDao.getAllPatientsWithBed();

    }

    public LiveData<List<PatientEntity>> getAllPatients() {
        return allPatients;
    }
    public LiveData<List<PatientWithBed>> getAllPatientsWithBed() {
        return allPatientsWithBed;
    }

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

    public void insert(PatientEntity patient) {
        new InsertPatientAsyncTask(patientDao).execute(patient);
    }
    public void delete(PatientEntity patient) {
        new DeleteNoteAsyncTask(patientDao).execute(patient);
    }
    public void deleteAllPatients() {
        new DeleteAllPatientsAsyncTask(patientDao).execute();
    }
    public void update(PatientEntity patient) {
        new UpdatePatientAsyncTask(patientDao).execute(patient);
    }

    private static class UpdatePatientAsyncTask extends AsyncTask<PatientEntity, Void, Void> {
        private PatientDao patientDao;

        private UpdatePatientAsyncTask(PatientDao patientDao) {
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

    private static class DeleteAllPatientsAsyncTask extends AsyncTask<PatientEntity, Void, Void> {
        private PatientDao patientDao;

        private DeleteAllPatientsAsyncTask(PatientDao patientDao) {
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
