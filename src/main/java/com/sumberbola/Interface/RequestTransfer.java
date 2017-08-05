package com.sumberbola.Interface;

import com.sumberbola.service.Coba;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by ronyrahmawan on 7/4/17.
 */

public interface RequestTransfer {
    @GET("v2/posts?fields=id,slug,categories,tags,title.rendered,better_featured_image.media_details,date,content.rendered,link&categories=1271&per_page=60")
    Call<List<Coba>> getPostDet();
}
