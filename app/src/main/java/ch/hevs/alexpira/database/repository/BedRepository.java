package ch.hevs.alexpira.database.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import ch.hevs.alexpira.BaseApp;
import ch.hevs.alexpira.database.AppDatabase;
import ch.hevs.alexpira.database.entity.BedEntity;
import ch.hevs.alexpira.database.firebase.BedListLiveData;
import ch.hevs.alexpira.database.firebase.BedLiveData;
import ch.hevs.alexpira.database.pojo.PatientWithBed;
import ch.hevs.alexpira.util.OnAsyncEventListener;

public class BedRepository {

    private static BedRepository instance;
    private LiveData<List<PatientWithBed>> allBeds;
    private LiveData<List<PatientWithBed>> allPatientsWithBeds;

    public BedRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
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

    public LiveData<BedEntity> getBed(final int bedid) {
        DatabaseReference reference = FirebaseDatabase.getInstance()
            .getReference("beds")
            .child(String.valueOf(bedid));
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




    public LiveData<List<PatientWithBed>> getAllPatientsWithBeds() {
        return allPatientsWithBeds;
    }

    private BedRepository() {
    }


}
