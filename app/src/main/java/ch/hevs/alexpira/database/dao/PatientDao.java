package ch.hevs.alexpira.database.dao;

import android.database.sqlite.SQLiteConstraintException;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import ch.hevs.alexpira.database.entity.PatientEntity;

@Dao
public interface PatientDao {


    @Query("SELECT * FROM patients")
    List<PatientEntity> getAll();

    @Query("SELECT * FROM patients WHERE id IN (:patientIds)")
    List<PatientEntity> loadAllByIds(int[] patientIds);

    @Query("SELECT * FROM patients WHERE patientFirstName LIKE :patientFirstName LIMIT 1")
    PatientEntity findByName(String patientFirstName);

    @Insert
    void insertAll(PatientEntity... patients) throws SQLiteConstraintException;

    @Update
    void updateFruits(PatientEntity... patients);

    @Delete
    void delete(PatientEntity patients);

    @Query("DELETE FROM patients")
    void deleteAll();
}
