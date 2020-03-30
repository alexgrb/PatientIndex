package ch.hevs.alexpira.database.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import ch.hevs.alexpira.BaseApp;
import ch.hevs.alexpira.database.AppDatabase;
import ch.hevs.alexpira.database.dao.BedDao;
import ch.hevs.alexpira.database.dao.PatientDao;
import ch.hevs.alexpira.database.entity.BedEntity;
import ch.hevs.alexpira.database.entity.PatientEntity;

public class BedRepository {

    private static BedRepository instance;

    ////////////////////////////////////////
    //TEST CODIN FLOW
    private BedDao bedDao;
    private LiveData<List<BedEntity>> allBeds;

    public BedRepository(Application application){
        AppDatabase database = AppDatabase.getInstance(application);
        bedDao = database.bedDao();
        allBeds = bedDao.getAll();
    }

    public LiveData<List<BedEntity>> getAllPatients() {
        return allBeds;
    }

    /////////////////////////////////////////
    private BedRepository(){

    }
    public static BedRepository getInstance(){
        if(instance == null){
            synchronized (BedRepository.class){
                if(instance == null){
                    instance = new BedRepository();
                }
            }
        }
        return instance;
    }

    public LiveData<BedEntity> getBed(final int bedid, Application application){
        return ((BaseApp) application).getDatabase().bedDao().getById(bedid);
    }

    public LiveData<List<BedEntity>> getBeds (Application application){
        return ((BaseApp) application).getDatabase().bedDao().getAll();
    }

   /* public void insert(final PatientEntity client, OnAsyncEventListener callback,
                       Application application) {
        new CreatePatient(application, callback).execute(client);
    }*/

    public void insert(BedEntity bedEntity) {
        new BedRepository.InsertBedAsyncTask(bedDao).execute(bedEntity);
    }

    private static class InsertBedAsyncTask extends AsyncTask<BedEntity, Void, Void> {
        private BedDao bedDao;

        private InsertBedAsyncTask(BedDao bedDao) {
            this.bedDao= bedDao;
        }

        @Override
        protected Void doInBackground(BedEntity... bedEntities) {
            bedDao.insert(bedEntities[0]);
            return null;
        }
    }
    public void delete(BedEntity bed) {
        new DeleteBedAsyncTask(bedDao).execute(bed);
    }

    public void deleteAllBeds() {
        new DeleteAllBedsAsyncTask(bedDao).execute();
    }
    private static class DeleteBedAsyncTask extends AsyncTask<BedEntity, Void, Void> {
        private BedDao bedDao;

        private DeleteBedAsyncTask(BedDao bedDao) {
            this.bedDao = bedDao;
        }

        @Override
        protected Void doInBackground(BedEntity... beds) {
            bedDao.delete(beds[0]);
            return null;
        }
    }

    private static class DeleteAllBedsAsyncTask extends AsyncTask<Void, Void, Void> {
        private BedDao bedDao;

        private DeleteAllBedsAsyncTask(BedDao bedDao) {
            this.bedDao = bedDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            bedDao.deleteAllNotes();
            return null;
        }
    }
}
