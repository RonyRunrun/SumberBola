package com.sumberbola.Interface;

import com.sumberbola.service.Coba;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by ronyrahmawan on 4/5/17.
 */

public interface RequestRacing {
    @GET("v2/posts?fields=id,slug,tags,categories,title.rendered,better_featured_image.media_details,date,content.rendered,link&categories=4214,4213&per_page=70")
    Call<List<Coba>> getPostDet();
}
