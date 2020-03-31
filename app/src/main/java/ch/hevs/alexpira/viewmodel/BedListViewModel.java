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

public class BedListViewModel extends AndroidViewModel {


    private BedRepository bedRepository;

    private final MediatorLiveData<List<PatientWithBed>> observablePatients;
    private final MediatorLiveData<List<BedEntity>> observableBeds;

    public BedListViewModel(@NonNull Application application){
        super(application);
        observableBeds = new MediatorLiveData<>();
        observableBeds.setValue(null);

        observablePatients = new MediatorLiveData<>();
        observablePatients.setValue(null);

        bedRepository = new BedRepository(application);

        LiveData<List<BedEntity>> allBeds = bedRepository.getAllBeds();
        LiveData<List<PatientWithBed>> allPatients =
                bedRepository.getAllPatientsWithBeds();

        observableBeds.addSource(allBeds, observableBeds::setValue);
        observablePatients.addSource(allPatients, observablePatients::setValue);
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
