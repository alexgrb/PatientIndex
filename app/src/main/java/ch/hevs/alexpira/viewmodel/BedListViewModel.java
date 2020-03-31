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
import ch.hevs.alexpira.database.entity.BedEntity;
import ch.hevs.alexpira.database.pojo.PatientWithBed;
import ch.hevs.alexpira.database.repository.BedRepository;
import ch.hevs.alexpira.database.repository.PatientRepository;

public class BedListViewModel extends AndroidViewModel {

    private Application application;

    private BedRepository bedRepository;
    private PatientRepository patientRepository;

    private final MediatorLiveData<List<PatientWithBed>> observablePatients;
    private final MediatorLiveData<List<BedEntity>> observableBeds;

    /////////////////////////////
    //TEST CODIN FLOW
    public BedListViewModel(@NonNull Application application){
        super(application);
        observableBeds = new MediatorLiveData<>();
        observableBeds.setValue(null);

        observablePatients = new MediatorLiveData<>();
        observablePatients.setValue(null);

        patientRepository = new PatientRepository(application);
        bedRepository = new BedRepository(application);

        LiveData<List<BedEntity>> allBeds = bedRepository.getAllBeds();
        LiveData<List<PatientWithBed>> allPatients =
                patientRepository.getAllPatientsWithBeds();

        observableBeds.addSource(allBeds, observableBeds::setValue);
        observablePatients.addSource(allPatients, observablePatients::setValue);
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

    public LiveData<List<BedEntity>> getBeds() {
        return observableBeds;
    }
    public LiveData<List<PatientWithBed>> getPatients() {
        return observablePatients;
    }

    public void insert(BedEntity bed){
        bedRepository.insert(bed);
    }

    public void delete(BedEntity bed) {
        bedRepository.delete(bed);
    }

    public void deleteAllBeds() {
        bedRepository.deleteAllBeds();
    }


    public void update(BedEntity bed) {
        bedRepository.update(bed);
    }
}
