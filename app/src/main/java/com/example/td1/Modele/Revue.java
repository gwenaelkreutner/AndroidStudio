package com.example.td1.Modele;

import java.io.Serializable;

public class Revue implements Serializable {
    private String reference;
    private String title;
    private String description;
    private float fee;
    private Periodicity periodicity;

    public Revue() {

    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getFee() {
        return fee;
    }

    public void setFee(float fee) {
        this.fee = fee;
    }

    public Periodicity getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(Periodicity periodicity) {
        this.periodicity = periodicity;
    }

    public Revue(String reference, String title, String description, float fee, Periodicity periodicity) {
        this.reference = reference;
        this.title = title;
        this.description = description;
        this.fee = fee;
        this.periodicity = periodicity;
    }

    @Override
    public String toString() {
        return title + " (" +  fee +" euros)";
    }
}
