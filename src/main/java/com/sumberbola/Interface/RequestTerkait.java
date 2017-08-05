package com.sumberbola.Interface;

import com.sumberbola.service.Coba;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Rony R on 3/16/2017.
 */

public interface RequestTerkait {
    @GET("v2/posts?fields=id,slug,categories,title.rendered,better_featured_image.media_details,date,content.rendered,link,tags&per_page=5")
    Call<List<Coba>> getPostDet();
}
