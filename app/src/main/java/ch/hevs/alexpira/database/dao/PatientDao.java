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

    @Query("SELECT * FROM patients")
    LiveData<List<PatientEntity>> getAll();

    @Query("SELECT * FROM patients WHERE rowid = :rowid")
    LiveData<PatientEntity> getById(int rowid);

    @Insert
    long insert(PatientEntity patient) throws SQLiteConstraintException;

    @Update
    void update(PatientEntity patients);

    @Delete
    void delete(PatientEntity patients);

    @Query("DELETE FROM patients")
    void deleteAll();
}
