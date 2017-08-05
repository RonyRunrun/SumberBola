package com.sumberbola.service;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Rony R on 3/12/2017.
 */

public class MediaDetails {

    @SerializedName("sizes")
    @Expose
    private Sizes sizes;

    public Sizes getSizes() {
        return sizes;
    }

    public void setSizes(Sizes sizes) {
        this.sizes = sizes;
    }

}