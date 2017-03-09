package com.oktagon.sumberbola.sumberbola.Interface;

import com.oktagon.sumberbola.sumberbola.service.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Rony R on 3/7/2017.
 */

public interface RequestSumbol {
    @GET("v2/posts?categories=3056")
    Call<List<Post>> getPostDet();
}
