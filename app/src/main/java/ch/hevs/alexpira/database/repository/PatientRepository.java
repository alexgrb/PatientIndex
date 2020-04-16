package ch.hevs.alexpira.database.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import ch.hevs.alexpira.database.AppDatabase;
import ch.hevs.alexpira.database.entity.PatientEntity;
import ch.hevs.alexpira.database.firebase.BedListLiveData;
import ch.hevs.alexpira.database.firebase.PatientListLiveData;
import ch.hevs.alexpira.database.firebase.PatientLiveData;
import ch.hevs.alexpira.database.pojo.PatientWithBed;
import ch.hevs.alexpira.util.OnAsyncEventListener;

public class PatientRepository {

    private static PatientRepository instance;
    private LiveData<List<PatientEntity>> allPatients;
    private LiveData<List<PatientWithBed>> allPatientsWithBed;

    public PatientRepository(Application application){
        AppDatabase database = AppDatabase.getInstance(application);

        allPatients = getAll();
        allPatientsWithBed = getAllPatientsWithBed();

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

    public LiveData<List<PatientEntity>> getAll() {
        DatabaseReference reference = FirebaseDatabase.getInstance()
                .getReference("patients");
        return new PatientListLiveData(reference);
    }

    public LiveData<List<PatientWithBed>> getAllPatientsWithBed() {
        DatabaseReference reference = FirebaseDatabase.getInstance()
                .getReference("beds");
        return new BedListLiveData(reference);
    }

    private PatientRepository(){

    }


    public LiveData<PatientEntity> getPatient(final String patientId){
        DatabaseReference reference = FirebaseDatabase.getInstance()
                .getReference("patients")
                .child(patientId);
        return new PatientLiveData(reference);
    }


    public void insert(final PatientEntity patient, final OnAsyncEventListener callback) {
        String id = FirebaseDatabase.getInstance().getReference("patients").push().getKey();
        FirebaseDatabase.getInstance()
                .getReference("patients")
                .child(id)
                .setValue(patient, (databaseError, databaseReference) -> {
                    if (databaseError != null) {
                        callback.onFailure(databaseError.toException());
                    } else {
                        callback.onSuccess();
                    }
                });
    }

    public void update(final PatientEntity patient, final OnAsyncEventListener callback) {
        FirebaseDatabase.getInstance()
                .getReference("patients")
                .child(String.valueOf(patient.getRowid()))
                .updateChildren(patient.toMap(), (databaseError, databaseReference) -> {
                    if (databaseError != null) {
                        callback.onFailure(databaseError.toException());
                    } else {
                        callback.onSuccess();
                    }
                });
    }

    public void delete(final PatientEntity patient, OnAsyncEventListener callback) {
        FirebaseDatabase.getInstance()
                .getReference("patients")
                .child(String.valueOf(patient.getRowid()))
                .removeValue((databaseError, databaseReference) -> {
                    if (databaseError != null) {
                        callback.onFailure(databaseError.toException());
                    } else {
                        callback.onSuccess();
                    }
                });
    }
}
