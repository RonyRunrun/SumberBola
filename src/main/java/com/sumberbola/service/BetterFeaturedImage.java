package com.sumberbola.service;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Rony R on 3/12/2017.
 */

public class BetterFeaturedImage {

    @SerializedName("media_details")
    @Expose
    private MediaDetails mediaDetails;

    public MediaDetails getMediaDetails() {
        return mediaDetails;
    }

    public void setMediaDetails(MediaDetails mediaDetails) {
        this.mediaDetails = mediaDetails;
    }

}