package ch.hevs.alexpira.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

import ch.hevs.alexpira.BaseApp;
import ch.hevs.alexpira.database.entity.PatientEntity;
import ch.hevs.alexpira.database.pojo.PatientWithBed;
import ch.hevs.alexpira.database.repository.PatientRepository;
public class PatientListViewModel extends AndroidViewModel {

    private Application application;

    private PatientRepository patientRepository;
    private final MediatorLiveData<List<PatientEntity>> observablePatients;
    private final MediatorLiveData<List<PatientWithBed>> observablePatientsWithBed;

    /////////////////////////////
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

    ////////////////////////////


    public PatientListViewModel(@NonNull Application application, PatientRepository patientRepository){
        super(application);

        this.patientRepository = patientRepository;

        observablePatients = new MediatorLiveData<>();
        observablePatients.setValue(null);

        observablePatientsWithBed = new MediatorLiveData<>();
        observablePatientsWithBed.setValue(null);
        LiveData<List<PatientEntity>> patients =
                patientRepository.getPatients(application);

        LiveData<List<PatientWithBed>> allPatientsWithBed = patientRepository.getAllPatientsWithBed();

        observablePatients.addSource(patients, observablePatients::setValue);

        observablePatientsWithBed.addSource(allPatientsWithBed, observablePatientsWithBed::setValue);
    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory{

        @NonNull
        private final Application application;

        private final PatientRepository repository;

        public Factory(@NonNull Application application){
            this.application = application;
            repository = ((BaseApp) application).getPatientRepository();
        }

        @Override
        public<T extends ViewModel> T create(Class<T> ModelClass){
            return (T) new PatientListViewModel(application,repository);
        }
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
        patientRepository.deleteAllNotes();
    }
    public void update(PatientEntity patient) {
        patientRepository.update(patient);
    }

}
