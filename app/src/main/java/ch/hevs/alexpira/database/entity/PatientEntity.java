package ch.hevs.alexpira.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Fts4;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

//@Fts4 //This is added so we can support search in the table
@Entity(tableName = "patients")
public class PatientEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "rowid")
    private int rowid;

    @ColumnInfo(name = "patientFirstName")
    private String patientFirstName;

    @ColumnInfo(name = "patientLastName")
    private String patientLastName;

    @ColumnInfo(name = "patientadress")
    private String patientAdress;

    @ColumnInfo(name = "patientBirthdate")
    private String patientDate;

    @ColumnInfo(name = "patientcity")
    private String patientcity;

    @ColumnInfo(name = "patientNPA")
    private String patientNPA;

    @Ignore
    public PatientEntity(int rowid, String patientFirstName, String patientLastName) {
        this.rowid = rowid;
        this.patientFirstName = patientFirstName;
        this.patientLastName = patientLastName;
    }
    @Ignore
    public PatientEntity(String patientFirstName, String patientLastName) {
        this.patientFirstName = patientFirstName;
        this.patientLastName = patientLastName;
    }

    public PatientEntity(String patientFirstName, String patientLastName, String patientAdress, String patientDate, String patientcity, String patientNPA) {
        this.patientFirstName = patientFirstName;
        this.patientLastName = patientLastName;
        this.patientAdress = patientAdress;
        this.patientDate = patientDate;
        this.patientcity = patientcity;
        this.patientNPA = patientNPA;
    }

    public String getPatientAdress() {
        return patientAdress;
    }


    public String getPatientcity() {
        return patientcity;
    }

    public String getPatientDate() {
        return patientDate;
    }

    public String getPatientNPA() {
        return patientNPA;
    }

    public int getRowid() {
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
