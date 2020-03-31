package ch.hevs.alexpira.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import java.util.List;
import ch.hevs.alexpira.database.entity.PatientEntity;
import ch.hevs.alexpira.database.pojo.PatientWithBed;
import ch.hevs.alexpira.database.repository.PatientRepository;
public class PatientListViewModel extends AndroidViewModel {

    private Application application;

    private PatientRepository patientRepository;
    private final MediatorLiveData<List<PatientEntity>> observablePatients;
    private final MediatorLiveData<List<PatientWithBed>> observablePatientsWithBed;

    public PatientListViewModel(@NonNull Application application){
        super(application);
        observablePatients = new MediatorLiveData<>();
        observablePatients.setValue(null);
        observablePatientsWithBed = new MediatorLiveData<>();
        observablePatientsWithBed.setValue(null);
        patientRepository = new PatientRepository(application);

        LiveData<List<PatientEntity>> allPatients = patientRepository.getAllPatients();
        LiveData<List<PatientWithBed>> allPatientsWithBed = patientRepository.getAllPatientsWithBed();

        observablePatients.addSource(allPatients, observablePatients::setValue);
        observablePatientsWithBed.addSource(allPatientsWithBed, observablePatientsWithBed::setValue);
    }


    public LiveData<List<PatientEntity>> getPatients() {
        return observablePatients;
    }
    public LiveData<List<PatientWithBed>> getPatientsWithBed() {
        return observablePatientsWithBed;
    }
    public void insert(PatientEntity patient){
    patientRepository.insert(patient);
    }
    public void delete(PatientEntity patient) {
        patientRepository.delete(patient);
    }
    public void deleteAllPatients() {
        patientRepository.deleteAllPatients();
    }
    public void update(PatientEntity patient) {
        patientRepository.update(patient);
    }

}
