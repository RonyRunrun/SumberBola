package com.sumberbola.Interface;

/**
 * Created by RR_PC on 2/19/2017.
 */

import com.sumberbola.service.Coba;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RequestInterface {

    @GET("v2/posts?fields=id,slug,categories,tags,title.rendered,content.rendered,better_featured_image.media_details,date&exclude_categories=5315&per_page=45")
    Call<List<Coba>> getPostDet();
}
