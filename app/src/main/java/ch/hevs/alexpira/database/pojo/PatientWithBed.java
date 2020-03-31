package ch.hevs.alexpira.database.pojo;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

import ch.hevs.alexpira.database.entity.BedEntity;
import ch.hevs.alexpira.database.entity.PatientEntity;

public class PatientWithBed {
    @Embedded
    public BedEntity bedEntity;
    @Relation(
            parentColumn = "rowid",
            entityColumn = "bedId",
            entity = PatientEntity.class
    )
    public PatientEntity patientEntity;
}