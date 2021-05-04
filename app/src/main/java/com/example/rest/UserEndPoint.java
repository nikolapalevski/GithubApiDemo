package com.example.rest;

import com.example.model.AccessToken;
import com.example.model.Commit;
import com.example.model.Repo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserEndPoint {

//    @GET("/repos/{user}/CoronaTrackingApp/commits")
//    Call<List<Commit>> getDataForUser(@Path("user") String user);

    //public user
    @GET("/repos/{user}/CoronaTrackingApp/commits")
    Call<List<Commit>> getDataForUser(@Path("user") String user);

    //public user
    @GET("/users/{user}/repos")
    Call<List<Repo>> getUserRepos(@Path("user") String user);

    //private user

    @Headers("Accept: application/json")
    @POST("login/oauth/access_token")
    @FormUrlEncoded
    Call<AccessToken> getAccessToken(
            @Field("client_id") String clientId,
            @Field("client_secret") String clientSecret,
            @Field("code") String code
    );


}
