package com.sumberbola.Interface;

import com.sumberbola.service.Student;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Rony R on 3/10/2017.
 */

public interface RequestStudent {
    @GET("api/RetrofitAndroidArrayResponse")
    Call<List<Student>> getStudentDetails();

}
