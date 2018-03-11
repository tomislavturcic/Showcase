package com.example.tt.showcase.data.network;

import com.example.tt.showcase.data.network.models.UserDTO;
import com.example.tt.showcase.data.network.models.UserDetailsDTO;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by TT on 8.3.2018..
 * Showcase
 */

public interface ApiService {

    @GET("/users")
    Single<Response<List<UserDTO>>> getUsers(
            @Query("per_page") int pageSize,
            @Query("since") int since);

    @GET("/users/{login}")
    Single<UserDetailsDTO> getSingleUser(@Path("login") String login);
}
