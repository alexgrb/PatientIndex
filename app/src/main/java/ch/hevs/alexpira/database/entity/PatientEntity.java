package ch.hevs.alexpira.database.entity;

import com.google.firebase.firestore.Exclude;

import java.util.HashMap;
import java.util.Map;

public class PatientEntity {

    private String patientFirstName;

    private String patientLastName;

    private String patientAdress;

    private String patientDate;

    private String patientcity;

    private String patientNPA;

    private String bedId;

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

    public String getBedId() {
        return bedId;
    }

    public void setBedId(String bedId) {
        this.bedId = bedId;
    }


    //CONSTRUCTORS
    public PatientEntity() {}

    public PatientEntity(String patientFirstName, String patientLastName, String bedId) {
        this.patientFirstName = patientFirstName;
        this.patientLastName = patientLastName;
        this.bedId = bedId;
    }

    public PatientEntity(String patientFirstName, String patientLastName) {
        this.patientFirstName = patientFirstName;
        this.patientLastName = patientLastName;
    }

    public PatientEntity(String patientFirstName, String patientLastName, String patientAdress, String patientDate, String patientcity, String patientNPA, String bedId) {
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
