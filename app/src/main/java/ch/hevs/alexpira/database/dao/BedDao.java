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

import ch.hevs.alexpira.database.entity.BedEntity;
import ch.hevs.alexpira.database.entity.PatientEntity;
import ch.hevs.alexpira.database.pojo.PatientWithBed;

@Dao
public interface BedDao {


    @Insert
    long insert(BedEntity bed) throws SQLiteConstraintException;

    @Update
    void update(BedEntity bed);

    @Delete
    void delete(BedEntity bed);

    @Query("DELETE FROM beds")
    void deleteAllBeds();

    @Query("SELECT * FROM beds ORDER BY bedNumber DESC")
    LiveData<List<BedEntity>> getAll();

    @Query("SELECT *,`rowid` FROM beds WHERE rowid = :bedid")
    LiveData<BedEntity> getById(int bedid);

    @Transaction
    @Query("SELECT * FROM beds")
    LiveData<List<PatientWithBed>> getAllPatientsWithBed();

}
