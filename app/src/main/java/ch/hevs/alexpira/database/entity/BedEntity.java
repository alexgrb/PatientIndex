package ch.hevs.alexpira.database.entity;

import androidx.room.Ignore;
import com.google.firebase.firestore.Exclude;

import java.util.HashMap;
import java.util.Map;

public class BedEntity {

    private int id;
    private int bedNumber;
    private String bedSize;
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

    @Exclude
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

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("bedNumber", bedNumber);
        result.put("bedAdjustablee", bedAdjustablee);
        result.put("bedSize", bedSize);
        return result;
    }

}

