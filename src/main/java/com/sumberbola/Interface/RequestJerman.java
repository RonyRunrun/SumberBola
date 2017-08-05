package com.sumberbola.Interface;

import com.sumberbola.service.Coba;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Rony R on 3/13/2017.
 */

public interface RequestJerman {
    @GET("v2/posts?fields=id,slug,tags,categories,title.rendered,content.rendered,better_featured_image.media_details,date&categories=86&per_page=60")
    Call<List<Coba>> getPostDet();
}
