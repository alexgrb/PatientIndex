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
import ch.hevs.alexpira.database.repository.BedRepository;
import ch.hevs.alexpira.database.repository.PatientRepository;

public class BedListViewModel extends AndroidViewModel {

    private Application application;

    private BedRepository bedRepository;
    private final MediatorLiveData<List<BedEntity>> observableBeds;

    /////////////////////////////
    //TEST CODIN FLOW
    public BedListViewModel(@NonNull Application application){
        super(application);
        observableBeds = new MediatorLiveData<>();
        observableBeds.setValue(null);

        bedRepository = new BedRepository(application);

        LiveData<List<BedEntity>> allBeds = bedRepository.getAllPatients();

        observableBeds.addSource(allBeds, observableBeds::setValue);
    }

    ////////////////////////////


    public BedListViewModel(@NonNull Application application, PatientRepository patientRepository){
        super(application);

        this.bedRepository = bedRepository;

        observableBeds = new MediatorLiveData<>();
        observableBeds.setValue(null);

        LiveData<List<BedEntity>> beds =
                bedRepository.getBeds(application);

        observableBeds.addSource(beds, observableBeds::setValue);
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

    /*public void insert(PatientEntity patientEntity, OnAsyncEventListener onAsyncEventListener) {
        patientRepository.insert(patientEntity, onAsyncEventListener, application);
    }*/


    public void insert(BedEntity bed){
        bedRepository.insert(bed);
    }

    public void delete(BedEntity bed) {
        bedRepository.delete(bed);
    }

    public void deleteAllBeds() {
        bedRepository.deleteAllBeds();
    }
}
