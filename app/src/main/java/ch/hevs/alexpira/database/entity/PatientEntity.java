package ch.hevs.alexpira.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Fts4;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Date;

//ENTITIY
@Entity(tableName = "patients",
        foreignKeys =
        @ForeignKey(
                entity = BedEntity.class,
                parentColumns = "rowid",
                childColumns = "bedId",
                onDelete = ForeignKey.CASCADE),
        indices = {
                @Index(
                        value = {"bedId"}
                )
        }
)
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

    @ColumnInfo(name = "bedId")
    private int bedId;

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
