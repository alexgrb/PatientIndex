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

public class PatientListViewModel extends AndroidViewModel {

    private Application application;

    private PatientRepository patientRepository;

    private final MediatorLiveData<List<PatientEntity>> observablePatients;

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
}
