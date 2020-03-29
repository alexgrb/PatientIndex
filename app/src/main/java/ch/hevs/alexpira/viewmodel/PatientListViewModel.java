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
import ch.hevs.alexpira.database.repository.PatientRepository;
import ch.hevs.alexpira.util.OnAsyncEventListener;

public class PatientListViewModel extends AndroidViewModel {

    private Application application;

    private PatientRepository patientRepository;
    private final MediatorLiveData<List<PatientEntity>> observablePatients;

    /////////////////////////////
    //TEST CODIN FLOW
    public PatientListViewModel(@NonNull Application application){
        super(application);
        observablePatients = new MediatorLiveData<>();
        observablePatients.setValue(null);

        patientRepository = new PatientRepository(application);

        LiveData<List<PatientEntity>> allPatients = patientRepository.getAllPatients();

        observablePatients.addSource(allPatients, observablePatients::setValue);
    }

    ////////////////////////////


    public PatientListViewModel(@NonNull Application application, PatientRepository patientRepository){
        super(application);

        this.patientRepository = patientRepository;

        observablePatients = new MediatorLiveData<>();
        observablePatients.setValue(null);

        LiveData<List<PatientEntity>> patients =
                patientRepository.getPatients(application);

        observablePatients.addSource(patients, observablePatients::setValue);
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

    /*public void insert(PatientEntity patientEntity, OnAsyncEventListener onAsyncEventListener) {
        patientRepository.insert(patientEntity, onAsyncEventListener, application);
    }*/


    public void insert(PatientEntity patient){
    patientRepository.insert(patient);
    }
}
