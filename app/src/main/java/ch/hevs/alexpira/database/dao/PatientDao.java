package ch.hevs.alexpira.database.dao;

import android.database.sqlite.SQLiteConstraintException;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

import ch.hevs.alexpira.database.entity.PatientEntity;
import ch.hevs.alexpira.database.pojo.PatientWithBed;

@Dao
public interface PatientDao {


    @Query("SELECT *, `rowid` FROM patients")
    LiveData<List<PatientEntity>> getAll();

    @Query("SELECT *,`rowid` FROM patients WHERE rowid = :rowid")
    LiveData<PatientEntity> getById(int rowid);

    @Query("SELECT *,`rowid` FROM patients WHERE rowid IN (:rowid)")
    List<PatientEntity> loadAllByIds(int[] rowid);

    @Query("SELECT *,`rowid` FROM patients WHERE patientFirstName LIKE :patientFirstName LIMIT 1")
    PatientEntity findByName(String patientFirstName);

    @Insert
    void insertAll(PatientEntity... patients) throws SQLiteConstraintException;

    @Insert
    long insert(PatientEntity patient) throws SQLiteConstraintException;

    @Update
    void updateFruits(PatientEntity... patients);

    @Update
    void update(PatientEntity patients);

    @Delete
    void delete(PatientEntity patients);

    @Query("DELETE FROM patients")
    void deleteAll();

    @Transaction
    @Query("SELECT *, `rowid` FROM patients")
    public LiveData<List<PatientWithBed>> getPatientWithBed();
}
