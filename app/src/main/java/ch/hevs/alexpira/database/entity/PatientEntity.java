package ch.hevs.alexpira.database.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "patients", primaryKeys = {"id"})
public class PatientEntity {

    @NonNull
    private String id;

    @ColumnInfo(name = "patientFirstName")
    private String patientFirstName;

    @ColumnInfo(name = "patientLastName")
    private String patientLastName;

    public PatientEntity(String id, String patientFirstName, String patientLastName){
        this.id = id;
        this.patientFirstName = patientFirstName;
        this.patientLastName = patientLastName;
    }

    public String getId(){
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPatientFirstName() {
        return patientFirstName;
    }

    public void setPatientFirstName(String patientFirstName) {
        this.patientFirstName = patientFirstName;
    }

    public String getPatientLastName() {
        return patientLastName;
    }

    public void setPatientLastName(String patientLastName) {
        this.patientLastName = patientLastName;
    }
}
