package com.sumberbola.Interface;

import com.sumberbola.service.Coba;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Rony R on 3/21/2017.
 */

public interface RequestPrancis {
    @GET("v2/posts?fields=id,slug,tags,categories,title.rendered,better_featured_image.media_details,date,content.rendered,link&categories=2107&per_page=70")
    Call<List<Coba>> getPostDet();
}
