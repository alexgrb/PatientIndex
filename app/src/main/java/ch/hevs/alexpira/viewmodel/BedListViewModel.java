package ch.hevs.alexpira.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import java.util.List;

import ch.hevs.alexpira.database.entity.BedEntity;
import ch.hevs.alexpira.database.pojo.PatientWithBed;
import ch.hevs.alexpira.database.repository.BedRepository;
import ch.hevs.alexpira.util.OnAsyncEventListener;

public class BedListViewModel extends AndroidViewModel {


    private BedRepository bedRepository;

    private final MediatorLiveData<List<PatientWithBed>> observablePatients;
    private final MediatorLiveData<List<PatientWithBed>> observableBeds;

    public BedListViewModel(@NonNull Application application){
        super(application);
        observableBeds = new MediatorLiveData<>();
        observableBeds.setValue(null);

        observablePatients = new MediatorLiveData<>();
        observablePatients.setValue(null);

        bedRepository = new BedRepository(application);

        LiveData<List<PatientWithBed>> allBeds = bedRepository.getAllBeds();
        LiveData<List<PatientWithBed>> allPatients =
                bedRepository.getAllPatientsWithBeds();

        observableBeds.addSource(allBeds, observableBeds::setValue);
        observablePatients.addSource(allPatients, observablePatients::setValue);
    }

    public LiveData<List<PatientWithBed>> getPatients() {
        return observablePatients;
    }

    public void insert(BedEntity bed,  OnAsyncEventListener callback){
        bedRepository.insert(bed, callback);
    }

    public void delete(BedEntity bed,  OnAsyncEventListener callback) {
        bedRepository.delete(bed, callback);
    }
/*
    public void deleteAllBeds( OnAsyncEventListener callback) {
        bedRepository.deleteAllBeds(callback);
    }
*/

    public void update(BedEntity bed,  OnAsyncEventListener callback) {
        bedRepository.update(bed, callback);
    }
}
