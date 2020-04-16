package ch.hevs.alexpira.database.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import ch.hevs.alexpira.BaseApp;
import ch.hevs.alexpira.database.AppDatabase;
import ch.hevs.alexpira.database.dao.BedDao;
import ch.hevs.alexpira.database.entity.BedEntity;
import ch.hevs.alexpira.database.firebase.BedListLiveData;
import ch.hevs.alexpira.database.firebase.BedLiveData;
import ch.hevs.alexpira.database.pojo.PatientWithBed;
import ch.hevs.alexpira.util.OnAsyncEventListener;

public class BedRepository {

    private static BedRepository instance;
    private BedDao bedDao;
    private LiveData<List<PatientWithBed>> allBeds;
    private LiveData<List<PatientWithBed>> allPatientsWithBeds;

    public BedRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        bedDao = database.bedDao();
        allBeds = getAllBeds();
        allPatientsWithBeds = getAllPatientsWithBed();
    }

    public static BedRepository getInstance() {
        if (instance == null) {
            synchronized (BedRepository.class) {
                if (instance == null) {
                    instance = new BedRepository();
                }
            }
        }
        return instance;
    }

    public LiveData<BedEntity> getBed(final String bedid) {
        DatabaseReference reference = FirebaseDatabase.getInstance()
            .getReference("beds")
            .child(bedid);
        return new BedLiveData(reference);
    }

    public LiveData<List<PatientWithBed>> getAllBeds() {
        DatabaseReference reference = FirebaseDatabase.getInstance()
                .getReference("beds");
        return new BedListLiveData(reference);
    }

    public LiveData<List<PatientWithBed>> getAllPatientsWithBed() {
        DatabaseReference reference = FirebaseDatabase.getInstance()
                .getReference("beds");
        return new BedListLiveData(reference);
    }

    public void insert(final BedEntity bed, final OnAsyncEventListener callback) {
        String id = FirebaseDatabase.getInstance().getReference("beds").push().getKey();
        FirebaseDatabase.getInstance()
                .getReference("beds")
                .child(id)
                .setValue(bed, (databaseError, databaseReference) -> {
                    if (databaseError != null) {
                        callback.onFailure(databaseError.toException());
                    } else {
                        callback.onSuccess();
                    }
                });
    }

    public void update(final BedEntity bed, final OnAsyncEventListener callback) {
        FirebaseDatabase.getInstance()
                .getReference("clients")
                .child(String.valueOf(bed.getId()))
                .updateChildren(bed.toMap(), (databaseError, databaseReference) -> {
                    if (databaseError != null) {
                        callback.onFailure(databaseError.toException());
                    } else {
                        callback.onSuccess();
                    }
                });
    }

    public void delete(final BedEntity bed, OnAsyncEventListener callback) {
        FirebaseDatabase.getInstance()
                .getReference("clients")
                .child(String.valueOf(bed.getId()))
                .removeValue((databaseError, databaseReference) -> {
                    if (databaseError != null) {
                        callback.onFailure(databaseError.toException());
                    } else {
                        callback.onSuccess();
                    }
                });
    }

    public LiveData<List<PatientWithBed>> getAllPatientsWithBed(
            Application application) {
        return ((BaseApp) application).getDatabase().bedDao().getAllPatientsWithBed();
    }

    public LiveData<List<PatientWithBed>> getAllPatientsWithBeds() {
        return allPatientsWithBeds;
    }

    private BedRepository() {
    }


}
