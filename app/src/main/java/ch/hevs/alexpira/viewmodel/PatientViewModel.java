package ch.hevs.alexpira.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import ch.hevs.alexpira.ui.BaseApp;
import ch.hevs.alexpira.database.entity.PatientEntity;
import ch.hevs.alexpira.database.repository.PatientRepository;

public class PatientViewModel extends AndroidViewModel {

    private PatientRepository repository;

    private Application application;

    //We are going to use a Mediator to observe the other LiveData objects.
    private final MediatorLiveData<PatientEntity> observablePatient;

    //This will create the patient on the screen
    public PatientViewModel(@NonNull Application application, final int patientId, PatientRepository patientRepository){
        super(application);

        this.application = application;

        repository = patientRepository;
        //Null until the database initialize it.
        observablePatient = new MediatorLiveData<>();

        LiveData<PatientEntity> patient = repository.getPatient(patientId, application);

        observablePatient.addSource(patient, observablePatient::setValue);
    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory{

        @NonNull
        private final Application application;

        private final int patientId;

        private final PatientRepository repository;

        public Factory(@NonNull Application application, int patientId){
            this.application = application;
            this.patientId = patientId;
            repository = ((BaseApp) application).getPatientRepository();
        }

        @Override
        public<T extends ViewModel> T create(Class<T> ModelClass){
            return (T) new PatientViewModel(application,patientId,repository);
        }
    }

    public LiveData<PatientEntity> getPatient() {
        return observablePatient;
    }

}
