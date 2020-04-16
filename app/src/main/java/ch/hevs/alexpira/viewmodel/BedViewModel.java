package ch.hevs.alexpira.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import ch.hevs.alexpira.BaseApp;
import ch.hevs.alexpira.database.entity.BedEntity;
import ch.hevs.alexpira.database.repository.BedRepository;

public class BedViewModel  extends AndroidViewModel {

    private BedRepository repository;

    private Application application;

    //We are going to use a Mediator to observe the other LiveData objects.
    private final MediatorLiveData<BedEntity> observableBed;

    //This will create the patient on the screen
    public BedViewModel(@NonNull Application application, final int rowId, BedRepository bedRepository){
        super(application);

        this.application = application;

        repository = bedRepository;
        //Null until the database initialize it.
        observableBed = new MediatorLiveData<>();

        LiveData<BedEntity> bed = repository.getBed(rowId);

        observableBed.addSource(bed, observableBed::setValue);
    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory{

        @NonNull
        private final Application application;

        private final int rowId;

        private final BedRepository repository;

        public Factory(@NonNull Application application, int rowId){
            this.application = application;
            this.rowId = rowId;
            repository = ((BaseApp) application).getBedRepository();
        }

        @Override
        public<T extends ViewModel> T create(Class<T> ModelClass){
            return (T) new BedViewModel(application,rowId,repository);
        }
    }

    public LiveData<BedEntity> getBed() {
        return observableBed;
    }


}
