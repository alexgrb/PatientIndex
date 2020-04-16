package ch.hevs.alexpira.database.entity;

import androidx.room.Entity;
import androidx.room.Ignore;
import com.google.firebase.firestore.Exclude;

import java.util.HashMap;
import java.util.Map;

public class PatientEntity {

    private int rowid;

    private String patientFirstName;

    private String patientLastName;

    private String patientAdress;

    private String patientDate;

    private String patientcity;

    private String patientNPA;

    private int bedId;

    public void setPatientAdress(String patientAdress) {
        this.patientAdress = patientAdress;
    }

    public void setPatientDate(String patientDate) {
        this.patientDate = patientDate;
    }

    public void setPatientcity(String patientcity) {
        this.patientcity = patientcity;
    }

    public void setPatientNPA(String patientNPA) {
        this.patientNPA = patientNPA;
    }

    public int getBedId() {
        return bedId;
    }

    public void setBedId(int bedId) {
        this.bedId = bedId;
    }


    //CONSTRUCTORS
    public PatientEntity(String patientFirstName, String patientLastName, int bedId) {
        this.patientFirstName = patientFirstName;
        this.patientLastName = patientLastName;
        this.bedId = bedId;
    }
    @Ignore
    public PatientEntity(String patientFirstName, String patientLastName) {
        this.patientFirstName = patientFirstName;
        this.patientLastName = patientLastName;
    }
    @Ignore
    public PatientEntity(String patientFirstName, String patientLastName, String patientAdress, String patientDate, String patientcity, String patientNPA, int bedId) {
        this.patientFirstName = patientFirstName;
        this.patientLastName = patientLastName;
        this.patientAdress = patientAdress;
        this.patientDate = patientDate;
        this.patientcity = patientcity;
        this.patientNPA = patientNPA;
        this.bedId = bedId;
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

    @Exclude
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

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("patientFirstName", patientFirstName);
        result.put("patientLastName", patientLastName);
        result.put("patientAdress", patientAdress);
        result.put("patientDate", patientDate);
        result.put("patientcity", patientcity);
        result.put("patientNPA", patientNPA);
        result.put("bedId", bedId);
        return result;
    }

}
