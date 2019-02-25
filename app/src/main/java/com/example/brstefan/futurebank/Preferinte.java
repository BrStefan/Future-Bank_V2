package com.example.brstefan.futurebank;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "preferences_table")
public class Preferinte {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String categorie;
    private String activitate;

    public Preferinte(String categorie, String activitate) {
        this.categorie = categorie;
        this.activitate = activitate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getCategorie() {
        return categorie;
    }

    public String getActivitate() {
        return activitate;
    }
}
