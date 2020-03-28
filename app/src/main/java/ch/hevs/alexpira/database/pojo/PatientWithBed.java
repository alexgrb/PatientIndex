package ch.hevs.alexpira.database.pojo;

import androidx.room.Embedded;
import androidx.room.Relation;

import ch.hevs.alexpira.database.entity.BedEntity;
import ch.hevs.alexpira.database.entity.PatientEntity;

public class PatientWithBed {
    @Embedded public PatientEntity patientEntity;
    @Relation(
            parentColumn = "rowid",
            entityColumn = "patientId",
            entity = BedEntity.class
    )
    public BedEntity bedEntity;
}
