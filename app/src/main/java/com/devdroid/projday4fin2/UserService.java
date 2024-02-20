package com.devdroid.projday4fin2;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UserService {
    @POST("/posts")
    Call<UserResponse> saveUser(@Body UserRequest userRequest);
}