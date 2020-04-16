package ch.hevs.alexpira.database.firebase;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import ch.hevs.alexpira.database.entity.BedEntity;
import ch.hevs.alexpira.database.entity.PatientEntity;
import ch.hevs.alexpira.database.pojo.PatientWithBed;

public class BedListLiveData extends LiveData<List<PatientWithBed>> {

    private static final String TAG = "ClientAccountsLiveData";

    private final DatabaseReference reference;
    private final MyValueEventListener listener = new MyValueEventListener();

    public BedListLiveData(DatabaseReference ref) {
        reference = ref;
    }

    @Override
    protected void onActive() {
        Log.d(TAG, "onActive");
        reference.addValueEventListener(listener);
    }

    @Override
    protected void onInactive() {
        Log.d(TAG, "onInactive");
    }

    private class MyValueEventListener implements ValueEventListener {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            setValue(toPatientWithBedList(dataSnapshot));
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
            Log.e(TAG, "Can't listen to query " + reference, databaseError.toException());
        }
    }

    private List<PatientWithBed> toPatientWithBedList(DataSnapshot snapshot) {
        List<PatientWithBed> patientWithBedList = new ArrayList<>();
        for (DataSnapshot childBed : snapshot.getChildren()) {
            PatientWithBed patientWithBed = new PatientWithBed();

            BedEntity newBed = childBed.getValue(BedEntity.class);
            newBed.setId(childBed.getKey());
            patientWithBed.bedEntity = newBed;

            PatientEntity newPatient = childBed.child("patient").getValue(PatientEntity.class);
            patientWithBed.patientEntity = newPatient;
            patientWithBedList.add(patientWithBed);
        }
        return patientWithBedList;
    }

    private List<BedEntity> toBedList(DataSnapshot snapshot) {
        List<BedEntity> bed = new ArrayList<>();
        for (DataSnapshot childSnapshot : snapshot.getChildren()) {
            BedEntity entity = childSnapshot.getValue(BedEntity.class);
            entity.setId(childSnapshot.getKey());
            bed.add(entity);
        }
        return bed;
    }
}