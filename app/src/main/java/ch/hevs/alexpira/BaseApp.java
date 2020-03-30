package ch.hevs.alexpira;

import android.app.Application;

import ch.hevs.alexpira.database.AppDatabase;
import ch.hevs.alexpira.database.repository.BedRepository;
import ch.hevs.alexpira.database.repository.PatientRepository;

public class BaseApp extends Application {

    @Override
    public void onCreate(){
        super.onCreate();
    }

    public AppDatabase getDatabase(){
        return AppDatabase.getInstance(this);
    }

    public PatientRepository getPatientRepository(){
        return PatientRepository.getInstance();
    }

    public BedRepository getBedRepository(){
        return BedRepository.getInstance();
    }
}
