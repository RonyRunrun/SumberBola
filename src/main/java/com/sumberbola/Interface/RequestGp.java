package com.sumberbola.Interface;

import com.sumberbola.service.Coba;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by ronyrahmawan on 4/5/17.
 */

public interface RequestGp {
    @GET("v2/posts?fields=id,slug,tags,categories,title.rendered,content.rendered,better_featured_image.media_details,date,content.rendered,link&categories=5701&per_page=50")
    Call<List<Coba>> getPostDet();
}
