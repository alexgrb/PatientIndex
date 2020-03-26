package ch.hevs.alexpira.database;



import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.Executors;

import ch.hevs.alexpira.database.dao.BedDao;
import ch.hevs.alexpira.database.dao.PatientDao;
import ch.hevs.alexpira.database.entity.PatientEntity;

@Database(entities = {PatientEntity.class}, exportSchema = false,version = 1)
public abstract class AppDatabase extends RoomDatabase {

    //This tag will be used in our logs.
    private static final String TAG = "AppDatabase";

    private static AppDatabase INSTANCE;

    private static final String DATABASE_NAME = "hospital-database";

    public abstract PatientDao patientDao();

    //public abstract BedDao bedDao();

    private final MutableLiveData<Boolean> mIsDatabaseCreated = new MutableLiveData<>();

    public static AppDatabase getInstance(final Context context){
        if(INSTANCE == null) {
            synchronized (AppDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = buildDatabase(context.getApplicationContext());
                    INSTANCE.updateDatabaseCreated(context.getApplicationContext());
                }
            }
        }
        return INSTANCE;
    }
    //Build the db and initialize it.
    private static AppDatabase buildDatabase(final Context appContext){
        Log.i(TAG, "Database will be initialized.");
        return Room.databaseBuilder(appContext, AppDatabase.class, DATABASE_NAME)
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        Executors.newSingleThreadExecutor().execute(() ->{
                            AppDatabase database = AppDatabase.getInstance(appContext);
                            initializeDemoData(database);
                            //Now we can notify that the database has been created.
                            database.setDatabaseCreated();
                        });
                    }
                }).build();
    }

    public static void initializeDemoData(final AppDatabase database){
        Executors.newSingleThreadExecutor().execute(() -> {
            database.runInTransaction(() -> {
                Log.i(TAG, "Wipe database.");
                database.patientDao().deleteAll();
                //database.bedDao().deleteAll();

                DatabaseInitializer.populateDatabase(database);
            });
        });
    }

    private void updateDatabaseCreated(final Context context){
        if(context.getDatabasePath(DATABASE_NAME).exists()){
            Log.i(TAG, "Database initialized");
            setDatabaseCreated();
        }
    }
    private void setDatabaseCreated(){
        mIsDatabaseCreated.postValue(true);
    }
}
