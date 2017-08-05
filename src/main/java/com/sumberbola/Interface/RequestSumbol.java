package com.sumberbola.Interface;

import com.sumberbola.service.Coba;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Rony R on 3/7/2017.
 */

public interface RequestSumbol {
    @GET("v2/posts?fields=id,slug,categories,title.rendered,better_featured_image.media_details,date,content.rendered,link,tags&categories=3056&per_page=70")
    Call<List<Coba>> getPostDet();
}
