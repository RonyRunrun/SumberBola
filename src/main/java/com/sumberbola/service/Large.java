package com.sumberbola.service;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ronyrahmawan on 3/31/17.
 */

public class Large {
    @SerializedName("source_url")
    @Expose
    private String sourceUrl;

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

}
