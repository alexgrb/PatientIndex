package ch.hevs.alexpira.database.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import ch.hevs.alexpira.BaseApp;
import java.util.List;

import ch.hevs.alexpira.database.entity.PatientEntity;

public class PatientRepository {

    private static PatientRepository instance;

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
}
