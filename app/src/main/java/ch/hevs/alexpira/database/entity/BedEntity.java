package ch.hevs.alexpira.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "beds")
public class BedEntity {

    @PrimaryKey (autoGenerate = true)
    @ColumnInfo(name="rowid")
    private int id;

    @ColumnInfo(name="bedNumber")
    private int bedNumber;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBedNumber() {
        return bedNumber;
    }

    public void setBedNumber(int bedNumber) {
        this.bedNumber = bedNumber;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    @ColumnInfo(name="patientId")
    private int patientId;

    public BedEntity(int bedNumber){
        this.bedNumber=bedNumber;
    }

    @Ignore
    public BedEntity(int bedNumber, int patientId ){
        this.bedNumber=bedNumber;
        this.patientId = patientId;
    }
}

