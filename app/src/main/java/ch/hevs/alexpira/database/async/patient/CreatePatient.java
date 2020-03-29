package ch.hevs.alexpira.database.async.patient;

import android.app.Application;
import android.os.AsyncTask;

import ch.hevs.alexpira.BaseApp;
import ch.hevs.alexpira.database.entity.PatientEntity;
import ch.hevs.alexpira.util.OnAsyncEventListener;

public class CreatePatient extends AsyncTask<PatientEntity, Void, Void> {

    private Application application;
    private OnAsyncEventListener callback;
    private Exception exception;

    public CreatePatient(Application application, OnAsyncEventListener callback) {
        this.application = application;
        this.callback = callback;
    }

    @Override
    protected Void doInBackground(PatientEntity... params) {
        try {
            for (PatientEntity patient : params)
                ((BaseApp) application).getDatabase().patientDao()
                        .insert(patient);
        } catch (Exception e) {
            exception = e;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        if (callback != null) {
            if (exception == null) {
                callback.onSuccess();
            } else {
                callback.onFailure(exception);
            }
        }
    }
}
