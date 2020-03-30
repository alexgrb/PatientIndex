package ch.hevs.alexpira.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Fts4;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

//@Fts4 //This is added so we can support search in the table
@Entity(tableName = "patients")
public class PatientEntity {

    @PrimaryKey (autoGenerate = true)
    @ColumnInfo(name="rowid")
    private int rowid;

    @ColumnInfo(name = "patientFirstName")
    private String patientFirstName;

    @ColumnInfo(name = "patientLastName")
    private String patientLastName;

@Ignore
    public PatientEntity(int rowid, String patientFirstName, String patientLastName){
        this.rowid = rowid;
        this.patientFirstName = patientFirstName;
        this.patientLastName = patientLastName;
    }

    public PatientEntity(String patientFirstName, String patientLastName){
        this.patientFirstName = patientFirstName;
        this.patientLastName = patientLastName;
    }


    public int getRowid(){
        return rowid;
    }

    public void setRowid(int rowid) {
        this.rowid = rowid;
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

    public void setId(int rowid) {
        this.rowid = rowid;
    }
}
