package com.example.farm;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ServiceApi {
    @POST("/user/login")
    Call<LoginResponse> userLogin(@Body LoginData data);

    @POST("/user/join")
    Call<JoinResponse> userJoin(@Body JoinData data);

    @GET("/user/test/{id}")
    Call<UserList> getUserInfo(@Path("id") String id); //뒤에 String id 를 지우면 빨간줄 뜨고 쓰면 제대로 됨



}
