package com.sumberbola.service;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Rony R on 3/12/2017.
 */

public class Sizes {

    @SerializedName("medium")
    @Expose
    private Medium medium;
    @SerializedName("large")
    @Expose
    private Large large;
    @SerializedName("td_534x462")
    @Expose
    private Td534x462 td534x462;
    @SerializedName("td_356x364")
    @Expose
    private Td356x364 td356x364;
    public Medium getMedium() {
        return medium;
    }

    public void setMedium(Medium medium) {
        this.medium = medium;
    }

    public Large getLarge() {
        return large;
    }

    public void setLarge(Large large) {this.large = large;}

    public Td534x462 getTd534x462() {return td534x462;}

    public void setTd534x462(Td534x462 td534x462) {this.td534x462 = td534x462;}

    public Td356x364 getTd356x364() {return td356x364;}

    public void setTd356x364(Td356x364 td356x364) {this.td356x364 = td356x364;}
}
