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

import ch.hevs.alexpira.database.entity.PatientEntity;

public class PatientListLiveData extends LiveData<List<PatientEntity>> {

    private static final String TAG = "ClientAccountsLiveData";

    private final DatabaseReference reference;
    private final MyValueEventListener listener = new MyValueEventListener();

    public PatientListLiveData(DatabaseReference ref) {
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
            setValue(toClientList(dataSnapshot));
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
            Log.e(TAG, "Can't listen to query " + reference, databaseError.toException());
        }
    }

    private List<PatientEntity> toClientList(DataSnapshot snapshot) {
        List<PatientEntity> patient = new ArrayList<>();
        for (DataSnapshot childSnapshot : snapshot.getChildren()) {
            PatientEntity entity = childSnapshot.child("patients").getValue(PatientEntity.class);
            entity.setId(Integer.valueOf(childSnapshot.getKey()));
            patient.add(entity);
        }
        return patient;
    }
}