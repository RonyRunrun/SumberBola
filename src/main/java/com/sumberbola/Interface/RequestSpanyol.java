package com.sumberbola.Interface;

import com.sumberbola.service.Coba;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Rony R on 3/20/2017.
 */

public interface RequestSpanyol {
    @GET("v2/posts?fields=id,slug,categories,tags,title.rendered,better_featured_image.media_details,date,content.rendered,link&categories=93&per_page=60")
    Call<List<Coba>> getPostDet();
}