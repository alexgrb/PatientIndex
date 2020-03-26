package ch.hevs.alexpira.database;



import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import ch.hevs.alexpira.database.dao.PatientDao;
import ch.hevs.alexpira.database.entity.PatientEntity;

@Database(entities = {PatientEntity.class}, version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract PatientDao patientDao();

    private static AppDatabase INSTANCE;

    private static final Object LOCK = new Object();

    public synchronized static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (LOCK) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "patient-database").build();
                }
            }
        }
        return INSTANCE;
    }
}
