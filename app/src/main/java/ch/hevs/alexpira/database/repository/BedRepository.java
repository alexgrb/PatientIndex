package ch.hevs.alexpira.database.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import ch.hevs.alexpira.BaseApp;
import ch.hevs.alexpira.database.AppDatabase;
import ch.hevs.alexpira.database.dao.BedDao;
import ch.hevs.alexpira.database.entity.BedEntity;
import ch.hevs.alexpira.database.pojo.PatientWithBed;

public class BedRepository {

    private static BedRepository instance;
    private BedDao bedDao;
    private LiveData<List<BedEntity>> allBeds;
    private LiveData<List<PatientWithBed>> allPatientsWithBeds;

    public BedRepository(Application application){
        AppDatabase database = AppDatabase.getInstance(application);
        bedDao = database.bedDao();
        allBeds = bedDao.getAll();
        allPatientsWithBeds = bedDao.getAllPatientsWithBed();
    }

    public LiveData<List<BedEntity>> getAllBeds() {
        return allBeds;
    }
    public LiveData<List<PatientWithBed>> getAllPatientsWithBeds() {
        return allPatientsWithBeds;
    }
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

    public LiveData<List<PatientWithBed>> getAllPatientsWithBed(
            Application application) {
        return ((BaseApp) application).getDatabase().bedDao().getAllPatientsWithBed();
    }

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
            bedDao.deleteAllBeds();
            return null;
        }
    }

    public void update(BedEntity bed) {
        new UpdateBedAsyncTask(bedDao).execute(bed);
    }

    private static class UpdateBedAsyncTask extends AsyncTask<BedEntity, Void, Void> {
        private BedDao bedDao;

        private UpdateBedAsyncTask(BedDao bedDao) {
            this.bedDao = bedDao;
        }

        @Override
        protected Void doInBackground(BedEntity... bed) {
            bedDao.update(bed[0]);
            return null;
        }
    }
}
