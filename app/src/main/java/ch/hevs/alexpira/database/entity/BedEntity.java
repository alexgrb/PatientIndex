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

    @ColumnInfo(name="bedSize")
    private String bedSize;

    @ColumnInfo(name="bedAdjustable")
    private String bedAdjustablee;


    @Ignore
    public BedEntity(int bedNumber, String bedSize, String bedAdjustablee ){
        this.bedNumber=bedNumber;
        this.bedSize = bedSize;
        this.bedAdjustablee = bedAdjustablee;
    }

    public String getBedSize() {
        return bedSize;
    }

    public void setBedSize(String bedSize) {
        this.bedSize = bedSize;
    }

    public String getBedAdjustablee() {
        return bedAdjustablee;
    }

    public void setBedAdjustablee(String bedAdjustablee) {
        this.bedAdjustablee = bedAdjustablee;
    }

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

    public BedEntity(int bedNumber){
        this.bedNumber=bedNumber;
    }


}

