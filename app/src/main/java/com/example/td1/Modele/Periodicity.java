package com.example.td1.Modele;

import com.example.td1.Activity.SaisieActvity;
import com.example.td1.R;

import java.io.Serializable;

public enum Periodicity implements Serializable {
    Quotidien(0, R.string.p_quo),
    Hebdomadaire(1, R.string.p_heb),
    Bimensuel(2, R.string.p_bi),
    Mensuel(3, R.string.p_men),
    Trimestriel(4, R.string.p_tri);

    private int idPerid;
    private int periode;

    Periodicity(int idPerid, int periode) {
        this.idPerid = idPerid;
        this.periode = periode;
    }

    public int getIdPerid() {
        return idPerid;
    }

    @Override
    public String toString() {
        return SaisieActvity.getContext().getString(periode);
    }
}
