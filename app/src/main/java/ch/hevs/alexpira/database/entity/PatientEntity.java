package ch.hevs.alexpira.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "patients", indices = {@Index(value = {"patientFirstName"}, unique = true)})
public class PatientEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "patientFirstName")
    private String patientFirstName;

    @ColumnInfo(name = "patientLastName")
    private String patientLastName;

    public PatientEntity(String patientFirstName, String patientLastName){
        this.patientFirstName = patientFirstName;
        this.patientLastName = patientLastName;
    }

    public int getId(){
        return id;
    }

    public void setId(int id) {
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
