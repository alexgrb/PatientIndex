package ch.hevs.alexpira.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import ch.hevs.alexpira.database.entity.BedEntity;
import ch.hevs.alexpira.database.entity.PatientEntity;

public interface BedDao {

    @Insert
    void insert(BedEntity bed);

    @Update
    void update(BedEntity bed);

    @Delete
    void delete(BedEntity bed);

    @Query("DELETE FROM beds")
    void deleteAllNotes();

    @Query("SELECT * FROM beds ORDER BY bedNumber DESC")
    LiveData<List<BedEntity>> getAll();

    @Query("SELECT *,`rowid` FROM beds WHERE bedid = :bedid")
    LiveData<BedEntity> getById(int bedid);
}
